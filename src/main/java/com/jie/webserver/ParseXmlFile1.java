package com.jie.webserver;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * 项目名称：learnJava
 * 类 名 称：ParseXmlFile1
 * 类 描 述：
 * 创建时间：2019/8/6 20:38
 * 创 建 人：杰哥
 */
public class ParseXmlFile1 {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        PHandler pHandler = new PHandler();
        saxParser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("p.xml"),pHandler);
    }
}

class PHandler extends DefaultHandler{
    @Override
    public void startDocument() throws SAXException {
        System.out.println("XML文件开始解析");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("元素"+qName+"开始解析");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch,start,length).trim();
        if (content.length()>0){
            System.out.println(content);
        }else{
            System.out.println("空");
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("元素"+qName+"解析结束");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("XML文件解析结束");
    }
}