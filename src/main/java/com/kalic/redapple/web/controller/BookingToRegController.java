package com.kalic.redapple.web.controller;

import com.kalic.redapple.service.BookingToRegService;
import com.kalic.redapple.utils.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kalic Li
 * @ClassName BookingToRegController
 * @Package com.kalic.redapple.web.controller
 * @Description TODO
 * @date 2020/2/17 15:14
 */
@RestController
public class BookingToRegController {
    @Autowired
    private BookingToRegService bookingToRegService;

    @RequestMapping("/booking/insRegForBooking")
    public ResultDto<Integer> insRegForBooking(String jsonReg,String linkRoomno,double dividedSecurity){
        return bookingToRegService.insRegForBooking(jsonReg, linkRoomno, dividedSecurity);
    }

    @RequestMapping("/booking/cancelGuestBooking")
    public ResultDto<Double> cancelGuestBooking(String jsonBooking, String cancelMeno){
        return bookingToRegService.updGuestCancelBookings(jsonBooking, cancelMeno);
    }

    @RequestMapping("/booking/cancelTeamBooking")
    public ResultDto<Double> calcelTeamBooking(String jsonBooking, String cancelMeno){
        return bookingToRegService.updTeamCancelBookings(jsonBooking, cancelMeno);
    }
}
