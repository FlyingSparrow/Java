package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
import com.huishu.entity.InvestmentDataBak;
import com.huishu.entity.InvestmentDataSmt;
import com.huishu.entity.InvestmentDataTz;
import com.huishu.service.InvestmentDataBakService;
import com.huishu.service.InvestmentDataSmtService;
import com.huishu.service.InvestmentDataTzService;
import com.huishu.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 投资数据转换器
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("investmentTransformer")
public class InvestmentTransformer extends AbstractTransformer {

    @Autowired
    private InvestmentDataTzService investmentDataTzService;
    @Autowired
    private InvestmentDataSmtService investmentDataSmtService;
    @Autowired
    private InvestmentDataBakService investmentDataBakService;
    @Autowired
    private TransformConfig transformConfig;

    @Override
    protected void transformData(int pageNumber) {
        transformInverstmentSmt(pageNumber);
        transformInverstmentTz(pageNumber);
    }

    private void transformInverstmentSmt(int pageNumber) {
        InvestmentDataSmt news = new InvestmentDataSmt();
        Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
        List<InvestmentDataSmt> list = investmentDataSmtService.findOneHundred(news, pageable);
        List<InvestmentDataBak> bakList = new ArrayList<InvestmentDataBak>();
        if (list != null && list.size() > 0) {
            for (InvestmentDataSmt item : list) {
                InvestmentDataBak bak = new InvestmentDataBak();
                BeanUtils.copyProperties(item, bak);
                bak.setTime(item.getTime());
                bak.setBiaoShi("0");
                bak.setSource("私募通");
                long count = investmentDataBakService.findExit(bak);
                if (count == 0) {
                    bakList.add(bak);
                }
            }
        }
        if (bakList.size() > 0) {
            investmentDataBakService.save(bakList);
        }
        investmentDataSmtService.delete(list);

    }

    private void transformInverstmentTz(int tempNum) {
        InvestmentDataTz news = new InvestmentDataTz();
        Pageable pageable = new PageRequest(tempNum, transformConfig.getTransformNum());
        List<InvestmentDataTz> list = investmentDataTzService.findOneHundred(news, pageable);
        List<InvestmentDataBak> bakList = new ArrayList<InvestmentDataBak>();
        if (list != null && list.size() > 0) {
            for (InvestmentDataTz item : list) {
                InvestmentDataBak bak = new InvestmentDataBak();
                bak.setFldUrlAddr(item.getFldUrlAddr());
                bak.setInvestor(item.getInvestor());
                bak.setIndustry(item.getIndustry());
                bak.setCompanyName(item.getCompanyName());
                bak.setTime(StringUtils.transformTime(item.getTime()));
                bak.setRegion(item.getRegion());
                bak.setAmount(item.getAmount());
                bak.setEquity(item.getEquity());
                bak.setProduct(item.getProduct());
                bak.setAppraisement(item.getAppraisement());
                bak.setInvestmentEvent(item.getInvestmentEvent());
                bak.setSource("投中网");
                bak.setBiaoShi("0");
                long count = investmentDataBakService.findExit(bak);
                if (count == 0) {
                    bakList.add(bak);
                }
            }
        }
        if (bakList.size() > 0) {
            investmentDataBakService.save(bakList);
        }
        investmentDataTzService.delete(list);
    }

    @Override
    public boolean getMark() {
        return transformConfig.isInvestmentMark();
    }

    @Override
    public int getThreadNum() {
        return transformConfig.getInvestmentThreadNum();
    }

    @Override
    public String getName() {
        return "投资";
    }
}
