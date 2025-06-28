package com.gautama.cabinbookingbackend.core.service;

import com.gautama.cabinbookingbackend.api.config.JwtUtil;
import com.gautama.cabinbookingbackend.core.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {
    private final JwtUtil jwtUtil;
    private final RevokedTokenRedisService revokedTokenRedisService;

    public void logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new BadCredentialsException("Неверный токен: отсутствует Bearer");
        }

        String token = authHeader.substring(7);

        try {
            jwtUtil.extractUsername(token);
        } catch (Exception e) {
            throw new BadCredentialsException("Токен невалиден или истёк");
        }

        if (revokedTokenRedisService.isTokenRevoked(token)) {
            throw new BadCredentialsException("Токен уже отозван");
        }

        revokedTokenRedisService.revokeToken(token, jwtUtil.getExpirationTimeMs());
    }

}
