package me.shinsunyoung.springbootdeveloper.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service

public class TokenProvider {

    private final JwtProperties jwtProperties;

    @Value("${security.jwt.token.expire-length:14400000}") // 기본값 4시간 = 4 * 60 * 60 * 1000
    private long validityInMilliseconds; // milliseconds

    public String generateToken(User user) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMilliseconds);
        return makeToken(now, expiry, user);
    }

    private String makeToken(Date now, Date expiry, User user) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .setSubject(user.getEmail())
                .claim("id", user.getId())
                .claim("name", user.getName())  // 또는 nickname
                .claim("nickname", user.getNickname())
                .claim("membership", user.getMembership())
                .claim("role", user.getRole())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    public boolean validToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Long userId = claims.get("id", Long.class);
        String email = claims.getSubject();
        String nickname = claims.get("nickname", String.class);
        String membership = claims.get("membership", String.class);

        Set<SimpleGrantedAuthority> authorities = Collections.singleton(
                new SimpleGrantedAuthority("ROLE_USER")
        );

        CustomUserDetails userDetails = new CustomUserDetails(userId, email, nickname, membership, authorities);

        return new UsernamePasswordAuthenticationToken(userDetails, token, authorities);
    }


    public Long getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("id", Long.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }
}

