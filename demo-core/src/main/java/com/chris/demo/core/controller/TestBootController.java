package com.chris.demo.core.controller;

import com.chris.demo.core.util.XF04RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController()
public class TestBootController {

    @Autowired
    XF04RecordService xf04RecordService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void getUser() {
//        xf04RecordService.readFile();
        return;
    }

    @RequestMapping(value = "/payrollDemoDownload", method = RequestMethod.GET)
    public void payrollDemoDownload() {
        ResponseEntity<byte[]> result = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "测试.xlsx");
        try {
            result = new ResponseEntity<>(Files.readAllBytes(Paths.get("D:\\work\\连薪\\测试工资表.xlsx")), headers, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
