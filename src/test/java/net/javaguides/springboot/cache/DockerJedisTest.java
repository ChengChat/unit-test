package net.javaguides.springboot.cache;

import net.javaguides.springboot.contains.RedisContainer;
import org.junit.ClassRule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DockerJedisTest {

  @ClassRule
  public static final RedisContainer redis = new RedisContainer();

  private final RedisCache cache = new RedisCache(redis.getContainerIpAddress(), redis.getFirstMappedPort());

  @Test
  public void shouldFetchDataFromCache() throws Exception {
    // make test pass with RedisContainer

    cache.put("foo", "bar");
    String value = cache.get("foo");

    assertThat(value).isEqualTo("bar");
  }
}
