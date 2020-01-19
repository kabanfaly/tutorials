package com.kaba.tutorials.redis;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;

public class RedisTestConfig {
  public static final int redisPort = 6370;
  public static RedisServer redisServer;

  @BeforeAll
  public static void startServer(){
    redisServer = new RedisServerBuilder().setting("maxheap 512Mb").port(redisPort).build();
    redisServer.start();
  }

  @AfterAll
  public static void stopServer(){
    redisServer.stop();
  }
}
