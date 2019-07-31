package com.jie.net.udp.chat;

/**
 * 项目名称：learnJava
 * 类 名 称：TeacherSpeaker
 * 类 描 述：
 * 创建时间：2019/8/1 0:02
 * 创 建 人：杰哥
 */
public class TeacherSpeaker {
    public static void main(String[] args) {
        System.out.println("老师端启动成功");
        new Thread(new UdpChatClient(9098,"localhost",6666)).start();
        new Thread(new UdpChatServe(9999,"学生说")).start();
    }
}
