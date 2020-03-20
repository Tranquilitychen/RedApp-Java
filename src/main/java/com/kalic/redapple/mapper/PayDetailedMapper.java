package com.kalic.redapple.mapper;

import com.kalic.redapple.pojo.PayDetailed;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayDetailedMapper {
    // 1. 获取所有账单信息
    List<PayDetailed> selPayDetaileds();
    // 2. 获取当前最大 支付单号 no

    // 3. 批量新增 账目明细
    int insBatchPayDetaileds(@Param("payDetailedList") List<PayDetailed> payDetailedList);
}
