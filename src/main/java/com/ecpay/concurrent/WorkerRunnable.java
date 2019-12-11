package com.ecpay.concurrent;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * Created by Joe on December, 10 2019 .
 */
@ToString
public abstract class WorkerRunnable implements Runnable {
  private static final AtomicInteger index = new AtomicInteger(0);
  private static final Logger logger = Logger.getLogger(WorkerRunnable.class.getName());

  @Getter
  private int id;

  @Getter
  private Date initializedDate;

  @Getter
  private Date completedDate;

  @Getter
  private long duration;

  WorkerRunnable() {
    id = index.incrementAndGet();
    initializedDate = new Date();
  }

  @Override
  public void run() {
    long ctm = System.currentTimeMillis();
    logger.info(Thread.currentThread().getName() + "-runnable(" + id + ")" + " starting");
    doSomething();
    duration = System.currentTimeMillis() - ctm;
    completedDate = new Date();
    logger.info(Thread.currentThread().getName() + "-runnable(" + id + ")" + " done");
  }

  abstract void doSomething();

  public static int getIndex() {
    return index.get();
  }

  public static void resetIndex() {
    index.set(0);
  }
}
