package com.sparrow.stockgarden.mysql.service.impl;

import com.sparrow.stockgarden.mysql.model.BriefingCustomizedFile;
import com.sparrow.stockgarden.mysql.repository.BriefingCustomizedFileRepository;
import com.sparrow.stockgarden.mysql.service.BriefingCustomizedFileService;
import com.sparrow.stockgarden.mysql.service.BriefingCustomizedFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhang tong
 * date: 2018/11/06 17:27
 * description:
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class BriefingCustomizedFileServiceImpl implements BriefingCustomizedFileService {

    @Autowired
    private BriefingCustomizedFileRepository briefingCustomizedFileRepository;
    @Autowired
    private BriefingCustomizedFolderService briefingCustomizedFolderService;
    @PersistenceContext
    private EntityManager em;

    @Override
    public BriefingCustomizedFile findById(Long id) {
        return briefingCustomizedFileRepository.findById(id).get();
    }

    @Override
    public List<BriefingCustomizedFile> findAllByCreatorIdOrderByCreatedDateDesc(Long creatorId) {
        return briefingCustomizedFileRepository.findAllByCreatorIdOrderByCreatedDateDesc(creatorId);
    }
}
