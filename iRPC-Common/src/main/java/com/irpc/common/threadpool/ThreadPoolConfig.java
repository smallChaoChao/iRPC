package com.irpc.common.threadpool;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 线程池配置类
 */
@Getter
@Setter
// @Builder
@NoArgsConstructor
public class ThreadPoolConfig {
    /**
     * 默认参数
     */
    private static final int CORE_SIZE = 10;
    private static final int MAX_POOL_SIZE = 50;
    private static final long KEEP_ALIVE_TIME = 1;
    private static final TimeUnit TIME_UNIT = TimeUnit.MINUTES;
    private static final int BLOCKING_QUEUE_CAPACITY = 100;

    /**
     * 定义默认参数
     */
    private int coreSize = CORE_SIZE;
    private int maximumPoolSize = MAX_POOL_SIZE;
    private long keepAliveTime = KEEP_ALIVE_TIME;
    private TimeUnit unit = TIME_UNIT;
    private BlockingQueue<Runnable> WorkQueue = new ArrayBlockingQueue<>(BLOCKING_QUEUE_CAPACITY);
}
