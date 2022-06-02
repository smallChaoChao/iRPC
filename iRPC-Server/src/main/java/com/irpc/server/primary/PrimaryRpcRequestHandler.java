package com.irpc.server.primary;

import com.irpc.common.serialize.primary.PrimarySerializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * 使用socket进行rpc通信 真正的服务端处理RPC请求的类
 */
public class PrimaryRpcRequestHandler implements Runnable {
    // 处理的socket
    private final Socket socket;

    public PrimaryRpcRequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())){
            // todo: 1. 反序列化为对象

            // todo: 2. 调用真正的handler方法处理业务 返回结果对象

            // todo: 3. 将结果对象序列化 然后返回结果

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
