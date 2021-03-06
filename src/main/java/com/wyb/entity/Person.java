package com.wyb.entity;

import java.io.Serializable;

/**
 * Created by wuyb3 on 2018/4/28.
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 8170456065113471845L;
    private String name;
    private int age;

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
