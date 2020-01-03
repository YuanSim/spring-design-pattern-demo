package yuansim.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/1/3
 */
public class LocalDateTimeConverter {

    /**
     * 因为 Spring 默认不支持将 String 类型的请求参数转换为 LocalDateTime 类型，所以我们需要自定义 converter 「转换器」完整整个转换过程
     */


    public static class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
        @Override
        public LocalDateTime convert(String s) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
            return LocalDateTime.parse(s, formatter);
        }
    }
}
