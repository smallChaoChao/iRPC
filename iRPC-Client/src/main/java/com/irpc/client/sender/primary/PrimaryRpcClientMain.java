package com.irpc.client.sender.primary;

import com.irpc.client.proxy.RpcClientProxy;
import com.irpc.api.calculator.CalculatorService;

/**
 * 发送的主函数
 *
 */
public class PrimaryRpcClientMain {
    public static void main(String[] args) throws InterruptedException {
        PrimaryRpcClient primaryRpcClient = new PrimaryRpcClient();
        RpcClientProxy rpcClientProxy = new RpcClientProxy(primaryRpcClient);
        CalculatorService calculatorService = rpcClientProxy.getProxy(CalculatorService.class);
        Double result = calculatorService.add(4.0, 4.0);
        System.out.println(result);
    }
}
