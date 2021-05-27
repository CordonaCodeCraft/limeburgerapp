package com.limeburger.bootstrap.initializers;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public abstract class Initializer {

  protected static final String IMAGES_PATH = "src/main/resources/static/images/";

  protected static <T> Set<T> getRandomObjects(List<T> source) {
    Set<T> collection = new HashSet<>();
    for (int i = 0; i < getRandomInt(1, source.size()); i++) {
      collection.add(source.get(getRandomInt(0, source.size())));
    }
    return collection;
  }

  private static int getRandomInt(final int min, final int max) {
    final Random random = new Random();
    return random.ints(min, max).findFirst().getAsInt();
  }
}
