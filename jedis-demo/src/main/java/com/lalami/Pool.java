package com.lalami;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Pool {

    public static void main(String[] args) {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379);

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set("h", "nihao");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
