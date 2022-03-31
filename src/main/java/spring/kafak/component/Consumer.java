package spring.kafak.component;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lombok.RequiredArgsConstructor;
import spring.kafak.component.utils.HttpUtils;

@Component
@RequiredArgsConstructor
public class Consumer {
  private final HttpUtils httpUtils;

  @KafkaListener(id = "tokenContainer", topics = "tokenStorage", groupId = "${kafka.groupId}", autoStartup = "false")
  public void listenMessage(String message) {
    System.out.println("Received Message : " + message);
    sendMessageToActionServer(message);
  }

  private void sendMessageToActionServer(String message) {
    System.out.println("Send Message To Action Server : " + message);
    Map<String, Object> response = new HashMap<String, Object>();

    // todo: domain 관리 어떻게 할지 고민
    final String actionServerAddress = "http://localhost:5005/deleteUserToken";
    MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

    parameters.add("params", message);

    response = httpUtils.request(HttpMethod.POST, actionServerAddress, parameters);
    System.out.println("============>" + response.get("data"));
  }
}
