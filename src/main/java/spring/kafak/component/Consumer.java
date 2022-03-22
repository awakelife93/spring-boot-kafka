package spring.kafak.component;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.HashMap;

import org.springframework.http.HttpMethod;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import spring.kafak.component.utils.ConvertUtils;
import spring.kafak.component.utils.HttpUtils;

@Component
@RequiredArgsConstructor
public class Consumer {

  @KafkaListener(id = "tokenContainer", topics = "tokenStorage", groupId = "${kafka.groupId}", autoStartup = "false")
  public void listenMessage(String message) throws IOException {
    System.out.println("Received Message : " + message);
    sendMessageToActionServer(message);
  }

  private void sendMessageToActionServer(String message) {
    try {
      System.out.println("Send Message To Action Server : " + message);

      // todo: domain 관리 어떻게 할지 고민
      final String actionServerAddress = "http://localhost:5005/deleteUserToken";
      HashMap<String, Object> response = new HashMap<String, Object>();
      HashMap<String, Object> body = new HashMap<String, Object>();
      body.put("params", message);

      String jsonParams = ConvertUtils.objectToJsonString(body);
      HttpURLConnection request = HttpUtils.generateRequest(actionServerAddress, HttpMethod.POST.name());

      DataOutputStream dataOutputStream = new DataOutputStream(request.getOutputStream());
      dataOutputStream.write(jsonParams.getBytes("UTF-8"));
      dataOutputStream.flush();
      dataOutputStream.close();

      response.put("status", request.getResponseCode());
      response.put("data", HttpUtils.generateResponse(request));

      System.out.println("============>" + response.get("data"));
    } catch (UnsupportedEncodingException exception) {
      // todo: exception 하나 만들기
      System.out.println("UnsupportedEncodingException " + exception);
    } catch (IOException exception) {
      // todo: exception 하나 만들기
      System.out.println("IOException " + exception);
    }

  }
}
