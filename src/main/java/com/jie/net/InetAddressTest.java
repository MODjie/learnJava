package com.jie.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 项目名称：learnJava
 * 类 名 称：InetAddressTest
 * 类 描 述：Ip地址
 * 创建时间：2019/7/30 20:42
 * 创 建 人：杰哥
 */
public class InetAddressTest {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();

        System.out.println(inetAddress.getAddress());
        System.out.println(inetAddress.getHostName());
        System.out.println(inetAddress.getHostAddress());
        System.out.println(inetAddress.getCanonicalHostName());

        inetAddress = InetAddress.getByName("www.baidu.com");
        System.out.println(inetAddress.getAddress());
        System.out.println(inetAddress.getHostName());
        System.out.println(inetAddress.getHostAddress());
        System.out.println(inetAddress.getCanonicalHostName());

    }
}
