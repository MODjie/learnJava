package com.jie.net;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 项目名称：learnJava
 * 类 名 称：UrlTest
 * 类 描 述：
 * 创建时间：2019/7/30 21:11
 * 创 建 人：杰哥
 */
public class UrlTest {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("https://www.baidu.com/s?ie=utf-8&tn=02003390_21_hao_pg&wd=jdk1.8api#18");
        System.out.println("协议："+url.getProtocol());
        System.out.println("域名|IP："+url.getHost());
        System.out.println("端口："+url.getPort());
        System.out.println("资源1："+url.getFile());
        System.out.println("资源2："+url.getPath());

        //获取参数
        System.out.println("参数："+url.getQuery());
        System.out.println("锚点:"+ url.getRef());
    }
}
