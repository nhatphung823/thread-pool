package com.ecpay.concurrent;

/**
 * Created by Joe on December, 09 2019 .
 */
public class ThreadPoolBuilder implements IBuilder {
  private int minThreads;
  private int maxThreads;

  public IBuilder minThreads(int minThreads) {
    this.minThreads = minThreads;
    return this;
  }

  @Override
  public IBuilder maxThreads(int maxThreads) {
    this.maxThreads = maxThreads;
    return this;
  }

  @Override
  public ThreadPool build() {
    return new ThreadPool(minThreads, maxThreads);
  }
}
