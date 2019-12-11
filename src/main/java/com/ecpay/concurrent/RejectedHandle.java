package com.ecpay.concurrent;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

/**
 * Created by Joe on December, 10 2019 .
 */
public class RejectedHandle implements RejectedExecutionHandler {
  private static final Logger logger = Logger.getLogger(RejectedHandle.class.getName());

  @Override
  public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
    logger.warning(r.toString() + "rejected by " + executor.toString() + " shutdown");
  }
}
