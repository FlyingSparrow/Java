package com.huishu.service.impl;

import com.huishu.datasource.TargetDataSource;
import com.huishu.entity.Wechat;
import com.huishu.repository.WechatRepository;
import com.huishu.service.WechatService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjianchun
 */
@Service
public class WechatServiceImpl implements WechatService {

    @Autowired
    private WechatRepository wechatRepository;

    @Override
    @TargetDataSource(name = "sapro")
    public List<Wechat> findOneHundred(Wechat wechat, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 100);
        }
        Page<Wechat> page = wechatRepository.findAll((root, query, cb) -> {
            Path<Long> id = root.get("id");
            Path<String> isRead = root.get("isRead");
            if (wechat != null) {
                List<Predicate> queryList = new ArrayList<Predicate>();
                if (wechat.getId() != null) {
                    queryList.add(cb.greaterThan(id, wechat.getId()));
                }
                if (StringUtils.isNotEmpty(wechat.getIsRead())) {
                    queryList.add(cb.equal(isRead, wechat.getIsRead()));
                }
                Predicate[] querys = new Predicate[queryList.size()];
                if (queryList != null && queryList.size() > 0) {
                    for (int i = 0, len = queryList.size(); i < len; i++) {
                        querys[i] = queryList.get(i);
                    }
                }
                query.where(querys).orderBy(new OrderImpl(id, true));
            }
            return null;
        }, pageable);
        if (page == null || page.getContent() == null || page.getContent().size() == 0) {
            return new ArrayList<Wechat>();
        } else {
            return page.getContent();
        }
    }

    @Override
    @TargetDataSource(name = "sapro")
    public void save(List<Wechat> news) {
        wechatRepository.save(news);
    }


}
