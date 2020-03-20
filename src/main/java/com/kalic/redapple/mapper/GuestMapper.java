package com.kalic.redapple.mapper;

import com.kalic.redapple.pojo.Guest;
import com.kalic.redapple.utils.ResultDto;

import java.util.List;

public interface GuestMapper {
    // 1. 获取所有的客人信息
    List<Guest> getAllGuest();

    // 2. 插入客人信息（包括团队信息）,返回的是插入主键的 id
    int insGuestInfo(Guest guest);
}
