package net.javaguides.springboot.contains;

import org.testcontainers.containers.GenericContainer;

public class RedisContainer extends GenericContainer<RedisContainer> {
  public RedisContainer() {
    super("redis:5.0.14-alpine");
  }

  @Override
  protected void configure() {
    super.configure();

    withExposedPorts(6379);
  }
}
