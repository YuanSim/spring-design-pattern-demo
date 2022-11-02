package com.yuansim.dao;

import com.github.pagehelper.Page;
import com.yuansim.domain.Driver;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cursor.Cursor;

import java.util.List;

@Mapper
public interface CursorDao {


    @Select("SELECT * FROM report_driver_status_count_by_minutes LIMIT #{start},#{end}")
    List<Driver> findByLimit(Integer start,Integer end);

    @Select("SELECT * FROM `report_driver_status_count_by_minutes`")
    List<Driver> findByPageHelper(Page<Object> objects);

    @Select("SELECT * FROM `report_driver_status_count_by_minutes` LIMIT #{start},#{end}")
    Cursor<Driver> findByStream(Integer start,Integer end);

}
