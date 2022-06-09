package com.irpc.server.primary;
import com.irpc.api.calculator.Calculator;
import com.irpc.api.calculator.CalculatorService;

/**
 * RPC Server 服务端 提供服务的
 */
public class PrimaryRpcServerMain {
    public static void main(String[] args) {
        // 先创建服务
        CalculatorService calculatorService = new Calculator();
        PrimaryRpcServer primaryRpcServer = new PrimaryRpcServer();
        primaryRpcServer.register(calculatorService);
        primaryRpcServer.start();
    }
}
