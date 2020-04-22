package com.chris.demo.core.spider.webmagic;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

@Component
public class EBookPip implements PageModelPipeline {


    @Override
    public void process(Object o, Task task) {
        if(o instanceof EBookModel) {
            EBookModel model = (EBookModel) o;
            EbookDO edo = new EbookDO();
            edo.setTitle(model.getTitle());
            edo.setAuthor(model.getAuthor());
            edo.setBrief(model.getBrief());
            edo.setCategory(model.getCategory());
            edo.setCover(model.getCover());
            edo.setDescription(model.getDescription());
            edo.setDownloadLink(model.getDownloadLink());
            edo.setFileFormat(model.getFileFormat());
            edo.setFileSize(model.getFileSize());
            edo.setIsbn(model.getIsbn());
            edo.setLanguage(model.getLanguage());
            edo.setYear(model.getYear());
            edo.setPages(model.getPages());
            edo.setUrl(model.getUrl());
            try {
//                eBookAO.save(edo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}