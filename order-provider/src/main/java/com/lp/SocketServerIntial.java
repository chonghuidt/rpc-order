package com.lp;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auther lp
 * @date 2020/6/20 0020 16:22
 * spring容器启用完成之后会发布一个ContextRefreshedEvent
 */
@Component
public class SocketServerIntial implements ApplicationListener<ContextRefreshedEvent> {
    private final ExecutorService executorService= Executors.newCachedThreadPool();
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //启动服务
        ServerSocket serverSocket=null;
        try {
            serverSocket=new ServerSocket(8888);
            while(true){
                Socket socket=serverSocket.accept(); //监听客户端请求
                executorService.execute(new ProcessHadlerZ(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
