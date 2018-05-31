package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
import com.huishu.entity.MergerDataBak;
import com.huishu.entity.MergerDataSmt;
import com.huishu.entity.MergerDataTz;
import com.huishu.service.MergerDataBakService;
import com.huishu.service.MergerDataSmtService;
import com.huishu.service.MergerDataTzService;
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
 * 投资并购数据转换器
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("mergerTransformer")
public class MergerTransformer implements Transformer {

    private static Logger logger = LoggerFactory.getLogger(MergerTransformer.class);

    @Autowired
    private MergerDataTzService mergerDataTzService;
    @Autowired
    private MergerDataSmtService mergerDataSmtService;
    @Autowired
    private MergerDataBakService mergerDataBakService;

    @Override
    public void transform(TransformConfig transformConfig, ThreadPoolExecutor executor) {
        if (transformConfig.isMergerMark()) {
            // 转换并购数据
            for (int i = 0; i < transformConfig.getMergerThreadNum(); i++) {
                final int pageNumber = i;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info("{}:{} 投资并购数据转换开始", currentThread.getName(), currentThread.getId());
                    try {
                        transformMergerSmt(transformConfig, pageNumber);
                        transformMergerTz(transformConfig, pageNumber);
                    } catch (Exception e) {
                        logger.error("投资并购数据转换", e);
                    }
                    logger.info("{}:{} 投资并购数据转换结束", currentThread.getName(), currentThread.getId());
                });
            }
        }
    }

    private void transformMergerSmt(TransformConfig transformConfig, int pageNumber) {
        MergerDataSmt news = new MergerDataSmt();
        Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
        List<MergerDataSmt> list = mergerDataSmtService.findOneHundred(news, pageable);
        List<MergerDataBak> bakList = new ArrayList<MergerDataBak>();
        if (list != null && list.size() > 0) {
            for (MergerDataSmt item : list) {
                MergerDataBak bak = new MergerDataBak();
                BeanUtils.copyProperties(item, bak);
                bak.setEndTime(StringUtils.transformTime(item.getEndTime()));
                bak.setBiaoShi("0");
                bak.setSource("私募通");
                long count = mergerDataBakService.findExit(bak);
                if (count == 0) {
                    bakList.add(bak);
                }
            }
        }
        if (bakList.size() > 0) {
            mergerDataBakService.save(bakList);
        }
        mergerDataSmtService.delete(list);
    }

    private void transformMergerTz(TransformConfig transformConfig, int pageNumber) {
        MergerDataTz news = new MergerDataTz();
        Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
        List<MergerDataTz> list = mergerDataTzService.findOneHundred(news, pageable);
        List<MergerDataBak> bakList = new ArrayList<MergerDataBak>();
        if (list != null && list.size() > 0) {
            for (MergerDataTz item : list) {
                MergerDataBak bak = new MergerDataBak();
                bak.setFldUrlAddr(item.getFldUrlAddr());
                bak.setAcquirer(item.getAcquirer());
                bak.setBeMergered(item.getBeMergered());
                bak.setIndustry(item.getIndustry());
                bak.setAcquirerInfo(item.getRegion());
                bak.setEndTime(StringUtils.transformTime(item.getTime()));
                bak.setMergerAmount(item.getMergerAmount());
                bak.setStockEquity(item.getStockEquity());
                bak.setProduct(item.getProduct());
                bak.setBusinessValuation(item.getGuzhi());
                bak.setMergerEvent(item.getMergerEvent());
                bak.setSource("投中网");
                bak.setBiaoShi("0");
                long count = mergerDataBakService.findExit(bak);
                if (count == 0) {
                    bakList.add(bak);
                }
            }
        }
        if (bakList.size() > 0) {
            mergerDataBakService.save(bakList);
        }
        mergerDataTzService.delete(list);

    }

}
