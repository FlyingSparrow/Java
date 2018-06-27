package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
import com.huishu.entity.MergerDataBak;
import com.huishu.entity.MergerDataSmt;
import com.huishu.entity.MergerDataTz;
import com.huishu.service.MergerDataBakService;
import com.huishu.service.MergerDataSmtService;
import com.huishu.service.MergerDataTzService;
import com.huishu.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    private void transformMergerSmt(int pageNumber) {
        MergerDataSmt entity = new MergerDataSmt();
        Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
        List<MergerDataSmt> list = mergerDataSmtService.findOneHundred(entity, pageable);
        if (list == null || list.isEmpty()) {
            return;
        }

        logger.info("待转换{}数据 {} 条", getName(), list.size());

        List<MergerDataBak> bakList = new ArrayList<MergerDataBak>(list.size());
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

        logger.info("待转换{}数据 {} 条", getName(), list.size());

        List<MergerDataBak> bakList = new ArrayList<MergerDataBak>(list.size());
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
