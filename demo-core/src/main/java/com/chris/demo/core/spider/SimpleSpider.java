package com.chris.demo.core.spider;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * todo
 *
 * @ClassName SimpleSpider
 * @Author jlhe
 * @Date 2019/7/9
 * @Version 1.0
 */
public class SimpleSpider {

    public void simpleCraw() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        BufferedWriter writer = null;
        try {
            HttpGet httpget = new HttpGet("http://httpbin.org/");
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(HttpResponse response) throws IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    }
                    return null;
                }
            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            writer = Files.newBufferedWriter(Paths.get("D:\\tmp\\testCraw.html"), StandardCharsets.UTF_8);
            System.out.println(responseBody);
            writer.write(responseBody);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        SimpleSpider ss = new SimpleSpider();
        ss.simpleCraw();
    }
}
