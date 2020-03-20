package com.kalic.redapple.web.controller;

import com.kalic.redapple.service.LoginService;
import com.kalic.redapple.utils.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kalic Li
 * @ClassName LoginController
 * @Package com.kalic.redapple.web.controller
 * @Description TODO
 * @date 2020/2/29 14:44
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginServiceImpl;

    @RequestMapping("/login/checkLogin")
    public ResultDto<Object> checkLogin(String loginJson){
        return loginServiceImpl.checkLoginInfo(loginJson);
    }
}
