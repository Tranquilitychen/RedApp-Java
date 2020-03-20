package com.kalic.redapple.web.controller;

import com.kalic.redapple.pojo.PayDetailed;
import com.kalic.redapple.service.PayDetailedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Kalic Li
 * @ClassName PayDetailedController
 * @Package com.kalic.redapple.web.controller
 * @Description TODO
 * @date 2020/3/13 19:18
 */
@RestController
public class PayDetailedController {
    @Autowired
    private PayDetailedService payDetailedService;

    @RequestMapping("payDetailed/selAllData")
    public List<PayDetailed> selAllPayDetailed(){
        return payDetailedService.selAllPayDetailed();
    }
}
