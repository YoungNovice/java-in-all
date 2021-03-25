package com.yangxuan.controller;

import com.yangxuan.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/redis1/user")
    public User user() {
        User user = new User();
        user.setId(1);
        user.setName("yangxuan");
        redisTemplate.opsForValue().set("1", user);

        return (User) redisTemplate.opsForValue().get("1");
    }
}
