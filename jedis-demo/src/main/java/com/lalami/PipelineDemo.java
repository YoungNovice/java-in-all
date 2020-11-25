package com.lalami;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class PipelineDemo {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.select(3);
       /* for (int i = 0; i < 100; i++) {
          Pipeline pipeline = jedis.pipelined();
            for (int j = 0; j < 100; j++) {
                pipeline.hset("hashkey" + j, "pipelinefield" + j, "pipevalue" + j);
            }
            pipeline.syncAndReturnAll();
        }*/

        jedis.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("message = " + message);
                System.out.println("nihaosd");
            }
        }, "channel1");

    }
}
