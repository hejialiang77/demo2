/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.chris.demo.core.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 *
 * @author chris
 * @version $Id: User.java, v 0.1 2018年03月01日 下午3:48 chris Exp $
 */
@Data
public class UserFile implements Serializable{
    private String name;

    MultipartFile file;

}