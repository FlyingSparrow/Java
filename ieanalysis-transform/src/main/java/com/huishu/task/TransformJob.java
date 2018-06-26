package com.huishu.task;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.huishu.constants.SysConst;
import com.huishu.transform.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
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
public class TransformJob implements CommandLineRunner {

    private static ThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(SysConst.DEFAULT_CORE_POOL_SIZE,
            new ThreadFactoryBuilder().setNameFormat("transform-pool-%d").build());

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

    @Autowired
    @Qualifier("industryDataTransformer")
    private Transformer industryDataTransformer;

    /**
     * 项目启动后执行
     */
    @Override
    public void run(String... strings) throws Exception {
        newsTransformer.transformV2(executor);
        policyTransformer.transformV2(executor);
        zongheTransformer.transformV2(executor);
        forumTransformer.transformV2(executor);
        videoTransformer.transformV2(executor);
        recruitmentTransformer.transformV2(executor);
        investmentTransformer.transformV2(executor);
        mergerTransformer.transformV2(executor);
        quitTransformer.transformV2(executor);
        industryDataTransformer.transformV2(executor);
    }
}
