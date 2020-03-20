package com.kalic.redapple.service.impl;

import com.alibaba.fastjson.JSON;
import com.kalic.redapple.mapper.RegBillMapper;
import com.kalic.redapple.pojo.RegBill;
import com.kalic.redapple.service.RegBillService;
import com.kalic.redapple.utils.ResultDto;
import com.kalic.redapple.vo.RegBillInfo;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class RegBillServiceImpl implements RegBillService {
    @Autowired
    private RegBillMapper regBillMapper;
    @Override
    public List<RegBill> getAllRegBill() {
        return regBillMapper.getAllRegBill();
    }

    @Override
    public ResultDto<List<RegBill>> selRegBillForRegno(String regno, String roomno) {
        if ((regno == "" || regno.isEmpty()) && (roomno == "" || roomno.isEmpty())){
            return new ResultDto<>(400, "参数错误");
        }
        List<RegBill> regBills = regBillMapper.getRegBillForRegnoAndRoomno(regno, roomno);
        return new ResultDto<>(200, "成功", regBills);
    }

    /**
     * 插入账单信息
     * @param regBillJson
     * @return
     */
    @Override
    public ResultDto<Integer> insRegBill(String regBillJson) {
        if (regBillJson == "" || regBillJson.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        List<RegBillInfo> regBillInfos = JSON.parseArray(regBillJson, RegBillInfo.class);
        int index = 0;
        try{
            List<RegBillInfo> insList = new ArrayList<>();
            List<RegBillInfo> updList = new ArrayList<>();
            for (RegBillInfo regBillInfo: regBillInfos){
                if (regBillInfo.getUpdateKbn().equals("C")){
                    regBillInfo.setNullify("Y");
                    insList.add(regBillInfo);
                }else if (regBillInfo.getUpdateKbn().equals("U")){
                    updList.add(regBillInfo);
                }else {
                    System.out.println("未知错误");
                }
            }
            System.out.println(insList);
            System.out.println(updList);
            // 新增
            if (insList.size() != 0){
                index += regBillMapper.insRegBill(insList);
            }
            if (updList.size() != 0){
                // 修改
                for (RegBillInfo updRegBill: updList){
                    index += regBillMapper.updRegBillNum(updRegBill);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDto<>(403, "新增失败！");
        }
        if (index == 0){
            return new ResultDto<>(403, "新增失败，未知错误！");
        }else {
            return new ResultDto<>(200, "新增成功！");
        }
    }

    /**
     * 删除账单信息
     * @param regno
     * @param roomno
     * @return
     */
    @Override
    public ResultDto<Integer> delRegBill(String regno, String roomno) {
        if ((regno == "" || regno.isEmpty()) && (roomno == "" || roomno.isEmpty())){
            return new ResultDto<>(400, "参数错误");
        }
        int index = 0;
        try{
            index = regBillMapper.delRegBill(regno, roomno);
        }catch(Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDto<>(403, "请求失败！");
        }
        if (index == 0){
            return new ResultDto<>(403, "删除失败，未知错误！");
        }else {
            return new ResultDto<>(200, "请求成功！");
        }
    }


}
