package com.yuansim.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 车险赔付率统计(StatisticsInsuranceLossRatio)实体类
 *
 * @author makejava
 * @since 2022-03-14 11:36:54
 */
@Data
public class StatisticsInsuranceLossRatio implements Serializable {
    private static final long serialVersionUID = -71254518131261492L;
    /**
    * 主键ID
    */
    private Long id;
    
    private Date createdAt;
    /**
    * 所属事业部 openId
    */
    private String adminBuOpenId;
    /**
    * 所属公司openId
    */
    private String adminBranchOpenId;
    /**
    * 业务模式，1: 网约CP，2: 网约自营
    */
    private Integer carBusType;
    /**
    * 车辆赔付总金额
    */
    private BigDecimal payTotal;
    /**
    * 车辆赔付净保费
    */
    private BigDecimal payNetPremium;
    /**
    * 车辆赔付率
    */
    private BigDecimal lossRate;


}