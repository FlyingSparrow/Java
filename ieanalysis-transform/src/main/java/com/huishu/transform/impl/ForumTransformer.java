package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
import com.huishu.entity.ForumLib;
import com.huishu.entity.ForumLibBak;
import com.huishu.service.ForumLibBakService;
import com.huishu.service.ForumLibService;
import com.huishu.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 论坛数据转换器
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("forumTransformer")
public class ForumTransformer extends AbstractTransformer {

    @Autowired
    private ForumLibService forumLibService;
    @Autowired
    private ForumLibBakService forumLibBakService;
    @Autowired
    private TransformConfig transformConfig;

    @Override
    protected void transformData(int pageNumber) {
        ForumLib entity = new ForumLib();
        Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
        List<ForumLib> list = forumLibService.findOneHundred(entity, pageable);
        if (list == null || list.isEmpty()) {
            return;
        }

        logger.info("待转换{}数据 {} 条", getName(), list.size());

        List<ForumLibBak> bakList = new ArrayList<ForumLibBak>(list.size());
        for (ForumLib item : list) {
            ForumLibBak bak = new ForumLibBak();
            BeanUtils.copyProperties(item, bak);
            bak.setFldrecddate(StringUtils.transformTime(bak.getFldrecddate()));
            bak.setBiaoShi("0");
            long count = forumLibBakService.findExist(bak);
            if (count == 0) {
                bakList.add(bak);
            }
        }

        if (bakList.size() > 0) {
            forumLibBakService.save(bakList);
        }
        forumLibService.delete(list);
    }

    @Override
    public boolean getMark() {
        return transformConfig.isForumMark();
    }

    @Override
    public int getThreadNum() {
        return transformConfig.getForumThreadNum();
    }

    @Override
    public String getName() {
        return "论坛";
    }
}
