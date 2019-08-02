package com.jie.net.tcp1;

import java.io.*;
import java.net.Socket;

/**
 * 项目名称：learnJava
 * 类 名 称：LoginClient
 * 类 描 述：模拟登录客户端
 * 创建时间：2019/8/1 21:16
 * 创 建 人：杰哥
 */
public class LoginClient {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入用户名");
        String username = bufferedReader.readLine();
        System.out.println("请输入密码");
        String password = bufferedReader.readLine();
        String userInfo = "username="+username+"&password="+password;

        Socket client = new Socket("localhost",8888);
        DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
        dataOutputStream.writeUTF(userInfo);

        DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
        String response = dataInputStream.readUTF();
        System.out.println(response);

        dataOutputStream.flush();
        dataOutputStream.close();
        bufferedReader.close();
        client.close();
    }
}
