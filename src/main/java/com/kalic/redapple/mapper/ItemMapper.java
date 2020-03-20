package com.kalic.redapple.mapper;

import com.kalic.redapple.pojo.Item;

import java.util.List;

public interface ItemMapper {
    // 1. 获取所有的消费项目
    List<Item> selAllItem();
    // 2. 新增消费项目
    int insItemInfo(Item item);
    // 3. 删除消费项目
    int delItemInfo(String itemno);
    // 4. 修改消费项目
    int updItemInfo(Item item);
}
