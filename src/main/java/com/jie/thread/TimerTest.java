package com.jie.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 项目名称：learnJava
 * 类 名 称：TimerTest
 * 类 描 述：定时调度任务
 * 创建时间：2019/7/28 13:09
 * 创 建 人：杰哥
 */
public class TimerTest {
    public static void main(String[] args) {
        MyTimerTask myTimerTask = new MyTimerTask();
        Timer timer = new Timer();
//        timer.schedule(myTimerTask,new Date());
        timer.schedule(myTimerTask,1000);
    }
}

class MyTimerTask extends TimerTask{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("我是定时调度哦");
        }
    }
}