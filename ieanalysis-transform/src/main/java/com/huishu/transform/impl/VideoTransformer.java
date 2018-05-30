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
            for (int x = 0; x < transformConfig.getVideoThreadNum(); x++) {
                final int tempNum = x;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info(currentThread.getName() + ":"
                            + currentThread.getId() + "视频数据转换开始");
                    try {
                        transformVideo(transformConfig, tempNum);
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("视频数据转换异常", e);
                    }
                    logger.info(currentThread.getName() + ":"
                            + currentThread.getId() + "视频数据转换结束");
                });
            }
        }
    }

    private void transformVideo(TransformConfig transformConfig, int tempNum) {
        Video news = new Video();
        Pageable pageable = new PageRequest(tempNum, transformConfig.getTransformNum());
        List<Video> lists = videoService.findOneHundred(news, pageable);
        List<VideoBak> bakList = new ArrayList<VideoBak>();
        if (lists != null && lists.size() > 0) {
            for (Video list : lists) {
                VideoBak bak = new VideoBak();
                BeanUtils.copyProperties(list, bak);
                bak.setFabushijian(StringUtils.transformTime(bak.getFabushijian()));
                bak.setBiaoShi("0");
                long count = videoBakService.findExist(bak);
                if (count == 0) {
                    bakList.add(bak);
                }
            }
        }
        if (bakList != null && bakList.size() > 0) {
            videoBakService.save(bakList);
        }
        videoService.delete(lists);
    }

}
