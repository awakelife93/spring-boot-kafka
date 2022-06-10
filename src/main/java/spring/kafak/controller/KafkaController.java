package spring.kafak.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spring.kafak.service.KafkaService;

@RestController
@RequiredArgsConstructor
public class KafkaController {

  private final KafkaService kafkaService;

  @PostMapping(value = "/consumerStart")
  public String startSubscribe(HttpServletRequest request, HttpServletResponse response) throws Exception {
    kafkaService.consumerStart();
    response.setStatus(HttpStatus.OK.value());
    return "Consumer Start!!";
  }

  @PostMapping(value = "/consumerStop")
  public String stopSubscribe(HttpServletRequest request, HttpServletResponse response) throws Exception {
    kafkaService.consumerStop();
    response.setStatus(HttpStatus.OK.value());
    return "Consumer Stop!!";
  }
}
