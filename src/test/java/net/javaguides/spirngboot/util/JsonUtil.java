package net.javaguides.spirngboot.util;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
      .configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

  static {
    SimpleModule module = new SimpleModule();
    module.addSerializer(LocalDateTime.class,
        new LocalDateTimeSerializer(ISO_LOCAL_DATE_TIME));
    module.addDeserializer(LocalDateTime.class,
        new LocalDateTimeDeserializer(ISO_LOCAL_DATE_TIME));
    module.addSerializer(OffsetDateTime.class, OffsetDateTimeSerializer.INSTANCE);
    OBJECT_MAPPER.registerModule(module);
  }

  private JsonUtil() {

  }

  public static <T> T fromJson(String json, Class<T> clazz) {
    try {
      return OBJECT_MAPPER.readValue(json, clazz);
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static String toJson(Object object) {
    try {
      return OBJECT_MAPPER.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static <T> List<T> toList(String json, Class<T> clazz){
    try {
      JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(ArrayList.class, clazz);
      return OBJECT_MAPPER.readValue(json, javaType);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
