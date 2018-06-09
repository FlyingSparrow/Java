package com.jdjr.opinion.mongodb.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jdjr.opinion.constants.SysConst;
import com.jdjr.opinion.mongodb.dao.MongodbSearchDao;
import com.jdjr.opinion.mongodb.entity.OpinionDetailsSnapshot;
import com.jdjr.opinion.mongodb.entity.OpinionRisk;
import com.jdjr.opinion.mongodb.repository.OpinionDetailsSnapshotRepository;
import com.jdjr.opinion.mongodb.repository.OpinionRiskRepository;
import com.jdjr.opinion.mongodb.service.OpinionDetailsSnapshotService;
import com.jdjr.opinion.mongodb.service.OpinionRiskService;
import com.jdjr.opinion.utils.DateUtils;
import com.jdjr.opinion.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OpinionRiskServiceImpl implements OpinionRiskService {

    @Autowired
    private OpinionRiskRepository opinionRiskRepository;

    @Autowired
    private MongodbSearchDao mongodbSearchDao;
    @Autowired
    private OpinionDetailsSnapshotService opinionDetailsSnapshotService;
    @Autowired
    private OpinionDetailsSnapshotRepository opinionDetailsSnapshotRepository;

    @Override
    public boolean save(OpinionRisk entity) {
        boolean isExist = this.findExistByEntity(entity.getEnterpriseId(), entity.getPublishDate());
        if (!isExist) {
            opinionRiskRepository.save(entity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public OpinionRisk saveResult(OpinionRisk entity) {
        return opinionRiskRepository.save(entity);
    }

    @Override
    public boolean batchAdd(List<OpinionRisk> list) {
        opinionRiskRepository.save(list);
        return true;
    }

    @Override
    public OpinionRisk findById(String id) {
        return opinionRiskRepository.findOne(id);
    }

    @Override
    public OpinionRisk findByEntity(String enterpriseId, String publishDate) {
        return opinionRiskRepository.findByEnterpriseIdAndPublishDate(enterpriseId, publishDate);
    }

    @Override
    public List<OpinionRisk> findListByEntity(OpinionRisk entity) {
        return mongodbSearchDao.findListOpinionRiskByEntity(entity);
    }

    @Override
    public List<OpinionRisk> findListByInPublishDate(String enterpriseId, List<String> publishDate) {
        return mongodbSearchDao.findListOpinionRiskByInPublishDate(enterpriseId, publishDate);
    }

    @Override
    public boolean findExistByEntity(String enterpriseId, String publishDate) {
        OpinionRisk opinionRisk = this.findByEntity(enterpriseId, publishDate);
        return opinionRisk != null;
    }

    @Override
    public boolean update(OpinionRisk entity) {
        opinionRiskRepository.save(entity);
        return true;
    }

    @Override
    public Map<String, String> findEnterpriseInfoMap(String publishDate) {
        return mongodbSearchDao.findOpinionRiskGroupEnterprise(publishDate);
    }

    @Override
    public List<OpinionRisk> findByEnterpriseIdList(List<String> enterpriseIds, List<String> publishDate) {
        return mongodbSearchDao.findOpinionRiskByEnterpriseIdList(enterpriseIds, publishDate);
    }

    @Override
    public JSONObject findOpinionQuantitativeIndicator(OpinionRisk entity) {
        String today = entity.getPublishDate();
        String enterpriseId = entity.getEnterpriseId();

        OpinionRisk todayOpinionRisk = this.findByEntity(enterpriseId, today);

        String yesterday = DateUtils.formatDate(DateUtils.paramesDateAddDay(-1, today));
        OpinionRisk yesterdayOpinionRisk = this.findByEntity(enterpriseId, yesterday);

        String beforeYesterday = DateUtils.formatDate(DateUtils.paramesDateAddDay(-2, today));
        OpinionRisk beforeYesterdayOpinionRisk = this.findByEntity(enterpriseId, beforeYesterday);


        JSONObject result = new JSONObject();
//        Double yesterdayTransformedPressureIndex = 0D;
        Double yesterdayTransformedNegativePressureIndex = 0D;
        if (todayOpinionRisk != null) {
            JSONObject jsonObject = opinionDetailsSnapshotService.findOpinionCountInfo(enterpriseId, today);

            result.put("todayOpinionCount", jsonObject.getIntValue("todayOpinionCount"));
            result.put("todayNegativeCount", jsonObject.getIntValue("todayNegativeCount"));
            result.put("todayPositiveCount", jsonObject.getIntValue("todayPositiveCount"));
        } else {
            result.put("todayOpinionCount", 0);
            result.put("todayNegativeCount", 0);
            result.put("todayPositiveCount", 0);
        }
        if (yesterdayOpinionRisk != null) {
//            yesterdayTransformedPressureIndex = yesterdayOpinionRisk.getTransformedPressureIndex();
            yesterdayTransformedNegativePressureIndex = yesterdayOpinionRisk.getTransformedNegativePressureIndex();
            result.put("ystdPressureIndex", NumberUtils.formatDouble(yesterdayTransformedNegativePressureIndex, 2));
            result.put("ystdOpinionCount", yesterdayOpinionRisk.getTotalOpinion());
            result.put("ystdNegativeCount", yesterdayOpinionRisk.getNegativeOpinionCount());
            result.put("ystdPositiveCount", yesterdayOpinionRisk.getPositiveOpinionCount());
        } else {
            result.put("ystdPressureIndex", 0);
            result.put("ystdOpinionCount", 0);
            result.put("ystdNegativeCount", 0);
            result.put("ystdPositiveCount", 0);
        }
        if (beforeYesterdayOpinionRisk != null && yesterdayOpinionRisk != null) {
//            Double beforeYesterdayTransformedPressureIndex = beforeYesterdayOpinionRisk.getTransformedPressureIndex();
            Double beforeYesterdayTransformedNegativePressureIndex = beforeYesterdayOpinionRisk.getTransformedNegativePressureIndex();
            String pressureIndexRatio = "0";
            String trend = "up";
            //公式为: （昨天压力值 - 前天压力值）/ 前天压力值  * 100  == （昨天压力值/ 前天压力值 - 1）  * 100
            pressureIndexRatio = NumberUtils.changeProportion(yesterdayTransformedNegativePressureIndex,
                    beforeYesterdayTransformedNegativePressureIndex, 2).toString();
            if (yesterdayTransformedNegativePressureIndex.compareTo(beforeYesterdayTransformedNegativePressureIndex) == 1) {    //上涨
                trend = "up";
            }
            if (yesterdayTransformedNegativePressureIndex.compareTo(beforeYesterdayTransformedNegativePressureIndex) == -1) {    //下降
                trend = "down";
            }
            result.put("ystdPressureIndexRatio", pressureIndexRatio + "%");
            result.put("pressureIndexTrend", trend);

        } else {
            result.put("ystdPressureIndexRatio", 0 + "%");
        }
        return result;
    }

    @Override
    public JSONObject findOpinionQuantitativeIndicatorOfLast7Days(OpinionRisk entity) {
        Date publishDate = DateUtils.parseDate(entity.getPublishDate(), DateUtils.DATE_FORMAT);
        String yesterday = DateUtils.formatDate(DateUtils.paramesDateAddDay(-1, entity.getPublishDate()));
        String beforeYesterday = DateUtils.formatDate(DateUtils.paramesDateAddDay(-2, entity.getPublishDate()));

        String enterpriseId = entity.getEnterpriseId();
        List<String> dateList = DateUtils.getDateList(publishDate, 7);
        List<OpinionRisk> opinionRiskList = opinionRiskRepository.findByEnterpriseIdAndPublishDateIn(enterpriseId, dateList);

        List<OpinionDetailsSnapshot> opinionDetailsSnapshotList = opinionDetailsSnapshotRepository.findByEnterpriseIdAndPublishDateIn(enterpriseId, dateList);


        JSONObject result = new JSONObject();
        result.put("ystdPressureIndexRatio", "0%");
        result.put("ystdPressureIndex", 0);
        //最近7天的舆情总数
        int last7DaysOpinionCount = 0;
        //最近7天的负面舆情数
        int last7DaysNegativeCount = 0;
        //最近7天的正面舆情数
        int last7DaysPositiveCount = 0;

        for (OpinionDetailsSnapshot item : opinionDetailsSnapshotList) {
            last7DaysOpinionCount++;
            if(SysConst.EmotionType.NEGATIVE.getCode().equals(item.getEmotion())){
                last7DaysNegativeCount++;
            }
            if(SysConst.EmotionType.POSITIVE.getCode().equals(item.getEmotion())){
                last7DaysPositiveCount++;
            }
        }

        Double yesterdayTransformedNegativePressureIndex = 0D;
        for (OpinionRisk item : opinionRiskList) {
            if (yesterday.equals(item.getPublishDate())) {
                //昨天
                yesterdayTransformedNegativePressureIndex = item.getTransformedNegativePressureIndex();
                result.put("ystdPressureIndex", NumberUtils.formatDouble(yesterdayTransformedNegativePressureIndex, 2));
            }
            if (beforeYesterday.equals(item.getPublishDate())) {
                //前天
                Double beforeYesterdayTransformedNegativePressureIndex = item.getTransformedNegativePressureIndex();
                String pressureIndexRatio = "0";
                String trend = "up";
                //公式为: （昨天压力值 - 前天压力值）/ 前天压力值  * 100  == （昨天压力值/ 前天压力值 - 1）  * 100
                pressureIndexRatio = NumberUtils.changeProportion(yesterdayTransformedNegativePressureIndex,
                        beforeYesterdayTransformedNegativePressureIndex, 2).toString();
                if (yesterdayTransformedNegativePressureIndex.compareTo(beforeYesterdayTransformedNegativePressureIndex) == 1) {    //上涨
                    trend = "up";
                }
                if (yesterdayTransformedNegativePressureIndex.compareTo(beforeYesterdayTransformedNegativePressureIndex) == -1) {    //下降
                    trend = "down";
                }
                result.put("ystdPressureIndexRatio", pressureIndexRatio + "%");
                result.put("pressureIndexTrend", trend);
            }
        }
        result.put("last7DaysOpinionCount", last7DaysOpinionCount);
        result.put("last7DaysNegativeCount", last7DaysNegativeCount);
        result.put("last7DaysPositiveCount", last7DaysPositiveCount);

        return result;
    }

    @Override
    public JSONObject findOpinionPressureTrend(OpinionRisk entity) {
        JSONObject result = new JSONObject();
        JSONArray xAxisData = new JSONArray();
        JSONArray pressureIndex = new JSONArray();  //压力指数
        JSONArray transformedPressureIndex = new JSONArray();    //指数改造数
        JSONArray safetyLineUpper = new JSONArray();  //安全线上
        JSONArray safetyLineLower = new JSONArray();  //安全线下
        JSONArray averageValueLine = new JSONArray();   //均值线

        String pressureType = entity.getPressureType();
        OpinionRisk opinionRisk = new OpinionRisk();
        opinionRisk.setContain(true);
        opinionRisk.setEnterpriseId(entity.getEnterpriseId());
        opinionRisk.setStartPublishDate(entity.getStartPublishDate());
        opinionRisk.setEndPublishDate(entity.getEndPublishDate());
        List<OpinionRisk> list = mongodbSearchDao.findListOpinionRiskByEntity(opinionRisk);
        Map<String, List<OpinionRisk>> ormap = list.stream().collect(Collectors.groupingBy(OpinionRisk::getPublishDate));
        List<Date> dateRange = DateUtils.dateRange(entity.getStartPublishDate(), entity.getEndPublishDate());

        if (!list.isEmpty()) {
            int defaultScale = 4;
            dateRange.forEach(date -> {
                String publishDate = DateUtils.formatDate(date);
                if (SysConst.PressureType.POSITIVE.getName().equals(pressureType)) {    //正面压力
                    List<OpinionRisk> opinionRisks = ormap.get(publishDate);
                    if (opinionRisks != null) {
                        OpinionRisk item = opinionRisks.get(0);
                        xAxisData.add(item.getPublishDate());
                        pressureIndex.add(NumberUtils.formatDouble(item.getPositivePressureIndex(), 2));
                        transformedPressureIndex.add(item.getTransformedPositivePressureIndex().toString());

                        BigDecimal averageValueBigDecimal = new BigDecimal(item.getPositiveAverageValue())
                                .setScale(defaultScale, RoundingMode.HALF_UP);
                        BigDecimal standardDiviationBigDecimal = new BigDecimal(item.getPositiveStandardDiviation())
                                .abs().multiply(new BigDecimal(2));
                        BigDecimal safetyLineBigDecimalUpper = averageValueBigDecimal.add(standardDiviationBigDecimal)
                                .setScale(defaultScale, RoundingMode.HALF_UP);
                        BigDecimal safetyLineBigDecimalLower = averageValueBigDecimal.subtract(standardDiviationBigDecimal)
                                .setScale(defaultScale, RoundingMode.HALF_UP);

                        safetyLineUpper.add(safetyLineBigDecimalUpper.toPlainString());
                        safetyLineLower.add(safetyLineBigDecimalLower.toPlainString());
                        averageValueLine.add(averageValueBigDecimal.toPlainString());

                    }

                } else if (SysConst.PressureType.NEGATIVE.getName().equals(pressureType)) { //负面压力
                    List<OpinionRisk> opinionRisks = ormap.get(publishDate);
                    if (opinionRisks != null) {
                        OpinionRisk item = opinionRisks.get(0);
                        xAxisData.add(item.getPublishDate());
                        pressureIndex.add(NumberUtils.formatDouble(item.getNegativePressureIndex(), 2));
                        transformedPressureIndex.add(item.getTransformedNegativePressureIndex().toString());
                        BigDecimal averageValueBigDecimal = new BigDecimal(item.getNegativeAverageValue())
                                .setScale(defaultScale, RoundingMode.HALF_UP);
                        BigDecimal standardDiviationBigDecimal = new BigDecimal(item.getNegativeStandardDiviation())
                                .abs().multiply(new BigDecimal(2));
                        BigDecimal safetyLineBigDecimalUpper = averageValueBigDecimal.add(standardDiviationBigDecimal)
                                .setScale(defaultScale, RoundingMode.HALF_UP);
                        BigDecimal safetyLineBigDecimalLower = averageValueBigDecimal.subtract(standardDiviationBigDecimal)
                                .setScale(defaultScale, RoundingMode.HALF_UP);

                        safetyLineUpper.add(safetyLineBigDecimalUpper.toPlainString());
                        safetyLineLower.add(safetyLineBigDecimalLower.toPlainString());
                        averageValueLine.add(averageValueBigDecimal.toPlainString());
                    }
                } else if (SysConst.PressureType.OVERALL.getName().equals(pressureType)) { //整体压力
                    List<OpinionRisk> opinionRisks = ormap.get(publishDate);
                    if (opinionRisks != null) {
                        OpinionRisk item = opinionRisks.get(0);
                        xAxisData.add(item.getPublishDate());
                        pressureIndex.add(NumberUtils.formatDouble(item.getPressureIndex(), 2));
                        transformedPressureIndex.add(item.getTransformedPressureIndex().toString());
                        BigDecimal averageValueBigDecimal = new BigDecimal(item.getWholeAverageValue())
                                .setScale(defaultScale, RoundingMode.HALF_UP);
                        BigDecimal standardDiviationBigDecimal = new BigDecimal(item.getWholeStandardDiviation())
                                .abs().multiply(new BigDecimal(2));
                        BigDecimal safetyLineBigDecimalUpper = averageValueBigDecimal.add(standardDiviationBigDecimal)
                                .setScale(defaultScale, RoundingMode.HALF_UP);
                        BigDecimal safetyLineBigDecimalLower = averageValueBigDecimal.subtract(standardDiviationBigDecimal)
                                .setScale(defaultScale, RoundingMode.HALF_UP);

                        safetyLineUpper.add(safetyLineBigDecimalUpper.toPlainString());
                        safetyLineLower.add(safetyLineBigDecimalLower.toPlainString());
                        averageValueLine.add(averageValueBigDecimal.toPlainString());
                    }
                }
            });
        }
        result.put("xAxisData", xAxisData);
        result.put("pressureIndex", pressureIndex);
        result.put("transformedPressureIndex", transformedPressureIndex);
        result.put("safetyLineUpper", safetyLineUpper);
        result.put("safetyLineLower", safetyLineLower);
        result.put("averageValueLine", averageValueLine);
        return result;
    }

    @Override
    public List<OpinionRisk> findListOpinionRiskByInPublishDate(List<String> enterpriseIdList,
                                                                List<String> publishDateList) {
//        return mongodbSearchDao.findListOpinionRiskByInPublishDate(enterpriseId, publishDate);

        return opinionRiskRepository.findByEnterpriseIdInAndPublishDateIn(enterpriseIdList, publishDateList);
    }

    @Override
    public List<OpinionRisk> findOpinionRiskListByEntity(String yesterday, String today, List<String> enterpriseIds) {
        return mongodbSearchDao.findOpinionRiskListByEntity(yesterday, today, enterpriseIds);
    }

    @Override
    public List<OpinionRisk> findOpinionRiskListByEntityOfLast7Days(String today, List<String> enterpriseIds) {
        return mongodbSearchDao.findOpinionRiskListByEntityOfLast7Days(today, enterpriseIds);
    }

    @Override
    public long findCount() {
        return opinionRiskRepository.count();
    }

    @Override
    public boolean delete(String id) {
        opinionRiskRepository.delete(id);
        return true;
    }

    @Override
    public List<String> findIdList(int pageNumber, int pageSize) {
        Pageable pageable = new PageRequest(pageNumber - 1, pageSize);
        Page<OpinionRisk> page = opinionRiskRepository.findAll(pageable);
        return page.getContent().parallelStream().map(OpinionRisk::getId)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteAll() {
        opinionRiskRepository.deleteAll();
        return true;
    }

}
