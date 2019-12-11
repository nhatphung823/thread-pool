package com.ecpay.concurrent;

/**
 * Created by Joe on December, 09 2019 .
 */
public interface IBuilder {

  IBuilder minThreads(int minThreads);

  IBuilder maxThreads(int maxThreads);

  ThreadPool build();
}
