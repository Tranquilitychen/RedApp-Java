package com.kalic.redapple.service;

import com.kalic.redapple.pojo.Booking;
import com.kalic.redapple.utils.ResultDto;
import com.kalic.redapple.vo.AllData;

import java.util.List;

public interface BookingService {
    // 1. 获取所有的 有效的 预定信息
    List<Booking> selValidBooking();
    // 2. 获取 预定查询信息
    List<AllData> selQueryBooking();
    // 3. 插入预定信息
    ResultDto<Booking> insBookingInfo(String bookingJson, String linkRoomno);
}
