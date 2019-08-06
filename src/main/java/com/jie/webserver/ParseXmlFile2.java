package com.jie.webserver;

import org.apache.commons.lang.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：learnJava
 * 类 名 称：ParseXmlFile1
 * 类 描 述：
 * 创建时间：2019/8/6 20:38
 * 创 建 人：杰哥
 */
public class ParseXmlFile2 {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        PersonHandler personHandler = new PersonHandler();
        saxParser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("p.xml"),personHandler);
        List<Person> personList = personHandler.getPersonList();
        if (personList.size()>0){
            for (Person person:personList) {
                System.out.println(person);
            }
        }
    }
}

class PersonHandler extends DefaultHandler{
    private List<Person> personList;
    private Person person;
    private String tag;
    @Override
    public void startDocument() throws SAXException {
        System.out.println("XML文件开始解析");
        personList = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        tag = qName;
        if (tag != null){
            if ("person".equals(tag)){
                person = new Person();
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch,start,length).trim();
        if (tag != null){
            if ("name".equals(tag)){
                person.setName(content);
            }else if ("age".equals(tag)){
                person.setAge(StringUtils.isEmpty(content)?0:Integer.valueOf(content));
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        tag = null;
        if (qName != null){
            if ("person".equals(qName)){
                personList.add(person);
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("XML文件解析结束");
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(){

    }

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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}