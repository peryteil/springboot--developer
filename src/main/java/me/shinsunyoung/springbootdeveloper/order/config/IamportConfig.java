package me.shinsunyoung.springbootdeveloper.order.config;

import com.siot.IamportRestClient.IamportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IamportConfig {

    @Bean
    public IamportClient iamportClient() {
        return new IamportClient(
                "YOUR_REST_API_KEY",     // ✅ 여기에 본인의 REST API 키 입력
                "YOUR_REST_API_SECRET"   // ✅ 여기에 본인의 REST API 시크릿 입력
        );
    }
}
