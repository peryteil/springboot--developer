package me.shinsunyoung.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.dto.LoginRequest;
import me.shinsunyoung.springbootdeveloper.dto.SignupRequest;
import me.shinsunyoung.springbootdeveloper.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // ✅ 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(404).body("사용자 없음");
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("비밀번호 불일치");
        }

        return ResponseEntity.ok("로그인 성공");
    }

    // ✅ 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.status(400).body("이미 가입된 이메일입니다.");
        }

        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setNickname(request.getName());

        userRepository.save(newUser);
        return ResponseEntity.ok("회원가입 성공");
    }
}
