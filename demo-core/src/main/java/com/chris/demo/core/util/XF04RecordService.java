package com.chris.demo.core.util;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.chris.demo.core.auto.Xf04RecordDO;
import com.chris.demo.core.auto.mapper.Xf04RecordDOMapper;
import com.chris.demo.core.auto.mapper.Xf04RecordDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
public class XF04RecordService {

    @Autowired
    Xf04RecordDOMapper xf04RecordDOMapper;

    List<Xf04RecordDO> list = new ArrayList<>();

    @Autowired
    Xf04RecordDao xf04RecordDao;

    static final String dirName = "E:\\xf04银行对账\\njymmjour9";
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
            log.info("总记录为{}条" ,list.size());
            ExecutorService threadPool = Executors.newFixedThreadPool(100);
            List<Xf04RecordDO> listSub = null;
            for (int i = 0; i < list.size();i++) {
                Xf04RecordDO tmp = list.get(i);
                if (listSub == null) {
                    listSub = new ArrayList<>();
                }
                listSub.add(tmp);
                if ((i+1) % 1000 == 0 || i == (list.size() - 1)) {
                    threadPool.execute(new XF04Runnable(listSub,xf04RecordDao ));
                    listSub = null;
                }
            }


        } catch (Exception e) {
            log.error("异常了", e);
        }

        return "ok le";
    }

    private void readSingleFile(String file) throws Exception {

        FileReader fr = new FileReader(new File(dirName, file));
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            if (StringUtils.isBlank(line)) {
                continue;
            }
            lineCount++;
            lineSpilt(line);
        }
        br.close();
        fr.close();
    }


    private void lineSpilt(String string) {
        String[] strs = string.split("\\|");

        Xf04RecordDO record = new Xf04RecordDO();
        record.setBankSerNo(strs[25]);
        record.setDateacct(strs[3]);
        record.setSerialno(convertSerialno(strs[25]));
        list.add(record);
    }

    private String convertSerialno(String str) {
        //16 ->
        String string = String.valueOf(Long.parseLong(str, 16));

        return string.substring(0, string.length() - 2);
    }

    @AllArgsConstructor
    class XF04Runnable implements Runnable {
        List<Xf04RecordDO> list;
        Xf04RecordDao recordDao;

        @Override
        public void run() {
            recordDao.batchInsert(list);
        }
    }

    public static void main(String[] args) {
    }
}
