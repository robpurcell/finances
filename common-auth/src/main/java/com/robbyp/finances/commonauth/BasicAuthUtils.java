package com.robbyp.finances.commonauth;

import com.robbyp.finances.common.customers.UserCredentials;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

public class BasicAuthUtils {

  public static HttpHeaders basicAuthHeaders(UserCredentials userCredentials) {
    return new HttpHeaders() {
      {
        String auth = userCredentials.getEmail() + ":" + userCredentials.getPassword();
        byte[] encodedAuth = Base64.encodeBase64(
          auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        set("Authorization", authHeader);
      }
    };
  }

  public static <T> T doBasicAuthenticatedRequest(RestTemplate restTemplate, String url, HttpMethod httpMethod,
                                                  Class<T> responseType, UserCredentials userCredentials) {
    return doBasicAuthenticatedRequest(restTemplate, url, httpMethod, responseType, null, userCredentials);
  }

  public static <T> T doBasicAuthenticatedRequest(RestTemplate restTemplate, String url, HttpMethod httpMethod,
                                                  Class<T> responseType, Object requestObject,
                                                  UserCredentials userCredentials) {
    HttpEntity httpEntity;
    if (requestObject != null) {
      httpEntity = new HttpEntity<>(requestObject, BasicAuthUtils.basicAuthHeaders(userCredentials));
    } else {
      httpEntity = new HttpEntity(BasicAuthUtils.basicAuthHeaders(userCredentials));
    }

    ResponseEntity<T> responseEntity = restTemplate.exchange(url,
                                                             httpMethod,
                                                             httpEntity,
                                                             responseType);

    return responseEntity.getBody();
  }
}
