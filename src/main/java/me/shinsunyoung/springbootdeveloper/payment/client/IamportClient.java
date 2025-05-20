package me.shinsunyoung.springbootdeveloper.payment.client;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class IamportClient {

    private final String apiKey = "포트원_API_KEY";       // application.properties에서 주입해도 됨
    private final String apiSecret = "포트원_API_SECRET";
    private String accessToken;
    private final RestTemplate restTemplate = new RestTemplate();

    public String getToken() {
        String url = "https://api.iamport.kr/users/getToken";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = Map.of(
                "imp_key", apiKey,
                "imp_secret", apiSecret
        );

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);
        Map resBody = response.getBody();
        Map responseMap = (Map) resBody.get("response");
        accessToken = (String) responseMap.get("access_token");
        return accessToken;
    }

    public Map getPaymentByImpUid(String impUid) {
        if (accessToken == null) getToken();

        String url = "https://api.iamport.kr/payments/" + impUid;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        Map resBody = response.getBody();
        return (Map) resBody.get("response");
    }
}
