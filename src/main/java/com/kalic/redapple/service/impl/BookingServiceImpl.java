package com.kalic.redapple.service.impl;

import com.alibaba.fastjson.JSON;
import com.kalic.redapple.mapper.*;
import com.kalic.redapple.pojo.*;
import com.kalic.redapple.service.BookingService;
import com.kalic.redapple.utils.Buildno;
import com.kalic.redapple.utils.ResultDto;
import com.kalic.redapple.vo.AllData;
import com.kalic.redapple.vo.PageBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.alibaba.fastjson.JSON.parseArray;

/**
 * @author Kalic Li
 * @ClassName BookingService
 * @Package com.kalic.redapple.service.impl
 * @Description TODO
 * @date 2020/2/14 14:41
 */
@Transactional(rollbackFor = {Exception.class})
@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingMapper bookingMapper;
    @Autowired
    private GuestMapper guestMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private RoomlbMapper roomlbMapper;
    @Autowired
    private PayDetailedMapper payDetailedMapper;
    @Autowired
    private RoomLogMapper roomLogMapper;

    /**
     * 获取所有 当前有效 的订单信息
     * @return
     */
    @Override
    public List<Booking> selValidBooking() {
        return bookingMapper.selValidBooking();
    }

    @Override
    public List<AllData> selQueryBooking() {
        List<Booking> bookings = bookingMapper.selValidBooking();
        List<Guest> guests = guestMapper.getAllGuest();
        List<Room> rooms = roomMapper.getAllRoom();
        List<Roomlb> roomlbs = roomlbMapper.getAllRoomlb();
        List<AllData> allDatas = new ArrayList<>();
        System.out.println(bookings);
        for (Booking booking :bookings){
            AllData allData = new AllData();
            for (Guest guest: guests){
                if (guest.getGuestno() == booking.getGuestno()){
                    allData.setGuest(guest);
                    break;
                }
            }
            for (Roomlb roomlb: roomlbs){
                if (roomlb.getRoomlbno().equals(booking.getRoomlbno())){
                    allData.setRoomlb(roomlb);
                    break;
                }
            }
            for (Room room: rooms){
                if (room.getRoomno().equals(booking.getRoomno())){
                    allData.setRoom(room);
                    break;
                }
            }
            allData.setBooking(booking);
            allDatas.add(allData);
        }
        System.out.println(allDatas);
        return allDatas;
    }

    /**
     * 插入预定信息
     * 1. 添加客人信息
     * 2. 添加定时任务，预定时间修改房间状态
     * 3. 添加预定信息
     * 4. 添加支付信息
     * 5. 添加日志信息
     * @param bookingJson
     * @return
     */
    @Override
    public ResultDto<Booking> insBookingInfo(String bookingJson, String linkRoomno) {
        //1. 解析Json
        if (bookingJson == "" || bookingJson.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        // 修改为预定信息
        System.out.println(bookingJson);
        List<PageBooking> pageBookings = JSON.parseArray(bookingJson, PageBooking.class);
        List<Booking> bookings = JSON.parseArray(bookingJson, Booking.class);
        List<PayDetailed> payDetaileds = new ArrayList<>();
        List<Booking> insBookings = new ArrayList<>();
        int index = 0;
        try {
            // 1. 插入 新房客
            // 取出其中一个来进行插入操作
            PageBooking pageBooking = pageBookings.get(0);
            Guest guest = insGuest(pageBooking);
            System.out.println(guest);
            index += guestMapper.insGuestInfo(guest);
            long guestno = guest.getGuestno();
            System.out.println("数据库返回的自增序列的编号：" + guestno);
            // 1.1 查询出当日的最大账单号
            // 1.1.1 获取 当前日期以及 预定 前缀
            String linkno = Buildno.buildBookingnoPref(pageBooking.getIsgroup());
            System.out.println("linkNo;" + linkno);
            String maxRegnoToday = bookingMapper.selMaxLikeBookingno(linkno);
            System.out.println("当日最大的订单编号：" + maxRegnoToday);
            int maxno= 0;
            if (maxRegnoToday != null) {
                maxno = Integer.parseInt(maxRegnoToday.substring(10));
            }
            System.out.println("最大号：" + maxno);
            System.out.println("==========================================");
            int maxLinkid = 0;
            if (pageBooking.getIsgroup().equals("0")){
                maxLinkid = bookingMapper.selMaxBookingLinkid();
                maxLinkid++;
                System.out.println("新的联房号：" + maxLinkid);
            }
            System.out.println("==========================================");
            for (Booking booking : bookings){
                Booking buildBooking;
                // 如果 linkRoomno 为 null 则 没有联房，联房号为账单号，联房id 为 0
                if (booking.getIsgroup().equals("0")) {
                    // 说明是一个房间(前台已经判断过了)
                    if (bookings.size() == 0) {
                        linkRoomno = "";
                    }
                    maxno++;
                    buildBooking = guestBookingBuilder(booking, linkRoomno, guestno, maxno, maxLinkid);
                }else{
                    // 如果 linkRoomno 不能为 null，团体房号为账单号，联房id 为 1
                    maxno++;
                    buildBooking = teamBookingBuilder(booking, linkRoomno, guestno, maxno);
                }
                System.out.println("======== buildReg ==========");
                System.out.println(buildBooking);
                // 3. 修改 房间表 预定信息，添加预定编号
                /*index += roomMapper.updateBookingBookingno(buildBooking.getBookingno(), buildBooking.getRoomno());*/
                // 4. 添加 账目表 支付信息 ()
                if (buildBooking.getSecurityReal() > 0){
                    PayDetailed buildPayDetailed = buildPayDetailed(buildBooking);
                    payDetaileds.add(buildPayDetailed);
                    buildBooking.setPaywayno(buildPayDetailed.getPayno());
                }
                insBookings.add(buildBooking);
            }
            System.out.println("======== 插入预定信息 ==========");
            System.out.println(insBookings);
            // 4. 插入预定信息 List
            index += bookingMapper.insBookingInfo(insBookings);
            // 5. 插入账目表信息
            System.out.println("payDetaileds:" + payDetaileds);
            if (payDetaileds.size() != 0){
                System.out.println(payDetaileds);
                index += payDetailedMapper.insBatchPayDetaileds(payDetaileds);
            }
            System.out.println(index);
            System.out.println("==========================");
            System.out.println(insBookings.get(0).getDtIndate());
            System.out.println("============ 下方可以编写 定时任务 =============");
        }catch(Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDto<>(403, "请求失败！");
        }
        return new ResultDto<>(200, "请求成功！");
    }

    /**
     *  顾客账单初始化，填充相关信息
     * @param booking 未初始化的账单信息
     * @return
     */
    private Booking guestBookingBuilder(Booking booking, String linkRoomno, long guestno, int maxno, int maxLinkid){
        String bookingno = Buildno.buildBookingnoPref(booking.getIsgroup()) + Buildno.zeroRegno(maxno);
        System.out.println("新的预定单号：" + bookingno);
        booking.setBookingno(bookingno);
        // 判断是单个房间 还是 需要联房
        if (linkRoomno == ""){
            booking.setLinkno("");
            booking.setLinkid(0);
        }else{
            // 需要联房
            booking.setLinkno(linkRoomno);
            booking.setLinkid(maxLinkid);
        }
        booking.setGuestno(guestno);
        booking.setSureflag("N");
        Timestamp now = new Timestamp(new Date().getTime());
        booking.setDtOper(now);
        return booking;
    }
    /**
     *  团队账单初始化，填充相关信息
     * @param booking 未初始化的预定信息
     * @param linkRoomno 使用团体房的房间号来判断主房间
     * @return
     */
    private Booking teamBookingBuilder(Booking booking, String linkRoomno, long guestno, int maxno){
        String bookingno = Buildno.buildBookingnoPref(booking.getIsgroup()) + Buildno.zeroRegno(maxno);
        System.out.println("新的预定单号：" + bookingno);
        booking.setBookingno(bookingno);
        booking.setGuestno(guestno);
        booking.setGroupno(linkRoomno);
        booking.setSureflag("N");
        Timestamp now = new Timestamp(new Date().getTime());
        booking.setDtOper(now);
        return booking;
    }


    /**
     * 完善 散客 信息
     * @param pageBooking 预定信息
     * @return
     */
    private Guest insGuest(PageBooking pageBooking) {
        Guest guest = new Guest();
        if (pageBooking.getIsgroup().equals("0")) {
            guest.setGuestname(pageBooking.getGuestname());
            guest.setIdtypeno(pageBooking.getIdtypeno());
            guest.setIdno(pageBooking.getIdno());
            guest.setSex(pageBooking.getSex());
            guest.setDtBirthday(pageBooking.getDtBirthday());
            guest.setCountryno(pageBooking.getCountryno());
            guest.setAreano(pageBooking.getAreano());
            guest.setAddress(pageBooking.getAddress());
            guest.setTel(pageBooking.getTel());
            guest.setMeno(pageBooking.getMeno());
            guest.setGroupflag("N");
            return guest;
        } else {
            guest.setGuestname(pageBooking.getGroupname());
            guest.setGroupflag("Y");
            guest.setTel(pageBooking.getTel());
            return guest;
        }
    }

    /**
     * 账单明细 信息
     */
    private PayDetailed buildPayDetailed (Booking booking){
        PayDetailed payDetailed = new PayDetailed();
        payDetailed.setPayno("P" + booking.getBookingno());
        payDetailed.setRoomno(booking.getRoomno());
        payDetailed.setBookingno(booking.getBookingno());
        payDetailed.setGuestno(booking.getGuestno());
        payDetailed.setMoney(booking.getSecurityReal());
        payDetailed.setIsReceipts("Y");
        payDetailed.setSourcetype(booking.getSecuritytype());
        payDetailed.setPaytype("001");
        payDetailed.setDtOper(booking.getDtOper());
        payDetailed.setOperid(booking.getOperid());
        payDetailed.setMeno("预定交押金");
        return payDetailed;
    }
}
