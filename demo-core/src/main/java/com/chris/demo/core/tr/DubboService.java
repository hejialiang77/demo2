/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.chris.demo.core.tr;


import com.chris.demo.facade.IDubboService;

/**
 *
 * @author chris
 * @version $Id: DubboService.java, v 0.1 2018年04月25日 下午2:57 chris Exp $
 */
public class DubboService implements IDubboService {
    @Override
    public String sayHello() {
        return "hello world";
    }
}