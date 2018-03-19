package com.sparrow.opinion.mysql.service.impl;

import com.google.common.collect.Lists;
import com.sparrow.opinion.mysql.entity.MediaType;
import com.sparrow.opinion.mysql.repository.MediaTypeRepository;
import com.sparrow.opinion.mysql.service.MediaTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>Title: MediaServiceImpl</p>
 * <p>Description: 媒体类型信息实现类</p>
 *
 * @author zhangtong
 * @date 2017年7月4日
 */
@Transactional
@Service
public class MediaTypeServiceImpl implements MediaTypeService {

    @Autowired
    private MediaTypeRepository mediaTypeRepository;

    @Override
    public boolean save(MediaType entity) {
        mediaTypeRepository.save(entity);
        return true;
    }

    @Override
    public boolean batchAdd(List<MediaType> list) {
        mediaTypeRepository.save(list);
        return true;
    }

    @Override
    public boolean del(Long id) {
        mediaTypeRepository.delete(id);
        return true;
    }

    @Override
    public boolean update(MediaType entity) {
        mediaTypeRepository.save(entity);
        return true;
    }

    @Override
    public List<MediaType> findList() {
        Iterable<MediaType> iterable = mediaTypeRepository.findAll();
        List<MediaType> list = Lists.newArrayList();
        iterable.forEach(list::add);
        return list;
    }

    @Override
    public boolean isExistByMediaUrl(String mediaUrl) {
        List<MediaType> mediaTypeList = mediaTypeRepository.findOneByMediaUrl(mediaUrl);
        return (mediaTypeList != null && mediaTypeList.size() > 0);
    }

    @Override
    public List<MediaType> findByByMediaUrl(String mediaUrl) {
        return mediaTypeRepository.findOneByMediaUrl(mediaUrl);
    }
}
