package gentle.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 性别注解（1:男 0：女）
 *
 * @author silence
 * @date 2019/1/7 16:12
 */
public class GenderSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {

        if ("1".equals(value)) {
            gen.writeString("男");
        } else {
            gen.writeString("女");
        }
    }
}
