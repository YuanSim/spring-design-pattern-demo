package yuansim.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import yuansim.entity.EnumConverter;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/1/3
 */
@Configuration
public class EnumConverterConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        /**
         * 注册
         */
        registry.addConverter(new EnumConverter.StringToEnumConverter());
    }
}
