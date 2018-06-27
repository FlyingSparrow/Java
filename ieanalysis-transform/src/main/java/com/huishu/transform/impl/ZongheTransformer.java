package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
import com.huishu.constants.SysConst;
import com.huishu.entity.NewsLib;
import com.huishu.entity.ZongheBak;
import com.huishu.service.NewsLibService;
import com.huishu.service.ZongheBakService;
import com.huishu.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 综合新闻转换器
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("zongheTransformer")
public class ZongheTransformer extends AbstractTransformer {

    @Autowired
    private NewsLibService newsLibService;
    @Autowired
    private ZongheBakService zongheBakService;
    @Autowired
    private TransformConfig transformConfig;

    @Override
    protected void transformData(int pageNumber) {
        NewsLib entity = new NewsLib();
        Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
        List<NewsLib> list = newsLibService.findOneHundredZonghe(entity, pageable);
        if (list == null || list.isEmpty()) {
            return;
        }

        logger.info("待转换{}数据 {} 条", getName(), list.size());

        List<ZongheBak> bakList = new ArrayList<ZongheBak>(list.size());
        for (NewsLib item : list) {
            ZongheBak bak = new ZongheBak();
            BeanUtils.copyProperties(item, bak);
            bak.setFldrecddate(StringUtils.transformTime(bak.getFldrecddate()));
            bak.setType(String.valueOf(SysConst.PublishType.COMPREHENSIVE.getCode()));
            bak.setBiaoShi("0");
            long count = zongheBakService.findExist(bak);
            if (count == 0) {
                bakList.add(bak);
            }
        }
        if (bakList.size() > 0) {
            zongheBakService.save(bakList);
        }
        newsLibService.deleteZonghe(list);
    }

    @Override
    public boolean getMark() {
        return transformConfig.isZongheMark();
    }

    @Override
    public int getThreadNum() {
        return transformConfig.getZongheThreadNum();
    }

    @Override
    public String getName() {
        return "综合";
    }
}
