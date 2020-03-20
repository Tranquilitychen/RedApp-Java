package com.kalic.redapple.service;

import com.kalic.redapple.pojo.RegBill;
import com.kalic.redapple.utils.ResultDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegBillService {
    // 1. 获取所有 已开房间 的 消费信息(没有分类)
    List<RegBill> getAllRegBill();
    // 2. 根据 regno 和 roomno 获取账单信息 (好像没人用啊)
    ResultDto<List<RegBill>> selRegBillForRegno(String regno, String roomno);
    // 3. 插入账单信息
    ResultDto<Integer> insRegBill(String regBillJson);
    // 4. 删除账单信息
    ResultDto<Integer> delRegBill(String regno, String roomno);
}
