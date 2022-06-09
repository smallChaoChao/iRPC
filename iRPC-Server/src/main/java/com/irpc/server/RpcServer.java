package com.irpc.server;

/**
 * RpcServer 需要实现的方法
 */
public interface RpcServer {
    // 启动服务器
    void start();

    // 注册服务
    // void register(Class<?> serviceInterface, Class<?> impl);
    void register(Object impl);

    // 服务是否正在运行中
    boolean isRunning();

    // 停止服务
    void stop();
}
