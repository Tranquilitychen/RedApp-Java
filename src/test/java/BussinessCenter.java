import com.kalic.redapple.mapper.*;
import com.kalic.redapple.pojo.*;
import com.kalic.redapple.vo.AllData;
import org.apache.ibatis.jdbc.Null;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kalic Li
 * @ClassName AllData
 * @Package PACKAGE_NAME
 * @Description TODO
 * @date 2020/2/14 20:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-dao.xml", "classpath:spring-service.xml",
        "classpath:spring-config-annotation.xml"})
public class BussinessCenter {
    @Autowired
    private FloorMapper floorMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private RoomlbMapper roomlbMapper;
    @Autowired
    private RegMapper regMapper;
    @Autowired
    private BookingMapper bookingMapper;
    @Autowired
    private GuestMapper guestMapper;
    @Autowired
    private PayDetailedMapper payDetailedMapper;
    @Autowired
    private RoomLogMapper roomLogMapper;
    @Test
    public void bussinessCenter123123131(){
        // 1. 获取所有的房间信息
        List<Room> roomList = roomMapper.getAllRoom();
        //2 . 获取所有的房间分类信息
        List<Roomlb> roomlbList = roomlbMapper.getAllRoomlb();
        // 3. 订单信息
        List<Reg> regList = regMapper.getAllReg();
        // 4. 所有的预定信息
        List<Booking> bookingList = bookingMapper.selAllBookingInfo();
        // 5. 所有的顾客信息
        List<Guest> guestList = guestMapper.getAllGuest();
        System.out.println("-------------3---------------");
        System.out.println("-------------2---------------");
        System.out.println("-------------1---------------");
        List<AllData> allDatas = new ArrayList<>();
        for (Room room: roomList) {
            // 1. 所有的房间分类信息。例如： roomno: 8089
            AllData allData = new AllData();
            // 1.1 添加房间信息
            allData.setRoom(room);
            // 1.2 根据房间信息检索房间分类信息
            List<Roomlb> collectRoomlb = roomlbList.stream()
                    .filter(roomlb -> roomlb.getRoomlbno().equals(room.getRoomlbno()))
                    .collect(Collectors.toList());
            allData.setRoomlb(collectRoomlb.get(0));
            // 1.3 判断房间状态
            // 1.3.1 房间状态是 在住房间
            if(room.getRegno() != null && room.getRstatus().equals("1")){
            // 1.4.1 根据房间信息检索房间账单信息
                List<Reg> collectReg = regList.stream()
                        .filter(reg -> reg.getRoomno().equals(room.getRoomno()) && reg.getNullify().equals("Y"))
                        .collect(Collectors.toList());
                if (collectReg.size() != 0){
                    Reg reg = collectReg.get(0);
                    allData.setReg(reg);
                    // 1.5.1 当账单存在时，可以查询客人信息
                    List<Guest> collectGuest = guestList.stream()
                            .filter(guest -> guest.getGuestno() == reg.getGuestno())
                            .collect(Collectors.toList());
                    if (collectGuest.size() != 0){
                        Guest guest = collectGuest.get(0);
                        allData.setGuest(guest);
                    }
                    System.out.println(collectGuest);
                }
                System.out.println("--------判断入住房间 " + room.getRoomno() + " 是否有预定--------------");
                System.out.println("--------判断入住房间 " + !room.getBookingno().isEmpty() + " 预定--------------");
                System.out.println(room.getBookingno());
                // 订单信息完成后，查看一下是否有预定信息
                if (!room.getBookingno().isEmpty()){
                    // 该房间有预定，但没有改变状态，没有开始计算时间
                    System.out.println(room.getRoomno() + "没有退房,所以不改变预定人信息");
                    List<Booking> collectBooking = bookingList.stream()
                            .filter(booking -> booking.getBookingno().equals(room.getBookingno()))
                            .collect(Collectors.toList());
                    System.out.println(collectBooking);
                    if (collectBooking.size() != 0) {
                        Booking booking = collectBooking.get(0);
                        allData.setBooking(booking);
                    }
                }
    /*            System.out.println(collectRoomlb);*/
                System.out.println(collectReg);
 /*               System.out.println(collectReg.size());*/
            }else if(!room.getBookingno().isEmpty() && room.getRstatus().equals("2")){
                // 1.3.2 房间状态是 预定房 （已经到了预定时间，颜色为紫色）
                System.out.println("该房间为预定房");
                List<Booking> collectBooking = bookingList.stream()
                        .filter(booking -> booking.getBookingno().equals(room.getBookingno()))
                        .collect(Collectors.toList());
                System.out.println(collectBooking);
                if (collectBooking.size() != 0){
                    Booking booking = collectBooking.get(0);
                    allData.setBooking(booking);
                    // 1.3.2.1 添加预定人信息
                    List<Guest> collectGuest = guestList.stream()
                            .filter(guest -> guest.getGuestno() == booking.getGuestno())
                            .collect(Collectors.toList());
                    if (collectGuest.size() != 0) {
                        Guest guest = collectGuest.get(0);
                        allData.setGuest(guest);
                    }
                    System.out.println(collectGuest);
                }
            } else if (room.getRstatus().equals("3")){
                // 1.3.3 房间状态是 脏房，房间基本信息
                System.out.println("该房间为脏房");

            }else if (!room.getOwnno().isEmpty() && room.getRstatus().equals("4")){
                // 1.3.4 房间状态是 自用房，添加自用房信息
                System.out.println("该房间为自用房");

            }else if (!room.getRstatus().isEmpty() && room.getRstatus().equals("5")){
                // 1.3.5 房间状态是 维修房，添加维修房信息
                System.out.println("该房间为维修房");
            }else {
                System.out.println("空房间？");
                System.out.println(room);
            }


            // 还有两种情况 空房间 以及 状态没有为2 的预定房
            System.out.println("--------------------------------------");
            allDatas.add(allData);
        }
        System.out.println("================================");
        for (AllData allData:allDatas) {
            System.out.println(allData);
        }

        System.out.println("================");
        List<Floor> floors = floorMapper.getAllFloor();
        System.out.println(floors);
    }

