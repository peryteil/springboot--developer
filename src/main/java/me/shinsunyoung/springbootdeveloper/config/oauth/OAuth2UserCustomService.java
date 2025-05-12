package me.shinsunyoung.springbootdeveloper.config.oauth;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class OAuth2UserCustomService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email = null;
        String name = null;
        String providerId = null;

        if ("naver".equals(registrationId)) {
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");
            email = (String) response.get("email");
            name = (String) response.get("name");
            providerId = (String) response.get("id");
        } else if ("kakao".equals(registrationId)) {
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            email = (String) kakaoAccount.get("email");
            name = (String) profile.get("nickname");
            providerId = attributes.get("id").toString();
        } else {
            email = (String) attributes.get("email");
            name = (String) attributes.get("name");
            providerId = attributes.get("sub").toString();
        }

        // hybrid 전략 적용
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            user = userRepository.findByProviderAndProviderId(registrationId, providerId).orElse(null);
        }

        if (user == null) {
            user = User.builder()
                    .email(email)
                    .nickname(name)
                    .provider(registrationId)
                    .providerId(providerId)
                    .build();
        } else if (!user.getEmail().equals(email)) {
            // 이메일 충돌 또는 연결 유도 메시지 필요
            // 여기서 사용자 안내 로직 (ex. 리다이렉트 페이지 or 경고 로그)
        }

        userRepository.save(user);
        return oAuth2User;
    }



    private void saveOrUpdate(String email, String name) {
        User user = userRepository.findByEmail(email)
                .map(entity -> entity.update(name))
                .orElse(User.builder()
                        .email(email)
                        .nickname(name)
                        .build());

        userRepository.save(user);
    }
}
