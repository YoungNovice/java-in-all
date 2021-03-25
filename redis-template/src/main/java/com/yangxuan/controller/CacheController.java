package com.yangxuan.controller;

import com.yangxuan.entity.User;
import org.springframework.cache.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Cacheable(cacheNames = "product",
 *             key = "#root.methodName+'['+#id+']'",
 *             condition = "#a0>10",
 *             unless = "#a0==11")
 * Cacheable的SPEL表达式与condition使用样例
 */
@RestController
@CacheConfig(cacheNames = {"User"})
public class CacheController {

    // http://localhost:8080/redis/findUserById/1

    // @Cacheable(key = "#id") ok
    // @Cacheable(cacheNames = "User") ok
    // @Cacheable(cacheNames = "User", key = "#id") ok
    // 如果没有指定key会用方法上的参数作为key 一个方法有多个参数他的key长什么样子 ？？？ SimpleKey [1, yangxuan]
    @Cacheable(keyGenerator = "simpleKeyGenerator")


    @GetMapping("/redis/findUserById/{id}")
    public User findUserById(@PathVariable("id") Integer id) {
        User user = new User();
        user.setId(id);
        user.setName("yangxuan id=" + id + " 查询时间戳 " + System.currentTimeMillis());
        System.out.println("数据库查询用户id = " + id + " ,name = " + user.getName());
        return user;
    }

    // http://localhost:8080/redis/updateUserById/1
    @CachePut(cacheNames = "User", key = "#id")
    @GetMapping("/redis/updateUserById/{id}")
    public User updateUserById(@PathVariable("id") Integer id) {
        User user = new User();
        user.setId(id);
        user.setName("yangxuan id=" + id + " 修改时间戳 " + System.currentTimeMillis());
        System.out.println("数据库更新用户id = " + id + " ,name = " + user.getName());
        return user;
    }

    // http://localhost:8080/redis/deleteUserById/1

    @CacheEvict(value = "User", allEntries = true, beforeInvocation = true)
    @GetMapping("/redis/deleteUserById/{id}")
    public User deleteUserById(@PathVariable("id") Integer id) {
        User user = new User();
        user.setId(id);
        user.setName("yangxuan id=" + id + " 删除时间戳 " + System.currentTimeMillis());
        System.out.println("数据库删除用户id = " + id + " ,name = " + user.getName());
        return user;
    }

    // http://localhost:8080/redis/updateUserByName/海豚老师
    @Caching(
            cacheable = {@Cacheable(value = "User1", key = "#name")},
            put = {
                    @CachePut(value = "User2", key = "#result.id"),
                    @CachePut(value = "User3", key = "#result.name")
            }
    )
    @GetMapping("/redis/updateUserByName/{name}")
    public User updateUserByName(@PathVariable("name") String name) {
        User user = new User();
        user.setId(100);
        user.setName("yangxuan id=" + 100 + " 更新名称 " + name);
        System.out.println("数据库更新用户id = " + 100 + " ,name = " + user.getName());
        return user;
    }



 }
