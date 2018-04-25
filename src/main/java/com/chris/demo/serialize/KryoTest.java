package com.chris.demo.serialize;


public class KryoTest {


    public static void main(String[] args) {

        Message message = new Message(1);

        //byte[] bytes = KryoSerializerFactory.serialization(message);
        //Message result = (Message) KryoSerializerFactory.deSerialization(bytes, Message.class);

        Message result = KryoSerializerFactory.get().copy(message);
        System.out.println(result.age);
    }

    private static class Message {
        private int age;

        public Message(int i) {
            this.age = i;
        }

    }
}