package com.redis.demo;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class RedisDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisDemoApplication.class, args);
	}

	@Bean
	public RedisClient getRedisTemplate() {
		RedisURI uri = RedisURI.Builder
				.redis("redis-10875.c251.east-us-mz.azure.redns.redis-cloud.com", 10875)
				.withAuthentication("default", "X5hpEvBuHIvpQzOZk1tYOxFR5OLJdkAS")
				.build();
		RedisClient client = RedisClient.create(uri);
		return client;
	}

}
