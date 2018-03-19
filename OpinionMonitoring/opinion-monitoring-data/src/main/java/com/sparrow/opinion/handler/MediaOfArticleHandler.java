package com.sparrow.opinion.handler;/**
 * Created by lenovo on 2017/7/20.
 */

import com.sparrow.opinion.constants.SysConst;
import com.sparrow.opinion.mongodb.entity.Article;
import com.sparrow.opinion.mongodb.service.ArticleService;
import com.sparrow.opinion.mysql.entity.Media;
import com.sparrow.opinion.mysql.entity.MediaType;
import com.sparrow.opinion.mysql.service.MediaService;
import com.sparrow.opinion.mysql.service.MediaTypeService;
import com.sparrow.opinion.utils.DateUtils;
import com.sparrow.opinion.utils.EncryptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: MediaOfArticleHandler</p>
 * <p>Description: 文章的媒体信息处理器，根据文章的url更新文章的媒体名称和媒体类型</p>
 *
 * @author lenovo
 * @date 2017/7/20
 * ${}
 */
@Component
public class MediaOfArticleHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MediaService mediaService;
    @Autowired
    private MediaTypeService mediaTypeService;
    @Autowired
    private ArticleService articleService;

    public void handle(Article article) throws InterruptedException, MalformedURLException {
        String userAccount = SysConst.DEFAULT_USER_ACCOUNT;
        Date currentDate = DateUtils.currentDate();
        String mediaUrl = com.sparrow.opinion.utils.StringUtils.getWebsiteUrl(article.getUrl());
        String urlMD5 = EncryptionUtils.md5(mediaUrl);
        List<Media> mediaList = mediaService.findByUrlMD5(urlMD5);
        if (mediaList != null && mediaList.size() > 0) {
            article.setMedia(mediaList.get(0).getMediaName());
        } else {
            /*article.setMedia(WebsiteUtil.getWebSiteName(article.getUrl()));
            //当前线程休眠300毫秒，以免对请求的网站造成太大压力
            Thread.sleep(300);
            String mediaName = article.getMedia();
            if (StringUtils.isNotBlank(mediaName)) {
                Media media = new Media();
                media.setMediaName(mediaName);
                media.setMediaUrl(mediaUrl);
                media.setUrlMD5(urlMD5);
                media.setWeight(WebsiteUtil.getWebSiteWeight(media.getMediaUrl()));
                media.setCreatedUser(userAccount);
                media.setModifiedUser(userAccount);
                media.setCreatedDate(currentDate);
                media.setModifiedDate(currentDate);
                mediaService.add(media);
            } else {
                logger.info("标题[{}]的文章，媒体名称为空，文章链接：{}",
                        article.getTitle(), article.getUrl());

                *//**
                 * 如果媒体名称为空，说明该网站在工业和信息化部未备案或者备案取消，
                 * 部分网站，例如香港文汇网（http://www.wenweipo.com/）
                 * 在工信部是查询不到网站的备案信息的，因此不能简单地认为查询不到备案信息
                 * 的网站就一定是非法网站或者不正规的网站
                 *//*
                article.setMedia("未备案或者备案取消");
            }*/


            /**
             * 2018-03-05 wangjianchun
             * 说明：由于测试环境的服务器和生产环境的服务器无法访问外网，
             * 所以不再通过访问站长之家网站查询网站的名称和权重
             */
            logger.info("标题[{}]的文章，媒体名称为空，文章链接：{}",
                    article.getTitle(), article.getUrl());

            /**
             * 如果媒体名称为空，说明该网站在工业和信息化部未备案或者备案取消，
             * 部分网站，例如香港文汇网（http://www.wenweipo.com/）
             * 在工信部是查询不到网站的备案信息的，因此不能简单地认为查询不到备案信息
             * 的网站就一定是非法网站或者不正规的网站
             */
            article.setMedia("未备案或者备案取消");
        }
        String mediaHost = com.sparrow.opinion.utils.StringUtils.getMediaHost(mediaUrl);
        MediaType mediaTypeEntity;
        List<MediaType> mediaTypeList = mediaTypeService.findByByMediaUrl(mediaHost);
        if(mediaTypeList != null && mediaTypeList.size() > 0){
            mediaTypeEntity = mediaTypeList.get(0);
        }else{
            mediaTypeEntity = new MediaType();
            mediaTypeEntity.setMediaName(article.getMedia());
            mediaTypeEntity.setMediaType(SysConst.MediaType.NEWS.getName());
            mediaTypeEntity.setMediaUrl(mediaHost);
            mediaTypeEntity.setCreatedUser(userAccount);
            mediaTypeEntity.setModifiedUser(userAccount);
            mediaTypeEntity.setCreatedDate(currentDate);
            mediaTypeEntity.setModifiedDate(currentDate);
            mediaTypeService.save(mediaTypeEntity);
        }
        article.setMediaType(mediaTypeEntity.getMediaType());
        articleService.save(article);
    }

}
