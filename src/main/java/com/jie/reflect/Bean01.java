package com.jie.reflect;

/**
 * 项目名称：learnJava
 * 类 名 称：Bean01
 * 类 描 述：
 * 创建时间：2019/8/21 23:00
 * 创 建 人：杰哥
 */
public class Bean01 {
    private String name;
    private int age;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Bean01(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Bean01() {

    }
}
