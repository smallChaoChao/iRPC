package com.irpc.client.sender;

import com.irpc.common.dto.RpcRequest;

/**
 * 定义 RpcRequest 发送的接口
 */
public interface RpcRequestSender {
    // 定义发送RPC请求的接口方法用于真正实现发送请求 处理返回的结果
    Object sendRpcRequest(RpcRequest rpcRequest);
}
