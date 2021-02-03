package com.chris.demo.core.spider.webmagic;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.processor.example.BaiduBaikePageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

/**
 * todo
 *
 * @ClassName EbookSpiderw
 * @Author jlhe
 * @Date 2019/7/9
 * @Version 1.0
 */
public class EbookSpider extends Spider {

    public EbookSpider(PageProcessor pageProcessor) {
        super(pageProcessor);
    }

    public static void main(String[] args) {
        Spider.create(new BaiduBaikePageProcessor())
                //从https://github.com/code4craft开始抓
                .addUrl("https://baike.baidu.com/item/%E9%95%BF%E5%AE%89%E5%8D%81%E4%BA%8C%E6%97%B6%E8%BE%B0/20110435")
                //设置Scheduler，使用Redis来管理URL队列
                .setScheduler(new FileCacheQueueScheduler("D:\\data\\fileCache"))
                //设置Pipeline，将结果以json方式保存到文件
                .addPipeline(new JsonFilePipeline("D:\\data\\webmagic"))
                //开启5个线程同时执行
                .thread(5)
                //启动爬虫
                .run();
    }
}
