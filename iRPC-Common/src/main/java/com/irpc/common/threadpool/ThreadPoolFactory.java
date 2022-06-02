package com.irpc.common.threadpool;


import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池创建工厂类
 */
@Slf4j
public class ThreadPoolFactory {
    /**
     * 创建一个map保存线程池
     *
     */
    private static final Map<String, ThreadPoolExecutor> THREAD_POOLS = new ConcurrentHashMap<>();

    public static ThreadPoolExecutor create(ThreadPoolConfig threadPoolConfig) {
        return new ThreadPoolExecutor(
                threadPoolConfig.getCoreSize(),
                threadPoolConfig.getMaximumPoolSize(),
                threadPoolConfig.getKeepAliveTime(),
                threadPoolConfig.getUnit(),
                threadPoolConfig.getWorkQueue());
    }

    /**
     * 默认的线程池
     * @param threadPoolName 线程池的名字
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor createThreadPoolIfAbsent(String threadPoolName) {
        // 放入到线程池中
        ThreadPoolExecutor threadPool = THREAD_POOLS.computeIfAbsent(threadPoolName,
                                                    k -> create(new ThreadPoolConfig()));
        if (threadPool.isShutdown() || threadPool.isTerminated()) {
            THREAD_POOLS.remove(threadPoolName);
            threadPool = create(new ThreadPoolConfig());
            THREAD_POOLS.put(threadPoolName, threadPool);
        }
        return threadPool;
    }

    public static ThreadPoolExecutor createThreadPoolIfAbsent(String threadPoolName, ThreadPoolConfig poolConfig) {
        // 放入到线程池中
        ThreadPoolExecutor threadPool = THREAD_POOLS.computeIfAbsent(threadPoolName,
                k -> create(poolConfig));
        if (threadPool.isShutdown() || threadPool.isTerminated()) {
            THREAD_POOLS.remove(threadPoolName);
            threadPool = create(poolConfig);
            THREAD_POOLS.put(threadPoolName, threadPool);
        }
        return threadPool;
    }

    public static boolean shutDownThreadPool(String threadPoolName) {
        // 判断当前线程池是否存在
        if (!THREAD_POOLS.containsKey(threadPoolName)) {
            log.warn("线程池 "+ threadPoolName +" 不存在");
            return false;
        }

        // 关闭线程池
        ThreadPoolExecutor threadPoolExecutor = THREAD_POOLS.get(threadPoolName);
        threadPoolExecutor.shutdown();

        // 等待关闭完成或者超过5s强制关闭
        try {
            threadPoolExecutor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.warn("线程池shutdown超时！强行关闭");
            threadPoolExecutor.shutdownNow();
        } finally {
            THREAD_POOLS.remove(threadPoolName);
        }
        log.info("线程池 " + threadPoolName + " 关闭成功！");
        return true;
    }
}
