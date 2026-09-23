package net.codesrhereaman.jounalapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisService {
    
    private final RedisTemplate redisTemplate;

    public <T> T get(String key,Class<T> entityClass){
        try {
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(o.toString(), entityClass);
        } catch (Exception e) {
            log.error("Exception in redis cache ",e);
            return null;
        }
    }


    public void set(String key, Object o , long ttl){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonvalue = objectMapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key,jsonvalue,ttl,TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Exception in redis cache ",e);
        }
    }
}
