package com.sparrow.opinion.mysql.repository;

import com.sparrow.opinion.mysql.entity.Media;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * <p>Title: MediaRepository</p>
 * <p>Description: 媒体信息数据库接口</p>
 *
 * @author zhangtong
 * @date 2017年7月4日
 */
public interface MediaRepository extends CrudRepository<Media, Long> {

    /**
     * <p>Description: 根据媒体地址查询媒体信息集合</p>
     *
     * @param urlMD5
     * @return
     * @author zhangtong
     * @date 2017年7月4日
     */
    List<Media> findByUrlMD5(String urlMD5);
}
