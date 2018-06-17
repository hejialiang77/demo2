package com.chris.demo.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class EasyTest {

    public static void main(String[] args) {
        EayObject real = new EayObject();
        Object o  = Proxy.newProxyInstance(EasyTest.class.getClassLoader(),
                EayObject.class.getInterfaces(), new ProxyObject(real));
        ByeInterface proxyInterface = (ByeInterface) o;
        proxyInterface.bye();
    }
}

interface ProxyInterface {
    void say();
}

interface ByeInterface {
    void bye();
}


//被代理类
class EayObject implements ProxyInterface , ByeInterface{

    @Override
    public void say() {
        System.out.println("i'm talking");
    }

    @Override
    public void bye() {
        System.out.println("good bye");

    }

}

//代理类，实现InvocationHandler 接口
class ProxyObject implements InvocationHandler {
    private Object proxied = null;

    ProxyObject(Object proxied) {
        this.proxied = proxied;
    }

    public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
        if (arg1.getName().equals("say") || arg1.getName().equals("bye") )
            System.out.println("hello");
        return arg1.invoke(proxied, arg2);
    }

}
