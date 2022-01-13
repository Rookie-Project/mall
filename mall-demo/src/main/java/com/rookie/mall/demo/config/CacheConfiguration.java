package com.rookie.mall.demo.config;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: chen
 * @date: 2022/1/7
 * @description: redis初始化
 **/
@Configuration
@EnableCaching
public class CacheConfiguration {

    @Bean
    public CacheManager cacheManager(@Autowired RedisConnectionFactory factory) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(factory);
        //默认策略缓存永久
        return new RedisCacheManager(redisCacheWriter, this.getRedisCacheConfigurationWithTtl(0), this.getRedisCacheConfigurationMap());
    }

    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap () {
        Map<String, RedisCacheConfiguration> map = new HashMap<>();
        // 有效时间1分钟
        map.put("authCode",this.getRedisCacheConfigurationWithTtl(1 * 60));

        // 有效时间30分钟
        map.put("m30",this.getRedisCacheConfigurationWithTtl(30 * 60));

        return map;
    }

    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
        //设置CacheManager的值序列化方式为json序列化
        RedisSerializer<Object> jsonSerializer = new GenericJackson2JsonRedisSerializer();
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair
                .fromSerializer(jsonSerializer);

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(pair)   //设置redis的序列话方式
                .entryTtl(Duration.ofSeconds(seconds))
                //.disableKeyPrefix()          //禁止该缓冲使用redis前缀
                .disableCachingNullValues(); //禁止对null值进行缓冲
        return redisCacheConfiguration;
    }
}
