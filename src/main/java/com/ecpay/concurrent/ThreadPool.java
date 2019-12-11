package com.ecpay.concurrent;

import lombok.Getter;

import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * Created by Joe on December, 09 2019 .
 */
public class ThreadPool {
  private static final Logger logger = Logger.getLogger(ThreadPool.class.getName());
  private ExecutorService executor;

  @Getter
  private int minThreads;
  @Getter
  private int maxThreads;

  ThreadPool(int minThreads, int maxThreads) {
    this.minThreads = minThreads;
    this.maxThreads = maxThreads;

    ThreadFactory threadFactory = new ThreadFactoryBuilder()
        .setNamePrefix("ECPay-ThreadPool")
        .setDaemon(false)
        .setPriority(Thread.NORM_PRIORITY)
        .setUncaughtExceptionHandler((t, e) -> logger.warning(String.format("Custom Exception: Thread %s threw exception - %s",
            t.getName(), e.getMessage()))).build();

    executor = new ThreadPoolExecutor(minThreads, maxThreads, 0, TimeUnit.MILLISECONDS,
        new LinkedBlockingDeque<>(), threadFactory, new RejectedHandle());
  }

  public void execute(WorkerRunnable runnable) {
    executor.execute(runnable);
  }

  public Future<?> submit(WorkerCallable<?> callable) {
    return executor.submit(callable);
  }

  public void shutdown() {
    executor.shutdown();
  }

  public void shutdownNow() {
    executor.shutdownNow();
  }

  public void awaitTermination(long timeout, TimeUnit timeUnit) throws InterruptedException {
    executor.awaitTermination(timeout, timeUnit);
  }

  public boolean isShutdown() {
    return executor.isShutdown();
  }

  public boolean isTerminated() {
    return executor.isTerminated();
  }

  public String getStatusInfo() {
    return "{\"taskCount\": " + ((ThreadPoolExecutor) executor).getTaskCount()
        + ", \"activeCount\": " + ((ThreadPoolExecutor) executor).getActiveCount()
        + ", \"completedTaskCount\": " + ((ThreadPoolExecutor) executor).getCompletedTaskCount()
        + ", \"isTerminated\": " + executor.isTerminated()
        + ", \"isShutdown\": " + executor.isShutdown()
        + "}";
  }
}
