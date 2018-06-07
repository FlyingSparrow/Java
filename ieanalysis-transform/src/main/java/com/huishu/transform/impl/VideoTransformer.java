package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
import com.huishu.entity.Video;
import com.huishu.entity.VideoBak;
import com.huishu.service.VideoBakService;
import com.huishu.service.VideoService;
import com.huishu.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 视频转换器
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("videoTransformer")
public class VideoTransformer extends AbstractTransformer  {

    @Autowired
    private VideoService videoService;
    @Autowired
    private VideoBakService videoBakService;
    @Autowired
    private TransformConfig transformConfig;

    @Override
    protected void transformData(int pageNumber) {
        Video entity = new Video();
        Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
        List<Video> list = videoService.findOneHundred(entity, pageable);
        if (list == null || list.isEmpty()) {
            return;
        }

        List<VideoBak> bakList = new ArrayList<VideoBak>();
        for (Video item : list) {
            VideoBak bak = new VideoBak();
            BeanUtils.copyProperties(item, bak);
            bak.setFabushijian(StringUtils.transformTime(bak.getFabushijian()));
            bak.setBiaoShi("0");
            long count = videoBakService.findExist(bak);
            if (count == 0) {
                bakList.add(bak);
            }
        }
        if (bakList.size() > 0) {
            videoBakService.save(bakList);
        }
        videoService.delete(list);
    }

    @Override
    protected void transformData() throws InterruptedException {
        int pageNumber = 0;
        int totalPages = 10;
        Video entity = new Video();
        while (pageNumber <= totalPages){
            Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
            Page<Video> page = videoService.findByPage(entity, pageable);
            totalPages = page.getTotalPages();

            List<Video> list = page.getContent();
            if(list != null && list.size() > 0){
                logger.info("总页数：{}，每页记录数：{}，剩余 {} 条{}数据待转换", page.getTotalPages(),
                        transformConfig.getTransformNum(), page.getTotalElements(), getName());

                logger.info("第 {} 页{}数据转换开始", pageNumber, getName());


                List<VideoBak> bakList = new ArrayList<VideoBak>();
                for (Video item : list) {
                    VideoBak bak = new VideoBak();
                    BeanUtils.copyProperties(item, bak);
                    bak.setFabushijian(StringUtils.transformTime(bak.getFabushijian()));
                    bak.setBiaoShi("0");
                    long count = videoBakService.findExist(bak);
                    if (count == 0) {
                        bakList.add(bak);
                    }
                }
                if (bakList.size() > 0) {
                    videoBakService.save(bakList);
                }
                videoService.delete(list);

                logger.info("第 {} 页{}数据转换结束", pageNumber, getName());

            }else{
                //如果没有数据需要分析，那么当前线程休眠5分钟
                Thread.sleep(300000);
            }
            pageNumber++;
        }
    }

    @Override
    public boolean getMark() {
        return transformConfig.isVideoMark();
    }

    @Override
    public int getThreadNum() {
        return transformConfig.getVideoThreadNum();
    }

    @Override
    public String getName() {
        return "视频";
    }
}
