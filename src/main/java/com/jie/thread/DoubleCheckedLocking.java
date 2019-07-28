package com.jie.thread;

/**
 * 项目名称：learnJava
 * 类 名 称：DoubleCheckedLocking
 * 类 描 述：DCL实现单例模式：懒汉模式，线程同步,volatile避免指令重排（如果实例化时需要比较久的时间，CPU会优先将地址返回，就可能导致调用者获得的引用为null）
 * 创建时间：2019/7/28 23:19
 * 创 建 人：杰哥
 */
public class DoubleCheckedLocking {
    private static volatile DoubleCheckedLocking doubleCheckedLocking;

    private DoubleCheckedLocking(){

    }

    public static DoubleCheckedLocking getInstance(){
        if (null == doubleCheckedLocking){
            doubleCheckedLocking = new DoubleCheckedLocking();
        }

        synchronized (DoubleCheckedLocking.class){
            if (null == doubleCheckedLocking){
                doubleCheckedLocking = new DoubleCheckedLocking();
            }
        }

        return  doubleCheckedLocking;
    }
    public static void main(String[] args) {
        new Thread(()->{
            DoubleCheckedLocking doubleCheckedLocking = DoubleCheckedLocking.getInstance();
            System.out.println(doubleCheckedLocking);
        }).start();
        DoubleCheckedLocking doubleCheckedLocking = DoubleCheckedLocking.getInstance();
        System.out.println(doubleCheckedLocking);
    }
}
