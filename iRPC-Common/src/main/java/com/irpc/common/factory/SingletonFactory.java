package com.irpc.common.factory;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 获取单例的工厂类
 */
@Slf4j
public class SingletonFactory {
    private static final Map<String, Object> SINGLETON_CACHE = new ConcurrentHashMap<>();

    private SingletonFactory(){}

    public static <T> T getInstance(Class<T> clazz) {
        if (null == clazz) {
            log.error("输入为null");
            throw new IllegalArgumentException();
        }

        String key = clazz.toString();
        if (SINGLETON_CACHE.containsKey(key)) {
            return clazz.cast(SINGLETON_CACHE.get(key));
        } else {
            return clazz.cast(SINGLETON_CACHE.computeIfAbsent(key, k -> {
                try {
                    return clazz.getDeclaredConstructor().newInstance();
                } catch (InvocationTargetException | InstantiationException |
                        IllegalAccessException | NoSuchMethodException e) {
                    log.error("创建单例失败");
                    throw new RuntimeException(e.getMessage(), e);
                }
            }));
        }
    }
}
