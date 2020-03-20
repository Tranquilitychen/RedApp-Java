package com.kalic.redapple.web.controller;

import com.kalic.redapple.pojo.Roomlb;
import com.kalic.redapple.service.RoomlbService;
import com.kalic.redapple.utils.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomlbController {
    @Autowired
    private RoomlbService roomlbServiceImpl;

    @RequestMapping("/type/getAllRoomlbData")
    public List<Roomlb> getAllData(){
        return roomlbServiceImpl.getAllRoomlb();
    }

    @RequestMapping("/type/insRoomlb")
    public ResultDto<Integer> insRoomlb(String roomlbJson){
        return roomlbServiceImpl.insRoomlb(roomlbJson);
    }

    @RequestMapping("/type/updRoomlb")
    public ResultDto<Integer> updRoomlb(String roomlbJson){
        return roomlbServiceImpl.updRoomlbForRoomlbno(roomlbJson);
    }

    @RequestMapping("/type/delRoomlbForRoomlbno")
    public ResultDto<Integer> delRoomlb(String roomlbno){
        return roomlbServiceImpl.delRoomlbForRoomlbno(roomlbno);
    }
}
