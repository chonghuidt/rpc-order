package com.lp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @auther lp
 * @date 2020/6/20 0020 16:30
 */
public class ProcessHadlerZ implements Runnable {
    private Socket socket;

    public ProcessHadlerZ(Socket socket) {

        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream=null;
        ObjectOutputStream outputStream=null;
        try {
            inputStream=new ObjectInputStream(socket.getInputStream());
            RpcRequest request=(RpcRequest)inputStream.readObject(); //反序列化
            //路由
            Mediator mediator=Mediator.getInstance();
            Object rs=mediator.processor(request);
            System.out.println("服务端的执行结果："+rs);
            outputStream=new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rs);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
