package com.kalic.redapple.mapper;

import com.kalic.redapple.pojo.Roomlb;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 房间类型Mapper
 */
public interface RoomlbMapper {
    // 1. 获取所有的房间类型
    List<Roomlb> getAllRoomlb();
    // 2. 新增房间类型
    int insRoomlb(@Param("roomlb") Roomlb roomlb);
    // 3. 修改房间类型
    int updRoomlbForRoomno(@Param("roomlb") Roomlb roomlb);
    // 3. 删除房间类型
    int delRoomlbForRoomno(@Param("roomlbno")String roomlbno);
}
