

### springboot 数据绑定

> 自定义枚举 demo

```
 /**
     * 数据自定义绑定测试-- 枚举
     */
    @GetMapping(value = "/bind/enum/{str}")
    public PayType getLocalDateTime(@PathVariable(value = "str") PayType pay) {

        System.out.println("测试");

        return pay;
    }

```

```aidl
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
```

```java
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

```

> LocalDate demo
```aidl
 /**
     *  LocalDateTime 数据自定义绑定测试
     * @param date
     * @return
     */
    @GetMapping(value = "/bind/{date}")
    public LocalDateTime getLocalDateTime(@PathVariable(value = "date") LocalDateTime date) {

        System.out.println("测试");
        return date;
    }
```

> 配置
```java
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
```

```java
/**
* 实现addFormatters 添加自定义的转换器
*/
@Configuration
public class UnifiedReturnConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new LocalDateTimeConverter.StringToLocalDateTimeConverter());
    }
}
```