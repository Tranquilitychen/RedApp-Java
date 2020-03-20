package com.kalic.redapple.service;

import com.kalic.redapple.pojo.Floor;
import com.kalic.redapple.utils.ResultDto;

import java.util.List;

public interface FloorService {
    // 1. 获取所有的楼层信息
    List<Floor> getAllFloor();
    // 2. 新增楼层数据
    ResultDto<Integer> insFloor(String floorJson);
    // 3. 保存楼层数据
    ResultDto<Integer> updFloor(String floorJson);
    // 4. 删除楼层数据
    ResultDto<Integer> delFloorForFloorno(String floorno);
}
