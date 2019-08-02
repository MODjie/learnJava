package com.jie.net.tcp;

import com.jie.util.TcpUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 项目名称：learnJava
 * 类 名 称：LoginClient
 * 类 描 述：群聊管道1.0
 * 创建时间：2019/8/1 21:16
 * 创 建 人：杰哥
 */
public class Chanel1 implements Runnable{
    private ServerSocket serverSocket;
    private Socket client;
    private boolean isRunning;
    private DataInputStream dis;
    private DataOutputStream dos;

    public Chanel1(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            isRunning = true;
            System.out.println("服务器已经启动");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            client = serverSocket.accept();
            System.out.println("一个客户端已连接");
            while (isRunning){
                String receiveMsg = this.receive(client);
                this.send(client,receiveMsg);
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                release();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void send(Socket client, String receiveMsg) throws IOException {
        dos = new DataOutputStream(client.getOutputStream());
        dos.writeUTF(receiveMsg);
        dos.flush();
    }

    private String receive(Socket client) throws IOException {
        dis = new DataInputStream(client.getInputStream());
        String receiveMsg = dis.readUTF();
        return receiveMsg;
    }

    private void release() throws IOException {
        isRunning = false;
        TcpUtil.close(dos,dis,client);
    }
}
