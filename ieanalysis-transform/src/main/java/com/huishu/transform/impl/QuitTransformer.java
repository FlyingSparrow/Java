package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
import com.huishu.entity.*;
import com.huishu.service.QuitDataBakService;
import com.huishu.service.QuitDataSmtService;
import com.huishu.service.QuitDataTzService;
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
 * 投资退出数据转换器
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("quitTransformer")
public class QuitTransformer extends AbstractTransformer {

    @Autowired
    private QuitDataTzService quitDataTzService;
    @Autowired
    private QuitDataSmtService quitDataSmtService;
    @Autowired
    private QuitDataBakService quitDataBakService;
    @Autowired
    private TransformConfig transformConfig;

    @Override
    protected void transformData(int pageNumber) {
        transformQuitSmt(pageNumber);
//        transformQuitTz(pageNumber);
    }

    @Override
    protected void transformData() throws InterruptedException {
        transformQuitSmt();
//        transformQuitTz();
    }

    private void transformQuitSmt(int pageNumber) {
        QuitDataSmt entity = new QuitDataSmt();
        Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
        List<QuitDataSmt> list = quitDataSmtService.findOneHundred(entity, pageable);
        if (list == null || list.isEmpty()) {
            return;
        }

        List<QuitDataBak> bakList = new ArrayList<QuitDataBak>();
        for (QuitDataSmt item : list) {
            QuitDataBak bak = new QuitDataBak();
            BeanUtils.copyProperties(item, bak);
            bak.setTime(StringUtils.transformTime(item.getTime()));
            bak.setBiaoShi("0");
            bak.setSource("私募通");
            long count = quitDataBakService.findExit(bak);
            if (count == 0) {
                bakList.add(bak);
            }
        }
        if (bakList.size() > 0) {
            quitDataBakService.save(bakList);
        }
        quitDataSmtService.delete(list);
    }

    private void transformQuitSmt() throws InterruptedException {
        int pageNumber = 1;
        int totalPages = 10;
        QuitDataSmt entity = new QuitDataSmt();
        while (pageNumber <= totalPages){
            Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
            Page<QuitDataSmt> page = quitDataSmtService.findByPage(entity, pageable);
            totalPages = page.getTotalPages();

            List<QuitDataSmt> list = page.getContent();
            if(list != null && list.size() > 0){
                logger.info("总页数：{}，每页记录数：{}，剩余 {} 条{}数据待转换", page.getTotalPages(),
                        transformConfig.getTransformNum(), page.getTotalElements(), getName());

                logger.info("第 {} 页{}数据转换开始", pageNumber, getName());


                List<QuitDataBak> bakList = new ArrayList<QuitDataBak>();
                for (QuitDataSmt item : list) {
                    QuitDataBak bak = new QuitDataBak();
                    BeanUtils.copyProperties(item, bak);
                    bak.setTime(StringUtils.transformTime(item.getTime()));
                    bak.setBiaoShi("0");
                    bak.setSource("私募通");
                    long count = quitDataBakService.findExit(bak);
                    if (count == 0) {
                        bakList.add(bak);
                    }
                }
                if (bakList.size() > 0) {
                    quitDataBakService.save(bakList);
                }
                quitDataSmtService.delete(list);

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
    private void transformQuitTz(int pageNumber) {
        QuitDataTz entity = new QuitDataTz();
        Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
        List<QuitDataTz> list = quitDataTzService.findOneHundred(entity, pageable);
        if (list == null || list.isEmpty()) {
            return;
        }

        List<QuitDataBak> bakList = new ArrayList<QuitDataBak>();
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
        if (bakList.size() > 0) {
            quitDataBakService.save(bakList);
        }
        quitDataTzService.delete(list);
    }

    /**
     * 说明：由于投中网页面改版，抓取不到数据，因此不再执行该方法
     *
     * @author Wangjianchun
     * @date 2018-6-3
     */
    private void transformQuitTz() throws InterruptedException {
        int pageNumber = 1;
        int totalPages = 10;
        QuitDataTz entity = new QuitDataTz();
        while (pageNumber <= totalPages){
            Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
            Page<QuitDataTz> page = quitDataTzService.findByPage(entity, pageable);
            totalPages = page.getTotalPages();

            List<QuitDataTz> list = page.getContent();
            if(list != null && list.size() > 0){
                logger.info("总页数：{}，每页记录数：{}，剩余 {} 条{}数据待转换", page.getTotalPages(),
                        transformConfig.getTransformNum(), page.getTotalElements(), getName());

                logger.info("第 {} 页{}数据转换开始", pageNumber, getName());


                List<QuitDataBak> bakList = new ArrayList<QuitDataBak>();
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
                if (bakList.size() > 0) {
                    quitDataBakService.save(bakList);
                }
                quitDataTzService.delete(list);

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
        return transformConfig.isQuitMark();
    }

    @Override
    public int getThreadNum() {
        return transformConfig.getQuitThreadNum();
    }

    @Override
    public String getName() {
        return "投资退出";
    }
}
