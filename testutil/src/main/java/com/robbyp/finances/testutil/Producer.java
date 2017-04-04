package com.robbyp.finances.testutil;

import java.util.concurrent.CompletableFuture;

public interface Producer<T> {
  public CompletableFuture<T> produce();
}
