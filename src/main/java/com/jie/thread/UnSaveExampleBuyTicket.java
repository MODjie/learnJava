package com.jie.thread;

import java.util.Date;

/**
 * 项目名称：learnJava
 * 类 名 称：UnSaveExampleBuyTicket
 * 类 描 述：线程不安全的例子——买票
 * 创建时间：2019/7/19 21:23
 * 创 建 人：杰哥
 */
public class UnSaveExampleBuyTicket {
    public static void main(String[] args) {

        SaleTicket saleTicket = new SaleTicket();

        Thread personThread1 = new Thread(saleTicket);
        Thread personThread2 = new Thread(saleTicket);
        Thread personThread3 = new Thread(saleTicket);

        personThread1.start();
        personThread2.start();
        personThread3.start();
    }
}

class SaleTicket implements Runnable {
    private static int ticketNum = 100;

    @Override
    public void run() {
        buyeTicket02();
    }

    /**
     * 同步方法，锁住this
     */
    public synchronized void buyeTicket01() {
        while (true) {
            if (ticketNum <= 0) {
                break;
            }
            try {
                Thread.sleep(200);
                ticketNum--;
                System.out.println("剩余票数：" + ticketNum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 同步代码块，锁住this
     */
    public void buyeTicket02() {
        long start = new Date().getTime();
        while (true) {
            synchronized (this) {
                if (ticketNum <= 0) {
                    break;
                }
                try {
                    Thread.sleep(200);
                    ticketNum--;
                    System.out.println("剩余票数：" + ticketNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(new Date().getTime()-start);
    }

    /**
     * 同步代码块，锁住ticketNum
     * ticketNum对象地址一直会变化，因此锁不住，还是线程不安全
     * 对象的属性变化就可以锁住
     */
    public void buyeTicket03() {
        while (true) {
            synchronized (Integer.valueOf(ticketNum)) {
                if (ticketNum <= 0) {
                    break;
                }
                try {
                    Thread.sleep(200);
                    ticketNum--;
                    System.out.println("剩余票数：" + ticketNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 线程不安全，锁的范围太小了
     */
    public void buyeTicket04() {
        while (true) {
            synchronized (this) {
                if (ticketNum <= 0) {
                    break;
                }
            }
            try {
                Thread.sleep(200);
                ticketNum--;
                System.out.println("剩余票数：" + ticketNum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 线程安全，缩小锁的粒度，性能更高
     * double checking （双重检测）
     */
    public void buyeTicket05() {
        long start = new Date().getTime();
        System.out.println(start);
        while (true) {
            //检测没票时
            if (ticketNum <= 0) {
                break;
            }
            synchronized (this) {
                //检测最后一张票时
                if (ticketNum <= 0) {
                    break;
                }
                try {
                    Thread.sleep(200);
                    ticketNum--;
                    System.out.println("剩余票数：" + ticketNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        System.out.println(new Date().getTime()-start);
    }
}
