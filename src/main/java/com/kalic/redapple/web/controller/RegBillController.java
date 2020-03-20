package com.kalic.redapple.web.controller;

import com.kalic.redapple.pojo.RegBill;
import com.kalic.redapple.service.RegBillService;
import com.kalic.redapple.utils.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegBillController {
    @Autowired
    private RegBillService regBillServiceImpl;

    @RequestMapping("/reg/getAllRegBill")
    public List<RegBill> getAllRegBill() {
        return regBillServiceImpl.getAllRegBill();
    }
    // 2. 根据 regno 和 roomno 获取账单信息
    @RequestMapping("/reg/selRegBillForRegno")
    public ResultDto<List<RegBill>> selRegBillForRegno(String regno, String roomno){
        return regBillServiceImpl.selRegBillForRegno(regno, roomno);
    }
    // 3. 插入账单信息
    @RequestMapping("/reg/insRegBills")
    public ResultDto<Integer> insRegBill(String regBillJson){
        return regBillServiceImpl.insRegBill(regBillJson);
    }
    // 4. 删除账单信息
    @RequestMapping("/reg/delRegBill")
    public ResultDto<Integer> delRegBill(String regno, String roomno){
        return regBillServiceImpl.delRegBill(regno, roomno);
    }
}
