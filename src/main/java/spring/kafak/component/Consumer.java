package spring.kafak.component;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Consumer {

  @KafkaListener(id = "tokenContainer", topics = "tokenStorage", groupId = "${kafka.groupId}", autoStartup = "false")
  public void listenMessage(String message) throws IOException {
    System.out.println("Received Message in group foo: " + message);
    // todo: node-sqs-message-action 프로젝트 리팩토링 & 여기서 node-sqs-message-action로 전송하기.
  }
}
