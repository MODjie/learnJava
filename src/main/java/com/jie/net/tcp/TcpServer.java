package com.jie.net.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket cllient = serverSocket.accept();
        System.out.println("一个客户端连接");
        InputStream inputStream = cllient.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        String receiveMsg = dataInputStream.readUTF();
        System.out.println(receiveMsg);
        dataInputStream.close();
        cllient.close();
    }
}
