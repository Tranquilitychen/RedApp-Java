package com.kalic.redapple.mapper;

import com.kalic.redapple.pojo.Floor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 楼层 Mapper
 */
public interface FloorMapper {
    // 1. 获取所有的楼层信息
    List<Floor> getAllFloor();
    // 2. 新增楼层信息
    int insFloorInfo(@Param("floor") Floor floor);
    // 3. 修改楼层信息
    int updFloorInfoForFloorno(@Param("floor") Floor floor);
    // 4. 删除楼层信息
    int delFloorForFloorno(@Param("floorno") String floorno);
}
