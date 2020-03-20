package com.kalic.redapple.service;

import com.kalic.redapple.pojo.Reg;
import com.kalic.redapple.utils.ResultDto;

import java.util.List;

public interface RegService {
    // 1. 获取所有 已开房间 的 消费信息
    List<Reg> getAllReg();
    // 2. 房间入住
    ResultDto<Integer> insRegData(String jsonReg, String linkRoomno);
    // 3. 联房
    ResultDto<Integer> updRegLinkData(String jsonLink);
    // 4. 拆房
    ResultDto<Integer> updRegUnpackData(String jsonUnpack);
}
