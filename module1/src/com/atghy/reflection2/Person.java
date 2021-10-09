package com.atghy.reflection2;

@MyAnnotation(value="hi")
public class Person extends CreaSure<String> implements Comparable<String>,MyInterface {
    private String name;
    int age;
    public int id;

    public Person(){}

    @MyAnnotation(value="abc")
    private Person(String name){
        this.name = name;
    }

    Person(String name,int age){
        this.name = name;
        this.age = age;
    }
    
    @MyAnnotation
    private String show(String nation){
        System.out.println("我的国籍是:"+ nation);
        return nation;
    }

    public String diaplay(String insterest,int age) throws NullPointerException,ClassCastException{
        return insterest + age;
    }

    @Override
    public void info() {
        System.out.println("我是一个人");
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }
}
