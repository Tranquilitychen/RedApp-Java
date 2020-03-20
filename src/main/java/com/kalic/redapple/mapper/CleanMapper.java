package com.kalic.redapple.mapper;

import com.kalic.redapple.pojo.Cleanman;
import org.apache.ibatis.annotations.Param;

public interface CleanMapper {
    Cleanman selCleanManForCleanno(@Param("cleanno") String cleanno, @Param("password")String password);
}
