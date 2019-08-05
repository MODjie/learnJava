package com.jie.webserver;

import java.lang.reflect.InvocationTargetException;

/**
 * 项目名称：learnJava
 * 类 名 称：basic
 * 类 描 述：获取反射的三种方式
 * 创建时间：2019/8/5 23:02
 * 创 建 人：杰哥
 */
public class basic {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Iphone iphone = new Iphone();
        Class clz = iphone.getClass();
        clz = Iphone.class;
        clz = Class.forName("com.jie.webserver.Iphone");

        //通过反射获取对象

        //此方法在JDK9被废弃，推荐使用构造器的方式
        Iphone iphone1 = (Iphone) clz.newInstance();

        iphone1 = (Iphone) clz.getConstructor().newInstance();
        System.out.println(iphone1);
    }
}

class Iphone {
    public Iphone() {
    }
}
