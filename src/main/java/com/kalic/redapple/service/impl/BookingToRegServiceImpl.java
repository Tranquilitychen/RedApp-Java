package com.kalic.redapple.service.impl;

import com.alibaba.fastjson.JSON;
import com.kalic.redapple.mapper.*;
import com.kalic.redapple.pojo.*;
import com.kalic.redapple.service.BookingToRegService;
import com.kalic.redapple.utils.Buildno;
import com.kalic.redapple.utils.ResultDto;
import com.kalic.redapple.vo.PageCancelBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Kalic Li
 * @ClassName BookingToRegServiceImpl
 * @Package com.kalic.redapple.service.impl
 * @Description TODO
 * @date 2020/2/17 13:53
 */
@Transactional(rollbackFor = {Exception.class})
@Service
public class BookingToRegServiceImpl implements BookingToRegService {
    @Autowired
    private RegMapper regMapper;
    @Autowired
    private GuestMapper guestMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private BookingMapper bookingMapper;
    @Autowired
    private RegBillMapper regBillMapper;
    @Autowired
    private PayDetailedMapper payDetailedMapper;
    @Autowired
    private RoomLogMapper roomLogMapper;
    /**
     * 预定转入住订单需要的操作
     * 1. 客人信息录入
     * 2. 订单填写完成
     * 3. 房间状态变更
     * 4. 订单消费 录入 房租（消费项目）
     * 5. 录入 账单明细 表
     * 6. 房间日志表
     * 以上哪一条出现错误都不算成功，所以需要事务回滚
     * @param jsonReg
     * @param linkRoomno
     * @return
     */
    @Override
    public ResultDto<Integer> insRegForBooking(String jsonReg, String linkRoomno,double dividedSecurity) {
        if (jsonReg == "" || jsonReg.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        List<Reg> regs = JSON.parseArray(jsonReg, Reg.class);
        for (Reg reg:regs) {
            System.out.println("预定转入住：" + reg);
        }
        // 订单信息
        List<Reg> insRegs = new ArrayList<>();
        // 账单信息
        List<RegBill> indRegBills = new ArrayList<>();
        // 账单明细信息
        List<PayDetailed> payDetaileds = new ArrayList<>();
        int index = 0;
        // 事务回滚
        try {
            // 1. 插入 新房客
            // 取出其中一个来进行插入操作
            Reg otherReg = regs.get(0);
            System.out.println("======= 模板账单 =========");
            System.out.println(otherReg);
            Guest guest = insGuest(otherReg);
            index += guestMapper.insGuestInfo(guest);
            long guestno = guest.getGuestno();
            System.out.println("======== 数据库返回插入客人信息的id =========");
            System.out.println(guestno);
            // 1.1 查询出当日的最大账单号
            // 1.1.1 获取最大的Regno
            String linkno = Buildno.buildRegnoPref(otherReg.getIsgroup());
            System.out.println("linkNo;" + linkno);
            String maxRegnoToday = regMapper.selMaxLikeRegno(linkno);
            System.out.println("当日最大的订单编号：" + maxRegnoToday);
            int maxno= 0;
            if (maxRegnoToday != null) {
                maxno = Integer.parseInt(maxRegnoToday.substring(9));
            }
            System.out.println("最大号：" + maxno);
            System.out.println("=======================================");
            int maxLinkid = 0;
            int maxGroupid = 0;
            if (otherReg.getIsgroup().equals("0")){
                maxLinkid = regMapper.selMaxLinkidReg();
                System.out.println("maxLinkid:" + maxLinkid);
                maxLinkid++;
                System.out.println("新的联房号：" + maxLinkid);
            }else{
                maxGroupid = regMapper.selMaxGroupidReg();
                System.out.println("maxGroupid:" + maxGroupid);
                maxGroupid++;
                System.out.println("新的团体房号：" + maxGroupid);
            }
            System.out.println("=======================================");
            Timestamp nowDate = new Timestamp(new Date().getTime());
            System.out.println(nowDate);
            for (Reg reg : regs){
                Reg buildReg;
                // 如果 linkRoomno 为 null 则 没有联房，联房号为账单号，联房id 为 0
                if (reg.getIsgroup().equals("0")) {
                    // 说明是一个房间(前台已经判断过了)
                    if (regs.size() == 0) {
                        linkRoomno = "";
                    }
                    maxno++;
                    buildReg = guestRegBuilder(reg, linkRoomno, guestno, maxno, maxLinkid);
                }else{
                    // 如果 linkRoomno 不能为 null，团体房号为账单号，联房id 为 1
                    maxno++;
                    buildReg = teamRegBuilder(reg, linkRoomno, guestno, maxno, maxGroupid);
                }
                System.out.println("======== buildReg ==========");
                System.out.println(buildReg);
                // 2. 修改房间状态 "0" 表示入住
                index += roomMapper.updateRoomStatus("1", buildReg.getRegno(), buildReg.getRoomno());
                // 3. 完成订单信息，取消预定信息
                index += bookingMapper.updBookingToReg(reg.getBookingno(), nowDate,
                        "客人已入住", "Y");
                // 4. 完成订单信息，合并或者返回 预定时支付的押金
                // 合并 dividedSecurity 这个是合并时 分散开的押金信息
                if (dividedSecurity > 0){
                    PayDetailed buildPayDetailed = buildPayDetailed(buildReg, dividedSecurity);
                    payDetaileds.add(buildPayDetailed);
                    buildReg.setPaywayno(buildPayDetailed.getPayno());
                }
                // 4. 插入房间账单
                // 添加了房租的 dtIndate, dtOutdate
                RegBill buildRegBill = insRegBill(buildReg);
                System.out.println("客房入住的房租账单：" + buildRegBill);
                indRegBills.add(buildRegBill);
                insRegs.add(buildReg);
            }
            System.out.println("======== 插入账单 ==========");
            System.out.println(insRegs);
            // 4. 插入账单信息 List
            index += regMapper.insRegInfo(insRegs);
            // 5. 插入 账单详情 表 （消费表）
            index += regBillMapper.insRegBillFormReg(indRegBills);
            // 6. 插入 收支明细表
            if (payDetaileds.size() != 0)   {
                System.out.println(payDetaileds);
                index += payDetailedMapper.insBatchPayDetaileds(payDetaileds);
            }
            System.out.println(index);
            System.out.println("==========================");
            System.out.println(insRegs.get(0).getDtIndate());
        }catch(Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDto<>(400, "账单插入失败");
        }
        System.out.println(index);
        if (index == 0){
            return new ResultDto<>(400, "账单插入，出现未知错误");
        }else{
            return new ResultDto<>(200, "账单插入成功");
        }
    }

    /**
     *  顾客 取消预定信息
     *  1. Booking 设置   取消信息
     *  2. Room 房间的 Booking 设置为 null 并根据情况修改状态
     *  3. 取消预定退押金
     *  4. 日志
     * @param jsonBooking 还需要进一步封装啊
     * @return 返还应该是一个对象 {退还人： “”， 退还金额： “”}
     */
    @Override
    public ResultDto<Double> updGuestCancelBookings(String jsonBooking, String cancelMeno) {
        if (jsonBooking == "" || jsonBooking.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        List<PageCancelBooking> bookings = JSON.parseArray(jsonBooking, PageCancelBooking.class);
        Timestamp now = new Timestamp(new Date().getTime());
        System.out.println("顾客信息：" + bookings);
        System.out.println("取消备注：" + cancelMeno);
        double backMoney = 0.0;
        int index = 0;
        List<PayDetailed> payDetaileds = new ArrayList<>();
        try{
            for (PageCancelBooking booking: bookings){
                System.out.println("预定的信息：" + booking);
                // 1. 修改预定信息
                index += bookingMapper.updCancelBooking(booking.getBookingno(), now, cancelMeno);
                // 2. 修改房间的预定信息为 null.
                // 修改时还需要判断房间状态，如果房间状态为 2 预定状态就修改，如果不是 2 就不修改
                if (booking.getRstatus() == 2){
                    // 预定状态 房间为紫颜色
                    index += roomMapper.updCalcelBookingRoom("", "0", booking.getRoomno());
                }else {
                    // 如果不是 2 紫色 就只删除预定信息
                    index += roomMapper.updateBookingBookingno("", booking.getRoomno());
                }
                // 3. 退还押金
                if (booking.getSecurityReal() > 0){
                    PayDetailed payDetailed = backPayDetailed(booking, now);
                    payDetaileds.add(payDetailed);
                }
                backMoney += booking.getSecurityReal();
            }
            if (payDetaileds.size() != 0){
                index += payDetailedMapper.insBatchPayDetaileds(payDetaileds);
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDto<>(400, "预定取消失败");
        }
        if (index == 0){
            return new ResultDto<>(400, "预定取消，出现未知错误");
        }else{
            return new ResultDto<>(200, "预定取消成功，请退还客人押金", backMoney);
        }
    }

    /**
     *  团体 取消预定信息。取消预定信息之后，预定的押金应该怎么退。
     * @param jsonBooking
     * @return
     */
    @Override
    public ResultDto<Double> updTeamCancelBookings(String jsonBooking, String cancelMeno) {
        if (jsonBooking == "" || jsonBooking.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        List<PageCancelBooking> bookings = JSON.parseArray(jsonBooking, PageCancelBooking.class);
        Timestamp now = new Timestamp(new Date().getTime());
        System.out.println("团体房信息：" + bookings);
        System.out.println("取消备注：" + cancelMeno);
        double backMoney = 0.0;
        int index = 0;
        List<PayDetailed> payDetaileds = new ArrayList<>();
        try{
            for (PageCancelBooking booking: bookings){
                System.out.println("预定的信息：" + booking);
                // 1. 修改预定信息
                index += bookingMapper.updCancelBooking(booking.getBookingno(), now, cancelMeno);
                // 2. 修改房间的预定信息为 null.
                // 修改时还需要判断房间状态，如果房间状态为 2 预定状态就修改，如果不是 2 就步修改
                if (booking.getRstatus() == 2){
                    index += roomMapper.updCalcelBookingRoom("", "0", booking.getRoomno());
                }else {
                    // 如果不是 2 紫色 就只删除预定信息
                    index += roomMapper.updateBookingBookingno("", booking.getRoomno());
                }
                // 3. 退还押金
                if (booking.getSecurityReal() > 0){
                    PayDetailed payDetailed = backPayDetailed(booking, now);
                    payDetaileds.add(payDetailed);
                }
                backMoney += booking.getSecurityReal();
            }
            if (payDetaileds.size() != 0){
                index += payDetailedMapper.insBatchPayDetaileds(payDetaileds);
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDto<>(400, "预定取消失败");
        }
        if (index == 0){
            return new ResultDto<>(400, "预定取消，出现未知错误");
        }else{
            return new ResultDto<>(200, "预定取消成功，请退还客人押金", backMoney);
        }
    }

    /**
     *  顾客账单初始化，填充相关信息
     * @param reg 未初始化的账单信息
     * @return
     */
    private Reg guestRegBuilder(Reg reg, String linkRoomno, long guestno, int maxno, int maxLinkid){
        String regno = Buildno.buildRegnoPref(reg.getIsgroup()) + Buildno.zeroRegno(maxno);
        reg.setRegno(regno);
        // 判断是单个房间 还是 需要联房
        if (linkRoomno == ""){
            reg.setLinkno("");
            reg.setLinkid(0);
        }else{
            // 需要联房
            reg.setLinkno(linkRoomno);
            // 获取最大的联房号
            reg.setLinkid(maxLinkid);
        }
        reg.setGuestno(guestno);
        reg.setSureflag("Y");
        reg.setNullify("Y");
        return reg;
    }
    /**
     *  团队账单初始化，填充相关信息
     * @param reg 未初始化的账单信息
     * @param linkRoomno 使用团体房的房间号来判断主房间
     * @return
     */
    private Reg teamRegBuilder(Reg reg, String linkRoomno, long guestno, int maxno, int maxGroupid){
        String regno = Buildno.buildRegnoPref(reg.getIsgroup()) + Buildno.zeroRegno(maxno);
        reg.setRegno(regno);
        reg.setGroupid(maxGroupid);
        reg.setGuestno(guestno);
        reg.setGroupno(linkRoomno);
        reg.setSureflag("Y");
        reg.setNullify("Y");
        return reg;
    }

    /**
     * 完善 散客 信息
     * @param reg 账单信息
     * @return
     */
    private Guest insGuest(Reg reg){
        Guest guest = new Guest();
        if (reg.getIsgroup().equals("0")){
            guest.setGuestname(reg.getGuestname());
            guest.setIdtypeno(reg.getIdtypeno());
            guest.setIdno(reg.getIdno());
            guest.setSex(reg.getSex());
            guest.setDtBirthday(reg.getDtBirthday());
            guest.setCountryno(reg.getCountryno());
            guest.setAreano(reg.getAreano());
            guest.setAddress(reg.getAddress());
            guest.setTel(reg.getTel());
            guest.setMeno(reg.getMeno());
            guest.setGroupflag("N");
            return guest;
        }else{
            guest.setGuestname(reg.getGroupname());
            guest.setGroupflag("Y");
            guest.setTel(reg.getTel());
            return guest;
        }

    }

    /**
     * 插入账单信息
     * @param reg
     * @return
     */
    private RegBill insRegBill(Reg reg){
        double totalprice = reg.getPrice() * reg.getDays();
        RegBill regBill = new RegBill();
        regBill.setRoomno(reg.getRoomno());
        regBill.setRegno(reg.getRegno());
        regBill.setItemno("001");
        regBill.setItemname("房租");
        regBill.setNum(reg.getDays());
        regBill.setPrice(reg.getPrice());
        regBill.setDiscount(reg.getDiscount());
        regBill.setTotalprice(totalprice);
        regBill.setDtIndate(reg.getDtIndate());
        regBill.setDtOutdate(reg.getDtOutdate());
        regBill.setDtOper(reg.getDtIndate());
        regBill.setOperid(reg.getOperid());
        regBill.setNullify("Y");
        return regBill;
    }

    /**
     * 收取押金 账单明细 信息
     */
    private PayDetailed buildPayDetailed (Reg reg,double dividedSecurity){
        PayDetailed payDetailed = new PayDetailed();
        payDetailed.setPayno("P" + reg.getRegno());
        payDetailed.setRoomno(reg.getRoomno());
        payDetailed.setRegno(reg.getRegno());
        payDetailed.setBookingno(reg.getBookingno());
        payDetailed.setGuestno(reg.getGuestno());
        // 此处的押金 为 入住交的押金 或者 没有交押金而已
        payDetailed.setMoney(dividedSecurity);

        payDetailed.setIsReceipts("Y");
        payDetailed.setSourcetype(reg.getSecuritytype());
        payDetailed.setPaytype("002");
        payDetailed.setDtOper(reg.getDtIndate());
        payDetailed.setOperid(reg.getOperid());
        payDetailed.setMeno("入住交押金");
        return payDetailed;
    }

    /**
     *  退 押金 账单明细 信息
     */
    private PayDetailed backPayDetailed(PageCancelBooking booking, Timestamp now){
        PayDetailed payDetailed = new PayDetailed();
        payDetailed.setPayno("PS" + booking.getBookingno());
        payDetailed.setRoomno(booking.getRoomno());
        payDetailed.setBookingno(booking.getBookingno());
       /* payDetailed.setGuestno(booking.getGuestno());*/
        // 此处的押金 为 入住交的押金 或者 没有交押金而已
        payDetailed.setMoney(booking.getSecurityReal());
        payDetailed.setIsReceipts("N");
        // 暂时默认 退押金的来源为 收押金的来源 哇 保障
        /*payDetailed.setSourcetype("");*/
        payDetailed.setPaytype("003");
        payDetailed.setDtOper(now);
        // 暂时线空着，不一样的
        payDetailed.setOperid("");
        payDetailed.setMeno("取消预定，退还押金");
        return payDetailed;
    }
}
