package spring.kafak.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
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
  public String publishKafka(HttpServletRequest request, HttpServletResponse response,
      @RequestBody Map<String, Object> body) throws Exception {
    return "hi";
  }

  @GetMapping(value = "/subscribe")
  public void subscribeKafka(HttpServletRequest request, HttpServletResponse response) throws Exception {
    // todo: 개발 해야함
  }
}
