package com.kalic.redapple.web.controller;

import com.kalic.redapple.pojo.Guest;
import com.kalic.redapple.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GuestController {
    @Autowired
    private GuestService guestServiceImpl;

    @RequestMapping("/guest/getAllGuestData")
    public List<Guest> getAllGuestData() {
        return guestServiceImpl.getAllGuest();
    }
}
