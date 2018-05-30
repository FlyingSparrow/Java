package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
import com.huishu.entity.InvestmentDataBak;
import com.huishu.entity.InvestmentDataSmt;
import com.huishu.entity.InvestmentDataTz;
import com.huishu.service.InvestmentDataBakService;
import com.huishu.service.InvestmentDataSmtService;
import com.huishu.service.InvestmentDataTzService;
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
 * 投资数据转换器
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("investmentTransformer")
public class InvestmentTransformer implements Transformer {

    private static Logger logger = LoggerFactory.getLogger(InvestmentTransformer.class);

    @Autowired
    private InvestmentDataTzService investmentDataTzService;
    @Autowired
    private InvestmentDataSmtService investmentDataSmtService;
    @Autowired
    private InvestmentDataBakService investmentDataBakService;

    @Override
    public void transform(TransformConfig transformConfig, ThreadPoolExecutor executor) {
        if (transformConfig.isInvestmentMark()) {
            // 转换投资数据
            for (int x = 0; x < transformConfig.getInvestmentThreadNum(); x++) {
                final int tempNum = x;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info(currentThread.getName() + ":"
                            + currentThread.getId() + "投资数据转换开始");
                    try {
                        transformInverstmentSmt(tempNum);
                        transformInverstmentTz(transformConfig, tempNum);
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("投资数据转换", e);
                    }
                    logger.info(currentThread.getName() + ":"
                            + currentThread.getId() + "投资数据转换结束");
                });
            }
        }
    }

    private void transformInverstmentSmt(int tempNum) {
        InvestmentDataSmt news = new InvestmentDataSmt();
        Pageable pageable = new PageRequest(tempNum, 100);
        List<InvestmentDataSmt> lists = investmentDataSmtService.findOneHundred(news, pageable);
        List<InvestmentDataBak> bakList = new ArrayList<InvestmentDataBak>();
        if (lists != null && lists.size() > 0) {
            for (InvestmentDataSmt list : lists) {
                InvestmentDataBak bak = new InvestmentDataBak();
                BeanUtils.copyProperties(list, bak);
                bak.setTime(list.getTime());
                bak.setBiaoShi("0");
                bak.setSource("私募通");
                long count = investmentDataBakService.findExit(bak);
                if (count == 0) {
                    bakList.add(bak);
                }
            }
        }
        if (bakList != null && bakList.size() > 0) {
            investmentDataBakService.save(bakList);
        }
        investmentDataSmtService.delete(lists);

    }

    private void transformInverstmentTz(TransformConfig transformConfig, int tempNum) {
        InvestmentDataTz news = new InvestmentDataTz();
        Pageable pageable = new PageRequest(tempNum, transformConfig.getTransformNum());
        List<InvestmentDataTz> lists = investmentDataTzService.findOneHundred(
                news, pageable);
        List<InvestmentDataBak> bakList = new ArrayList<InvestmentDataBak>();
        if (lists != null && lists.size() > 0) {
            for (InvestmentDataTz list : lists) {
                InvestmentDataBak bak = new InvestmentDataBak();
                bak.setFldUrlAddr(list.getFldUrlAddr());
                bak.setInvestor(list.getInvestor());
                bak.setIndustry(list.getIndustry());
                bak.setCompanyName(list.getCompanyName());
                bak.setTime(StringUtils.transformTime(list.getTime()));
                bak.setRegion(list.getRegion());
                bak.setAmount(list.getAmount());
                bak.setEquity(list.getEquity());
                bak.setProduct(list.getProduct());
                bak.setAppraisement(list.getAppraisement());
                bak.setInvestmentEvent(list.getInvestmentEvent());
                bak.setSource("投中网");
                bak.setBiaoShi("0");
                long count = investmentDataBakService.findExit(bak);
                if (count == 0) {
                    bakList.add(bak);
                }
            }
        }
        if (bakList != null && bakList.size() > 0) {
            investmentDataBakService.save(bakList);
        }
        investmentDataTzService.delete(lists);
    }

}
