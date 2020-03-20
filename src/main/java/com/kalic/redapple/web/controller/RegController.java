package com.kalic.redapple.web.controller;

import com.kalic.redapple.mapper.RegMapper;
import com.kalic.redapple.pojo.Reg;
import com.kalic.redapple.service.RegService;
import com.kalic.redapple.utils.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegController {
    @Autowired
    private RegService regServiceImpl;

    @RequestMapping("reg/getAllReg")
    public List<Reg> getAllReg() {
        return regServiceImpl.getAllReg();
    }

    @RequestMapping("reg/insRegData")
    public ResultDto<Integer> insRegData(String  jsonReg, String linkRoomno) {
        // 1. 查看插入的 入住信息
        return regServiceImpl.insRegData(jsonReg, linkRoomno);
    }

    @RequestMapping("reg/updRegLinkData")
    public ResultDto<Integer> updRegLinkData(String jsonLink){
        return regServiceImpl.updRegLinkData(jsonLink);
    }

    @RequestMapping("reg/updRegUnpackData")
    public ResultDto<Integer> updRegUnpackData(String jsonUnpack){
        return regServiceImpl.updRegUnpackData(jsonUnpack);
    }

}
