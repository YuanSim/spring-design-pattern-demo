package com.yuansim.dao;

import com.yuansim.domain.StatisticsInsuranceLossRatio;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatisticsInsuranceLossRatioDao {

   void insertListByXml(List<StatisticsInsuranceLossRatio> list);
}
