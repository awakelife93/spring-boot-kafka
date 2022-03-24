package spring.kafak.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spring.kafak.service.KafkaService;

/**
 * todo1 : kafka 써보면서 재밌을만한 로직들 구상하면서 endpoint 붙여보기
 */
@RestController
@RequiredArgsConstructor
public class KafkaController {

  private final KafkaService kafkaService;

  private final String topic = "tokenStorage";

  @PostMapping(value = "/publishSingleMessage")
  public String publishSingleMessage(HttpServletRequest request, HttpServletResponse response,
      @RequestBody Map<String, Object> body) throws InterruptedException, ExecutionException {
    try {
      String topic = body.get("topic") == null ? this.topic : (String) body.get("topic");
      String message = (String) body.get("message");
      String result = kafkaService.publisher(topic, message);

      response.setStatus(HttpStatus.OK.value());
      return result;
    } catch (InterruptedException | ExecutionException exception) {
      throw exception;
    }
  }

  @PostMapping(value = "/publishMultipleMessages")
  public List<String> publishMultipleMessages(HttpServletRequest request, HttpServletResponse response,
      @RequestBody Map<String, Object> body) throws InterruptedException, ExecutionException {
    try {
      String topic = body.get("topic") == null ? this.topic : (String) body.get("topic");
      List<String> result = new ArrayList<String>();

      if (body.get("messages") instanceof ArrayList<?>) {
        for (Object message : (ArrayList<?>) body.get("messages")) {
          if (message instanceof String) {
            String _message = (String) message;
            result.add(kafkaService.publisher(topic, _message));
          }
        }
      }

      return result;
    } catch (InterruptedException | ExecutionException exception) {
      throw exception;
    }
  }

  @PostMapping(value = "/subscribeStart")
  public String startSubscribe(HttpServletRequest request, HttpServletResponse response) throws Exception {
    kafkaService.subscribeStart();
    response.setStatus(HttpStatus.OK.value());
    return "Consumer Start!!";
  }

  @PostMapping(value = "/subscribeStop")
  public String stopSubscribe(HttpServletRequest request, HttpServletResponse response) throws Exception {
    kafkaService.SubscribeStop();
    response.setStatus(HttpStatus.OK.value());
    return "Consumer Stop!!";
  }
}
