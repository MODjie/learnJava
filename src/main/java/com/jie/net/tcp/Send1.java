package com.jie.net.tcp;

import com.jie.util.TcpUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 项目名称：learnJava
 * 类 名 称：LoginClient
 * 类 描 述：客户端发送信息1.0
 * 创建时间：2019/8/1 21:16
 * 创 建 人：杰哥
 */
public class Send1 implements Runnable{
    private DataOutputStream dos;
    private boolean isRunning;
    private BufferedReader br;
    private Socket client;

    public Send1(Socket client) {
        try {
            this.client = client;
            this.dos = new DataOutputStream(client.getOutputStream());
            this.br = new BufferedReader(new InputStreamReader(System.in));
            this.isRunning = true;
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
            String sendMsg = "";
            try {
                sendMsg = this.consoleInput();
                this.send(sendMsg);
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

    private String consoleInput() throws IOException {
        String sendMsg = br.readLine();
        return sendMsg;
    }

    private void send(String msg) throws IOException {
        dos.writeUTF(msg);
    }

    private void release() throws IOException {
        isRunning = false;
        TcpUtil.close(dos,br,client);
    }
}
