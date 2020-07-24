package yuansim.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/7/24
 */
public class LocalDateTimeDeserializer  extends JsonDeserializer<LocalDateTime> {

    /**
     *
     */
    private String pattern;


    public LocalDateTimeDeserializer(String pattern) {
        this.pattern = pattern;
    }

    /**
     * @param jsonParser
     * @param deserializationContext
     * @return
     * @throws IOException
     */
    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        return LocalDateTime.parse(jsonParser.getValueAsString(), DateTimeFormatter.ofPattern(pattern));
    }

}
