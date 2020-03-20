package com.kalic.redapple.service.impl;

import com.kalic.redapple.mapper.PayDetailedMapper;
import com.kalic.redapple.pojo.PayDetailed;
import com.kalic.redapple.service.PayDetailedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Kalic Li
 * @ClassName PayDetailedServiceImpl
 * @Package com.kalic.redapple.service.impl
 * @Description TODO
 * @date 2020/3/13 19:16
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class PayDetailedServiceImpl implements PayDetailedService {
    @Autowired
    private PayDetailedMapper payDetailedMapper;
    @Override
    public List<PayDetailed> selAllPayDetailed() {
        return payDetailedMapper.selPayDetaileds();
    }
}
