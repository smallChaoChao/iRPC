package com.irpc.server.manager;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.spi.ServiceRegistry;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ServiceManagerImpl implements ServiceManager{
    // 本地缓存的容器
    private final Map<String, Object> serviceCache;
    // private final Set<String> registeredService;
    // private final ServiceRegistry serviceRegistry;

    public ServiceManagerImpl() {
        serviceCache = new ConcurrentHashMap<>();
        // registeredService = ConcurrentHashMap.newKeySet();
    }

    @Override
    public void putService(Object service) {
        String serviceName = service.getClass().getInterfaces()[0].getCanonicalName();
        if (serviceCache.containsKey(serviceName)) {
            return;
        }
        serviceCache.put(serviceName, service);
    }

    @Override
    public Object getService(String serviceName) {
        Object service = serviceCache.get(serviceName);
        if (null == service) {
            log.error(serviceName + " 无服务");
        }
        return service;
    }

    @Override
    public void publishService(Object service) {
        this.putService(service);
    }
}
