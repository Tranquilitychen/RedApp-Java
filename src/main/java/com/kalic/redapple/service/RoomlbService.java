package com.kalic.redapple.service;

import com.kalic.redapple.pojo.Roomlb;
import com.kalic.redapple.utils.ResultDto;

import java.util.List;

public interface RoomlbService {
    // 1. 获取所有的房间类型信息
    List<Roomlb> getAllRoomlb();
    // 2. 新增房间类型
    ResultDto<Integer> insRoomlb(String roomlbJson);
    // 3. 修改房间类型
    ResultDto<Integer> updRoomlbForRoomlbno(String roomlbJson);
    // 4. 删除房间类型
    ResultDto<Integer> delRoomlbForRoomlbno(String roomlbno);
}
