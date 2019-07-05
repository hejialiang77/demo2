package com.chris.demo.core.controller;

import com.alibaba.fastjson.JSON;
import com.chris.demo.core.domain.UserFile;
import com.chris.demo.core.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController()
public class TestCloudController {

    @Value("${server.port}")
    String port;

    @Autowired
    UserService userService;

    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi " + name + ",i am from port:" + port;
    }

    @RequestMapping(value = "/testNotify", method = RequestMethod.POST)
    public String testNotify(HttpServletRequest request) {
        System.out.println(request.getHeaderNames());
        System.out.println(JSON.toJSONString(request));
        userService.sayHello();
        return port;
    }

    @RequestMapping("/uploadFile")
    public String uploadFile( UserFile file) {
        file.getFile();
        return file.getFile().getName();
    }


}

