package com.kalic.redapple.service.impl;

import com.alibaba.fastjson.JSON;
import com.kalic.redapple.mapper.ItemMapper;
import com.kalic.redapple.pojo.Item;
import com.kalic.redapple.pojo.Room;
import com.kalic.redapple.service.ItemService;
import com.kalic.redapple.utils.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * @author Kalic Li
 * @ClassName ItemServiceImpl
 * @Package com.kalic.redapple.service.impl
 * @Description TODO
 * @date 2020/3/10 13:10
 */
@Transactional(rollbackFor = {Exception.class})
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<Item> selAllItem() {
        return itemMapper.selAllItem();
    }

    @Override
    public ResultDto<Integer> insItemInfo(String itemJson) {
        if (itemJson == "" || itemJson.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        List<Item> items = JSON.parseArray(itemJson, Item.class);
        int resultIndex = 0;
        try {
            for (Item item : items){
                System.out.println(item);
                resultIndex = itemMapper.insItemInfo(item);
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDto<>(406, "插入失败");
        }
        if (resultIndex == 0){
            return new ResultDto<>(406, "插入失败， 不明原因");
        }else {
            return new ResultDto<>(200, "获取成功", resultIndex);
        }
    }

    @Override
    public ResultDto<Integer> delItemInfo(String itemno) {
        if (itemno == "" || itemno.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        System.out.println("项目id：" + itemno);
        int resultIndex = 0;
        try {
            resultIndex = itemMapper.delItemInfo(itemno);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDto<>(406, "删除失败");
        }
        if (resultIndex == 0){
            return new ResultDto<>(406, "删除失败， 不明原因");
        }else {
            return new ResultDto<>(200, "删除成功", resultIndex);
        }
    }

    @Override
    public ResultDto<Integer> updItemInfo(String itemJson) {
        if (itemJson == "" || itemJson.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        List<Item> items = JSON.parseArray(itemJson, Item.class);
        int resultIndex = 0;
        try {
            for (Item item : items){
                System.out.println(item);
                resultIndex = itemMapper.updItemInfo(item);
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDto<>(406, "修改失败");
        }
        if (resultIndex == 0){
            return new ResultDto<>(406, "修改失败， 不明原因");
        }else {
            return new ResultDto<>(200, "修改成功", resultIndex);
        }
    }
}
