package com.sparrow.opinion.jmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jd.jmq.client.consumer.MessageListener;
import com.jd.jmq.common.message.Message;
import com.sparrow.opinion.config.CustomConfig;
import com.sparrow.opinion.constants.SysConst;
import com.sparrow.opinion.mongodb.entity.Article;
import com.sparrow.opinion.mongodb.service.ArticleService;
import com.sparrow.opinion.utils.DateUtils;
import com.sparrow.opinion.utils.HttpRequestProxy;
import com.sparrow.opinion.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 消息监听器
 */
@Component
public class DefaultMessageListener implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(DefaultMessageListener.class);
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CustomConfig customConfig;
    private Date today = DateUtils.currentDate();
    private int count = 0;
    

    /**
     * 消费方法。注意: 消费不成功请抛出异常，MQ会自动重试
     *
     * @param messages
     * @throws Exception
     */
    @Override
    public void onMessage(List<Message> messages) throws Exception {
    	String[] urls = customConfig.getCoreAnalysisUrls().split(",");
        try {
            if (messages == null || messages.isEmpty()) {
                return;
            }
            final Random rand = new Random();
            for (Message message : messages) {
            	int randUrlKey = rand.nextInt(urls.length);
                JSONObject jsonObject = JSON.parseObject(message.getText());
                count++;
                if(Integer.parseInt(DateUtils.formatDate(today, DateUtils.DATE_FORMAT_2))
                        < Integer.parseInt(DateUtils.formatDate(DateUtils.currentDate(),DateUtils.DATE_FORMAT_2))){
                    logger.error("{} 推送了 {} 条数据", DateUtils.formatDate(today, DateUtils.DATE_FORMAT), count);
                    count = 0;
                    today = DateUtils.currentDate();
                }
                if (check(jsonObject)) {
                    String url = jsonObject.getString("detail_url");
                    String urlMD5 = StringUtils.md5(url);
                    Date crawlTime = jsonObject.getDate("crawl_time");
                    String publishDateStr = StringUtils.adjustDateTime(
                            jsonObject.getString("publish_time"));

                    //如果发布时间为空的话，默认发布时间为当前时间
                    Date currentDate = new Date();
                    Date publishTime = new Date();
                    if (StringUtils.isNotEmpty(publishDateStr)) {
                        try {
                            publishTime = DateUtils.parseDate(publishDateStr);
                        } catch(Exception e) {
                            //发布时间解析出错，将发布时间设置为当前时间
                            publishTime = currentDate;
                            logger.error("解析发布时间发生异常，错误的发布时间[{}]", publishDateStr);
                        }
                    }
                    if (publishTime.compareTo(currentDate) > 0) {
                        //如果发布时间大于当前时间，那么设置发布时间为当前时间
                        publishTime = currentDate;
                    }
                    Article article = new Article();
                    article.setTitle(jsonObject.getString("title"));
                    article.setContent(jsonObject.getString("content"));
                    article.setPublishDateTime(publishTime);
                    article.setPublishDate(DateUtils.formatDate(publishTime));
                    article.setHot(0);
                    article.setAuthor(jsonObject.getString("author"));
                    article.setKeywords(jsonObject.getString("key_word"));
                    article.setUrl(url);
                    article.setUrlMD5(urlMD5);
                    article.setCrawlerDate(DateUtils.formatDate(crawlTime));
                    article.setCrawlerDateTime(crawlTime);
                    article.setIsAnalyzed("否");
                    article.setTaskId(jsonObject.getString("taskId"));
                    try {
                        String articleId = articleService.add(article);

                        String postUrl = String.format("http://%s/analysis/put",
            					urls[randUrlKey]);

                        Map<String, String> datas = new HashMap<String, String>();
            			datas.put("articleId", articleId);

                        logger.error("准备分析!!!");
            			HttpRequestProxy.doPost(postUrl, datas, SysConst.ENCODING_UTF_8);
            			logger.error("分析完成!!!");

                        logger.error("发送了一条待分析的文章id[{}], 发布时间: {}", articleId, article.getPublishDate());
                    } catch (Exception e) {
                        Throwable cause = e.getCause();
                        if(cause != null){
                            if("com.mongodb.DuplicateKeyException".equals(cause.getClass().getName())){
                                logger.error("文章已存在，保存出错");
                            }else{
                                logger.error("文章保存出错", e);
                            }
                        }else{
                            logger.error("文章保存出错", e);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("文章处理出错，异常信息：", e);
        }
    }

    /**
     * 检查采集数据是否有效
     *
     * @param jsonObject 采集数据
     * @return 验证结果
     */
    private boolean check(JSONObject jsonObject) {
        String url = jsonObject.getString("detail_url");
        String keywords = jsonObject.getString("key_word");
        if (!url.startsWith("http")) {
            //如果原文链接字段存储的内容不是url，那么跳过本次循环，不做处理
            logger.error("url[{}]的文章的原文链接不是URL，属于无效数据，不做处理", url);
            return false;
        } else if (StringUtils.isNotEmpty(keywords) && keywords.endsWith("万元人民币")) {
            logger.error("url[{}]的文章的关键词无效，属于无效数据，不做处理", url, keywords);
            return false;
        } else {
            return true;
        }
    }
}
