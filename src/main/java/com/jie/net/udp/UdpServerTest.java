package com.jie.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 项目名称：learnJava
 * 类 名 称：UdpServerTest
 * 类 描 述：UDP服务器（接收端）
 * 创建时间：2019/7/31 22:58
 * 创 建 人：杰哥
 */
public class UdpServerTest {
    public static void main(String[] args) {
        receive();
    }
    
    private static void receive(){
        try {
            System.out.println("接收端启动中");
            DatagramSocket socket = new DatagramSocket(8888);
            byte[] msgByte = new byte[1024*6];
            DatagramPacket receivePacket = new DatagramPacket(msgByte,0,msgByte.length);
            socket.receive(receivePacket);
            String receiveMsg = new String(receivePacket.getData());
            System.out.println(receiveMsg);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
