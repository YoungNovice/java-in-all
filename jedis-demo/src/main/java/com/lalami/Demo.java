package com.lalami;

import redis.clients.jedis.Jedis;

public class Demo {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println(jedis.set("hello", "world"));
        String hello = jedis.get("hello");
        System.out.println(hello);

        System.out.println(jedis.incr("counter"));

        jedis.hset("myhash", "f1", "v1");
        jedis.hset("myhash", "f2", "v2");

        System.out.println(jedis.hgetAll("myhash"));
    }
}
