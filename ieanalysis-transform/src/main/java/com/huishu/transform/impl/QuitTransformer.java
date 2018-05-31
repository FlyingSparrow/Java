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
            for (int i = 0; i < transformConfig.getQuitThreadNum(); i++) {
                final int pageNumber = i;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info("{}:{} 投资退出数据转换开始", currentThread.getName(), currentThread.getId());
                    try {
                        transformQuitSmt(transformConfig, pageNumber);
                        transformQuitTz(transformConfig, pageNumber);
                    } catch (Exception e) {
                        logger.error("投资退出数据转换", e);
                    }
                    logger.info("{}:{} 投资退出数据转换结束", currentThread.getName(), currentThread.getId());
                });
            }
        }
    }

    private void transformQuitSmt(TransformConfig transformConfig, int pageNumber) {
        QuitDataSmt news = new QuitDataSmt();
        Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
        List<QuitDataSmt> lists = quitDataSmtService.findOneHundred(news, pageable);
        List<QuitDataBak> bakList = new ArrayList<QuitDataBak>();
        if (lists != null && lists.size() > 0) {
            for (QuitDataSmt list : lists) {
                QuitDataBak bak = new QuitDataBak();
                BeanUtils.copyProperties(list, bak);
                bak.setTime(StringUtils.transformTime(list.getTime()));
                bak.setBiaoShi("0");
                bak.setSource("私募通");
                long count = quitDataBakService.findExit(bak);
                if (count == 0) {
                    bakList.add(bak);
                }
            }
        }
        if (bakList.size() > 0) {
            quitDataBakService.save(bakList);
        }
        quitDataSmtService.delete(lists);
    }

    private void transformQuitTz(TransformConfig transformConfig, int pageNumber) {
        QuitDataTz news = new QuitDataTz();
        Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
        List<QuitDataTz> list = quitDataTzService.findOneHundred(news, pageable);
        List<QuitDataBak> bakList = new ArrayList<QuitDataBak>();
        if (list != null && list.size() > 0) {
            for (QuitDataTz item : list) {
                QuitDataBak bak = new QuitDataBak();
                bak.setFldUrlAddr(item.getFldUrlAddr());
                bak.setIndustry(item.getIndustry());
                bak.setInvestor(item.getInvestor());
                bak.setRegion(item.getRegion());
                bak.setTime(StringUtils.transformTime(item.getTime()));
                bak.setReturnAmount(item.getReturnAmount());
                bak.setCompanyName(item.getCompanyName());
                bak.setReturnMultiple(item.getReturnMultiple());
                bak.setQuitWay(item.getQuitWay());
                bak.setQuitEvent(item.getQuitEvent());
                bak.setProduct(item.getProduct());
                bak.setZongtouzie(item.getZongtouzie());
                bak.setSource("投中网");
                bak.setBiaoShi("0");
                long count = quitDataBakService.findExit(bak);
                if (count == 0) {
                    bakList.add(bak);
                }
            }
        }
        if (bakList.size() > 0) {
            quitDataBakService.save(bakList);
        }
        quitDataTzService.delete(list);
    }

}
