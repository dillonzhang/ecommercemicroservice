package com.bdtv.ms.ecom.order.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
	@Value("${spring.redis.host}")
	private String redisHost;

	@Value("${spring.redis.port}")
	private String redisPort;

	@Value("${spring.redis.timeout}")
	private String timeout;

	@SuppressWarnings("rawtypes")
	@Bean
	public RedisTemplate redisTemplate() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setUsePool(true);
		jedisConnectionFactory.setHostName(redisHost);
		jedisConnectionFactory.setPort(Integer.parseInt(redisPort));
		jedisConnectionFactory.setTimeout(Integer.parseInt(timeout));
		jedisConnectionFactory.afterPropertiesSet();

		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory);
		redisTemplate.afterPropertiesSet();
		setSerializer(redisTemplate);
		return redisTemplate;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	private void setSerializer(RedisTemplate<String, Object> template) {
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		template.setKeySerializer(stringRedisSerializer);
		template.setHashKeySerializer(stringRedisSerializer);

		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(
				Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
	}

	@Bean
	public CacheManager cacheManager(
			RedisConnectionFactory redisConnectionFactory) {
		RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder
				.fromConnectionFactory(redisConnectionFactory);
		return builder.build();
	}

	// @Bean
	// public KeyGenerator keyGenerator() {
	// return new KeyGenerator() {
	// // com.frog.mvcdemo.controller.FrogTestController-show-[params]
	// @Override
	// public Object generate(Object target, Method method,
	// Object... params) {
	// StringBuffer sb = new StringBuffer();
	// sb.append(target.getClass().getName());
	// sb.append("-");
	// sb.append(method.getName());
	// sb.append("-");
	// for (Object param : params) {
	// sb.append(param.toString());
	// }
	// return sb.toString();
	// }
	// };
	// }
}
