package com.huishu.config;

import com.sc.articleToKeywordCloud.ArticleConToKeywordCloud;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjianchun
 */
@Component
@Order(value = 1)
public class RunConfig implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(RunConfig.class);

    @Autowired
    private CustomConfig customConfig;

    /**
     * 项目启动后执行
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        logger.info(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作开始<<<<<<<<<<<<<");
        initWordCloud();
        logger.info(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作完成<<<<<<<<<<<<<");
    }

    private void initWordCloud() {
        System.setProperty("appPath", customConfig.getWordCloudPath());
        List<String> list = new ArrayList<String>();
        list.add("双创");
        ArticleConToKeywordCloud.toKeywordCloud(list, 0, 1);
    }
}
