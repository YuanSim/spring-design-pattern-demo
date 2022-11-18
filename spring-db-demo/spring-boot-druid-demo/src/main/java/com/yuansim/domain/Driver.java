package com.yuansim.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Driver implements Serializable {
    /**
     *
     */
    private Long driverStatusCountId;
    /**
     *
     */
    private Integer cityId;
    /**
     *城市名称
     */
    private String cityName;
    /**
     *待服务中的司机数量
     */
    private Integer waitServiceCount;
    /**
     *服务中的司机数量
     */
    private Integer inServiceCount;
    /**
     *离线的司机数量
     */
    private Integer offLineCount;
    /**
     *下班的司机数量
     */
    private Integer offDutyCount;
    /**
     *用车事由类型ID
     */
    private Integer onDutyCount;

    /**
     *创建时间
     */
    private LocalDateTime createTime;

    private Integer waitServiceCountTaxi;

    private Integer inServiceCountTaxi;

    private Integer offLineCountTaxi;

    private Integer offDutyCountTaxi;

    private Integer onDutyCountTaxi;


}











