package com.irpc.other;

import com.irpc.server.primary.PrimaryRpcServer;
import org.junit.Test;

public class ServerMain {
    @Test
    public void primaryServerMain() {
        PrimaryRpcServer primaryRpcServer = new PrimaryRpcServer();
        primaryRpcServer.start();
    }
}
