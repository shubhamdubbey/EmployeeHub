package com.cts.hr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        // ✅ Use host/port from environment (Spring Boot picks up from application.yml)
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName(System.getenv().getOrDefault("SPRING_REDIS_HOST", "redis"));
        redisConfig.setPort(Integer.parseInt(System.getenv().getOrDefault("SPRING_REDIS_PORT", "6379")));
        // If you ever add a password, uncomment this:
        // redisConfig.setPassword("yourpassword");
        return new LettuceConnectionFactory(redisConfig);
    }

    /**
     * When we manually add data to our Redis cache, we want to use JSON serialization
     * so that the data is human-readable and interoperable with other systems. But when we use annotations then at that time, redis template dont comes into picture.
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
    /**
     * the @Cacheable annotation doesn’t use your custom RedisTemplate by default.
     * Instead, Spring Boot’s cache abstraction uses its own internal Redis serializer, which by default is:JdkSerializationRedisSerializer
     *
     * So below is the code to configure the cache manager to use your custom RedisTemplate i.e. to use GenericJackson2JsonRedisSerializer
     *
     *  // 🔥 This is what changes how @Cacheable stores data
     *     @Bean
     *     public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
     *         RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
     *             .entryTtl(Duration.ofHours(1)) // optional TTL
     *             .disableCachingNullValues()
     *             .serializeKeysWith(
     *                 RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
     *             .serializeValuesWith(
     *                 RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
     *
     *         return RedisCacheManager.builder(redisConnectionFactory)
     *             .cacheDefaults(cacheConfig)
     *             .build();
     *     }
     */
}