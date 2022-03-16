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

@RestController("KafkaController")
public class KafkaController {

  @Resource(name = "ConsumerService")
  private ConsumerService consumerService;

  @Resource(name = "ProducerService")
  private ProducerService producerService;

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
