package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
import com.huishu.entity.ForumLib;
import com.huishu.entity.ForumLibBak;
import com.huishu.service.ForumLibBakService;
import com.huishu.service.ForumLibService;
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

        List<ForumLibBak> bakList = new ArrayList<ForumLibBak>();
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
    protected void transformDataV2(){
        try {
            int pageNumber = 1;
            int totalPages = 10;
            ForumLib entity = new ForumLib();
            while (pageNumber <= totalPages){
                Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
                Page<ForumLib> page = forumLibService.findPageList(entity, pageable);
                totalPages = page.getTotalPages();

                List<ForumLib> list = page.getContent();
                if(list != null && list.size() > 0){
                    logger.info("总页数：{}，每页记录数：{}，剩余 {} 条{}数据待转换", page.getTotalPages(),
                            transformConfig.getTransformNum(), page.getTotalElements(), getName());

                    logger.info("第 {} 页{}数据转换开始", pageNumber, getName());

                    list.stream().forEach(item -> {
                        List<ForumLibBak> bakList = new ArrayList<ForumLibBak>();
                        for (ForumLib forumItem : list) {
                            ForumLibBak bak = new ForumLibBak();
                            BeanUtils.copyProperties(forumItem, bak);
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
                    });

                    logger.info("第 {} 页{}数据转换结束", pageNumber, getName());

                }else{
                    //如果没有数据需要分析，那么当前线程休眠5分钟
                    Thread.sleep(300000);
                }
                pageNumber++;
            }
        } catch (InterruptedException e) {
            logger.error("转换{}数据出错", getName(), e);
        }
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
