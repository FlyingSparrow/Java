package com.jdjr.opinion.utils;/**
 * Created by lenovo on 2017/7/19.
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.forget.analysis.Analysis;
import com.forget.company.CompanyAbbr;
import com.forget.docClassification.EventClassification;
import com.forget.find.FinderCompany;
import com.forget.itemModelComp.CompWeight;
import com.forget.keywordCloud.KeywordCloud;
import com.forget.sm.getSimHash.GetSimHash;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

/**
 * <p>Title: OpinionAnalysisUtils</p>
 * <p>Description: 舆情分析算法工具类</p>
 *
 * @author Wangjianchun
 * @date 2017/7/19
 * ${}
 */
public class OpinionAnalysisUtils {

    private static Logger logger = LoggerFactory.getLogger(OpinionAnalysisUtils.class);

    private OpinionAnalysisUtils() {
    }

    /**
     * <p>Description: 从文章标题中提取公司名称</p>
     *
     * @param title   文章标题
     * @param content 文章内容
     * @author Wangjianchun
     * @date 2017年6月30日
     */
    public static JSONObject getEnterpriseInfo(String title, String content) {
        JSONObject result;
        try {
            result = FinderCompany.toFinder(title, content);
            if (result != null && result.getBooleanValue("status")) {
                return result;
            }
        } catch(Exception e) {
            logger.error("获取企业信息出错，title：{}，异常信息：{}", title, e);
        }

        return null;
    }

    /**
     * <p>Description: 正负面关键词云</p>
     *
     * @param artList       包含文章的标题和内容的集合
     * @param emotionType   情感分类
     * @param startIndex    开始的索引，从哪个位置开始提取关键词
     * @param keywordsCount 提取的关键词数量
     * @author Wangjianchun
     * @date 2017年6月30日
     */
    public static JSONObject getKeywordCloud(List<String> artList, String emotionType,
                                             int startIndex, int keywordsCount) {
        JSONObject result = null;
        try {
            result = KeywordCloud.keywordCloud(artList, emotionType,
                    startIndex, keywordsCount);
        } catch(Exception e) {
            logger.error("获取正负面关键词云出错，artList: {}，emotionType: {}, startIndex: {}, keywordsCount: {}, 异常信息：{}",
                    artList, emotionType, startIndex, keywordsCount, e);
        }
        return result;
    }

    /**
     * <p>Description: 正负面关键词</p>
     *
     * @param artList       包含文章的标题和内容的集合
     * @param emotionType   情感分类
     * @param startIndex    开始的索引，从哪个位置开始提取关键词
     * @param keywordsCount 提取的关键词数量
     * @throws Exception
     * @author Wangjianchun
     * @date 2017年7月19日
     */
    public static String getKeywords(List<String> artList, String emotionType,
                                     int startIndex, int keywordsCount) {
        StringBuilder result = new StringBuilder();
        try {
            JSONObject jsonObject = KeywordCloud.keywordCloud(artList, emotionType,
                    startIndex, keywordsCount);
            if (jsonObject == null) {
                return result.toString();
            }
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            if (jsonArray == null) {
                return result.toString();

            }
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                result.append(",").append(item.get("name"));
            }
            if (result.length() > 0) {
                result.deleteCharAt(0);
            }
        } catch(Exception e) {
            logger.error("获取正负面关键词出错，artList: {}，emotionType: {}, startIndex: {}, keywordsCount: {}, 异常信息：{}",
                    artList, emotionType, startIndex, keywordsCount, e);
        }

