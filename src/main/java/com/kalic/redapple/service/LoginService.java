package com.kalic.redapple.service;

import com.kalic.redapple.utils.ResultDto;

/**
 * @author Kalic Li
 * @ClassName LoginService
 * @Package com.kalic.redapple.service
 * @Description TODO
 * @date 2020/2/29 14:09
 */
public interface LoginService {
    // 1. 检验登录信息
    ResultDto<Object> checkLoginInfo(String loginJson);
}
