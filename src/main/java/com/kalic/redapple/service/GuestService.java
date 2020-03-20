package com.kalic.redapple.service;

import com.kalic.redapple.pojo.Guest;
import com.kalic.redapple.utils.ResultDto;

import java.util.List;

public interface GuestService {
    // 1. 获取所有的客人信息
    List<Guest> getAllGuest();
    // 2. 新增客人信息
    ResultDto<Guest> insGuestInfo(Guest guest);
}
