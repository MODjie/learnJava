package com.jie.net.udp.chat;

/**
 * 项目名称：learnJava
 * 类 名 称：StudentSpeaker
 * 类 描 述：
 * 创建时间：2019/8/1 0:02
 * 创 建 人：杰哥
 */
public class StudentSpeaker {
    public static void main(String[] args) {
        System.out.println("学生端启动成功");
        new Thread(new UdpChatClient(8088,"localhost",9999)).start();
        new Thread(new UdpChatServe(6666,"老师说")).start();
    }
}
