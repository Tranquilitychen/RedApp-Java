package com.kalic.redapple.web.controller;

import com.kalic.redapple.pojo.Item;
import com.kalic.redapple.service.ItemService;
import com.kalic.redapple.utils.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Kalic Li
 * @ClassName ItemController
 * @Package com.kalic.redapple.web.controller
 * @Description TODO
 * @date 2020/3/10 13:17
 */
@RestController
public class ItemController {
    @Autowired
    private ItemService itemServiceImpl;

    @RequestMapping("item/selAllItems")
    public List<Item> selAllItems(){
        return itemServiceImpl.selAllItem();
    }

    @RequestMapping("item/insItemData")
    public ResultDto<Integer> insItem(String itemJson){
        return itemServiceImpl.insItemInfo(itemJson);
    }
    @RequestMapping("item/updItemData")
    public ResultDto<Integer> updItem(String itemJson){
        return itemServiceImpl.updItemInfo(itemJson);
    }
    @RequestMapping("item/delItemData")
    public ResultDto<Integer> delItem(String itemno){
        return itemServiceImpl.delItemInfo(itemno);
    }

}
