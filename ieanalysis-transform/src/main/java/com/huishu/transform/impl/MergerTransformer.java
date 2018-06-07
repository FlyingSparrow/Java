package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
import com.huishu.entity.*;
import com.huishu.service.MergerDataBakService;
import com.huishu.service.MergerDataSmtService;
import com.huishu.service.MergerDataTzService;
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
 * 投资并购数据转换器
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("mergerTransformer")
public class MergerTransformer extends AbstractTransformer{

    @Autowired
    private MergerDataTzService mergerDataTzService;
    @Autowired
    private MergerDataSmtService mergerDataSmtService;
    @Autowired
    private MergerDataBakService mergerDataBakService;
    @Autowired
    private TransformConfig transformConfig;

    @Override
    protected void transformData(int pageNumber) {
        transformMergerSmt(pageNumber);
//        transformMergerTz(pageNumber);
    }

    @Override
    protected void transformData() throws InterruptedException {
        transformMergerSmt();
//        transformMergerTz();
    }

    private void transformMergerSmt(int pageNumber) {
        MergerDataSmt entity = new MergerDataSmt();
        Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
        List<MergerDataSmt> list = mergerDataSmtService.findOneHundred(entity, pageable);
        if (list == null || list.isEmpty()) {
            return;
        }

        List<MergerDataBak> bakList = new ArrayList<MergerDataBak>();
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
        if (bakList.size() > 0) {
            mergerDataBakService.save(bakList);
        }
        mergerDataSmtService.delete(list);
    }

    private void transformMergerSmt() throws InterruptedException {
        int pageNumber = 1;
        int totalPages = 10;
        MergerDataSmt entity = new MergerDataSmt();
        while (pageNumber <= totalPages){
            Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
            Page<MergerDataSmt> page = mergerDataSmtService.findByPage(entity, pageable);
            totalPages = page.getTotalPages();

            List<MergerDataSmt> list = page.getContent();
            if(list != null && list.size() > 0){
                logger.info("总页数：{}，每页记录数：{}，剩余 {} 条{}数据待转换", page.getTotalPages(),
                        transformConfig.getTransformNum(), page.getTotalElements(), getName());

                logger.info("第 {} 页{}数据转换开始", pageNumber, getName());


                List<MergerDataBak> bakList = new ArrayList<MergerDataBak>();
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
                if (bakList.size() > 0) {
                    mergerDataBakService.save(bakList);
                }
                mergerDataSmtService.delete(list);

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
    private void transformMergerTz(int pageNumber) {
        MergerDataTz entity = new MergerDataTz();
        Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
        List<MergerDataTz> list = mergerDataTzService.findOneHundred(entity, pageable);
        if (list == null || list.isEmpty()) {
            return;
        }

        List<MergerDataBak> bakList = new ArrayList<MergerDataBak>();
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
        if (bakList.size() > 0) {
            mergerDataBakService.save(bakList);
        }
        mergerDataTzService.delete(list);
    }

    /**
     * 说明：由于投中网页面改版，抓取不到数据，因此不再执行该方法
     *
     * @author Wangjianchun
     * @date 2018-6-3
     */
    private void transformMergerTz() throws InterruptedException {
        int pageNumber = 1;
        int totalPages = 10;
        MergerDataTz entity = new MergerDataTz();
        while (pageNumber <= totalPages){
            Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
            Page<MergerDataTz> page = mergerDataTzService.findByPage(entity, pageable);
            totalPages = page.getTotalPages();

            List<MergerDataTz> list = page.getContent();
            if(list != null && list.size() > 0){
                logger.info("总页数：{}，每页记录数：{}，剩余 {} 条{}数据待转换", page.getTotalPages(),
                        transformConfig.getTransformNum(), page.getTotalElements(), getName());

                logger.info("第 {} 页{}数据转换开始", pageNumber, getName());


                List<MergerDataBak> bakList = new ArrayList<MergerDataBak>();
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
                if (bakList.size() > 0) {
                    mergerDataBakService.save(bakList);
                }
                mergerDataTzService.delete(list);

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
        return transformConfig.isMergerMark();
    }

    @Override
    public int getThreadNum() {
        return transformConfig.getMergerThreadNum();
    }

    @Override
    public String getName() {
        return "投资并购";
    }
}
