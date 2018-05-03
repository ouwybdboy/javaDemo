package com.wyb.entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by wuyb3 on 2018/5/3.
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 8170956065113471845L;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //提供一个方法，折腾自己，先把自己的这个对象写到某个文件中，然后再读取出来
    public Object myclone() throws ClassNotFoundException, IOException {
        //序列化成功
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oo = null;
        try {
            oo = new ObjectOutputStream(baos);
            oo.writeObject(this);
            System.out.println("Student序列化成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //反序列化
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object obj = ois.readObject();
        return obj;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
