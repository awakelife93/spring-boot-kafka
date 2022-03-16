package spring.kafak.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import spring.kafak.service.ConsumerService;
import spring.kafak.service.ProducerService;

/**
 * todo1 : kafka 써보면서 재밌을만한 로직들 구상하면서 endpoint 붙여보기
 * todo2: node-sqs-message-action project랑 브로드캐스팅 해볼 것.
 */
@RestController("KafkaController")
public class KafkaController {

  @Resource(name = "ConsumerService")
  private final ConsumerService consumerService;

  @Resource(name = "ProducerService")
  private final ProducerService producerService;

  KafkaController(ConsumerService consumerService, ProducerService producerService) {
    this.consumerService = consumerService;
    this.producerService = producerService;
  }

  @PostMapping(value = "/publish")
  public void publishKafka(HttpServletRequest request, HttpServletResponse response,
      @RequestBody Map<String, Object> body) throws Exception {
    // todo: 개발 해야함
  }

  @GetMapping(value = "/subscribe")
  public void subscribeKafka(HttpServletRequest request, HttpServletResponse response) throws Exception {
    // todo: 개발 해야함
  }
}
