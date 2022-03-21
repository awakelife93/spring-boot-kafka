package spring.kafak.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class HttpUtils {

  public static HttpURLConnection generateRequest(String url, String method, String token) throws IOException {
    URL endpoint = new URL(url);
    HttpURLConnection connection = (HttpURLConnection) endpoint.openConnection();

    // https://stackoverflow.com/questions/25163131/httpurlconnection-invalid-http-method-patch
    if (method.equals("PATCH") || method.equals("PUT")) {
      connection.setRequestProperty("X-HTTP-Method-Override", method);
      method = "POST";
    }

    connection.setRequestMethod(method);
    connection.setDoInput(true);
    connection.setDoOutput(true);
    connection.setRequestProperty("Cache-Control", "no-cache");
    connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
    connection.setRequestProperty("authorization", token);
    return connection;
  }

  public static String generateResponse(HttpURLConnection request) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
    StringBuffer stringBuffer = new StringBuffer();
    String inputLine;

    while ((inputLine = bufferedReader.readLine()) != null) {
      stringBuffer.append(inputLine);
    }
    bufferedReader.close();

    String _response = stringBuffer.toString();
    return _response;
  }

  public static String send(HashMap<String, Object> apiResponse, HttpServletResponse response) {
    int status = (int) apiResponse.get("status");
    String jsonString = (String) apiResponse.get("data");

    response.setStatus(status);
    return jsonString;
  }
}