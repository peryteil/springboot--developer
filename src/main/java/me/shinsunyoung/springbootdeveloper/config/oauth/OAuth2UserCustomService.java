package me.shinsunyoung.springbootdeveloper.config.oauth;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email = null;
        String nickname = null;
        String providerId = null;

        if ("naver".equals(registrationId)) {
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");
            email = (String) response.get("email");
            nickname = (String) response.get("nickname");
            providerId = (String) response.get("id");
        } else if ("kakao".equals(registrationId)) {
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            email = (String) kakaoAccount.get("email");
            nickname = (String) profile.get("nickname");
            providerId = attributes.get("id").toString();
        } else {
            email = (String) attributes.get("email");
            nickname = (String) attributes.get("nickname");
            providerId = attributes.get("sub").toString();
        }

        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            user = userRepository.findByProviderAndProviderId(registrationId, providerId).orElse(null);
        }

        if (user == null) {
            String encodedPassword = passwordEncoder.encode("oauth2user-default-password"); // 더미 비밀번호 암호화
            user = User.builder()
                    .email(email)
                    .nickname(nickname)
                    .password(encodedPassword) // 암호화된 비밀번호 저장
                    .provider(registrationId)
                    .providerId(providerId)
                    .build();
        } else if (!user.getEmail().equals(email)) {
            // 이메일 충돌 또는 연결 유도 메시지 필요
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
                        .password(passwordEncoder.encode("oauth2user")) // 여기도 동일하게 적용
                        .build());

        userRepository.save(user);
    }
}
