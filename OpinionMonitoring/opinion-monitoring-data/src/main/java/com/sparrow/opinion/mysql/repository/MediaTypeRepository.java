package com.sparrow.opinion.mysql.repository;


import com.sparrow.opinion.mysql.entity.MediaType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * <p>Title: MediaRepository</p>
 * <p>Description: 媒体类型信息数据库接口</p>
 *
 * @author zhangtong
 * @date 2017年7月4日
 */
public interface MediaTypeRepository extends CrudRepository<MediaType, Long> {

    /**
     * <p>Description: 根据媒体地址查询媒体类型信息</p>
     *
     * @param mediaUrl
     * @return
     * @author zhangtong
     * @date 2017年7月4日
     */
    List<MediaType> findOneByMediaUrl(String mediaUrl);
}
