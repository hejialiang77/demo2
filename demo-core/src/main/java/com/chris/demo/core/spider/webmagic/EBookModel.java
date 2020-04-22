package com.chris.demo.core.spider.webmagic;

import lombok.Data;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.*;
import us.codecraft.webmagic.pipeline.JsonFilePageModelPipeline;

@TargetUrl("https://www.dytt8.net/html/gndy/dyzz/*/*.html")
//@HelpUrl({"https://www.dytt8.net/html/gndy/dyzz/*"})
@Data
public class EBookModel {

    @ExtractBy(value = "//*[@id=\"Zoom\"]/span/p[1]/text()[1]", type = ExtractBy.Type.XPath)
    private String title;

    @ExtractBy(value = "//header[@class='entry-header']/h4/text()",type = ExtractBy.Type.XPath)
    private String brief;

    @ExtractBy(value = "//header[@class='entry-header']/div/div[@class*='entry-body-thumbnail']/a/img/@src",type = ExtractBy.Type.XPath)
    private String cover;

    @ExtractBy(value = "//div[@class='book-detail']/dl/dd[1]/allText()",type = ExtractBy.Type.XPath)
    private String author;

    @ExtractBy(value = "//div[@class='book-detail']/dl/dd[2]/allText()",type = ExtractBy.Type.XPath)
    private String isbn;

    @ExtractBy(value = "//div[@class='book-detail']/dl/dd[3]/text()",type = ExtractBy.Type.XPath)
    private String year;

    @ExtractBy(value = "//div[@class='book-detail']/dl/dd[4]/text()",type = ExtractBy.Type.XPath)
    private String pages;

    @ExtractBy(value = "//div[@class='book-detail']/dl/dd[5]/allText()",type = ExtractBy.Type.XPath)
    private String language;

    @ExtractBy(value = "//div[@class='book-detail']/dl//dd[6]/allText()",type = ExtractBy.Type.XPath)
    private String fileSize;

    @ExtractBy(value = "//div[@class='book-detail']/dl/dd[7]/allText()",type = ExtractBy.Type.XPath)
    private String fileFormat;

    @ExtractBy(value = "//div[@class='book-detail']/dl/dd[8]/allText()",type = ExtractBy.Type.XPath)
    private String category;

    @ExtractBy(value = "//div[@class='entry-content']/allText()",type = ExtractBy.Type.XPath)
    private String description;

    @ExtractBy("//span[@class='download-links'][1]/a/@href")
    private String downloadLink;

    @ExtractByUrl("http://www.allitebooks.com/([\\w-]+)")
    private String url;


    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000).setCharset("utf-8")
                , new JsonFilePageModelPipeline("D:\\data\\webmagic"), EBookModel.class)
                .addUrl("https://www.dytt8.net/html/gndy/dyzz/").thread(5).run();
    }
}