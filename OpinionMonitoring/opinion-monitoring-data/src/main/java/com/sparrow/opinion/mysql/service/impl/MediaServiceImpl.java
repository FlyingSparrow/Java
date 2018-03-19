package com.sparrow.opinion.mysql.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sparrow.opinion.constants.SysConst;
import com.sparrow.opinion.mysql.entity.Media;
import com.sparrow.opinion.mysql.repository.MediaRepository;
import com.sparrow.opinion.mysql.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: MediaServiceImpl</p>
 * <p>Description: 媒体信息实现类</p>
 *
 * @author zhangtong
 * @date 2017年7月4日
 */
@Transactional
@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Override
    public boolean add(Media entity) {
        boolean exist = this.isExistByUrlMD5(entity.getUrlMD5());
        if (!exist) {
            mediaRepository.save(entity);
        }
        return true;
    }

    @Override
    public boolean batchAdd(List<Media> list) {
        mediaRepository.save(list);
        return true;
    }

    @Override
    public boolean del(Long id) {
        mediaRepository.delete(id);
        return true;
    }

    @Override
    public boolean update(Media entity) {
        mediaRepository.save(entity);
        return true;
    }

    @Override
    public List<Media> findList() {
        Iterable<Media> iterable = mediaRepository.findAll();
        List<Media> list = Lists.newArrayList();
        iterable.forEach(list::add);
        return list;
    }

    @Override
    public boolean isExistByUrlMD5(String urlMD5) {
        List<Media> mediaList = mediaRepository.findByUrlMD5(urlMD5);
        return (mediaList != null && mediaList.size() > 0);
    }

    @Override
    public List<Media> findByUrlMD5(String urlMD5) {
        return mediaRepository.findByUrlMD5(urlMD5);
    }

    @Cacheable(value = SysConst.MEDIA_CACHE_NAME, key = "#url")
    @Override
    public Map<String, Integer> findMediaWeightCache(String url) {
        List<Media> medias = this.findList();
        Map<String, Integer> map = Maps.newHashMap();
        for(Media media : medias){
            map.put(media.getMediaUrl(), media.getWeight());
        }
        return map;
    }

    @CachePut(value = SysConst.MEDIA_CACHE_NAME, key = "#url")
    @Override
    public Map<String, Integer> updateMediaWeightCache(String url) {
        List<Media> medias = this.findList();
        Map<String, Integer> map = Maps.newHashMap();
        for(Media media : medias){
            map.put(media.getMediaUrl(), media.getWeight());
        }
        return map;
    }
}
