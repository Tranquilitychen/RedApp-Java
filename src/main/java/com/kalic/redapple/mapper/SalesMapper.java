package com.kalic.redapple.mapper;

import com.kalic.redapple.pojo.Salesman;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SalesMapper {
    Salesman selcheckSales(@Param("salemanno") String salemanno, @Param("password") String password);
}
