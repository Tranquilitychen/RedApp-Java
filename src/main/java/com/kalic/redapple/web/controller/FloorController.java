package com.kalic.redapple.web.controller;


import com.kalic.redapple.pojo.Floor;
import com.kalic.redapple.service.FloorService;
import com.kalic.redapple.utils.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FloorController {
    @Autowired
    private FloorService floorServiceImpl;

    @RequestMapping("/floor/getAllFloorData")
    public List<Floor> allFloorData(){
        System.out.println("floor:" + floorServiceImpl.getAllFloor());
        return floorServiceImpl.getAllFloor();
    }

    @RequestMapping("/floor/insFloor")
    public ResultDto<Integer> insFloor(String floorJson){
        return floorServiceImpl.insFloor(floorJson);
    }

    @RequestMapping("/floor/updFloor")
    public ResultDto<Integer> updFloor(String floorJson){
        return floorServiceImpl.updFloor(floorJson);
    }

    @RequestMapping("/floor/delFloor")
    public ResultDto<Integer> delFloor(String floorno){
        return floorServiceImpl.delFloorForFloorno(floorno);
    }

}
