package com.jie.net.tcp;

import java.io.IOException;
import java.net.Socket;

/**
 * 项目名称：learnJava
 * 类 名 称：LoginClient
 * 类 描 述：模拟登录客户端
 * 创建时间：2019/8/1 21:16
 * 创 建 人：杰哥
 */
public class TcpMultiClient1 {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost",8888);
            new Thread(new Send1(client)).start();
            new Thread(new Receive1(client)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
