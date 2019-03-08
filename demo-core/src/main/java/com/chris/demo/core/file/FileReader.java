package com.chris.demo.core.file;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * todo
 *
 * @ClassName FileReader
 * @Author jlhe
 * @Date 2019/1/25
 * @Version 1.0
 */
public class FileReader {

    private static String path = "E:\\TG63补单\\0928\\";


    public static void readerFile(String filename) throws IOException {
        StringBuilder sb = new StringBuilder("(");
        StringBuilder sb2 = new StringBuilder();
        List<String> th = Files.readAllLines(Paths.get(path + filename));
        int i = 1;
        for (String str : th) {
            if (StringUtils.isBlank(str)) {
                continue;
            }
            String[] strs = str.split("#\\*#");

            String serialNo =    strs[0];
             JSONObject object = JSON.parseObject(strs[2]);
            String currfixSerialNo = object.getString("paySerialNo");
            sb2.append(serialNo).append(",").append(currfixSerialNo).append("\n");
            sb.append(serialNo).append(",");
            if (i%5 ==0){
                sb.append("\n");
            }
            i++;
        }
        sb.append(")\n\n\n---------------------------------------\n").append(sb2);
        Files.write(Paths.get(path + "ed" + filename) ,sb.toString().getBytes() );

    }


    public static void main(String[] args) throws IOException {
        readerFile("1008补单1");
        readerFile("1008补单2");

    }


}
