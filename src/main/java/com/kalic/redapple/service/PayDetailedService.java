package com.kalic.redapple.service;

import com.kalic.redapple.pojo.PayDetailed;

import java.util.List;

public interface PayDetailedService {
    //1 . 获取所有的账单信息
    List<PayDetailed> selAllPayDetailed();
}
