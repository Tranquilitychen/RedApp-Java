package com.kalic.redapple.service.impl;

import com.alibaba.fastjson.JSON;
import com.kalic.redapple.mapper.FloorMapper;
import com.kalic.redapple.pojo.Floor;
import com.kalic.redapple.service.FloorService;
import com.kalic.redapple.utils.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Transactional(rollbackFor = {Exception.class})
@Service
public class FloorServiceImpl implements FloorService {
    @Autowired
    private FloorMapper floorMapper;


    @Override
    public List<Floor> getAllFloor() {
        return floorMapper.getAllFloor();
    }

    @Override
    public ResultDto<Integer> insFloor(String floorJson) {
        //1. 解析Json
        if (floorJson == "" || floorJson.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        Floor floor = JSON.parseObject(floorJson, Floor.class);
        System.out.println("插入楼层信息");
        System.out.println(floor);
        // 2.数据库
        int index = 0;
        try{
            index = floorMapper.insFloorInfo(floor);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDto<>(400, "保存信息出错");
        }
        if (index == 0){
            return new ResultDto<>(400, "无法保存信息");
        }else{
            return new ResultDto<>(200, "保存信息成功");
        }
    }

    @Override
    public ResultDto<Integer> updFloor(String floorJson) {
        //1. 解析Json
        if (floorJson == "" || floorJson.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        Floor floor = JSON.parseObject(floorJson, Floor.class);
        System.out.println("更新楼层信息");
        System.out.println(floor);
        // 2.
        int index = 0;
        try{
            index = floorMapper.updFloorInfoForFloorno(floor);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDto<>(400, "保存信息出错");
        }
        if (index == 0){
            return new ResultDto<>(400, "无法保存信息");
        }else{
            return new ResultDto<>(200, "保存信息成功");
        }
    }

    @Override
    public ResultDto<Integer> delFloorForFloorno(String floorno) {
        //1. 解析Json
        if (floorno == "" || floorno.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        int index = 0;
        try{
            index = floorMapper.delFloorForFloorno(floorno);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDto<>(400, "删除信息出错");
        }
        if (index == 0){
            return new ResultDto<>(400, "无法删除信息");
        }else{
            return new ResultDto<>(200, "删除信息成功");
        }
    }
}
