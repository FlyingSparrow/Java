package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
import com.huishu.entity.QuitDataBak;
import com.huishu.entity.QuitDataSmt;
import com.huishu.entity.QuitDataTz;
import com.huishu.service.QuitDataBakService;
import com.huishu.service.QuitDataSmtService;
import com.huishu.service.QuitDataTzService;
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
 * 投资退出数据转换器
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("quitTransformer")
public class QuitTransformer implements Transformer {

    private static Logger logger = LoggerFactory.getLogger(QuitTransformer.class);

    @Autowired
    private QuitDataTzService quitDataTzService;
    @Autowired
    private QuitDataSmtService quitDataSmtService;
    @Autowired
    private QuitDataBakService quitDataBakService;

    @Override
    public void transform(TransformConfig transformConfig, ThreadPoolExecutor executor) {
        if (transformConfig.isQuitMark()) {
            // 转换招聘数据
            for (int x = 0; x < transformConfig.getQuitThreadNum(); x++) {
                final int tempNum = x;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info(currentThread.getName() + ":"
                            + currentThread.getId() + "投资退出数据转换开始");
                    try {
                        transformQuitSmt(transformConfig, tempNum);
                        transformQuitTz(transformConfig, tempNum);
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("投资退出数据转换", e);
                    }
                    logger.info(currentThread.getName() + ":"
                            + currentThread.getId() + "投资退出数据转换结束");
                });
            }
        }
    }

    private void transformQuitSmt(TransformConfig transformConfig, int tempNum) {
        QuitDataSmt news = new QuitDataSmt();
        Pageable pageable = new PageRequest(tempNum, transformConfig.getTransformNum());
        List<QuitDataSmt> lists = quitDataSmtService.findOneHundred(news, pageable);
        List<QuitDataBak> bakList = new ArrayList<QuitDataBak>();
        if (lists != null && lists.size() > 0) {
            for (QuitDataSmt list : lists) {
                QuitDataBak bak = new QuitDataBak();
                BeanUtils.copyProperties(list, bak);
                bak.setTime(StringUtils.toTransformTime(list.getTime()));
                bak.setBiaoShi("0");
                bak.setSource("私募通");
                long count = quitDataBakService.findExit(bak);
                if (count == 0) {
                    bakList.add(bak);
                }
            }
        }
        if (bakList != null && bakList.size() > 0) {
            quitDataBakService.save(bakList);
        }
        quitDataSmtService.delete(lists);
    }

    private void transformQuitTz(TransformConfig transformConfig, int tempNum) {
        QuitDataTz news = new QuitDataTz();
        Pageable pageable = new PageRequest(tempNum, transformConfig.getTransformNum());
        List<QuitDataTz> lists = quitDataTzService.findOneHundred(news, pageable);
        List<QuitDataBak> bakList = new ArrayList<QuitDataBak>();
        if (lists != null && lists.size() > 0) {
            for (QuitDataTz list : lists) {

                QuitDataBak bak = new QuitDataBak();
                bak.setFldUrlAddr(list.getFldUrlAddr());
                bak.setIndustry(list.getIndustry());
                bak.setInvestor(list.getInvestor());
                bak.setRegion(list.getRegion());
                bak.setTime(StringUtils.toTransformTime(list.getTime()));
                bak.setReturnAmount(list.getReturnAmount());
                bak.setCompanyName(list.getCompanyName());
                bak.setReturnMultiple(list.getReturnMultiple());
                bak.setQuitWay(list.getQuitWay());
                bak.setQuitEvent(list.getQuitEvent());
                bak.setProduct(list.getProduct());
                bak.setZongtouzie(list.getZongtouzie());
                bak.setSource("投中网");
                bak.setBiaoShi("0");
                long count = quitDataBakService.findExit(bak);
                if (count == 0) {
                    bakList.add(bak);
                }
            }
        }
        if (bakList != null && bakList.size() > 0) {
            quitDataBakService.save(bakList);
        }
        quitDataTzService.delete(lists);
    }

}
