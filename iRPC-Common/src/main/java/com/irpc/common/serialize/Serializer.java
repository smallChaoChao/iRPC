package com.irpc.common.serialize;

/**
 * 抽象的序列化接口 后期优化通过不同的序列化框架实现接口
 */
public interface Serializer {
    /**
     * 序列化过程 将对象序列化为字节数组
     *
     * @param obj 需要序列化的对象
     * @return  序列化后的字节数组
     */
    byte[] serialize(Object obj);

    /**
     * 反序列化过程 将字节数组反序列化为对象
     *
     * @param bytes 序列化后的字节数组
     * @param clazz 反序列化类的类型
     * @param <T>   类类型
     * @return 反序列化对象
     */
    <T> T deserialize(byte[] bytes, Class<T> clazz);
}
