package com.irpc.client.sender.primary;

import com.irpc.client.sender.RpcRequestSender;
import com.irpc.common.dto.RpcRequest;
import javafx.beans.property.ObjectProperty;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 定义一个socket版本的 客户端 实现: 请求-处理-返回
 */
@Slf4j
public class PrimaryRpcClient implements RpcRequestSender {
    @Override
    public Object sendRpcRequest(RpcRequest rpcRequest) {
        // todo: 从注册中心获取服务的IP和端口
        // InetSocketAddress inetSocketAddress = serviceDiscovery.lookupService(rpcRequest);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("192.168.1.158", 8899);
        try (Socket socket = new Socket()){
            socket.connect(inetSocketAddress);
            // 将请求发送出去
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(rpcRequest);

            // 获取服务端的结果
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            log.error("RPC 远程调用失败");
            e.printStackTrace();
            // todo: 自定义异常
            return null;
        }
    }
}
