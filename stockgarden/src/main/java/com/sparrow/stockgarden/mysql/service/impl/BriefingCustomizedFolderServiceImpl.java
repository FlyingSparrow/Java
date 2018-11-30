package com.sparrow.stockgarden.mysql.service.impl;

import com.sparrow.stockgarden.mysql.repository.BriefingCustomizedFolderRepository;
import com.sparrow.stockgarden.mysql.service.BriefingCustomizedFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhang tong
 * date: 2018/11/06 17:27
 * description:
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class BriefingCustomizedFolderServiceImpl implements BriefingCustomizedFolderService {

    @Autowired
    private BriefingCustomizedFolderRepository briefingCustomizedFolderRepository;
    @PersistenceContext
    private EntityManager em;
}
