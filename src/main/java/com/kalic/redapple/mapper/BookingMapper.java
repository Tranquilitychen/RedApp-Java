package com.kalic.redapple.mapper;

import com.kalic.redapple.pojo.Booking;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Kalic Li
 * @ClassName BookingMapper
 * @Package com.kalic.redapple.mapper
 * @Description TODO
 * @date 2020/2/14 14:31
 */
public interface BookingMapper {
    // 1. 查询所有的预定信息
    List<Booking> selAllBookingInfo();
    // 2. 插入预定信息
    int insBookingInfo(@Param("bookings")List<Booking> booking);
    // 3. 获取订单信息中最大的 bookingno
    String selMaxLikeBookingno(@Param("linkno") String linkno);
    // 4. 取消预定
    int updCancelBooking(@Param("bookingno") String bookingno,
       @Param("dtCancel") Timestamp dtCancel,@Param("cancelMeno") String cancelMeno);
    // 4.1 批量取消预定
    int updCancelBatchBooking(@Param("bookings") List<Booking> bookings);
    // 5. 查询所有 已经确认 并且 有效的订单
    List<Booking> selValidBooking();
    // 6. 查询最大的联房号
    int selMaxBookingLinkid();
    // 7. 预定转入住
    int updBookingToReg(@Param("bookingno") String bookingno,@Param("dtCancel") Timestamp dtCancel,
                        @Param("cancelMeno") String cancelMeno, @Param("sureflag") String sureflag);
}
