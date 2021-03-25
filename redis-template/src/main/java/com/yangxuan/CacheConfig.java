package com.yangxuan;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Cacheable 方法被调用前先从缓存中查询 缓存未命中则调用方法返回
 * @CacheEvict 方法执行后删除缓存  beforeInvocation=true提前执行删除缓存操作
 * @CachePut 方法执行后更新缓存 旧值被覆盖
 * @Caching 多个缓存复合操作
 */
@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * 自定义默认的key生成策略
     * @return .
     */
    @Bean
    public KeyGenerator simpleKeyGenerator() {
        return (o, method, params) -> {
            StringBuilder builder = new StringBuilder();
            builder.append(o.getClass().getSimpleName());
            builder.append(".");
            builder.append(method.getName());
            builder.append("{{{");
            for (Object param : params) {
                builder.append(param.toString());
            }
            builder.append("}}}");
            return builder.toString();
        };
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheManager redisCacheManager =
                RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defaultCacheConfig(10000))
                .withInitialCacheConfigurations(initDefaultConfigMap())
                .transactionAware()
                .build();
        return redisCacheManager;
    }

    private RedisCacheConfiguration defaultCacheConfig(Integer second) {
        Jackson2JsonRedisSerializer<Object> serializer
                = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        // 指定序列化输入的类型 类必须是非final
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(objectMapper);

        RedisCacheConfiguration config =
                RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(second))
                .serializeKeysWith(RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(serializer))
                .disableCachingNullValues();

        return config;
    }

    private Map<String, RedisCacheConfiguration> initDefaultConfigMap() {
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        configMap.put("User", this.defaultCacheConfig(1000));
        configMap.put("User1", this.defaultCacheConfig(1000));
        configMap.put("User2", this.defaultCacheConfig(1000));
        configMap.put("User3", this.defaultCacheConfig(1000));
        return configMap;
    }





}
