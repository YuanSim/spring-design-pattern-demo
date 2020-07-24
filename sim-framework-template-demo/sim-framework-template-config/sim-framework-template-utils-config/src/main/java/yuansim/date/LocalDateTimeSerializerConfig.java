package yuansim.date;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/7/24
 */

public class LocalDateTimeSerializerConfig {

    /**
     *
     */
    public static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

 /*   *//** @Configuration
     * @return
     *//*
    @Bean
    @Primary
    public ObjectMapper serializingObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(PATTERN));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(PATTERN));
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }*/

}
