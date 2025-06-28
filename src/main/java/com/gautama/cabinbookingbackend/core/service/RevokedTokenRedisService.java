package com.gautama.cabinbookingbackend.core.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RevokedTokenRedisService {

    private final RedisTemplate<String, String> redisTemplate;

    public RevokedTokenRedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void revokeToken(String token, long expirationMillis) {
        redisTemplate.opsForValue().set(token, "revoked", Duration.ofMillis(expirationMillis));
    }

    public boolean isTokenRevoked(String token) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(token));
    }
}

