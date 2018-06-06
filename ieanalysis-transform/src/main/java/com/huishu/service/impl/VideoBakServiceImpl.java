package com.huishu.service.impl;

import com.huishu.entity.VideoBak;
import com.huishu.repository.VideoBakRepository;
import com.huishu.service.VideoBakService;
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
public class VideoBakServiceImpl implements VideoBakService {

    @Autowired
    private VideoBakRepository videoBakRepository;

    @Override
    public List<VideoBak> findOneHundred(VideoBak data, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 100);
        }
        Page<VideoBak> page = videoBakRepository.findAll((root, query, cb) -> {
            Path<Long> id = root.get("id");
            Path<String> biaoShi = root.get("biaoShi");
            if (data != null) {
                List<Predicate> queryList = new ArrayList<Predicate>();
                if (data.getId() != null) {
                    queryList.add(cb.greaterThan(id, data.getId()));
                }
                if (data.getBiaoShi() != null) {
                    queryList.add(cb.equal(biaoShi, data.getBiaoShi()));
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
            return new ArrayList<VideoBak>();
        } else {
            return page.getContent();
        }
    }

    @Override
    public void save(List<VideoBak> news) {
        videoBakRepository.save(news);
    }

    @Override
    public void delete(List<VideoBak> news) {
        videoBakRepository.delete(news);
    }

    @Override
    public long findExist(VideoBak bak) {
        return videoBakRepository.countByUrlAndFabushijian(bak.getUrl(), bak.getFabushijian());
    }


}
