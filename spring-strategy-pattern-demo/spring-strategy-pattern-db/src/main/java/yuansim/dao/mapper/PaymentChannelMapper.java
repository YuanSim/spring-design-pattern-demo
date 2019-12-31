package yuansim.dao.mapper;

import org.apache.ibatis.annotations.Select;
import yuansim.dao.dto.PaymentChannel;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2019/12/31
 */
public interface PaymentChannelMapper {

    @Select("SELECT  id ,channelName ,channelId,strategyBeanId " +
            "FROM payment_channel where channelId=#{payCode}")
    PaymentChannel getPaymentChannel(String payCode);
}
