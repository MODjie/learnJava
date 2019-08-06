package com.jie.net.tcp;

import com.jie.util.TcpUtil;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 项目名称：learnJava
 * 类 名 称：LoginClient
 * 类 描 述：客户端发送信息1.0
 * 创建时间：2019/8/1 21:16
 * 创 建 人：杰哥
 */
public class Receive1 implements Runnable{

    private Socket client;
    private boolean isRunning;
    private DataInputStream dis;

    public Receive1(Socket client){
        try {
            this.client = client;
            this.isRunning = true;
            this.dis = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            try {
                release();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        while (isRunning){
            try {
                String receiveMsg = this.receive();
                System.out.println(receiveMsg);
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    release();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private String receive() throws IOException {
        return dis.readUTF();
    }

    private void release() throws IOException {
        isRunning = false;
        TcpUtil.close(dis,client);
    }

}
