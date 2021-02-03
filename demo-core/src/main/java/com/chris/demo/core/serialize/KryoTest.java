package com.chris.demo.core.serialize;


import com.alibaba.com.caucho.hessian.io.Hessian2Input;
import com.alibaba.com.caucho.hessian.io.Hessian2Output;
import com.alibaba.com.caucho.hessian.io.HessianInput;
import com.alibaba.com.caucho.hessian.io.HessianOutput;
import com.chris.demo.core.domain.User;
import com.chris.demo.core.domain.UserInfo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class KryoTest {


    public static void main(String[] args) throws IOException {

        hessian2();
    }

    public static void hessian2() throws IOException {
        UserInfo user = new UserInfo();
        user.setName("1");
        user.setId(1);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Hessian2Output ho = new Hessian2Output(os);
        ho.writeObject(user);
        ho.close();
        byte[] userByte = os.toByteArray();
        os.close();
        ByteArrayInputStream is = new ByteArrayInputStream(userByte);
        Hessian2Input hi = new Hessian2Input(is);
        UserInfo msg = (UserInfo) hi.readObject();
        System.out.println(msg.getName());
    }



    public static void kryo() {
        UserInfo user = new UserInfo();
        user.setName("1");

        //byte[] bytes = KryoSerializerFactory.serialization(message);
        //Message result = (Message) KryoSerializerFactory.deSerialization(bytes, Message.class);

        User result = KryoSerializerFactory.get().copy(user);
        UserInfo resultSon = KryoSerializerFactory.get().copy(user);
        System.out.println(result.getName());
        System.out.println(resultSon.getName());
    }

}