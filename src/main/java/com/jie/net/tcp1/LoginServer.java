package com.jie.net.tcp1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 项目名称：learnJava
 * 类 名 称：LoginServer
 * 类 描 述：模拟登录服务器
 * 创建时间：2019/8/1 21:17
 * 创 建 人：杰哥
 */
public class LoginServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket client = serverSocket.accept();
        System.out.println("客户端已经连接");
        DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
        String receiveMsg = dataInputStream.readUTF();
        String[] userInfo = validateUser(receiveMsg);
        String username="";
        String password="";
        DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
        if ( "noel".equals(userInfo[0]) && "12345678".equals(userInfo[1])){
            username = userInfo[0];
            password = userInfo[1];
            System.out.println("用户名："+username+"  密码："+password);
            dataOutputStream.writeUTF("登录成功");
            dataOutputStream.flush();
        } else {
            dataOutputStream.writeUTF("用户名或密码错误");
            dataOutputStream.flush();
        }

        dataOutputStream.close();
        dataInputStream.close();
        client.close();
    }

    private static String[] validateUser(String receiveMsg) {
        String[] userInfoFirstPacket = receiveMsg.split("&");
        String[] username = userInfoFirstPacket[0].split("=");
        String[] password = userInfoFirstPacket[1].split("=");
        String[] userInfo = new String[2];
        userInfo[0]=username[1];
        userInfo[1]=password[1];
        return userInfo;
    }
}
