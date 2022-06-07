package com.irpc.server.handler;

import com.irpc.common.dto.RpcRequest;
import com.irpc.common.factory.SingletonFactory;
import com.irpc.server.manager.ServiceManager;
import com.irpc.server.manager.ServiceManagerImpl;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 真正调用方法处理请求的地方 所有的版本的server都要调用这个方法处理
 */
@Slf4j
public class RpcRequestHandler {
    private final ServiceManager serviceManager;

    public RpcRequestHandler() {
        serviceManager = SingletonFactory.getInstance(ServiceManagerImpl.class);
    }

    public Object handle(RpcRequest rpcRequest) {
        Object service = serviceManager.getService(rpcRequest.getInterfaceName());
        return invokeTargetMethod(rpcRequest, service);
    }

    private Object invokeTargetMethod(RpcRequest rpcRequest, Object service) {
        Object result;
        try {
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getArgsTypes());
            result = method.invoke(service, rpcRequest.getArgs());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            log.error("执行方法失败");
            e.printStackTrace();
            // todo: 抛出自定义异常
            // throw new RpcException(e.getMessage(), e);
            return null;
        }

        return result;
    }
}
