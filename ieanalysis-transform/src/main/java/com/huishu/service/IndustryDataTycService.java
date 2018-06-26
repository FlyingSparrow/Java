package com.huishu.service;

import com.huishu.entity.IndustryDataTyc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 * @date 2018-6-6
 */
public interface IndustryDataTycService {

	/**
	 * 分页查询
	 *
	 * @param entity
	 * @param pageable
	 * @return
	 */
	List<IndustryDataTyc> findOneHundred(IndustryDataTyc entity, Pageable pageable);

	/**
	 * 分页查询
	 *
	 * @param entity
	 * @param pageable
	 * @return
	 */
	Page<IndustryDataTyc> findByPage(IndustryDataTyc entity, Pageable pageable);

	/**
	 * 批量保存
	 *
	 * @param list
	 */
	void save(List<IndustryDataTyc> list);

	/**
	 * 批量删除
	 *
	 * @param list
	 */
	void delete(List<IndustryDataTyc> list);

}
