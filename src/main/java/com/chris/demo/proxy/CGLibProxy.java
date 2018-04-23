package com.chris.demo.proxy;

import com.chris.demo.service.IUserService;
import com.chris.demo.service.impl.UserService;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibProxy implements MethodInterceptor {

    private Object targetObject;// CGLib需要代理的目标对象    

    public Object createProxyObject(Object obj) {
        this.targetObject = obj;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        return enhancer.create();// 返回代理对象
    }

    public Object intercept(Object proxy, Method method, Object[] args,
                            MethodProxy methodProxy) throws Throwable {
        System.out.println("hello cglib");
        return method.invoke(targetObject, args);
    }

    public static void main(String[] args) {

        IUserService userService = (IUserService) new CGLibProxy()
                .createProxyObject(new UserService());
        System.out.println("-----------CGLibProxy-------------");
        userService.sayHello();
        System.out.println("-----------JDKProxy-------------");
        //JDKProxy jdkPrpxy = new JDKProxy();
        //IUserService userManagerJDK = (IUserService) jdkPrpxy
        //        .newProxy(new UserService());
        //userManagerJDK.sayHello("tom", "root");
    }
}