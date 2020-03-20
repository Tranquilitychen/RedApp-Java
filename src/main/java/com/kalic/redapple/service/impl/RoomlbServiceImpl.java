package com.kalic.redapple.service.impl;

import com.alibaba.fastjson.JSON;
import com.kalic.redapple.mapper.RoomlbMapper;
import com.kalic.redapple.pojo.Roomlb;
import com.kalic.redapple.service.RoomlbService;
import com.kalic.redapple.utils.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
@Transactional(rollbackFor = {Exception.class})
@Service
public class RoomlbServiceImpl implements RoomlbService {
    @Autowired
    private RoomlbMapper roomlbMapper;
    @Override
    public List<Roomlb> getAllRoomlb() {
        return roomlbMapper.getAllRoomlb();
    }

    @Override
    public ResultDto<Integer> insRoomlb(String roomlbJson) {
        if (roomlbJson == "" || roomlbJson.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        Roomlb roomlb = JSON.parseObject(roomlbJson, Roomlb.class);
        int index = 0;
        try{
            System.out.println("新增房间类型");
            System.out.println(roomlb);
            index = roomlbMapper.insRoomlb(roomlb);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDto<>(400, "保存信息出错");
        }
        if (index == 0){
            return new ResultDto<>(400, "无法保存信息");
        }else {
            return new ResultDto<>(200, "保存信息成功");
        }
    }

    @Override
    public ResultDto<Integer> updRoomlbForRoomlbno(String roomlbJson) {
        if (roomlbJson == "" || roomlbJson.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        Roomlb roomlb = JSON.parseObject(roomlbJson, Roomlb.class);
        int index = 0;
        try{
            System.out.println("修改房间类型");
            System.out.println(roomlb);
            index = roomlbMapper.updRoomlbForRoomno(roomlb);
            System.out.println("你到底执行了没有啊！");
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
    public ResultDto<Integer> delRoomlbForRoomlbno(String roomlbno) {
        if (roomlbno == "" || roomlbno.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        int index = 0;
        try{
            System.out.println("删除房间类型,类型编号：" + roomlbno);
            index = roomlbMapper.delRoomlbForRoomno(roomlbno);
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
