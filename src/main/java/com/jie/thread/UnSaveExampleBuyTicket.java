package com.jie.thread;

/**
 * 项目名称：learnJava
 * 类 名 称：UnSaveExampleBuyTicket
 * 类 描 述：线程不安全的例子——买票
 * 创建时间：2019/7/19 21:23
 * 创 建 人：杰哥
 */
public class UnSaveExampleBuyTicket {
    public static void main(String[] args) {

        SaleTicket saleTicket =new SaleTicket();

        Thread personThread1 = new Thread(saleTicket);
        Thread personThread2 = new Thread(saleTicket);
        Thread personThread3 = new Thread(saleTicket);

        personThread1.start();
        personThread2.start();
        personThread3.start();
    }
}

class SaleTicket implements  Runnable{
    private static int ticketNum = 100;

    @Override
    public void run() {
        buyeTicket();
    }

    public synchronized void buyeTicket(){
        while(true) {
            if (ticketNum<0){
                break;
            }
            try {
                Thread.sleep(200);
                ticketNum--;
                System.out.println("剩余票数："+ticketNum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
