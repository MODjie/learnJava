package com.jie.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 项目名称：learnJava
 * 类 名 称：GetByReflect
 * 类 描 述：通过反射动态获取类的相关内容
 * 创建时间：2019/8/21 22:59
 * 创 建 人：杰哥
 */
public class GetByReflect {
    public static void main(String[] args) {
        //获取Class对象的方式
        //1、通过类名.class获得
        Class c1 = Bean01.class;
        //2、通过对象.getClass()获得
        Bean01 bean01 = new Bean01();
        Class c2 =  bean01.getClass();
        //3、通过Class.forName获得
        try {
            Class c3 = Class.forName("com.jie.webserver.Iphone");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //一个类在加载时只会存在一个Class对象。

        //通过Class对象获取实体对象
        try {
            //1、通过class的newInstance方法,实际是使用了实体类的无参构造方法
            Bean01 bean02 = (Bean01) c2.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //2、使用有参构造方法的newInstance方法获得
//        try {
//            Constructor constructor = c2.getDeclaredConstructor(String.class,int.class,String.class);
//            Bean01 bean03 = (Bean01) constructor.newInstance();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }


        //通过反射获取实体类的所有方法（不带declared的方法是获取public修饰的方法，获取属性和构造器也是一样）
        Method[] methods = c2.getDeclaredMethods();
        for (Method method:methods) {
            System.out.println("方法返回类型"+method.getReturnType()+"方法名："+method.getName());
        }

        //获取属性
        Field[] fields = c2.getDeclaredFields();
        for (Field field :fields) {
            System.out.println( "属性类型："+field.getType()+"   属性名："+field.getName());
        }

        //获取构造器
        Constructor[] constructors = c2.getConstructors();
        for ( Constructor constructor: constructors) {
            System.out.println(constructor.getName());
        }
    }
}
