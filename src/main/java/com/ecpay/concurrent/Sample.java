package com.ecpay.concurrent;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Joe on December, 09 2019 .
 */
public class Sample {
  public static void main(String... args) throws ExecutionException, InterruptedException {
    test1();

//    test2();
  }

  static void test1() throws ExecutionException, InterruptedException {
    ThreadPool pool = new ThreadPoolBuilder().minThreads(1).maxThreads(1).build();
    for (int i = 0; i < 30; i++) {
      WorkerRunnable worker = new WorkerRunnable() {
        @Override
        void doSomething() {
          System.out.println("okka");
        }
      };
      pool.execute(worker);
    }
  }

  static class Obt {
    private String sReq = "Request-" + System.currentTimeMillis();
    @Getter
    @Setter
    private String sRes;
  }
}
