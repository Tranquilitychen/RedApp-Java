package com.kalic.redapple.service;

import com.kalic.redapple.utils.ResultDto;
import com.kalic.redapple.vo.AllData;

import java.util.List;

public interface BussinessCenterService {
    // 1. 获取营业中心的所有信息
    ResultDto<List<AllData>> getBCData();
    // 2. 修改房间状态为 紫色 rstatus = 2,预定状态
    ResultDto<Integer> updRoomRstatus(String roomjson);
    // 3, 取消预定信息
    ResultDto<Integer> updCancelBooking(String roomjson);
    // 4. 续约房租
    ResultDto<Integer> updRoomqtyReg(String regjson, String resultjson);
    // 5. 换房
    ResultDto<Integer> updSwapRoom(String swapJson);
    // 5. 退房
    ResultDto<Integer> updExitRoom(String regjson,String resultjson);

}
