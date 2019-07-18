package com.jie.thread;

/**
 * 项目名称：learnJava
 * 类 名 称：BlockedJoin
 * 类 描 述：Thread类的join方法的使用
 * 创建时间：2019/7/18 22:45
 * 创 建 人：杰哥
 */
public class BlockedJoin {

    public static void main(String[] args) {
        new Thread(new Father()).start();
    }
}

class Father implements Runnable{

    public void run() {
        System.out.println("老爸没烟了，叫儿子去买烟，给了儿子钱");
        Thread sonThread = new Thread(new Son());
        sonThread.start();
        try {
            sonThread.join();
            System.out.println("老爸接过儿子的烟，并因为等太久抽了儿子一巴掌");
            System.out.println("老爸深吸一口烟，眼神迷离");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("老爸出去找走丢的儿子");
        }
    }
}

class Son implements  Runnable{

    public void run() {
        System.out.println("儿子接过老爸的钱，上街买烟");
        System.out.println("在路上碰到一个网吧，进去玩了5小时游戏");
        for (int i = 0; i <5 ; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(i+1+"小时过去了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("儿子突然想起来忘记买烟了，赶紧跑去买烟");
        System.out.println("儿子带了包中华回来了");
    }
}
