package spring.kafak.controller;

import java.util.Map;

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
 * todo2: node-sqs-message-action project랑 브로드캐스팅 해볼 것.
 */
@RestController
@RequiredArgsConstructor
public class KafkaController {

  private final KafkaService kafkaService;

  @PostMapping(value = "/publish")
  public String publish(HttpServletRequest request, HttpServletResponse response,
      @RequestBody Map<String, Object> body) throws Exception {

    String topic = (String) body.get("topic");
    // todo: 단순 string이나, string 배열일 때 각각 로직 개발하자.
    String message = (String) body.get("messages");
    String result = kafkaService.publisher(topic, message);

    response.setStatus(HttpStatus.OK.value());
    return result;
  }

  @PostMapping(value = "/subscribeStart")
  public void startSubscribe(HttpServletRequest request, HttpServletResponse response) throws Exception {
    kafkaService.subscribeStart();
  }

  @PostMapping(value = "/subscribeStop")
  public void stopSubscribe(HttpServletRequest request, HttpServletResponse response) throws Exception {
    kafkaService.SubscribeStop();
  }
}
