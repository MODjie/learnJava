package com.jie.net;

import java.net.InetSocketAddress;

/**
 * 项目名称：learnJava
 * 类 名 称：InetSocketAddressTest
 * 类 描 述：操作端口的类
 * 创建时间：2019/7/30 21:03
 * 创 建 人：杰哥
 */
public class InetSocketAddressTest {
    public static void main(String[] args) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost",8000);
        InetSocketAddress inetSocketAddress2 = new InetSocketAddress("127.0.0.1",8000);
        System.out.println(inetSocketAddress.getAddress());
        System.out.println(inetSocketAddress.getPort());
        System.out.println(inetSocketAddress.getHostName());
    }
}
