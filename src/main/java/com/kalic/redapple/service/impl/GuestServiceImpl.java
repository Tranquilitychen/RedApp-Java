package com.kalic.redapple.service.impl;

import com.kalic.redapple.mapper.GuestMapper;
import com.kalic.redapple.pojo.Guest;
import com.kalic.redapple.service.GuestService;
import com.kalic.redapple.utils.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GuestServiceImpl implements GuestService {
    @Autowired
    private GuestMapper guestMapper;
    @Override
    public List<Guest> getAllGuest() {
        return guestMapper.getAllGuest();
    }

    @Override
    public ResultDto<Guest> insGuestInfo(Guest guest) {
        return null;
    }
}
