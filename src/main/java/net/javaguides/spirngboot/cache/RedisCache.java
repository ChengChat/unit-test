package net.javaguides.spirngboot.cache;

import redis.clients.jedis.Jedis;

public class RedisCache implements Cache {

  private final Jedis jedis;
  public RedisCache(String ip, int port) {
    jedis = new Jedis(ip, port);
  }

  @Override
  public void put(String key, String value) {
    jedis.set(key, value);
  }

  @Override
  public String get(String key) {
    return jedis.get(key);
  }
}
