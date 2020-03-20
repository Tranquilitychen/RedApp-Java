package com.kalic.redapple.service;

import com.kalic.redapple.pojo.Booking;
import com.kalic.redapple.utils.ResultDto;

public interface BookingToRegService {
    // 1. 预定信息 转未 入住 信息
    ResultDto<Integer> insRegForBooking(String jsonReg, String linkRoomno, double dividedSecurity);
    // 2. 顾客取消预定信息
    ResultDto<Double> updGuestCancelBookings(String jsonBooking, String cancelMeno);
    // 3. 团体取消预定信息
    ResultDto<Double> updTeamCancelBookings(String jsonBooking, String cancelMeno);
}
