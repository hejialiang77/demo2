package com.chris.demo.core.controller;

import com.chris.demo.core.util.XF04RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class TestBootController {

    @Autowired
    XF04RecordService xf04RecordService;

    @RequestMapping(value = "/test" ,method = RequestMethod.GET)
    public void getUser() {
        xf04RecordService.readFile();
        return ;
    }
}
