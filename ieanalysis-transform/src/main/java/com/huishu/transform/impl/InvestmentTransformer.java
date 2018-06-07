package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
import com.huishu.entity.*;
import com.huishu.service.InvestmentDataBakService;
import com.huishu.service.InvestmentDataSmtService;
import com.huishu.service.InvestmentDataTzService;
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
        transformInvestmentSmt(pageNumber);
//        transformInvestmentTz(pageNumber);
    }

    @Override
    protected void transformData() throws InterruptedException {
        transformInvestmentSmt();
//        transformInvestmentTz();
    }

    private void transformInvestmentSmt(int pageNumber) {
        InvestmentDataSmt entity = new InvestmentDataSmt();
        Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
        List<InvestmentDataSmt> list = investmentDataSmtService.findOneHundred(entity, pageable);
        if (list == null || list.isEmpty()) {
            return;
        }

        List<InvestmentDataBak> bakList = new ArrayList<InvestmentDataBak>();
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
        if (bakList.size() > 0) {
            investmentDataBakService.save(bakList);
        }
        investmentDataSmtService.delete(list);
    }

    private void transformInvestmentSmt() throws InterruptedException {
        int pageNumber = 0;
        int totalPages = 10;
        InvestmentDataSmt entity = new InvestmentDataSmt();
        while (pageNumber <= totalPages){
            Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
            Page<InvestmentDataSmt> page = investmentDataSmtService.findByPage(entity, pageable);
            totalPages = page.getTotalPages();

            List<InvestmentDataSmt> list = page.getContent();
            if(list != null && list.size() > 0){
                logger.info("总页数：{}，每页记录数：{}，剩余 {} 条{}数据待转换", page.getTotalPages(),
                        transformConfig.getTransformNum(), page.getTotalElements(), getName());

                logger.info("第 {} 页{}数据转换开始", pageNumber, getName());

                List<InvestmentDataBak> bakList = new ArrayList<InvestmentDataBak>();
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

                if (bakList.size() > 0) {
                    investmentDataBakService.save(bakList);
                }
                investmentDataSmtService.delete(list);

                logger.info("第 {} 页{}数据转换结束", pageNumber, getName());

            }else{
                //如果没有数据需要分析，那么当前线程休眠5分钟
                Thread.sleep(300000);
            }
            pageNumber++;
        }
    }

    /**
     * 说明：由于投中网页面改版，抓取不到数据，因此不再执行该方法
     *
     * @param pageNumber
     * @author Wangjianchun
     * @date 2018-6-3
     */
    private void transformInvestmentTz(int pageNumber) {
        InvestmentDataTz entity = new InvestmentDataTz();
        Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
        List<InvestmentDataTz> list = investmentDataTzService.findOneHundred(entity, pageable);
        if (list == null || list.isEmpty()) {
            return;
        }

        List<InvestmentDataBak> bakList = new ArrayList<InvestmentDataBak>();
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
        if (bakList.size() > 0) {
            investmentDataBakService.save(bakList);
        }
        investmentDataTzService.delete(list);
    }

    /**
     * 说明：由于投中网页面改版，抓取不到数据，因此不再执行该方法
     *
     * @author Wangjianchun
     * @date 2018-6-3
     */
    private void transformInvestmentTz() throws InterruptedException {
        int pageNumber = 0;
        int totalPages = 10;
        InvestmentDataTz entity = new InvestmentDataTz();
        while (pageNumber <= totalPages){
            Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
            Page<InvestmentDataTz> page = investmentDataTzService.findByPage(entity, pageable);
            totalPages = page.getTotalPages();

            List<InvestmentDataTz> list = page.getContent();
            if(list != null && list.size() > 0){
                logger.info("总页数：{}，每页记录数：{}，剩余 {} 条{}数据待转换", page.getTotalPages(),
                        transformConfig.getTransformNum(), page.getTotalElements(), getName());

                logger.info("第 {} 页{}数据转换开始", pageNumber, getName());

                List<InvestmentDataBak> bakList = new ArrayList<InvestmentDataBak>();
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
                if (bakList.size() > 0) {
                    investmentDataBakService.save(bakList);
                }
                investmentDataTzService.delete(list);

                logger.info("第 {} 页{}数据转换结束", pageNumber, getName());
            }else{
                //如果没有数据需要分析，那么当前线程休眠5分钟
                Thread.sleep(300000);
            }
            pageNumber++;
        }
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
