package net.javaguides.spirngboot.cache;

public interface Cache {
  void put(String key, String value);

  String get(String key);
}
