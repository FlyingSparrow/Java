package com.huishu.task;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.huishu.config.TransformConfig;
import com.huishu.constants.SysConst;
import com.huishu.transform.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 转换任务
 * <p>
 * Title: TransformTask
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author xiaobo
 * @date 2017年4月10日
 */
@Component
public class TransformTask {

    private static ThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(SysConst.DEFAULT_CORE_POOL_SIZE,
            new ThreadFactoryBuilder().setNameFormat("transform-pool-%d").build());

    @Autowired
    private TransformConfig transformConfig;

    @Autowired
    @Qualifier("newsTransformer")
    private Transformer newsTransformer;

    @Autowired
    @Qualifier("policyTransformer")
    private Transformer policyTransformer;

    @Autowired
    @Qualifier("zongheTransformer")
    private Transformer zongheTransformer;

    @Autowired
    @Qualifier("forumTransformer")
    private Transformer forumTransformer;

    @Autowired
    @Qualifier("videoTransformer")
    private Transformer videoTransformer;

    @Autowired
    @Qualifier("recruitmentTransformer")
    private Transformer recruitmentTransformer;

    @Autowired
    @Qualifier("investmentTransformer")
    private Transformer investmentTransformer;

    @Autowired
    @Qualifier("mergerTransformer")
    private Transformer mergerTransformer;

    @Autowired
    @Qualifier("quitTransformer")
    private Transformer quitTransformer;

    /**
     * 间隔15秒钟执行一次
     */
    @Scheduled(fixedDelay = 1000 * 15)
    public void warn() {
        newsTransformer.transform(transformConfig, executor);
        policyTransformer.transform(transformConfig, executor);
        zongheTransformer.transform(transformConfig, executor);
        forumTransformer.transform(transformConfig, executor);
        videoTransformer.transform(transformConfig, executor);
        recruitmentTransformer.transform(transformConfig, executor);
        investmentTransformer.transform(transformConfig, executor);
        mergerTransformer.transform(transformConfig, executor);
        quitTransformer.transform(transformConfig, executor);
    }

}
