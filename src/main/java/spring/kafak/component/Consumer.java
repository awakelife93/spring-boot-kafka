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
  }
}
