package com.chris.demo.core.util;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.chris.demo.core.auto.Xf04RecordDO;
import com.chris.demo.core.auto.mapper.Xf04RecordDOMapper;
import com.chris.demo.core.auto.mapper.Xf04RecordDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 光大傻逼
 *
 * @ClassName XF04Record
 * @Author jlhe
 * @Date 2018/9/7
 * @Version 1.0
 */
@Slf4j
@Component
public class HcbRecord {

    @Autowired
    Xf04RecordDOMapper xf04RecordDOMapper;

    List<Xf04RecordDO> list = new ArrayList<>();

    @Autowired
    Xf04RecordDao xf04RecordDao;

    HashMap<String, HashMap<String, BigDecimal>> map = new HashMap<>();


    static final String dirName = "E:\\光大对账";
    private long lineCount = 0L;

    public String readFile() {
        //找出所有文件切割插入表里面
        try {
            File dirFile = new File(dirName);
            //如果dir对应的文件不存在，或者不是一个文件夹则退出
            if (!dirFile.exists() || (!dirFile.isDirectory())) {
                System.out.println("List失败！找不到目录：" + dirName);
            }

            String[] files = dirFile.list();
            for (int i = 0; i < files.length; i++) {
                System.out.println(files[i]);
                //读单个文件，写入数据库
                readSingleFile(files[i]);
            }
            System.out.println(JSON.toJSONString(map));
        } catch (Exception e) {
            log.error("异常了", e);
        }

        return "ok le";
    }

    private void readSingleFile(String file) throws Exception {

        FileReader fr = new FileReader(new File(dirName, file));
        BufferedReader br = new BufferedReader(fr);
        String line;
        int i = 0;
        while ((line = br.readLine()) != null) {
            if (i == 0) {
                i = 1;
                continue;
            }
            if (StringUtils.isBlank(line)) {
                continue;
            }
            lineSpilt(line);
        }
        br.close();
        fr.close();
    }


    private void lineSpilt(String string) {
        String[] strs = string.split(",");
        HashMap<String, BigDecimal> tmp;
        String payerId = strs[9];
        if (!payerId.equals("4584519")&&!payerId.equals("8998273") ){
            return;
        }
        String type = strs[20];
        String amt = strs[21];
        if (!map.containsKey(payerId)) {
            map.put(payerId, new HashMap<>());
        }
        tmp = map.get(payerId);
        if (!tmp.containsKey(type)) {
            tmp.put(type, new BigDecimal(0));

        }
        tmp.put(type, tmp.get(type).add(new BigDecimal(amt)));
    }


    public static void main(String[] args) {

        HcbRecord hcbRecord = new HcbRecord();
        hcbRecord.readFile();
    }
}
