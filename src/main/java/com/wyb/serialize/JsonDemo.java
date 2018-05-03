package com.wyb.serialize;

import com.wyb.entity.Person2;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by wuyb3 on 2018/5/3.
 */
public class JsonDemo {

    public static void main(String[] args) throws IOException {
        serializeByJackson();
    }

    /**
     * 使用jackson进行序列化操作
     * @throws IOException
     */
    public static void serializeByJackson() throws IOException {
        Person2 person = initPerson2();
        //使用jackson对其进行序列化
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] writeBytes= null;
        writeBytes = objectMapper.writeValueAsBytes(person);
        System.out.println("使用Jackson序列化成功！");
         //使用jackson进行一个反序列化操作
        Person2 p = objectMapper.readValue(writeBytes,Person2.class);
        System.out.println(p);

    }

    public static Person2 initPerson2(){
        Person2 person = new Person2();
        person.setName("wyb");
        person.setAge(30);
        return person;
    }
}
