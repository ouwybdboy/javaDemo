package com.wyb.serialize;

import com.wyb.entity.Person;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by wuyb3 on 2018/5/2.
 */
public class SerizlizeDeom {

    public static void main(String[] args) {
//        serialize();
        deserialize();
    }
    public static void serialize() {
        Person person = new Person();
        person.setName("wyb");
        person.setAge(30);

        try {
            ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File("persion")));
            oo.writeObject(person);
            oo.close();
            System.out.println("Persion对象序列化成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void deserialize() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("persion")));
            Person person = (Person) ois.readObject();
            System.out.println(person);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
