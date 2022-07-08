package com.awakelife93.kafka.controller;

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

import com.awakelife93.kafka.service.ProducerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProducerController {
  
  private final ProducerService producerService;

  private final String topic = "tokenStorage";

  @PostMapping(value = "/sendSingleMessage")
  public String sendSingleMessage(HttpServletRequest request, HttpServletResponse response,
      @RequestBody Map<String, Object> body) throws InterruptedException, ExecutionException {
    try {
      String topic = body.get("topic") == null ? this.topic : (String) body.get("topic");
      String message = (String) body.get("message");
      String result = producerService.sendMessage(topic, message);

      response.setStatus(HttpStatus.OK.value());
      return result;
    } catch (InterruptedException | ExecutionException exception) {
      throw exception;
    }
  }

  @PostMapping(value = "/sendMultipleMessages")
  public List<String> sendMultipleMessages(HttpServletRequest request, HttpServletResponse response,
      @RequestBody Map<String, Object> body) throws InterruptedException, ExecutionException {
    try {
      String topic = body.get("topic") == null ? this.topic : (String) body.get("topic");
      List<String> result = new ArrayList<String>();

      if (body.get("messages") instanceof ArrayList<?>) {
        for (Object message : (ArrayList<?>) body.get("messages")) {
          if (message instanceof String) {
            String _message = (String) message;
            result.add(producerService.sendMessage(topic, _message));
          }
        }
      }

      return result;
    } catch (InterruptedException | ExecutionException exception) {
      throw exception;
    }
  }
}
