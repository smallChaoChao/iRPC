package com.irpc.server.primary;

import com.irpc.common.factory.SingletonFactory;
import com.irpc.common.threadpool.ThreadPoolFactory;
import com.irpc.server.RpcServer;
import com.irpc.server.manager.ServiceManager;
import com.irpc.server.manager.ServiceManagerImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 实现最简单的服务器端的接收程序 使用socket通信(BIO, NIO) 后续采用netty接管
 */
@AllArgsConstructor
@Getter
@Setter
@Slf4j
public class PrimaryRpcServer implements RpcServer {
    // 端口和IP
    private static int PORT = 8899;
    private static String ADDRESS = "192.168.1.158";

    // server的运行状态
    private static boolean isRunning = false;

    // 线程池和注册中心
    private final ThreadPoolExecutor threadPool;
    private final ServiceManager serviceManager;

    public PrimaryRpcServer() {
        threadPool = ThreadPoolFactory.createThreadPoolIfAbsent("primary_rpc_server_thread_pool");
        // todo: 注册中心提供的注册服务的方法..
        serviceManager = SingletonFactory.getInstance(ServiceManagerImpl.class);
    }

    @Override
    public void start() {
        try (ServerSocket server = new ServerSocket()) {
            // 绑定IP和端口
            server.bind(new InetSocketAddress(ADDRESS, PORT));

            // todo: 可以在这里加上 如果服务器down掉后 从注册中心删除所提供的服务
            isRunning = true;   // 线程池状态为运行

            Socket socket;
            while ((socket = server.accept()) != null) {
                // 监听到了一个客户端的接入
                log.info("客户端连接 [{}]", socket.getInetAddress());

                // todo: 具体的执行流程
                threadPool.execute(new PrimaryRpcRequestHandler(socket));
            }

            // 关闭线程池
            threadPool.shutdown();
            ThreadPoolFactory.shutDownThreadPool("primary_rpc_server_thread_pool");
        } catch (IOException e) {
            log.error("PrimaryRpcServer 发生IO Socket异常", e);
        }
    }

    @Override
    public void register(Object impl) {
        // todo: 调用注册中心的publishedServer方法发布该服务器的服务到注册中心
        serviceManager.publishService(impl);
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void stop() {
        isRunning = false;
        threadPool.shutdown();
        // ThreadPoolFactory.shutDownThreadPool("primary_rpc_server_thread_pool");
    }
}
