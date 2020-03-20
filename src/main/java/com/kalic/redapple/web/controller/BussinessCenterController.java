package com.kalic.redapple.web.controller;

import com.kalic.redapple.service.BussinessCenterService;
import com.kalic.redapple.utils.ResultDto;
import com.kalic.redapple.vo.AllData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Kalic Li
 * @ClassName BussinessCenterController
 * @Package com.kalic.redapple.web.controller
 * @Description TODO
 * @date 2020/2/15 13:45
 */
@RestController
public class BussinessCenterController {
    @Autowired
    private BussinessCenterService bussinessCenterServiceImpl;

    @RequestMapping("/bussinessCenter/getAllBCData")
    public ResultDto<List<AllData>> getAllBCData(){
        return bussinessCenterServiceImpl.getBCData();
    }

    @RequestMapping("/bussinessCenter/bookingUpRoomRstatus")
    public ResultDto<Integer> bookingUpRoomRstatus(String roomjson){
        return bussinessCenterServiceImpl.updRoomRstatus(roomjson);
    }

    @RequestMapping("/bussinessCenter/overTimeBooking")
    public ResultDto<Integer> cancelBookingOverTime(String roomjson){
        return bussinessCenterServiceImpl.updCancelBooking(roomjson);
    }

    @RequestMapping("/bussinessCenter/roomqtyReg")
    public ResultDto<Integer> roomqtyReg(String regjson, String resultjson){
        return bussinessCenterServiceImpl.updRoomqtyReg(regjson, resultjson);
    }

    @RequestMapping("/bussinessCenter/swapRoom")
    public ResultDto<Integer> swapRoom(String swapJson){
        return bussinessCenterServiceImpl.updSwapRoom(swapJson);
    }

    @RequestMapping("/bussinessCenter/closeReg")
    public ResultDto<Integer> closeReg(String regjson, String resultjson){
        return bussinessCenterServiceImpl.updExitRoom(regjson, resultjson);
    }
}
