package com.jie.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * 项目名称：learnJava
 * 类 名 称：UdpCientTest
 * 类 描 述：UDP客户端（发送端）
 * 创建时间：2019/7/31 22:59
 * 创 建 人：杰哥
 */
public class UdpCientTest {
    public static void main(String[] args) {
        send();
    }

    private static void send(){
        try {
            System.out.println("发送端启动");
            DatagramSocket datagramSocket = new DatagramSocket(9999);
            String sendMsg = "你杰哥还是你杰哥";
            byte [] msgBytes = sendMsg.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(msgBytes,0,msgBytes.length,new InetSocketAddress("localhost",8888));
            datagramSocket.send(sendPacket);
            System.out.println("发送成功");
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
