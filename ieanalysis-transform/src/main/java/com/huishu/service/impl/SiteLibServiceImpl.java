package com.huishu.service.impl;

import com.huishu.entity.SiteLib;
import com.huishu.repository.SiteLibRepository;
import com.huishu.service.SiteLibService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author wangjianchun
 */
@Service
public class SiteLibServiceImpl implements SiteLibService {

    @Autowired
    private SiteLibRepository siteLibRepository;

    @PersistenceContext
    private EntityManager em;

    public SiteLib search(SiteLib site) {
        String dataSql = "select * from site_lib t where 1 = 1";
        if (null != site) {
            if (StringUtils.isNotEmpty(site.getName())) {
                dataSql += " and t.name = ?1";
            }
        }
        Query dataQuery = em.createQuery(dataSql);
        if (null != site) {
            if (StringUtils.isNotEmpty(site.getName())) {
                dataQuery.setParameter(1, site.getName());
            }
        }
        List<SiteLib> data = dataQuery.getResultList();
        if (data != null && data.size() > 0) {
            return data.get(0);
        }
        return null;
    }

    @Override
    public SiteLib findByName(String name) {
        List<SiteLib> list = siteLibRepository.findByName(name);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}
