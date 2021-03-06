/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.chris.demo.core.service.impl;


import com.chris.demo.core.service.IUserService;
import org.springframework.stereotype.Component;

/**
 *
 * @author chris
 * @version $Id: UserService.java, v 0.1 2018年03月16日 下午3:01 chris Exp $
 */
@Component
public class UserService implements IUserService {

    @Override
    public void sayHello() {
        System.out.println("Hello");
    }

    @Override
    public String sayBye() {
        return "goodBye";
    }
}