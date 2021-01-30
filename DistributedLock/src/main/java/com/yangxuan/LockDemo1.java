package com.yangxuan;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class LockDemo1 {


    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 如果没有执行到delete (会造成死锁)
     *
     * 解决方式 设置锁过期时间
     * @see {demo2}
     */
    public void demo1() {
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", "100");
        if (lock) {
            // 加锁成功 执行业务

            redisTemplate.delete("lock");
        } else {
            // 加锁失败 休眠100ms重试
            // 自旋
        }
    }

    /**
     * 如果没有执行expire(异常 断电等等) 则过期时间还是会设置不上
     *
     * 解决方式 过期时间和加锁用原子操作
     * @see {demo3}
     */
    public void demo2() {
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", "100");
        if (lock) {
            // 加锁成功 执行业务

            redisTemplate.expire("lock", 30, TimeUnit.SECONDS);
            redisTemplate.delete("lock");
        } else {
            // 加锁失败 休眠100ms重试
            // 自旋
        }
    }

    /**
     * 假设加锁成功 有了uuid 但是过期了， 那么别人就可以设置值了， 如果我们直接delete 有可能删除的是别人加的锁
     *
     * 解决方式 比较lockvalue 只让删除自己加的锁
     * @see {demo4}
     */
    public void demo3() {
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", "100", 300, TimeUnit.SECONDS);
        if (lock) {
            // 加锁成功 执行业务

            redisTemplate.delete("lock");
        } else {
            // 加锁失败 休眠100ms重试
            // 自旋
        }
    }


    /**
     * 只让删除自己加的锁 就算我们通过代码只这么写了只删除自己的锁， 由于获取值再比较不是原子的 还是会有问题
     *
     * 解决方式 同lua脚本原子删除锁
     * @see {demo5}
     */
    public void demo4() {
        String lockValue = "uuid";
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", lockValue, 300, TimeUnit.SECONDS);
        if (lock) {
            // 加锁成功 执行业务

            // 只能删除自己加的锁
            String lockValue1 = redisTemplate.opsForValue().get("lock");
            if (lockValue.equals(lockValue1)) {
                redisTemplate.delete("lock");
            }
        } else {
            // 加锁失败 休眠100ms重试
            // 自旋
        }
    }


    /**
     * 假设加锁成功 有了uuid 但是过期了， 那么别人就可以设置值了， 如果我们直接delete 有可能删除的是别人加的锁
     *
     * 解决方式 过期时间和加锁用原子操作
     *
     * 最后一切的一切 我们都不需要自己做 用 Redisson 别人都帮忙写好了
     */
    public void demo6() {
        String lockValue = "uuid";
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", lockValue, 300, TimeUnit.SECONDS);
        if (lock) {
            // 加锁成功 执行业务

            // 只能删除自己加的锁
            String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            // todo 执行lua脚本
        } else {
            // 加锁失败 休眠100ms重试
            // 自旋
        }
    }

}
