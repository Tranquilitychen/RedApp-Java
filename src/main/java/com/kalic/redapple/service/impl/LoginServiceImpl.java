package com.kalic.redapple.service.impl;

import com.alibaba.fastjson.JSON;
import com.kalic.redapple.mapper.CleanMapper;
import com.kalic.redapple.mapper.SalesMapper;
import com.kalic.redapple.pojo.Cleanman;
import com.kalic.redapple.pojo.Salesman;
import com.kalic.redapple.service.LoginService;
import com.kalic.redapple.utils.ResultDto;
import com.kalic.redapple.vo.LoginInfo;
import com.kalic.redapple.vo.LoginRole;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Kalic Li
 * @ClassName LoginServiceImpl
 * @Package com.kalic.redapple.service.impl
 * @Description TODO
 * @date 2020/2/29 14:11
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private SalesMapper salesMapper;
    @Autowired
    private CleanMapper cleanMapper;

    @Override
    public ResultDto<Object> checkLoginInfo(String loginJson) {
        //1. 解析Json
        if (loginJson == "" || loginJson.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        LoginInfo loginInfo = JSON.parseObject(loginJson, LoginInfo.class);
        System.out.println(loginInfo);
        if (loginInfo.getRole() == 0){
            // admin 登录
            if (loginInfo.getUsername().equals("admin") && loginInfo.getPassword().equals("123123")){
                LoginRole loginRole = new LoginRole();
                loginRole.setUid("admin");
                loginRole.setUsername(loginInfo.getUsername());
                loginRole.setRole("admin");
                return new ResultDto<>(200, "管理员登录成功", loginRole);
            }else{
                return new ResultDto<>(400, "登陆信息出错");
            }
        }else if (loginInfo.getRole() == 1){
            // 前台营业员登录
            Salesman salesman = salesMapper.selcheckSales(loginInfo.getUsername(), loginInfo.getPassword());
            System.out.println(salesman);
            if (salesman != null){
                // 获取到了登录信息
                LoginRole loginRole = new LoginRole();
                loginRole.setUid(salesman.getSalemanno());
                loginRole.setUsername(salesman.getSalemanname());
                loginRole.setRole("sales");
                return new ResultDto<>(200, "前台营业员登录成功", loginRole);
            }else {
                return new ResultDto<>(400, "登陆信息出错");
            }
        }else if (loginInfo.getRole() == 2){
            // 清洁员登录
            Cleanman cleanman = cleanMapper.selCleanManForCleanno(loginInfo.getUsername(), loginInfo.getPassword());
            System.out.println(cleanman);
            if (cleanman != null){
                // 获取到了登录信息
                LoginRole loginRole = new LoginRole();
                loginRole.setUid(cleanman.getCleanno());
                loginRole.setUsername(cleanman.getCleanname());
                loginRole.setRole("clean");
                return new ResultDto<>(200, "清洁员登录成功", loginRole);
            }else {
                return new ResultDto<>(400, "登陆信息出错");
            }
        }else {
            // 出错
            return new ResultDto<>(401, "登录信息错误");
        }
    }
}
