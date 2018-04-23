/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.chris.demo.proxy;

import com.chris.demo.service.IUserService;
import com.chris.demo.service.impl.UserService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * @author chris
 * @version $Id: UserProxyHandler.java, v 0.1 2018年03月16日 下午3:02 chris Exp $
 */
public class UserJDKProxyHandler implements InvocationHandler {

    private Object target;

    public UserJDKProxyHandler(Object target) {this.target = target;}

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("test start");
        Object result = method.invoke(target, args);
        System.out.println("test end");
        return result;
    }

    public static void main(String[] args) {
        UserService userService = new UserService();
        IUserService proxyUserService = (IUserService) Proxy.newProxyInstance(IUserService.class.getClassLoader(),
                UserService.class.getInterfaces(),
                new UserJDKProxyHandler(userService));
        proxyUserService.sayHello();
    }
}