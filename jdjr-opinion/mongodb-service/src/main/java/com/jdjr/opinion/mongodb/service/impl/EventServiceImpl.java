package com.jdjr.opinion.mongodb.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ehcache.EhCache;
import com.forget.articleSimilarity.ArticleSimilarity;
import com.forget.dataSort.SortByValidValue2Event;
import com.forget.dataSort.SortByValidValueDouble;
import com.forget.docClassification.EventClassification;
import com.forget.test.SubModel;
import com.forget.test.SubModelColl;
import com.forget.utils.DateTest;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.jdjr.opinion.mongodb.dao.MongodbSearchDao;
import com.jdjr.opinion.mongodb.entity.*;
import com.jdjr.opinion.mongodb.repository.EventRepository;
import com.jdjr.opinion.mongodb.service.ArticleService;
import com.jdjr.opinion.mongodb.service.EventArticleService;
import com.jdjr.opinion.mongodb.service.EventService;
import com.jdjr.opinion.mongodb.service.ProductService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * <p>Title: EventRepository</p>
 * <p>Description: 事件信息接口实现类</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
@Transactional
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventArticleService eventArticleService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private MongodbSearchDao mongodbSearchDao;

    @Override
    public boolean add(EventInfo entity) {
        eventRepository.save(entity);
        return true;
    }

    @Override
    public boolean update(EventInfo entity) {
        eventRepository.save(entity);
        return true;
    }

    @Override
    public boolean updateEventByArticle(String articleId, String article, String enterpriseId,
                                        String enterpriseName, String publishDate) {
        findEvent1_db(articleId, article, enterpriseId, enterpriseName, publishDate);
        return true;
    }

    @SuppressWarnings("unchecked")
    public void findEvent1_db(String articleId, String article, String enterpriseId,
                              String enterpriseName, String publishDate) {
        EhCache ehCache = EhCache.getEhCache();
        Cache myCache = ehCache.getCache();
        EventArticle eventArticle = new EventArticle();
        Product product = new Product();
        /*
         *
		 */
        Element element = myCache.get(enterpriseId);
        Map<String, SubModelColl> subColl = new HashMap<String, SubModelColl>();
        if (element != null) {
            subColl = (Map<String, SubModelColl>) element.getObjectValue();
        }
        if (subColl == null || subColl.isEmpty()) {
            product = productService.findProductByCompany(enterpriseId);
            if(product != null){
                subColl = product.getProduct();
            }
        }

        Map<String, Double> map = new HashMap<String, Double>();
        if (subColl == null || subColl.isEmpty()) {
            subColl = new HashMap<String, SubModelColl>();

            SubModelColl subModelColl = new SubModelColl();
            SubModel subModel = new SubModel();
            subModel.setContent(articleId);
            subModel.setTarget(null);
            subModel.setPublishTime(publishDate);
            subModel.setSimilarity(Double.valueOf(0));
            subModelColl.setItemSubModel(subModel);
            subModelColl.setLinkSubModel(new HashMap<String, SubModel>());
            subColl.put(articleId, subModelColl);
            ArticleSimilarity.updateArticleWeight(articleId, article);
            Set<String> own_article_id_list = new HashSet<String>();
            //第一次
            eventArticle = new EventArticle();
            eventArticle.setArticleId(articleId);

            eventArticle.setEnterpriseIdList(enterpriseId);
            eventArticle.setOwnArticleIdList(own_article_id_list);

            eventArticle.setEnterpriseIdList(enterpriseId);
            eventArticle.setOwnArticleIdList(own_article_id_list);

            eventArticleService.updateEventArticleByCompanyAndArticle(eventArticle);

            product = new Product();
            product.setEnterpriseId(enterpriseId);
            product.setProduct(subColl);
            productService.updateProductByCompany(product);

            element = new Element(enterpriseId, subColl);
            myCache.put(element);

        } else {
            if(subColl.entrySet() != null && subColl.entrySet().size() > 0){
                Set<String> tempSet = new HashSet<String>(subColl.keySet());
                for(String articleIdItem : tempSet){
                    Double articleSimilarity = ArticleSimilarity.articleSimilarity1(articleId, article, articleIdItem);
                    map.put(articleIdItem, articleSimilarity);
                }

                /**
                 * 说明：由于下面的代码在执行过程中报 java.util.ConcurrentModificationException: null 的错误，
                 * 并且暂未找到原因，因此改为上面的方式进行处理
                 * @author wangjianchun
                 * @date 2018-01-24
                 */
                /*for (Entry<String, SubModelColl> entry : subColl.entrySet()) {
                    Double articleSimilarity = ArticleSimilarity.articleSimilarity1(articleId, article, entry.getKey());
                    map.put(entry.getKey(), articleSimilarity);
                }*/
            }

            map = SortByValidValueDouble.sortMapByValue(map);
            Entry<String, Double> next = null;
            String key = "";
            Iterator<Entry<String, Double>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                next = iterator.next();
                key = next.getKey();
                SubModelColl subModelColl = subColl.get(key);
                SubModel itemSubModel = subModelColl.getItemSubModel();
                String publishTime = itemSubModel.getPublishTime();
                boolean compare_dateTime = DateTest.compare_dateTime(publishDate, publishTime);
                if (compare_dateTime) {
                    break;
                }
            }

            if (Double.valueOf(0.2) > next.getValue()) {
                if (!subColl.containsKey(articleId)) {
                    SubModelColl subModelColl = new SubModelColl();
                    SubModel subModel = new SubModel();
                    subModel.setContent(articleId);
                    subModel.setTarget(null);
                    subModel.setSimilarity(0.0);
                    subModel.setPublishTime(publishDate);
                    subModelColl.setItemSubModel(subModel);
                    subModelColl.setLinkSubModel(new HashMap<String, SubModel>());
                    subColl.put(articleId, subModelColl);
                    Set<String> own_article_id_list = new HashSet<String>();
                    //第一次
                    eventArticle = new EventArticle();
                    eventArticle.setArticleId(articleId);

                    eventArticle.setEnterpriseIdList(enterpriseId);
                    eventArticle.setOwnArticleIdList(own_article_id_list);

                    eventArticle.setEnterpriseIdList(enterpriseId);
                    eventArticle.setOwnArticleIdList(own_article_id_list);

                    eventArticleService.updateEventArticleByCompanyAndArticle(eventArticle);

                    Map<String, SubModelColl> newSubColl = new HashMap<String, SubModelColl>(subColl);
                    product = new Product();
                    product.setEnterpriseId(enterpriseId);
                    product.setProduct(newSubColl);
                    productService.updateProductByCompany(product);

                    element = new Element(enterpriseId, subColl);
                    myCache.put(element);
                }
            } else {
                //相似
                if (subColl.containsKey(key)) {
                    SubModelColl subModelColl = subColl.get(key);

                    if (null == subModelColl.getItemSubModel().getTarget()) {
                        //原文
                        SubModel subModel = new SubModel();
                        subModel.setContent(key);
                        subModel.setSimilarity(next.getValue());
                        subModel.setTarget(articleId);
                        subModel.setPublishTime(publishDate);
                        subModelColl.setItemSubModel(subModel);
                        //相似集合
                        Map<String, SubModel> subMap = subModelColl.getLinkSubModel();
                        if (subMap == null) {
                            subMap = new HashMap<String, SubModel>();
                        }
                        subModel = new SubModel();
                        subModel.setContent(articleId);
                        subModel.setPublishTime(publishDate);
                        subModel.setSimilarity(next.getValue());
                        subMap.put(articleId, subModel);
                        subModelColl.setLinkSubModel(subMap);
                        subColl.put(key, subModelColl);
                        Set<String> own_article_id_list = subMap.keySet();
                        //第一次
                        eventArticle = new EventArticle();
                        eventArticle.setEnterpriseIdList(enterpriseId);
                        eventArticle.setArticleId(key);
                        eventArticle.setTargetArticleId(articleId);
                        eventArticle.setOwnArticleIdList(own_article_id_list);
                        eventArticleService.updateEventArticleByCompanyAndArticle(eventArticle);

                        if (!subColl.containsKey(articleId)) {
                            subModelColl = new SubModelColl();
                            subMap = new HashMap<String, SubModel>();
                            subModel = new SubModel();
                            subModel.setContent(articleId);
                            subModel.setTarget(key);
                            subModel.setPublishTime(publishDate);
                            subModel.setSimilarity(next.getValue());
                            subModelColl.setItemSubModel(subModel);
                            subModel = new SubModel();
                            subModel.setContent(key);
                            subModel.setPublishTime(publishDate);
                            subModel.setSimilarity(next.getValue());
                            subMap.put(key, subModel);
                            subModelColl.setLinkSubModel(subMap);
                            subColl.put(articleId, subModelColl);
                            //第一次
                            eventArticle = new EventArticle();
                            eventArticle.setEnterpriseIdList(enterpriseId);
                            eventArticle.setArticleId(articleId);
                            eventArticle.setTargetArticleId(key);
                            eventArticle.setOwnArticleIdList(own_article_id_list);
                            eventArticleService.updateEventArticleByCompanyAndArticle(eventArticle);
                        }
                    } else {
                        SubModel itemSubModel = subModelColl.getItemSubModel();
                        if (itemSubModel != null) {
                            Double similarity = itemSubModel.getSimilarity();
                            //判断互相关联
                            if (next.getValue() > similarity) {
                                String target = itemSubModel.getTarget();
                                itemSubModel.setTarget(articleId);
                                itemSubModel.setSimilarity(next.getValue());

                                Set<String> own_article_id_list = new HashSet<String>();
                                eventArticle = new EventArticle();
                                eventArticle.setEnterpriseIdList(enterpriseId);
                                eventArticle.setArticleId(key);
                                eventArticle.setTargetArticleId(articleId);
                                eventArticle.setOwnArticleIdList(own_article_id_list);
                                eventArticleService.updateEventArticleByCompanyAndArticle(eventArticle);

                                if (subColl.containsKey(articleId)) {
                                    subModelColl = subColl.get(articleId);
                                    SubModel subModel = subModelColl.getItemSubModel();
                                    if (subModel.getSimilarity() < similarity) {
                                        subModel.setContent(articleId);
                                        subModel.setPublishTime(publishDate);
                                        subModel.setSimilarity(next.getValue());
                                        subModel.setTarget(key);
                                    }
                                    Map<String, SubModel> subMap = subModelColl.getLinkSubModel();
                                    subModel = new SubModel();
                                    subModel.setContent(key);
                                    subModel.setPublishTime(publishDate);
                                    subModel.setSimilarity(next.getValue());
                                    subMap.put(key, subModel);

                                } else {
                                    subModelColl = new SubModelColl();
                                    SubModel subModel = new SubModel();
                                    subModel.setContent(articleId);
                                    subModel.setPublishTime(publishDate);
                                    subModel.setSimilarity(next.getValue());
                                    subModel.setTarget(key);
                                    subModelColl.setItemSubModel(subModel);
                                    Map<String, SubModel> subMap = new HashMap<String, SubModel>();
                                    subModel = new SubModel();
                                    subModel.setContent(key);
                                    subModel.setPublishTime(publishDate);
                                    subModel.setSimilarity(next.getValue());
                                    subMap.put(key, subModel);
                                    subModelColl.setLinkSubModel(subMap);
                                    subColl.put(articleId, subModelColl);

                                    own_article_id_list = subMap.keySet();
                                    eventArticle = new EventArticle();
                                    eventArticle.setEnterpriseIdList(enterpriseId);
                                    eventArticle.setArticleId(articleId);
                                    eventArticle.setOwnArticleIdList(own_article_id_list);
                                    eventArticleService.updateEventArticleByCompanyAndArticle(eventArticle);

                                }

                                //扯断 last_target
                                if (target != null && subColl.containsKey(target)) {
                                    subModelColl = subColl.get(target);

                                    Map<String, SubModel> linkSubModel = subModelColl.getLinkSubModel();
                                    if (linkSubModel != null && linkSubModel.containsKey(key)) {
                                        linkSubModel.remove(key);
                                    } else {
                                        linkSubModel = Maps.newHashMap();
                                    }
                                    own_article_id_list = linkSubModel.keySet();
                                    eventArticle = new EventArticle();
                                    eventArticle.setEnterpriseIdList(enterpriseId);
                                    eventArticle.setArticleId(target);
                                    eventArticle.setOwnArticleIdList(own_article_id_list);
                                    eventArticleService.updateEventArticleByCompanyAndArticle(eventArticle);
                                }
                            } else {
                                Map<String, SubModel> linkSubModel = subModelColl.getLinkSubModel();
                                if (linkSubModel != null && !linkSubModel.containsKey(articleId)) {
                                    SubModel subModel = new SubModel();
                                    subModel.setContent(articleId);
                                    subModel.setPublishTime(publishDate);
                                    subModel.setSimilarity(next.getValue());
                                    linkSubModel.put(articleId, subModel);
                                    eventArticle = mongodbSearchDao.findEventArticleByCompanyAndArtId(enterpriseId, articleId);
                                    if (eventArticle != null) {
                                        Set<String> ownArticleIdList = eventArticle.getOwnArticleIdList();
                                        if (ownArticleIdList != null) {
                                            ownArticleIdList.add(articleId);
                                        } else {
                                            ownArticleIdList = new HashSet<String>();
                                            ownArticleIdList.add(articleId);
                                        }
                                        eventArticle.setOwnArticleIdList(ownArticleIdList);
                                        eventArticleService.updateEventArticleByCompanyAndArticle(eventArticle);
                                    }

                                    if (!subColl.containsKey(articleId)) {
                                        subModelColl = new SubModelColl();
                                        subModel = new SubModel();
                                        subModel.setContent(articleId);
                                        subModel.setTarget(key);
                                        subModel.setPublishTime(publishDate);
                                        subModel.setSimilarity(next.getValue());
                                        subModelColl.setItemSubModel(subModel);
                                        Map<String, SubModel> subMap = new HashMap<String, SubModel>();
                                        subModelColl.setLinkSubModel(subMap);
                                        subColl.put(articleId, subModelColl);
                                        //第一次
                                        eventArticle = new EventArticle();
                                        eventArticle.setEnterpriseIdList(enterpriseId);
                                        eventArticle.setArticleId(articleId);
                                        HashSet<String> hashSet = new HashSet<String>();
                                        hashSet.add(key);
                                        eventArticle.setOwnArticleIdList(hashSet);
                                        eventArticleService.updateEventArticleByCompanyAndArticle(eventArticle);
                                    }
                                }
                            }
                        }
                    }

                }
                product = new Product();
                product.setEnterpriseId(enterpriseId);
                product.setProduct(Maps.newHashMap(subColl));
                productService.updateProductByCompany(product);
                element = new Element(enterpriseId, subColl);
                myCache.put(element);
            }
        }

        toUpdateEventColl1_(subColl, enterpriseId, enterpriseName, articleId);
    }

    /*
     * 更新事件库
     */
    public void toUpdateEventColl1(Map<String, SubModelColl> subColl, String enterpriseId, String enterpriseName) {

        EventInfo eventInfo;
        List<EventInfo> list = new ArrayList<EventInfo>();
        Map<Integer, Set<String>> map = new HashMap<Integer, Set<String>>();
        Set<String> set = new HashSet<String>();
        int eventId = 0;
        int index = 0;
        for (Entry<String, SubModelColl> entry : subColl.entrySet()) {
            if (0 == eventId) {
                set = new HashSet<String>();
                set.add(entry.getKey());
                SubModelColl value = entry.getValue();
                if (value != null) {
                    Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                    if (linkSubModel != null && !linkSubModel.isEmpty()) {
                        for (Entry<String, SubModel> e : linkSubModel.entrySet()) {
                            set.add(e.getKey());
                        }
                    }
                }
                map.put(eventId, set);
                eventId = 1;
            } else {
                if (map != null && !map.isEmpty()) {
                    set = new HashSet<String>();

                    for (Entry<Integer, Set<String>> ent : map.entrySet()) {
                        set = ent.getValue();
                        if (set.contains(entry.getKey())) {
                            index = ent.getKey();
                            break;
                        } else {
                            set = new HashSet<String>();
                        }
                    }

                    if (set != null && !set.isEmpty()) {
                        SubModelColl value = entry.getValue();
                        if (value != null) {
                            Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                            if (linkSubModel != null && !linkSubModel.isEmpty()) {
                                for (Entry<String, SubModel> e : linkSubModel.entrySet()) {
                                    set.add(e.getKey());
                                }
                            }
                        }
                        map.put(index, set);
                    } else {
                        SubModelColl value = entry.getValue();
                        if (value != null) {
                            SubModel itemSubModel = value.getItemSubModel();
                            String target = itemSubModel.getTarget();
                            if (target != null) {

                                for (Entry<Integer, Set<String>> ent : map.entrySet()) {
                                    set = ent.getValue();
                                    if (set.contains(target)) {
                                        index = ent.getKey();
                                        break;
                                    } else {
                                        set = new HashSet<String>();
                                    }
                                }

                                if (set != null && !set.isEmpty()) {
                                    set.add(entry.getKey());
                                    set.add(target);
                                    Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                                    if (linkSubModel != null && !linkSubModel.isEmpty()) {
                                        for (Entry<String, SubModel> e : linkSubModel.entrySet()) {
                                            set.add(e.getKey());
                                        }
                                    }
                                    map.put(index, set);
                                } else {
                                    set = new HashSet<String>();
                                    set.add(entry.getKey());
                                    Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                                    if (linkSubModel != null && !linkSubModel.isEmpty()) {
                                        for (Entry<String, SubModel> e : linkSubModel.entrySet()) {
                                            set.add(e.getKey());
                                        }
                                    }
                                    map.put(eventId, set);
                                }
                            } else if (value.getLinkSubModel() != null && !value.getLinkSubModel().isEmpty()) {
                                Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                                for (Entry<String, SubModel> e : linkSubModel.entrySet()) {
                                    set.add(e.getKey());
                                }
                                set.add(entry.getKey());
                                map.put(eventId, set);
                            } else {
                                set = new HashSet<String>();
                                set.add(entry.getKey());
                                map.put(eventId, set);
                            }
                        }
                    }
                    eventId++;
                }
            }
        }
        if (map != null && !map.isEmpty()) {
            //相似度排序
            Map<String, SubModelColl> byValue = SortByValidValue2Event.sortMapByValue(subColl);
            for (Entry<Integer, Set<String>> entry : map.entrySet()) {
                if (entry.getKey() != null) {
                    //List<String> artList = new ArrayList<String>();
                    Set<String> value = entry.getValue();
                    if (value != null && !value.isEmpty() && value.size() >= 3) {
                        String title = "";
                        String content = "";
                        String artId = "";
                        Date publishDateTime = new Date();
                        for (Entry<String, SubModelColl> subModel : byValue.entrySet()) {
                            if (value.contains(subModel.getKey())) {
                                SubModelColl value2 = subModel.getValue();
                                if (value2 != null) {
                                    SubModel itemSubModel = value2.getItemSubModel();
                                    String target = itemSubModel.getTarget();
                                    if (target != null && value.contains(target) && byValue.containsKey(target)) {
                                        SubModelColl subModelColl = byValue.get(target);
                                        if (subModelColl != null) {
                                            String publishTime = subModelColl.getItemSubModel().getPublishTime();
                                            //最早发布  即核心文章
                                            boolean compare_date = DateTest.compare_date(itemSubModel.getPublishTime(), publishTime);
                                            if (compare_date) {
                                                Article article = articleService.findById(subModel.getKey());
                                                if (article != null) {
                                                    title = article.getTitle();
                                                    content = article.getContent();
                                                    artId = article.getId();
                                                    publishDateTime = article.getPublishDateTime();
                                                    break;
                                                }
                                            } else {
                                                Article article = articleService.findById(target);
                                                if (article != null) {
                                                    title = article.getTitle();
                                                    content = article.getContent();
                                                    artId = article.getId();
                                                    publishDateTime = article.getPublishDateTime();
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        /*Iterator<String> iterator = value.iterator();
                        while (iterator.hasNext()) {
							String next = iterator.next();
							Article article = articleService.findById(next);
							if(article != null){
								if(article.getTitle().trim().equals("")){
									artList.save(article.getTitle()+";"+article.getContent());
								}else{
									artList.save(article.getTitle()+";"+article.getContent());
								}
							}
						}*/

                        eventInfo = new EventInfo();
                        //eventInfo.setId(entry.getKey().toString());
                        eventInfo.setArticleIdList(entry.getValue());
                        eventInfo.setEnterpriseId(enterpriseId);
                        eventInfo.setEnterpriseName(enterpriseName);
                        eventInfo.setCoreArticleId(artId);
                        eventInfo.setCoreArticleTitle(title);
                        JSONObject json = null;
                        try {
                            json = EventClassification.docClassification(title + ";" + content);
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                        if (json != null && json.getBooleanValue("status")) {
                            eventInfo.setEventType(json.getString("classification"));
                        }
                        eventInfo.setArticleIdList(entry.getValue());
                        eventInfo.setEnterpriseId(enterpriseId);
                        eventInfo.setEventName(title);
                        eventInfo.setPublishDatetime(publishDateTime);
                        list.add(eventInfo);

                    }
                }
            }
            if (!list.isEmpty()) {
                eventRepository.deleteByEnterpriseId(enterpriseId);
                eventRepository.save(list);
            }
        }
    }

    /*
     * 更新事件库
     */
    public void toUpdateEventColl1_(Map<String, SubModelColl> subColl, String enterpriseId, String enterpriseName, String articleId) {

        EventInfo eventInfo = new EventInfo();
        List<EventInfo> list = new ArrayList<EventInfo>();
        Map<Integer, Set<String>> map = new HashMap<Integer, Set<String>>();
        Set<String> set = new HashSet<String>();
        int eventId = 0;
        int index = 0;
        for (Entry<String, SubModelColl> entry : subColl.entrySet()) {
            if (0 == eventId) {
                set = new HashSet<String>();
                set.add(entry.getKey());
                SubModelColl value = entry.getValue();
                if (value != null) {
                    Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                    if (linkSubModel != null && !linkSubModel.isEmpty()) {
                        for (Entry<String, SubModel> e : linkSubModel.entrySet()) {
                            set.add(e.getKey());
                        }
                    }
                }
                map.put(eventId, set);
                eventId = 1;
            } else {
                if (map != null && !map.isEmpty()) {
                    set = new HashSet<String>();

                    for (Entry<Integer, Set<String>> ent : map.entrySet()) {
                        set = ent.getValue();
                        if (set.contains(entry.getKey())) {
                            index = ent.getKey();
                            break;
                        } else {
                            set = new HashSet<String>();
                        }
                    }

                    if (set != null && !set.isEmpty()) {
                        SubModelColl value = entry.getValue();
                        if (value != null) {
                            Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                            if (linkSubModel != null && !linkSubModel.isEmpty()) {
                                for (Entry<String, SubModel> e : linkSubModel.entrySet()) {
                                    set.add(e.getKey());
                                }
                            }
                        }
                        map.put(index, set);
                    } else {
                        SubModelColl value = entry.getValue();
                        if (value != null) {
                            SubModel itemSubModel = value.getItemSubModel();
                            String target = itemSubModel.getTarget();
                            if (target != null) {

                                for (Entry<Integer, Set<String>> ent : map.entrySet()) {
                                    set = ent.getValue();
                                    if (set.contains(target)) {
                                        index = ent.getKey();
                                        break;
                                    } else {
                                        set = new HashSet<String>();
                                    }
                                }

                                if (set != null && !set.isEmpty()) {
                                    set.add(entry.getKey());
                                    set.add(target);
                                    Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                                    if (linkSubModel != null && !linkSubModel.isEmpty()) {
                                        for (Entry<String, SubModel> e : linkSubModel.entrySet()) {
                                            set.add(e.getKey());
                                        }
                                    }
                                    map.put(index, set);
                                } else {
                                    set = new HashSet<String>();
                                    set.add(entry.getKey());
                                    Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                                    if (linkSubModel != null && !linkSubModel.isEmpty()) {
                                        for (Entry<String, SubModel> e : linkSubModel.entrySet()) {
                                            set.add(e.getKey());
                                        }
                                    }
                                    map.put(eventId, set);
                                }
                            } else if (value.getLinkSubModel() != null && !value.getLinkSubModel().isEmpty()) {
                                Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                                for (Entry<String, SubModel> e : linkSubModel.entrySet()) {
                                    set.add(e.getKey());
                                }
                                set.add(entry.getKey());
                                map.put(eventId, set);
                            } else {
                                set = new HashSet<String>();
                                set.add(entry.getKey());
                                map.put(eventId, set);
                            }
                        }
                    }
                    eventId++;
                }
            }
        }
        if (map != null && !map.isEmpty()) {
            Integer eventId_srcArticle = -1;
            for (Entry<Integer, Set<String>> entry : map.entrySet()) {
                Set<String> value = entry.getValue();
                if (value != null && !value.isEmpty() && value.size() >= 3) {
                    if (value.contains(articleId)) {
                        eventId_srcArticle = entry.getKey();
                    }
                }
            }
            //
            if (-1 != eventId_srcArticle) {
                //相似度排序
                Map<String, SubModelColl> byValue = SortByValidValue2Event.sortMapByValue(subColl);
                for (Entry<Integer, Set<String>> entry : map.entrySet()) {
                    if (entry.getKey() != null && entry.getKey().equals(eventId_srcArticle)) {
                        //List<String> artList = new ArrayList<String>();
                        Set<String> value = entry.getValue();
                        if (value != null && !value.isEmpty() && value.size() >= 3) {
                            String title = "";
                            String content = "";
                            String artId = "";
                            Date publishDateTime = new Date();
                            for (Entry<String, SubModelColl> subModel : byValue.entrySet()) {
                                if (value.contains(subModel.getKey())) {
                                    SubModelColl value2 = subModel.getValue();
                                    if (value2 != null) {
                                        SubModel itemSubModel = value2.getItemSubModel();
                                        String target = itemSubModel.getTarget();
                                        if (target != null && value.contains(target) && byValue.containsKey(target)) {
                                            SubModelColl subModelColl = byValue.get(target);
                                            if (subModelColl != null) {
                                                String publishTime = subModelColl.getItemSubModel().getPublishTime();
                                                //最早发布  即核心文章
                                                boolean compare_date = DateTest.compare_date(itemSubModel.getPublishTime(), publishTime);
                                                if (compare_date) {
                                                    Article article = articleService.findById(subModel.getKey());
                                                    if (article != null) {
                                                        title = article.getTitle();
                                                        content = article.getContent();
                                                        artId = article.getId();
                                                        publishDateTime = article.getPublishDateTime();
                                                        break;
                                                    }
                                                } else {
                                                    Article article = articleService.findById(target);
                                                    if (article != null) {
                                                        title = article.getTitle();
                                                        content = article.getContent();
                                                        artId = article.getId();
                                                        publishDateTime = article.getPublishDateTime();
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            /*Iterator<String> iterator = value.iterator();
                            while (iterator.hasNext()) {
								String next = iterator.next();
								Article article = articleService.findById(next);
								if(article != null){
									if(article.getTitle().trim().equals("")){
										artList.save(article.getTitle()+";"+article.getContent());
									}else{
										artList.save(article.getTitle()+";"+article.getContent());
									}
								}
							}*/
                            if (artId.equals(articleId)) {
                                //创建新的事件
                                eventInfo = new EventInfo();
                                //eventInfo.setId(entry.getKey().toString());
                                eventInfo.setArticleIdList(entry.getValue());
                                eventInfo.setEnterpriseId(enterpriseId);
                                eventInfo.setEnterpriseName(enterpriseName);
                                eventInfo.setCoreArticleId(artId);
                                eventInfo.setCoreArticleTitle(title);
                                JSONObject json = null;
                                try {
                                    json = EventClassification.docClassification(title + ";" + content);
                                } catch(Exception e) {
                                    e.printStackTrace();
                                }
                                if (json != null && json.getBooleanValue("status")) {
                                    eventInfo.setEventType(json.getString("classification"));
                                }
                                eventInfo.setArticleIdList(entry.getValue());
                                eventInfo.setEnterpriseId(enterpriseId);
                                eventInfo.setEventName(title);
                                eventInfo.setPublishDatetime(publishDateTime);
                                eventInfo = save(eventInfo);
                                //添加事件并返回事件id
                                String event_id = "";
                                if (eventInfo != null) {
                                    event_id = eventInfo.getId();
                                } else {
                                    eventInfo = eventRepository.findByCoreArticleIdAndEnterpriseId(articleId, enterpriseId);
                                    if (eventInfo != null) {
                                        event_id = eventInfo.getId();
                                    }
                                }
                                Set<String> value2 = entry.getValue();
                                for (String string : value2) {
                                    eventInfo = eventRepository.findByCoreArticleIdAndEnterpriseId(string, enterpriseId);
                                    if (eventInfo != null) {
                                        eventInfo.setReplaceId(event_id);
                                        eventInfo.setIsReplace("replace");
                                        list.add(eventInfo);
                                    }
                                }

                            } else {
                                eventInfo = eventRepository.findByCoreArticleIdAndEnterpriseId(artId, enterpriseId);
                                if (eventInfo != null) {
                                    //事件相关文章追加
                                    Set<String> articleIdList = eventInfo.getArticleIdList();
                                    if (articleIdList != null) {
                                        articleIdList.add(articleId);
                                    }
                                    eventInfo.setIsReplace(null);
                                    eventInfo.setReplaceId(null);
                                    list.add(eventInfo);
                                } else {
                                    //创建新的事件
                                    eventInfo = new EventInfo();
                                    //eventInfo.setId(entry.getKey().toString());
                                    eventInfo.setArticleIdList(entry.getValue());
                                    eventInfo.setEnterpriseId(enterpriseId);
                                    eventInfo.setEnterpriseName(enterpriseName);
                                    eventInfo.setCoreArticleId(artId);
                                    eventInfo.setCoreArticleTitle(title);
                                    JSONObject json = null;
                                    try {
                                        json = EventClassification.docClassification(title + ";" + content);
                                    } catch(Exception e) {
                                        e.printStackTrace();
                                    }
                                    if (json != null && json.getBooleanValue("status")) {
                                        eventInfo.setEventType(json.getString("classification"));
                                    }
                                    eventInfo.setArticleIdList(entry.getValue());
                                    eventInfo.setEnterpriseId(enterpriseId);
                                    eventInfo.setEventName(title);
                                    eventInfo.setPublishDatetime(publishDateTime);
                                    eventInfo.setIsReplace("");
                                    eventInfo.setReplaceId(null);
                                    list.add(eventInfo);
                                }
                            }
                        }
                    }
                }
                if (!list.isEmpty()) {
//					eventRepository.deleteByEnterpriseId(enterpriseId);
                    eventRepository.save(list);
                }
            }

        }
    }

    /*
     * 更新事件库
     */
    public List<EventInfo> toUpdateEventColl1_error(Map<String, SubModelColl> subColl, String enterpriseId, String enterpriseName) {

        EventInfo eventInfo;
        List<EventInfo> list = new ArrayList<EventInfo>();
        Map<Integer, Set<String>> map = new HashMap<Integer, Set<String>>();
        Set<String> set = new HashSet<String>();
        int eventId = 0;
        int index = 0;
        for (Entry<String, SubModelColl> entry : subColl.entrySet()) {
            if (0 == eventId) {
                set = new HashSet<String>();
                set.add(entry.getKey());
                SubModelColl value = entry.getValue();
                if (value != null) {
                    Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                    if (linkSubModel != null && !linkSubModel.isEmpty()) {
                        for (Entry<String, SubModel> e : linkSubModel.entrySet()) {
                            set.add(e.getKey());
                        }
                    }
                }
                map.put(eventId, set);
                eventId = 1;
            } else {
                if (map != null && !map.isEmpty()) {
                    set = new HashSet<String>();

                    for (Entry<Integer, Set<String>> ent : map.entrySet()) {
                        set = ent.getValue();
                        if (set.contains(entry.getKey())) {
                            index = ent.getKey();
                            break;
                        } else {
                            set = new HashSet<String>();
                        }
                    }

                    if (set != null && !set.isEmpty()) {
                        SubModelColl value = entry.getValue();
                        if (value != null) {
                            Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                            if (linkSubModel != null && !linkSubModel.isEmpty()) {
                                for (Entry<String, SubModel> e : linkSubModel.entrySet()) {
                                    set.add(e.getKey());
                                }
                            }
                        }
                        map.put(index, set);
                    } else {
                        SubModelColl value = entry.getValue();
                        if (value != null) {
                            SubModel itemSubModel = value.getItemSubModel();
                            String target = itemSubModel.getTarget();
                            if (target != null) {

                                for (Entry<Integer, Set<String>> ent : map.entrySet()) {
                                    set = ent.getValue();
                                    if (set.contains(target)) {
                                        index = ent.getKey();
                                        break;
                                    } else {
                                        set = new HashSet<String>();
                                    }
                                }

                                if (set != null && !set.isEmpty()) {
                                    set.add(entry.getKey());
                                    set.add(target);
                                    Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                                    if (linkSubModel != null && !linkSubModel.isEmpty()) {
                                        for (Entry<String, SubModel> e : linkSubModel.entrySet()) {
                                            set.add(e.getKey());
                                        }
                                    }
                                    map.put(index, set);
                                } else {
                                    set = new HashSet<String>();
                                    set.add(entry.getKey());
                                    Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                                    if (linkSubModel != null && !linkSubModel.isEmpty()) {
                                        for (Entry<String, SubModel> e : linkSubModel.entrySet()) {
                                            set.add(e.getKey());
                                        }
                                    }
                                    map.put(eventId, set);
                                }
                            } else if (value.getLinkSubModel() != null && !value.getLinkSubModel().isEmpty()) {
                                Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                                for (Entry<String, SubModel> e : linkSubModel.entrySet()) {
                                    set.add(e.getKey());
                                }
                                set.add(entry.getKey());
                                map.put(eventId, set);
                            } else {
                                set = new HashSet<String>();
                                set.add(entry.getKey());
                                map.put(eventId, set);
                            }
                        }
                    }
                    eventId++;
                }
            }
        }
        if (map != null && !map.isEmpty()) {
            //相似度排序
            Map<String, SubModelColl> byValue = SortByValidValue2Event.sortMapByValue(subColl);
            for (Entry<Integer, Set<String>> entry : map.entrySet()) {
                if (entry.getKey() != null) {
                    List<String> artList = new ArrayList<String>();
                    Set<String> value = entry.getValue();
                    if (value != null && !value.isEmpty()) {
                        String title = "";
                        String artId = "";
                        for (Entry<String, SubModelColl> subModel : byValue.entrySet()) {
                            if (value.contains(subModel.getKey())) {
                                SubModelColl value2 = subModel.getValue();
                                if (value2 != null) {
                                    SubModel itemSubModel = value2.getItemSubModel();
                                    String target = itemSubModel.getTarget();
                                    if (target != null && value.contains(target) && byValue.containsKey(target)) {
                                        SubModelColl subModelColl = byValue.get(target);
                                        if (subModelColl != null) {
                                            String publishTime = subModelColl.getItemSubModel().getPublishTime();
                                            //最早发布  即核心文章
                                            boolean compare_date = DateTest.compare_date(itemSubModel.getPublishTime(), publishTime);
                                            if (compare_date) {
                                                Article article = articleService.findById(subModel.getKey());
                                                if (article != null) {
                                                    title = article.getTitle();
                                                    artId = article.getId();
                                                    break;
                                                }
                                            } else {
                                                Article article = articleService.findById(target);
                                                if (article != null) {
                                                    title = article.getTitle();
                                                    artId = article.getId();
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        Iterator<String> iterator = value.iterator();
                        while (iterator.hasNext()) {
                            Article article = articleService.findById(iterator.next());
                            if (article != null) {
                                if (title.trim().equals("")) {
                                    artList.add(title + ";" + article.getContent());
                                } else {
                                    artList.add(article.getTitle() + ";" + article.getContent());
                                }
                            }
                        }

                        eventInfo = new EventInfo();
                        eventInfo.setId(entry.getKey().toString());
                        eventInfo.setArticleIdList(entry.getValue());
                        eventInfo.setEnterpriseId(enterpriseId);
                        eventInfo.setEnterpriseName(enterpriseName);
                        eventInfo.setCoreArticleId(artId);
                        eventInfo.setCoreArticleTitle(title);
                        JSONObject json = EventClassification.classification(artList);
                        if (json != null && json.getBooleanValue("status")) {
                            eventInfo.setEventType(json.getString("classification"));
                        }
                        eventInfo.setArticleIdList(entry.getValue());
                        eventInfo.setEnterpriseId(enterpriseId);
                        eventInfo.setEventName(title);
                        list.add(eventInfo);

                    }
                }
            }
            if (!list.isEmpty()) {
                return list;
            }
        }
        return list;
    }

    /*
     * 更新事件库
     */
    public List<EventInfo> toUpdateEventColl1_old(Map<String, SubModelColl> subColl, String enterpriseId, String enterpriseName) {

        EventInfo eventInfo;
        List<EventInfo> list = new ArrayList<EventInfo>();
        Map<Integer, Set<String>> map = new HashMap<Integer, Set<String>>();
        Set<String> set = new HashSet<String>();
        int eventId = 0;
        int index = 0;
        for (Entry<String, SubModelColl> entry : subColl.entrySet()) {
            if (0 == eventId) {
                set = new HashSet<String>();
                set.add(entry.getKey());
                SubModelColl value = entry.getValue();
                if (value != null) {
                    Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                    if (linkSubModel != null && !linkSubModel.isEmpty()) {
                        for (Entry<String, SubModel> e : linkSubModel.entrySet()) {
                            set.add(e.getKey());
                        }
                    }
                }
                map.put(eventId, set);
                eventId = 1;
            } else {
                if (map != null && !map.isEmpty()) {
                    set = new HashSet<String>();

                    for (Entry<Integer, Set<String>> ent : map.entrySet()) {
                        set = ent.getValue();
                        if (set.contains(entry.getKey())) {
                            index = ent.getKey();
                            break;
                        } else {
                            set = new HashSet<String>();
                        }
                    }

                    if (set != null && !set.isEmpty()) {
                        SubModelColl value = entry.getValue();
                        if (value != null) {
                            Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                            if (linkSubModel != null && !linkSubModel.isEmpty()) {
                                for (Entry<String, SubModel> e : linkSubModel.entrySet()) {
                                    set.add(e.getKey());
                                }
                            }
                        }
                        map.put(index, set);
                    } else {
                        SubModelColl value = entry.getValue();
                        if (value != null) {
                            SubModel itemSubModel = value.getItemSubModel();
                            String target = itemSubModel.getTarget();
                            if (target != null) {

                                for (Entry<Integer, Set<String>> ent : map.entrySet()) {
                                    set = ent.getValue();
                                    if (set.contains(target)) {
                                        index = ent.getKey();
                                        break;
                                    } else {
                                        set = new HashSet<String>();
                                    }
                                }

                                if (set != null && !set.isEmpty()) {
                                    set.add(entry.getKey());
                                    set.add(target);
                                    Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                                    if (linkSubModel != null && !linkSubModel.isEmpty()) {
                                        for (Entry<String, SubModel> e : linkSubModel.entrySet()) {
                                            set.add(e.getKey());
                                        }
                                    }
                                    map.put(index, set);
                                } else {
                                    set = new HashSet<String>();
                                    set.add(entry.getKey());
                                    Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                                    if (linkSubModel != null && !linkSubModel.isEmpty()) {
                                        for (Entry<String, SubModel> e : linkSubModel.entrySet()) {
                                            set.add(e.getKey());
                                        }
                                    }
                                    map.put(eventId, set);
                                }
                            } else if (value.getLinkSubModel() != null && !value.getLinkSubModel().isEmpty()) {
                                Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                                for (Entry<String, SubModel> e : linkSubModel.entrySet()) {
                                    set.add(e.getKey());
                                }
                                set.add(entry.getKey());
                                map.put(eventId, set);
                            } else {
                                set = new HashSet<String>();
                                set.add(entry.getKey());
                                map.put(eventId, set);
                            }
                        }
                    }
                    eventId++;
                }
            }
        }
        if (map != null && !map.isEmpty()) {

            for (Entry<Integer, Set<String>> entry : map.entrySet()) {
                if (entry.getKey() != null) {
                    if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                        List<String> artList = new ArrayList<String>();
                        Iterator<String> iterator = entry.getValue().iterator();
                        String title = "";
                        String artId = "";
                        while (iterator.hasNext()) {
                            Article article = articleService.findById(iterator.next());
                            if (article != null) {
                                if (title.trim().equals("")) {
                                    artId = article.getId();
                                    title = article.getTitle();
                                    artList.add(title + ";" + article.getContent());
                                } else {
                                    artList.add(article.getTitle() + ";" + article.getContent());
                                }
                            }
                        }

                        eventInfo = new EventInfo();
                        eventInfo.setId(entry.getKey().toString());
                        eventInfo.setArticleIdList(entry.getValue());
                        eventInfo.setEnterpriseId(enterpriseId);
                        eventInfo.setEnterpriseName(enterpriseName);
                        eventInfo.setCoreArticleId(artId);
                        eventInfo.setCoreArticleTitle(title);
                        JSONObject json = EventClassification.classification(artList);
                        if (json != null && json.getBooleanValue("status")) {
                            json.getString("classification");
                        }
                        eventInfo.setArticleIdList(entry.getValue());
                        eventInfo.setEnterpriseId(enterpriseId);
                        eventInfo.setEventName(title);
                        list.add(eventInfo);

                    }
                }
            }
            if (!list.isEmpty()) {
                eventRepository.delete(enterpriseId);
                eventRepository.save(list);
            }
        }
        return list;
    }


    /*
     * 更新事件库
     */
    public void toUpdateEventColl2(Map<String, SubModelColl> subColl, String enterpriseId, String enterpriseName) {

        EventInfo eventInfo;

        Map<Integer, Set<String>> map = new HashMap<Integer, Set<String>>();
        Set<String> set = new HashSet<String>();
        int index = 0;
        int eventId = 1;
        for (Entry<String, SubModelColl> entry : subColl.entrySet()) {

            if (0 == index) {
                set = new HashSet<String>();
                set.add(entry.getKey());
                SubModelColl value = JSONObject.parseObject(JSONObject.toJSONString(entry.getValue()), SubModelColl.class);
                if (value != null) {
                    Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                    if (linkSubModel != null && !linkSubModel.isEmpty()) {
                        set.addAll(linkSubModel.keySet());
                    }
                }
                map.put(0, set);
                index = 1;
            } else {

                if (map != null && !map.isEmpty()) {
                    set = new HashSet<String>();

                    for (Entry<Integer, Set<String>> ent : map.entrySet()) {
                        index = ent.getKey();
                        set = ent.getValue();
                        if (set.contains(entry.getKey())) {
                            break;
                        }
                    }

                    if (set != null && !set.isEmpty()) {
                        SubModelColl value = JSONObject.parseObject(JSONObject.toJSONString(entry.getValue()), SubModelColl.class);
                        if (value != null) {
                            Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                            if (linkSubModel != null && !linkSubModel.isEmpty()) {
                                set.addAll(linkSubModel.keySet());
                            }
                        }
                        map.put(index, set);
                    } else {
                        SubModelColl value = JSONObject.parseObject(JSONObject.toJSONString(entry.getValue()), SubModelColl.class);
                        if (value != null) {
                            SubModel itemSubModel = value.getItemSubModel();
                            String target = itemSubModel.getTarget();
                            if (target != null) {

                                for (Entry<Integer, Set<String>> ent : map.entrySet()) {
                                    index = ent.getKey();
                                    set = ent.getValue();
                                    if (set.contains(target)) {
                                        break;
                                    }
                                }

                                if (set != null && !set.isEmpty()) {
                                    set.add(target);
                                    Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                                    if (linkSubModel != null && !linkSubModel.isEmpty()) {
                                        set.addAll(linkSubModel.keySet());
                                    }
                                    map.put(index, set);
                                } else {
                                    set = new HashSet<String>();
                                    set.add(entry.getKey());
                                    Map<String, SubModel> linkSubModel = value.getLinkSubModel();
                                    if (linkSubModel != null && !linkSubModel.isEmpty()) {
                                        set.addAll(linkSubModel.keySet());
                                    }
                                    eventId++;
                                    map.put(eventId, set);
                                }
                            } else if (value.getLinkSubModel() != null && !value.getLinkSubModel().isEmpty()) {
                                map.put(eventId, value.getLinkSubModel().keySet());
                            } else {
                                set = new HashSet<String>();
                                set.add(entry.getKey());
                                map.put(eventId, set);
                                eventId++;
                            }

                        }
                    }
                }
            }
        }

        if (map != null && !map.isEmpty()) {

            List<EventInfo> list = new ArrayList<EventInfo>();
            List<String> artList = new ArrayList<String>();
            for (Entry<Integer, Set<String>> entry : map.entrySet()) {
                if (entry.getKey() != null) {
                    if (entry.getValue() != null && !entry.getValue().isEmpty() && entry.getValue().size() >= 3) {
                        Iterator<String> iterator = entry.getValue().iterator();
                        String title = "";
                        String artId = "";
                        while (iterator.hasNext()) {
                            Article article = articleService.findById(iterator.next());
                            if (article != null) {
                                if (title.trim().equals("")) {
                                    artId = article.getId();
                                    title = article.getTitle();
                                    artList.add(title + ";" + article.getContent());
                                } else {
                                    artList.add(article.getTitle() + ";" + article.getContent());
                                }
                            }
                        }

                        eventInfo = new EventInfo();
                        //event.setId(entry.getKey().toString());
                        eventInfo.setArticleIdList(entry.getValue());
                        eventInfo.setEnterpriseId(enterpriseId);
                        eventInfo.setEnterpriseName(enterpriseName);
                        eventInfo.setCoreArticleId(artId);
                        eventInfo.setCoreArticleTitle(title);
                        JSONObject json = EventClassification.classification(artList);
                        if (json != null && json.getBooleanValue("status")) {
                            json.getString("classification");
                        }
                        //eventInfo.setId(entry.getKey().toString());
                        eventInfo.setArticleIdList(entry.getValue());
                        eventInfo.setEnterpriseId(enterpriseId);
                        eventInfo.setEventName(title);
                        list.add(eventInfo);

                    }
                }
            }
            if (!list.isEmpty()) {
                eventRepository.deleteByEnterpriseId(enterpriseId);
                eventRepository.save(list);
            }
        }

    }

    @Override
    public EventInfo findById(String id) {
        return eventRepository.findOne(id);
    }

    @Override
    public JSONArray findListByEntity(OpinionDetailsSnapshot entity) {
        return mongodbSearchDao.findListByEntity(entity);
    }

    @Override
    public EventInfo save(EventInfo entity) {
        return eventRepository.save(entity);
    }

    @Override
    public List<EventInfo> findListByCoreArticleIdAndEnterpriseId(String coreArticleId, String enterpriseId) {
        return mongodbSearchDao.findListEventInfoByCoreArticleId(coreArticleId, enterpriseId);
    }

    @Override
    public JSONObject findEventAbstract(String eventId) {
        JSONObject result = new JSONObject();
        result.put("eventName", "");
        result.put("opinionCount", 0);
        EventInfo eventInfo = eventRepository.findOne(eventId);
        if (eventInfo != null) {
            Set<String> articleIdList = eventInfo.getArticleIdList();
            if (articleIdList == null) {
                articleIdList = Sets.newHashSet();
            }
            result.put("eventName", eventInfo.getEventName());
            result.put("opinionCount", articleIdList.size());
        }

        return result;
    }

    @Override
    public String getEventInfoRootId(String eventId) {
        String rootId;
        while (true) {
            EventInfo eventInfo = eventRepository.findOne(eventId);
            if (eventInfo != null) {
                if (StringUtils.isNotEmpty(eventInfo.getIsReplace())
                        && eventInfo.getIsReplace().equals("replace")) {
                    eventId = eventInfo.getReplaceId();
                } else {
                    rootId = eventInfo.getId();
                    break;
                }
            }
        }
        return rootId;
    }

    @Override
    public long findCount() {
        return eventRepository.count();
    }

    @Override
    public boolean delete(String id) {
        eventRepository.delete(id);
        return true;
    }

    @Override
    public List<String> findIdList(int pageNumber, int pageSize) {
        Pageable pageable = new PageRequest(pageNumber-1, pageSize);
        Page<EventInfo> page = eventRepository.findAll(pageable);
        return page.getContent().parallelStream().map(EventInfo::getId)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteAll() {
        eventRepository.deleteAll();
        return true;
    }

}
