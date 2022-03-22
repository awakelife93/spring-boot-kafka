package spring.kafak.component.utils;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;

@Component
public class ConvertUtils {

  public static String objectToJsonString(Map<String, Object> params) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    String jsonParams = mapper.writeValueAsString(params);

    return jsonParams;
  }
}
