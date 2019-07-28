package com.jie.thread;

/**
 * 项目名称：learnJava
 * 类 名 称：HappenBefore
 * 类 描 述：指令重排
 * 创建时间：2019/7/28 14:08
 * 创 建 人：杰哥
 */
public class HappenBefore {
    private static int a = 0;
    private static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<10;i++) {
            a = 0;
            flag = false;

            //线程1 更改数据
            Thread t1 = new Thread(()->{
                a = 1;
                flag = true;
            }) ;
            //线程2 读取数据
            Thread t2 = new Thread(()->{
                if(flag) {
                    a *=1;
                }
                //指令重排
                if(a == 0) {
                    System.out.println("happen before a->"+a);
                }
            }) ;

            t1.start();
            t2.start();

            //合并线程
            t1.join();
            t2.join();
        }
    }
}
