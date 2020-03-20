package com.kalic.redapple.web.controller;

import com.kalic.redapple.mapper.BookingMapper;
import com.kalic.redapple.pojo.Booking;
import com.kalic.redapple.service.BookingService;
import com.kalic.redapple.utils.ResultDto;
import com.kalic.redapple.vo.AllData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Kalic Li
 * @ClassName BookingController
 * @Package com.kalic.redapple.web.controller
 * @Description TODO
 * @date 2020/2/14 14:50
 */
@RestController
public class BookingController {
    @Autowired
    private BookingService bookingServiceImpl;

    @RequestMapping("booking/insBookingData")
    public ResultDto<Booking> insBookingInfo(String bookingJson, String linkRoomno){
        return bookingServiceImpl.insBookingInfo(bookingJson, linkRoomno);
    }

    @RequestMapping("/booking/getValidBooking")
    public List<Booking> selValidBooking(){
        return bookingServiceImpl.selValidBooking();
    }

    @RequestMapping("/booking/selQueryBooking")
    public List<AllData> selQueryBooking(){
        return bookingServiceImpl.selQueryBooking();
    }

}
