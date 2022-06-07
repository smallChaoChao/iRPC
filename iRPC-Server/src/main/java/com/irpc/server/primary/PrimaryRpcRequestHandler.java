package com.irpc.server.primary;

import com.irpc.common.dto.RpcRequest;
import com.irpc.common.dto.RpcResponse;
import com.irpc.common.factory.SingletonFactory;
import com.irpc.common.serialize.primary.PrimarySerializer;
import com.irpc.server.handler.RpcRequestHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

/**
 * 使用socket进行rpc通信 真正的服务端处理RPC请求的类
 */
@Slf4j
public class PrimaryRpcRequestHandler implements Runnable {
    // 处理的socket
    private final Socket socket;
    private final RpcRequestHandler rpcRequestHandler;

    public PrimaryRpcRequestHandler(Socket socket) {
        this.socket = socket;
        this.rpcRequestHandler = SingletonFactory.getInstance(RpcRequestHandler.class);
    }

    @Override
    public void run() {
        try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())){
            // todo: 1. 反序列化为对象
            RpcRequest rpcRequest = (RpcRequest) ois.readObject();

            // todo: 2. 调用真正的handler方法处理业务 返回结果对象
            Object result = rpcRequestHandler.handle(rpcRequest);
            // 打印结果
            log.info(result.toString());

            // todo: 3. 将结果对象序列化 然后返回结果
            oos.writeObject(new RpcResponse<>(result));
            oos.flush();
        } catch (IOException | ClassNotFoundException e) {
            log.error("server处理失败");
            e.printStackTrace();
        }
    }
}
