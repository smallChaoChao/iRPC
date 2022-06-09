package com.irpc.common.serialize.primary;

import com.irpc.common.serialize.Serializer;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * 使用jdk原生代码实现序列化和反序列化的任务
 */
@Slf4j
public class PrimarySerializer implements Serializer {
    public byte[] serialize(Object obj) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(obj);
            return baos.toByteArray();

        } catch (IOException e) {
            log.error("序列化出错", e);
            return null;
        }
    }

    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            Object o = ois.readObject();
            return clazz.cast(o);

        } catch (IOException | ClassNotFoundException e) {
            log.error("反序列化出错", e);
            return null;
        }
    }
}
