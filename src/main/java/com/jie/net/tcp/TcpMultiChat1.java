package com.jie.net.tcp;

import java.io.IOException;
import java.net.Socket;

/**
 * 项目名称：learnJava
 * 类 名 称：LoginClient
 * 类 描 述：群聊服务器1.0
 * 创建时间：2019/8/1 21:16
 * 创 建 人：杰哥
 */
public class TcpMultiChat1 {

    public static void main(String[] args) {
        new Thread(new Chanel1(8888)).start();
    }
}
