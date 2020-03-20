import com.alibaba.fastjson.JSON;
import com.kalic.redapple.mapper.BookingMapper;
import com.kalic.redapple.mapper.RegMapper;
import com.kalic.redapple.mapper.RoomMapper;
import com.kalic.redapple.pojo.Booking;
import com.kalic.redapple.pojo.Reg;
import com.kalic.redapple.pojo.Room;
import com.kalic.redapple.service.BookingService;
import com.kalic.redapple.service.RegService;
import com.kalic.redapple.service.RoomService;
import com.kalic.redapple.service.impl.RoomServiceImpl;
import com.kalic.redapple.vo.VoRoom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-dao.xml", "classpath:spring-service.xml",
        "classpath:spring-config-annotation.xml" })
@Transactional
public class RoomData {
    @Autowired
    private RoomService roomServiceImpl;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private RegMapper regMapper;
    @Autowired
    private BookingMapper bookingMapper;
    @Test
    public void getListRoomsService(){
        List<VoRoom> rooms = roomServiceImpl.getListRooms();
        System.out.println(rooms);
    }

    @Test
    public void mybatisGetAllRoom(){
        String json = "[{roomno='8101', roomname='8101', roomlbno='01', floorno='01', beds=0, tel='null'}" +
                ",{roomno='8101', roomname='8101', roomlbno='01', floorno='01', beds=0, tel='null}]";
        System.out.println(json);
        List<Room> rooms = JSON.parseArray(json, Room.class);
        System.out.println(rooms);
    }

    @Test
    public void BookingRoom(){
        // 1. 获取所有房间信息
        List<Room> roomList = roomMapper.getAllRoom();
        // 2. 获取所有有效的订单信息
        List<Reg> regList = regMapper.getAllReg();
        // 3. 获取所有有效的预定信息
        List<Booking> bookingList = bookingMapper.selValidBooking();

        System.out.println("=================");
        for (Room room: roomList) {
            System.out.println(room);
        }
        System.out.println("=================");
        for (Reg reg: regList) {
            System.out.println(reg);
        }
        System.out.println("=================");
        for (Booking booking: bookingList) {
            System.out.println(booking);
        }
        System.out.println("=================");

        Timestamp bookingTime = new Timestamp(new Date().getTime());
        Timestamp bookingTimeY = new Timestamp(new Date("2020-3-14 13:00:00").getTime());
        // 1. 遍历房间
        for (int i = 0; i < roomList.size(); i++){
            Room room = roomList.get(i);
            System.out.println("房间：" + room.getRoomno());
            // 如果房间状态为 0, 查看该房间是否有被预定
            // 第一步: 先判断 入住时间是否 在预订单中
/*            System.out.println(regList.get(0).getDtIndate());
            Timestamp now = new Timestamp(new Date().getTime());*/
/*            System.out.println(now);*/
            /*before 谁的时间比谁早*/
/*            System.out.println(regList.get(0).getDtIndate().before(now));*/
  /*          if (room.getRegno() != null){
                System.out.println("进行账单Reg的比对");
                regList.get(i).getDtIndate(), regList
                        .get(i).getDtOutdate();
            }*/
/*            for (int k = 0; k < bookingList.size(); k++){

            }*/
        }
    }

    @Test
    public void testDate() throws ParseException {
        // 时间相减 （固定的）
        Date bookingDtIndate =  SimpleDate("2020-3-18 12:30:00");
        Date bookingDtOutdate = SimpleDate("2020-3-19 03:00:00");
        List<Date> dtIndateList = new ArrayList<>();
        List<Date> dtOutDateList = new ArrayList<>();
        // 1.
        dtIndateList.add(SimpleDate("2020-3-14 12:30:00"));
        dtOutDateList.add(SimpleDate("2020-3-15 12:00:00"));
        // 2.
        dtIndateList.add(SimpleDate("2020-3-15 15:00:00"));
        dtOutDateList.add(SimpleDate("2020-3-18 12:00:00"));
        // 3.
        dtIndateList.add(SimpleDate("2020-3-19 03:30:00"));
        dtOutDateList.add(SimpleDate("2020-3-19 08:00:00"));

        for (int i = 0; i < dtIndateList.size(); i++){
            boolean flag = judge(bookingDtIndate, bookingDtOutdate, dtIndateList.get(i), dtOutDateList.get(i));
            System.out.println(flag);
        }
    }

    public Date SimpleDate(String time) throws ParseException {
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.parse(time);
    }

    // 无交集返回true
    public Boolean judge(Date date1, Date date2, Date startTime, Date endDate) {
        if(date1 == null || date2 == null || startTime == null || endDate == null)
            return false;
        long d1 = date1.getTime();
        long d2 = date2.getTime();
        long v = d2-d1;                         // 做差
        long start = startTime.getTime();
        long end = endDate.getTime();

        if (v < 0){
            System.out.println("不符合要求");
            return false;
        }
        if (d1 - start <= 0 && d2 - start <= 0){
            return true;
        }else if (d1 - end >= 0 && d2 - end >= 0){
            return true;
        }else {
            return false;
        }
    }
}
