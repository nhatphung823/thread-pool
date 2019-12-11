package com.ecpay.concurrent;

import lombok.Getter;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * Created by Joe on December, 10 2019 .
 */
public abstract class WorkerCallable<T> implements Callable<T> {
  private static final AtomicInteger index = new AtomicInteger(0);
  private static final Logger logger = Logger.getLogger(WorkerCallable.class.getName());

  @Getter
  private int id;

  @Getter
  private Date initializedDate;

  @Getter
  private Date completedDate;

  @Getter
  private long duration;

  WorkerCallable() {
    id = index.incrementAndGet();
    initializedDate = new Date();
  }

  @Override
  public T call() throws Exception {
    long ctm = System.currentTimeMillis();
    logger.info(Thread.currentThread().getName() + "-callable(" + id + ")" + " starting");
    T t = doSomething();
    duration = System.currentTimeMillis() - ctm;
    completedDate = new Date();
    logger.info(Thread.currentThread().getName() + "-callable(" + id + ")" + " done");
    return t;
  }

  abstract T doSomething() throws Exception;

  public static int getIndex(){
    return index.get();
  }

  public static void resetIndex(){
    index.set(0);
  }
}
