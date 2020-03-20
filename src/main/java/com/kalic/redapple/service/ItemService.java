package com.kalic.redapple.service;

import com.kalic.redapple.pojo.Item;
import com.kalic.redapple.utils.ResultDto;

import java.util.List;

public interface ItemService {
    // 1. 获取所有的消费项目
    List<Item> selAllItem();
    // 2. 新增消费项目
    ResultDto<Integer> insItemInfo(String itemJson);
    // 3. 删除消费项目
    ResultDto<Integer> delItemInfo(String itemno);
    // 4. 修改消费项目
    ResultDto<Integer> updItemInfo(String itemJson);
}