        return result.toString();
    }

    /**
     * <p>Description: 获取文档指纹</p>
     *
     * @param str 文章的标题或者内容
     * @author Wangjianchun
     * @date 2017年6月30日
     */
    public static String getFingerprint(String str) {
        String result = null;
        try {
            result = GetSimHash.toGetSimHash(str);
        } catch(Exception e) {
            logger.error("获取文档指纹出错，str: {}, 异常信息：{}", str, e);
        }
        return result;
    }

    /**
     * <p>Description: 计算企业压力指数
     *
     * @param url             文章的url
     * @param company         公司全称
     * @param publishTime     文章的发布时间，格式：yyyy/MM/dd  HH:mm:ss
     * @param emotionType     情感类型：positive：正面；neutral：中性；negative：负面
     * @param crawlerDateTime 文章的采集时间，格式：yyyy/MM/dd  HH:mm:ss
     *                        </p>
     * @author Wangjianchun
     * @date 2017年6月30日
     */
    public static JSONObject calculateEnterprisePressureIndex(String url, String company,
                                                              String publishTime,
                                                              String emotionType,
                                                              String crawlerDateTime) {
        JSONObject result = null;
        try {
            result = Analysis.updatePressure(url, company, publishTime, emotionType, crawlerDateTime);
        } catch(Exception e) {
            logger.error("计算企业压力指数出错，url: {}, company: {}, publishTime: {}, "
                            + "emotionType: {}, crawlerDateTime: {}, 异常信息：{}", url, company, publishTime,
                    emotionType, crawlerDateTime, e);
        }
        return result;
    }

    /**
     * <p>Description: 更新公司名称</p>
     *
     * @param company 公司全称
     * @return 更新结果
     * @author Wangjianchun
     * @date 2017年7月20日
     */
    public static JSONObject saveCompany(String company) {
        JSONObject result = null;
        try {
            result = CompanyAbbr.toAddCompany(company);
        } catch(Exception e) {
            logger.error("更新公司名称出错，company: {}, 异常信息：{}", company, e);
        }
        return result;
    }

    /**
     * 缓存研判后的分类语料
     *
     * @param title      标题
     * @param content    内容
     * @param classifier 分类类型
     * @return
     * @throws Exception
     */
    public static JSONObject addArticleType(String title, String content, String classifier) {
        JSONObject json = null;
        try {
            json = EventClassification.addArticleByUpdModel(title, content, classifier);
        } catch(Exception e) {
            logger.error("缓存研判后的分类语料出错，title: {}, classifier: {}, 异常信息：{}", title, classifier, e);
        }
        return json;
    }

    /**
     * 保存jar包内一些累计缓存数据(压力指数)
     *
     * @return
     */
    public static boolean save() {
        boolean result = false;
        try {
            result = Analysis.save();
        } catch(Exception e) {
            logger.error("保存累计缓存数据(压力指数)出错，异常信息：{}", e);
        }
        return result;
    }

    /**
     * 根据上次压力指数计算当前衰减后的压力指数
     *
     * @param lastPressure    上一次的压力指数
     * @param lastDateTime    上一次的时间，格式：yyyy/MM/dd HH:mm:ss
     * @param currentDateTime 当前时间：格式：yyyy/MM/dd HH:mm:ss
     * @param category        情感
     * @param company         公司名称
     */
    public static JSONObject getAttenuationPressure(Double lastPressure, String lastDateTime,
                                                    String currentDateTime, String category, String company) {
        JSONObject json = CompWeight.countPressure(lastPressure, lastDateTime, currentDateTime, category, company);
        return json;
    }

    /**
     * 获取文章提及人物
     *
     * @param title      文章标题
     * @param content    文章内容
     * @param peopleList 人物列表
     * @return
     */
    public static List<String> getRelatedPeople(String title, String content, List<String> peopleList) {
        List<String> result = Lists.newArrayList();
        try {
            JSONObject jsonObject = Analysis.getRelatedPeople(title, content, peopleList);
            if (jsonObject.getBoolean("status")) {
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                if (jsonArray == null) {
                    return result;
                }
                for (int i = 0, size = jsonArray.size(); i < size; i++) {
                    result.add(jsonArray.getString(i));
                }
            }
        } catch(Exception e) {
            logger.error("获取文章提及人物出错，title: {}, peopleList: {}, 异常信息：{}", title, peopleList, e);
        }

        return result;
    }

    /**
     * 文本分类
     *
     * @param article 文本内容
     * @return
     * @throws Exception
     */
    public static JSONObject docClassifier(String article) throws Exception {
        JSONObject result = Analysis.docClassifier(article);
        return result;
    }

    /**
     * <p>Description: 获取企业舆情压力指数</p>
     * 说明：获取到的压力指数是一个累计值，也就是说，是基于过去计算的压力指数累计生成的
     * @param company 公司全称
     * @author Wangjianchun
     * @date 2017年6月30日
     */
    public static JSONObject getEnterprisePressureIndex(String company) {
        JSONObject result = null;
        try {
            result = CompWeight.getCompWeight(company);
        } catch(Exception e) {
            logger.error("获取企业舆情压力指数出错，company: {}, 异常信息：{}", company, e);
        }
        return result;
    }

    /**
     * 根据文章找出对指定企业描述中提及的用户定义的关键词集合
     *
     * @param title   标题
     * @param content 内容
     * @param company 企业
     * @param keyword 关键词集合
     * @return
     */
    public static JSONObject getArticleMentionKeywords(String title, String content, String company, Set<String> keyword) {
        JSONObject result = null;
        try {
            result = Analysis.keywordWarning(title, content, company, keyword);
        } catch(Exception e) {
            logger.error("根据文章找出对指定企业描述中提及的用户定义的关键词集合，company: {}, 异常信息：{}", company, e);
        }
        return result;
    }

    /**
     * 添加企业别称
     * @param company 企业名称
     * @param abbr 企业别称
     * @return
     */
    public static JSONObject saveCompanyAbbr(String company, String abbr) {
        JSONObject result = null;
        try {
            result = Analysis.saveCompanyAbbr(company, abbr);
        } catch (Exception e) {
            logger.error("添加企业别称出错，company: {}, abbr: {}, 异常信息：{}", company, abbr, e);
        }
        return result;
    }

    /**
     * 删除企业别称
     * @param company 企业名称
     * @param abbr 企业别称
     * @return
     */
    public static JSONObject delCompanyAbbr(String company, String abbr) {
        JSONObject result = null;
        try {
            result = Analysis.delCompanyAbbr(company, abbr);
        } catch (Exception e) {
            logger.error("删除企业别称出错，company: {}, abbr: {}, 异常信息：{}", company, abbr, e);
        }
        return result;
    }

    /**
     * 根据公司全称重新生成简称列表
     * @param company 公司全称
     * @return 重新生成的公司简称列表
     * @throws Exception
     */
    public static JSONObject getInitCompanyAbbrList(String company) throws Exception {
        JSONObject result = Analysis.getInitCompanyAbbr(company);
        return result;
    }

    /**
     * 根据公司全称获取简称列表
     * @param company 公司全称
     * @return 公司简称列表
     * @throws Exception
     */
    public static JSONObject getCompanyAbbrList(String company) throws Exception {
        JSONObject result = Analysis.getCompanyAbbr(company);
        return result;
    }

    /**
     * <p>Description: 根据企业名称生成企业简称并更新简称库</p>
     *
     * @param company 公司全称
     * @return 更新结果
     * @author Wangjianchun
     * @date 2017年7月20日
     */
    public static JSONObject updateCompany(String company) {
        JSONObject result = null;
        try {
            result = CompanyAbbr.toAddCompany(company);
        } catch(Exception e) {
            logger.error("更新公司名称出错，company: {}", company, e);
        }
        return result;
    }

    /**
     * 添加企业名称对应排除词
     * @param company 企业全称
     * @param excludeWords 排除词
     * @return
     */
    public static JSONObject addCompanyExcludeWord(String company, String excludeWords) {
        JSONObject result = null;
        try {
            result = Analysis.addCompanyExcludeWord(company, excludeWords);
        } catch (Exception e) {
            logger.error("添加企业名称对应排除词出错，company: {}", company, e);
        }
        return result;
    }

    /**
     * 删除企业名称对应排除词
     * @param company 企业全称
     * @param excludeWords 排除词
     * @return
     */
    public static JSONObject delCompanyExcludeWord(String company, String excludeWords) {
        JSONObject result = null;
        try {
            result = Analysis.delCompanyExcludeWord(company, excludeWords);
        } catch (Exception e) {
            logger.error("删除企业名称对应排除词出错，company: {}", company, e);
        }
        return result;
    }

    /**
     * 添加情感词，丰富情感词库
     * @param emotionWord 待补充情感词集合
     * @param emotion 情感类型（negative、positive、neutral）
     * @return {"msg":"success","status":true}
     */
    public static JSONObject insertEmotionWord(Set<String> emotionWord,String emotion) {
        JSONObject result = null;
        try {
            result = Analysis.insertEmotionWord(emotionWord, emotion);
        } catch (Exception e) {
            logger.error("丰富情感词库出错，emotionWord: {}，emotion: {}", emotionWord, emotion, e);
        }
        return result;
    }

    /**
     * 获取情感词库列表
     *
     * 返回结果示例：{"msg":"success","negative":["信用卡1元额度","群体维权","未获许可在线播放","非议"],"neutral":["没有胜诉","经历失败"],"positive":["优秀创新产品","很抢手","用功苦读","销量上涨"],"status":true}
     * @return
     */
    public static JSONObject findAllEmotionWord() {
        JSONObject result = null;
        try {
            result = Analysis.findAllEmotionWord();
        } catch (Exception e) {
            logger.error("获取情感词库列表出错", e);
        }
        return result;
    }

    /**
     * 删除情感词
     * @param emotionWord 情感词集合
     * @param emotion 情感类型（negative、positive、neutral）
     * @return {"msg":"success","status":true}
     */
    public static JSONObject delEmotionWord(Set<String> emotionWord, String emotion) {
        JSONObject result = null;
        try {
            result = Analysis.delEmotionWord(emotionWord, emotion);
        } catch (Exception e) {
            logger.error("删除情感词出错，emotionWord: {}，emotion: {}", emotionWord, emotion, e);
        }
        return result;
    }

    /**
     * <p>Description: 根据文章的url获取媒体权重</p>
     *
     * @param url
     * @return
     * @author Wangjianchun
     * @date 2017年7月3日
     */
    public static Double getMediaWeight(String url) {
        Double mediaWeight = null;
        try {
            mediaWeight = Analysis.postWeight(url);
        } catch(Exception e) {
            logger.error("根据文章的url获取媒体权重出错，url: {}, 异常信息：{}", url, e);
        }
        return mediaWeight;
    }

    /**
     * 重新生成文本分类模板
     *
     * @return
     * @throws Exception
     */
    public static boolean initClassifierModel() throws Exception {
        return EventClassification.updateModel();
    }

    /**
     * 更新用户自定义文章分类词库
     *
     * @param classifierId   事件分类id
     * @param classifierName 事件分类名称
     * @param classifierColl 事件分类关键词
     * @return
     */
    public static JSONObject updateClassifierColl(String classifierId, String classifierName, Set<String> classifierColl) {
        JSONObject result = null;
        try {
            result = Analysis.updateClassifierColl(classifierId, classifierName, classifierColl);
        } catch(Exception e) {
            logger.error("更新用户自定义文章分类词库出错，classifierId: {}, classifierName: {}, classifierColl: {}, 异常信息：{}",
                    classifierId, classifierName, classifierColl, e);
        }
        return result;
    }

    /**
     * 关键词预警（根据文章标题和内容找出对指定企业描述中提及的用户定义的关键词集合）
     *
     * @param title 文章标题
     * @param content 文章内容
     * @param company 公司全称
     * @param keywords 预警关键词
     * @return 提及该企业的预警关键词集合
     */
    public static JSONArray getContainedWarningKeywords(String title, String content,
                                                        String company, Set<String> keywords) {
        try{
            JSONObject jsonObject = Analysis.keywordWarning(title, content,company, keywords);
            if(jsonObject != null){
                String result = jsonObject.getString("result");
                if("暂无".equals(result)){
                    return null;
                }
                return jsonObject.getJSONArray("result");
            }
        }catch (Exception e){
            logger.error("关键词预警（根据文章标题和内容找出对指定企业描述中提及的用户定义的关键词集合）出错，异常信息：{}", e);
        }

        return null;
    }

    /**
     * 初始化压力指数
     * @return
     */
    public static JSONArray initPressure() {
        try{
            JSONObject clearPressure = Analysis.clearPressure();
            logger.info("初始化压力指数完成，结果：{}", clearPressure);
        }catch (Exception e){
            logger.error("初始化压力指数出错", e);
        }

        return null;
    }

}