    @Test
    public void insGuestGetGuestno(){
        Guest guest = new Guest();
        guest.setGuestname("张三丰");
        guest.setIdtypeno("0");
        guest.setIdno("410210101010100001");
        guest.setGroupflag("N");
        int guestno = guestMapper.insGuestInfo(guest);
        System.out.println(guestno);
        System.out.println(guest);
    }

    @Test
    public void linkRegnoMax(){
        String maxRegno1 = regMapper.selMaxLikeRegno("G20200215");
        String maxRegno2 = regMapper.selMaxLikeRegno("G20200216");
        System.out.println(maxRegno1);
        System.out.println(maxRegno2);
/*        System.out.println(maxRegno2.isEmpty());*/
/*        System.out.println(maxRegno2.equals(""));*/
/*        System.out.println(maxRegno2.equals(null));*/
        System.out.println(maxRegno2 == "");
        System.out.println(maxRegno2 == null);
        String maxBooking1 = bookingMapper.selMaxLikeBookingno("BG20200215");
        String maxBooking2 = bookingMapper.selMaxLikeBookingno("BG20200216");
        System.out.println(maxBooking1);
        System.out.println(maxBooking2);
    }

    @Test
    public void TimestampNull(){
        Timestamp now = new Timestamp(new Date().getTime());
        System.out.println(now);
        System.out.println(now.equals(""));
        System.out.println(now.equals(null));
        System.out.println(now == null);
        Timestamp nullnow = null;
        System.out.println(nullnow == null);
    }

    @Test
    public void test(){
        // 8. 所有账单明细
        List<PayDetailed> payDetailedList = payDetailedMapper.selPayDetaileds();
        // 1.5.2 当账单存在时，可以查询当前账单 信息
        List<PayDetailed> collectPayDetailed = payDetailedList.stream()
                .filter(payDetailed -> payDetailed.getRegno() != null && payDetailed.getRegno().equals("G20200312000003"))
                .collect(Collectors.toList());
        System.out.println("collectPayDetailed:" + collectPayDetailed);
        if (collectPayDetailed.size() != 0){
            System.out.println(collectPayDetailed);
        }
    }
}
