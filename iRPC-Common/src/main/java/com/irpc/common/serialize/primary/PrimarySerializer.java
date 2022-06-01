package com.irpc.common.serialize.primary;

import com.irpc.common.serialize.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * 使用jdk原生代码实现序列化和反序列化的任务
 */
public class PrimarySerializer implements Serializer {
    public byte[] serialize(Object obj) {
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(obj);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();

            // todo 自定义跑出异常 或者打印日志
            System.out.println("Serialization failed!");
            return null;
        }
    }

    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        return null;
    }
}
