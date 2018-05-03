package com.wyb.serialize;

import com.wyb.entity.Student;

import java.io.IOException;

/**
 * Created by wuyb3 on 2018/5/3.
 */
public class CloneDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student = new Student();
        student.setName("wyb");
        System.out.println(student);
        //深克隆的操作，使用序列化的方式进行
        System.out.println("通过序列化和反序列化操作克隆出一个Student对象...");
        Student su = (Student) student.myclone();
        su.setName("lish");
        System.out.println(su);
    }
}
