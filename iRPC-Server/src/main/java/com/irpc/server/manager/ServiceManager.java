package com.irpc.server.manager;

/**
 * 缓存RpcRequest接口的服务器端实现类
 */
public interface ServiceManager {
    // 添加服务到本地缓存
    void putService(Object service);

    // 获取服务
    Object getService(String serviceName);

    // 发布服务
    void publishService(Object service);
}
