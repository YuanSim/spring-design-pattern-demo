package com.yuansim.dao;

import com.yuansim.domain.Driver;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CursorDao {

    @Select("SELECT * FROM report_driver_status_count_by_minutes LIMIT #{start},#{end}")
    List<Driver> findByLimit(Integer start, Integer end);

}
