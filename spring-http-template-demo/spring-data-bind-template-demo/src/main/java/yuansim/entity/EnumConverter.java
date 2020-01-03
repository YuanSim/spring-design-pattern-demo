package yuansim.entity;

import org.springframework.core.convert.converter.Converter;
import yuansim.PayType;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/1/3
 */
public class EnumConverter {

    /**
     * 自定义枚举转换
     */
    public static class StringToEnumConverter implements Converter<String, PayType> {

        @Override
        public PayType convert(String s) {
            return PayType.valueOf(s);
        }
    }

}
