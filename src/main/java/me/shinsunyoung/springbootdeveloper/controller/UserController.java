package me.shinsunyoung.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.config.jwt.TokenProvider;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.dto.AddUserRequest;
import me.shinsunyoung.springbootdeveloper.dto.LoginRequest;
import me.shinsunyoung.springbootdeveloper.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // 1. 사용자 검증
        User user = userService.login(request.getEmail(), request.getPassword());

        // 2. 토큰 생성
        String token = tokenProvider.generateToken(user);

        // 3. 토큰 반환
        return ResponseEntity.ok(Map.of("accessToken", token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AddUserRequest request) {
        userService.save(request);
        return ResponseEntity.ok("회원가입 완료");
    }
}
