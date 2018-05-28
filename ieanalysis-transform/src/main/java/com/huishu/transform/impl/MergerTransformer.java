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
            for (int x = 0; x < transformConfig.getMergerThreadNum(); x++) {
                final int tempNum = x;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info(currentThread.getName() + ":"
                            + currentThread.getId() + "投资并购数据转换开始");
                    try {
                        transformMergerSmt(transformConfig, tempNum);
                        transformMergerTz(transformConfig, tempNum);
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("投资并购数据转换", e);
                    }
                    logger.info(currentThread.getName() + ":"
                            + currentThread.getId() + "投资并购数据转换结束");
                });
            }
        }
    }

    private void transformMergerSmt(TransformConfig transformConfig, int tempNum) {
        MergerDataSmt news = new MergerDataSmt();
        Pageable pageable = new PageRequest(tempNum, transformConfig.getTransformNum());
        List<MergerDataSmt> lists = mergerDataSmtService.findOneHundred(news, pageable);
        List<MergerDataBak> bakList = new ArrayList<MergerDataBak>();
        if (lists != null && lists.size() > 0) {
            for (MergerDataSmt list : lists) {

                MergerDataBak bak = new MergerDataBak();
                BeanUtils.copyProperties(list, bak);
                bak.setEndTime(StringUtils.toTransformTime(list.getEndTime()));
                bak.setBiaoShi("0");
                bak.setSource("私募通");
                long count = mergerDataBakService.findExit(bak);
                if (count == 0) {
                    bakList.add(bak);
                }
            }
        }
        if (bakList != null && bakList.size() > 0) {
            mergerDataBakService.save(bakList);
        }
        mergerDataSmtService.delete(lists);
    }

    private void transformMergerTz(TransformConfig transformConfig, int tempNum) {
        MergerDataTz news = new MergerDataTz();
        Pageable pageable = new PageRequest(tempNum, transformConfig.getTransformNum());
        List<MergerDataTz> lists = mergerDataTzService.findOneHundred(news, pageable);
        List<MergerDataBak> bakList = new ArrayList<MergerDataBak>();
        if (lists != null && lists.size() > 0) {
            for (MergerDataTz list : lists) {
                MergerDataBak bak = new MergerDataBak();
                bak.setFldUrlAddr(list.getFldUrlAddr());
                bak.setAcquirer(list.getAcquirer());
                bak.setBeMergered(list.getBeMergered());
                bak.setIndustry(list.getIndustry());
                bak.setAcquirerInfo(list.getRegion());
                bak.setEndTime(StringUtils.toTransformTime(list.getTime()));
                bak.setMergerAmount(list.getMergerAmount());
                bak.setStockEquity(list.getStockEquity());
                bak.setProduct(list.getProduct());
                bak.setBusinessValuation(list.getGuzhi());
                bak.setMergerEvent(list.getMergerEvent());
                bak.setSource("投中网");
                bak.setBiaoShi("0");
                long count = mergerDataBakService.findExit(bak);
                if (count == 0) {
                    bakList.add(bak);
                }
            }
        }
        if (bakList != null && bakList.size() > 0) {
            mergerDataBakService.save(bakList);
        }
        mergerDataTzService.delete(lists);

    }

}
