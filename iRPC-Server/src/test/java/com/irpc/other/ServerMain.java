package com.irpc.other;

import com.irpc.api.calculator.Calculator;
import com.irpc.api.calculator.CalculatorService;
import com.irpc.server.manager.ServiceManagerImpl;
import com.irpc.server.primary.PrimaryRpcServer;
import org.junit.Test;

public class ServerMain {
    @Test
    public void primaryServerMain() {
        // 先创建服务
        CalculatorService calculatorService = new Calculator();
        PrimaryRpcServer primaryRpcServer = new PrimaryRpcServer();
        primaryRpcServer.register(calculatorService);
        primaryRpcServer.start();
    }
}
