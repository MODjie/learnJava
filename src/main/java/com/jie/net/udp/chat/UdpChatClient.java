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
 * 类 名 称：UdpChatClient
 * 类 描 述：聊天客户端
 * 创建时间：2019/7/31 23:41
 * 创 建 人：杰哥
 */
public class UdpChatClient implements Runnable{

    private DatagramSocket client;
    private String targetIpAddress;
    private int targetPort;

    public UdpChatClient(int startPort, String targetIpAddress, int targetPort) {
        try {
            this.client = new DatagramSocket(startPort);
            this.targetIpAddress = targetIpAddress;
            this.targetPort = targetPort;
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        BufferedReader bufferedReader = null;
        boolean existFlag = false;
        while (!existFlag){
            existFlag = send(bufferedReader);
        }
        try {
            client.close();
            if (bufferedReader!=null){
                bufferedReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  boolean send(BufferedReader bufferedReader) {
        boolean existFlag = false;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String sendMsg = bufferedReader.readLine();
            byte[] msgBytes = sendMsg.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(msgBytes, 0, msgBytes.length, new InetSocketAddress(targetIpAddress, targetPort));
            client.send(sendPacket);
            if (sendMsg.equals("bye")) {
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