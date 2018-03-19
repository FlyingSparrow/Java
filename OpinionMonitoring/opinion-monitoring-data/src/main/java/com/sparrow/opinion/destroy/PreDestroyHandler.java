package com.sparrow.opinion.destroy;

import com.sparrow.opinion.utils.OpinionAnalysisUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * Created by wangjianchun on 2017/10/30.
 * SpringBoot退出服务时执行自定义操作，用于保存算法jar文件中一些累计缓存数据(压力指数)
 */
@Component
public class PreDestroyHandler {

    @PreDestroy
    public void destroy() {
        OpinionAnalysisUtils.save();
    }
}
