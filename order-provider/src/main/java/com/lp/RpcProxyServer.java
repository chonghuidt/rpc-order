package com.lp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auther lp
 * @date 2020/6/20 0020 14:02
 */
public class RpcProxyServer {
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    public void publish(Object service,int port){
        ServerSocket serverSocket =null;
        try {
            serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();   //监听客户端请求
                //避免IO阻塞，但是受限制与线程的资源
                executorService.execute(new ProcessHadlerZ(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
