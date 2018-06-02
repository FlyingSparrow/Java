package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
import com.huishu.entity.Video;
import com.huishu.entity.VideoBak;
import com.huishu.service.VideoBakService;
import com.huishu.service.VideoService;
import com.huishu.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
