package com.yangxuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/redis")
    public String redis() {
        String key = "redis-string-key";
        String value = String.valueOf(System.currentTimeMillis() + 1);
        stringRedisTemplate.opsForValue().set(key, value);
        return stringRedisTemplate.opsForValue().get(key);
    }

    @GetMapping("/redisList")
    public String redisList() {
        String key = "redis-list-key";
        String value = String.valueOf(System.currentTimeMillis() + 1);
        stringRedisTemplate.opsForList().leftPush(key, value);
        stringRedisTemplate.opsForList().leftPush(key, value);
        stringRedisTemplate.opsForList().leftPush(key, value);
        return stringRedisTemplate.opsForList().leftPop(key);
    }

    @GetMapping("/redisSet")
    public String redisSet() {
        String key = "redis-set-key";
        String value = String.valueOf(System.currentTimeMillis() + 1);
        stringRedisTemplate.opsForSet().add(key, value);
        stringRedisTemplate.opsForSet().add(key, value + "-1");
        stringRedisTemplate.opsForSet().add(key, value + "-2");
        return stringRedisTemplate.opsForSet().pop(key);
    }

    @GetMapping("/redisHash")
    public String redisHash() {
        String key = "redis-hash-key";
        String hashKey = String.valueOf(System.currentTimeMillis() + 1);
        String value = String.valueOf(System.currentTimeMillis() + 2);
        stringRedisTemplate.opsForHash().put(key, hashKey, value);

        return String.valueOf(stringRedisTemplate.opsForHash().get(key, hashKey));
    }

    @GetMapping("/redisZSet")
    public String redisZSet() {
        String key = "redis-zset-key";
        String hashKey = String.valueOf(System.currentTimeMillis() + 1);
        long value = System.currentTimeMillis();
        stringRedisTemplate.opsForZSet().add(key, hashKey, value);
        // 统计元素个数
        return String.valueOf(stringRedisTemplate.opsForZSet().zCard(key));
    }

}
