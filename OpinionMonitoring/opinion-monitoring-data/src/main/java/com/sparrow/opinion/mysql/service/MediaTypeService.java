package com.sparrow.opinion.mysql.service;

import com.sparrow.opinion.mysql.entity.MediaType;

import java.util.List;

public interface MediaTypeService {

    /**
     * <p>Description: 媒体类型信息添加</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年7月4日
     */
    boolean save(MediaType entity);

    /**
     * <p>Description: 批量添加媒体类型信息</p>
     *
     * @param list
     * @return
     * @author zhangtong
     * @date 2017年7月4日
     */
    boolean batchAdd(List<MediaType> list);

    /**
     * <p>Description: 删除媒体类型信息（根据id删除）</p>
     *
     * @param id
     * @return
     * @author zhangtong
     * @date 2017年7月4日
     */
    boolean del(Long id);

    /**
     * <p>Description: 更新媒体类型信息</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年7月4日
     */
    boolean update(MediaType entity);

    /**
     * <p>Description: 查询媒体类型信息</p>
     *
     * @return
     * @author zhangtong
     * @date 2017年7月4日
     */
    List<MediaType> findList();

    /**
     * <p>Description: 查询媒体类型信息是否存在</p>
     *
     * @param mediaUrl
     * @return
     * @author zhangtong
     * @date 2017年7月4日
     */
    boolean isExistByMediaUrl(String mediaUrl);

    /**
     * <p>Description: 根据媒体url查询媒体类型</p>
     *
     * @param mediaUrl
     * @return
     * @author zhangtong
     * @date 2017年7月4日
     */
    List<MediaType> findByByMediaUrl(String mediaUrl);

}
