package net.javaguides.springboot.cache;

import net.javaguides.springboot.contains.RedisContainer;
import org.junit.*;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import static org.assertj.core.api.Assertions.assertThat;

public class DockerRedisTemplateTest {

  @ClassRule
  public static final RedisContainer redis = new RedisContainer();


  private static final StringRedisTemplate redisTemplate = new StringRedisTemplate();

  private static JedisConnectionFactory connectionFactory;

  @BeforeClass
  public static void before() {
    connectionFactory = new JedisConnectionFactory(
        new RedisStandaloneConfiguration(
            redis.getContainerIpAddress(),
            redis.getFirstMappedPort()));

    redisTemplate.setConnectionFactory(connectionFactory);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
    redisTemplate.afterPropertiesSet();
  }

  @AfterClass
  public static void after() {
    connectionFactory.destroy();
  }


  @Test
  public void shouldFetchDataFromCache() {

    redisTemplate.opsForValue().set("foo", "bar");
    String value = redisTemplate.opsForValue().get("foo");

    assertThat(value).isEqualTo("bar");
  }

  @Test
  public void shouldPutDataFromCache() {
    redisTemplate.opsForHash().put("zhans", "li", "3");
    String value = (String) redisTemplate.opsForHash().get("zhans", "li");
    assertThat(value).isEqualTo("3");
  }
}
