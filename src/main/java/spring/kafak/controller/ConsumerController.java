package spring.kafak.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spring.kafak.service.ConsumerService;

@RestController
@RequiredArgsConstructor
public class ConsumerController {

  private final ConsumerService consumerService;

  @PostMapping(value = "/consumerStart")
  public String start(HttpServletRequest request, HttpServletResponse response) throws Exception {
    boolean isStart = consumerService.start();

    response.setStatus(isStart ? HttpStatus.OK.value() : HttpStatus.ACCEPTED.value());
    return isStart ? "Consumer Start!!!!" : "Consumer is Already Started";
  }

  @PostMapping(value = "/consumerStop")
  public String stop(HttpServletRequest request, HttpServletResponse response) throws Exception {
    boolean isStop = consumerService.stop();

    response.setStatus(isStop ? HttpStatus.OK.value() : HttpStatus.ACCEPTED.value());
    return isStop ? "Consumer Stop!!!!" : "Consumer is Already Stopped";
  }

  @PostMapping(value = "/consumerPause")
  public String pause(HttpServletRequest request, HttpServletResponse response) throws Exception {
    boolean isPause = consumerService.pause();

    response.setStatus(isPause ? HttpStatus.OK.value() : HttpStatus.ACCEPTED.value());
    return isPause ? "Consumer Pause!!!!" : "Consumer is Already Pause";
  }
  
  @PostMapping(value = "/consumerResume")
  public String resume(HttpServletRequest request, HttpServletResponse response) throws Exception {
    boolean isResume = consumerService.resume();

    response.setStatus(isResume ? HttpStatus.OK.value() : HttpStatus.ACCEPTED.value());
    return isResume ? "Consumer Resume!!!!" : "Consumer is Already Active";
  }
}
