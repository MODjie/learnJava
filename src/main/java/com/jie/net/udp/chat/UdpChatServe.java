package com.jie.net.udp.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * 项目名称：learnJava
 * 类 名 称：UdpChatServe
 * 类 描 述：聊天服务器
 * 创建时间：2019/7/31 23:41
 * 创 建 人：杰哥
 */
public class UdpChatServe implements Runnable{
    private DatagramSocket server;
    private String msgFrom;

    public UdpChatServe(int startPort,String msgFrom) {
        try {
            this.server = new DatagramSocket(startPort);
            this.msgFrom = msgFrom;
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        boolean existFlag = false;
        while (!existFlag){
            existFlag = receive();
        }
        server.close();
    }

    private  boolean receive(){
        boolean existFlag = false;
        try {
            byte[] msgByte = new byte[1024*6];
            DatagramPacket receivePacket = new DatagramPacket(msgByte,0,msgByte.length);
            server.receive(receivePacket);
            String receiveMsg = new String(receivePacket.getData());
            System.out.println(msgFrom+"："+receiveMsg);
            if (receiveMsg.equals("bye")){
                existFlag = true;
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return existFlag;
    }
}
