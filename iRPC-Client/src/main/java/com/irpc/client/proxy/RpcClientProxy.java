package com.irpc.client.proxy;

import com.irpc.client.sender.RpcRequestSender;
import com.irpc.client.sender.primary.PrimaryRpcClient;
import com.irpc.common.dto.RpcRequest;
import com.irpc.common.dto.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RpcClientProxy implements InvocationHandler {
    // 定义被代理的对象
    private final RpcRequestSender rpcRequestSender;

    public RpcClientProxy(RpcRequestSender rpcRequestSender) {
        this.rpcRequestSender = rpcRequestSender;
    }

    // 获取代理对象
    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 定义RPC 请求对象
        RpcRequest rpcRequest = RpcRequest.builder()
                .methodName(method.getName())
                .args(args)
                .argsTypes(method.getParameterTypes())
                .interfaceName(method.getDeclaringClass().getCanonicalName())
                .build();

        // 定义RPC 返回对象
        RpcResponse<Object> response = null;
        if (rpcRequestSender instanceof PrimaryRpcClient) {
            response = (RpcResponse<Object>) rpcRequestSender.sendRpcRequest(rpcRequest);
        }

        // 返回结果
        return response.getResult();
    }
}
