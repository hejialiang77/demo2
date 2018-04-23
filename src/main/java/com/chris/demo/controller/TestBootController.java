package com.chris.demo.controller;

import com.chris.demo.dao.UserMapper;
import com.chris.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class TestBootController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "/test" ,method = RequestMethod.GET)
    public User getUser() {
        User user = new User();

        //user.setName("chris");
        //user.setAge(27);

        userMapper.updataById("1","chrisCache");
        user = userMapper.selectByPrimaryKey("1");


        return user;
    }
}
