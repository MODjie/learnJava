package com.jie.net.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost",8888);
        DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
        dataOutputStream.writeUTF("我是客户端");
        dataOutputStream.flush();
        dataOutputStream.close();
        client.close();
    }
}
