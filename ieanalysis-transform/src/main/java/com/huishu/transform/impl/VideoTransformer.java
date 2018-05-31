package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
import com.huishu.entity.Video;
import com.huishu.entity.VideoBak;
import com.huishu.service.VideoBakService;
import com.huishu.service.VideoService;
import com.huishu.transform.Transformer;
import com.huishu.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 视频转换器
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("videoTransformer")
public class VideoTransformer implements Transformer {

    private static Logger logger = LoggerFactory.getLogger(VideoTransformer.class);

    @Autowired
    private VideoService videoService;
    @Autowired
    private VideoBakService videoBakService;

    @Override
    public void transform(TransformConfig transformConfig, ThreadPoolExecutor executor) {
        if (transformConfig.isVideoMark()) {
            for (int i = 0; i < transformConfig.getVideoThreadNum(); i++) {
                final int pageNumber = i;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info("{}:{} 视频数据转换开始", currentThread.getName(), currentThread.getId());
                    try {
                        transformVideo(transformConfig, pageNumber);
                    } catch (Exception e) {
                        logger.error("视频数据转换异常", e);
                    }
                    logger.info("{}:{} 视频数据转换结束", currentThread.getName(), currentThread.getId());
                });
            }
        }
    }

    private void transformVideo(TransformConfig transformConfig, int pageNumber) {
        Video news = new Video();
        Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
        List<Video> list = videoService.findOneHundred(news, pageable);
        List<VideoBak> bakList = new ArrayList<VideoBak>();
        if (list != null && list.size() > 0) {
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
        }
        if (bakList.size() > 0) {
            videoBakService.save(bakList);
        }
        videoService.delete(list);
    }

}
