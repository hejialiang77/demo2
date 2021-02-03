package com.chris.demo.core.controller;

import com.chris.demo.core.domain.User;
import com.chris.demo.core.util.XF04RecordService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController()
public class TestBootController {

    @Autowired
    XF04RecordService xf04RecordService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void getUser() {
//        xf04RecordService.readFile();
        return;
    }
    @RequestMapping(value = "/test2")
    public User getUser2(@RequestBody User user) {
        user.setId(1);
        user.setName("2");
        user.setAge(3);

        return user;
    }


    @RequestMapping(value = "/test1")
    public String getUser2(String method, @RequestBody RestSMS body, HttpServletRequest request) throws IOException {

        System.out.println(method);
        BufferedReader br = new BufferedReader(new InputStreamReader(
                request.getInputStream(), "UTF-8"));
        String buffer = null;
        // 存放请求内容
        StringBuffer xml = new StringBuffer();
        while ((buffer = br.readLine()) != null) {
            // 在页面中显示读取到的请求参数
            xml.append(buffer);
        }

        String callbackMessage = xml.toString();
        request.getContentType();
        return "ok";
    }




    @Data
    @XmlRootElement(name ="request")
    public  static class RestSMS {
        private List<EntryOrder> order;
    }
    @Data
    @XmlRootElement(name ="entryOrder")
    public static class EntryOrder {
        private String totalOrderLines;
        private String ownerCode;
        private String entryOrderCode;
        private String entryOrderType;
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
