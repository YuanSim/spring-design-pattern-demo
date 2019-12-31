package yuansim.dao.dto;

import lombok.Data;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2019/12/31
 */
@Data
public class PaymentChannel {

    private Integer id;
    /** 渠道名称 */
    private String channelName;
    /** 渠道ID */
    private String channelId;
    /**
     * 策略执行beanId
     */
    private String strategyBeanId;
}
