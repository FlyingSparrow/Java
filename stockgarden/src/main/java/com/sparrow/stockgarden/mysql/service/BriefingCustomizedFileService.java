package com.sparrow.stockgarden.mysql.service;

import com.sparrow.stockgarden.mysql.model.BriefingCustomizedFile;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhang tong
 * date: 2018/11/06 17:27
 * description:
 */
public interface BriefingCustomizedFileService {

    BriefingCustomizedFile findById(Long id);

    List<BriefingCustomizedFile> findAllByCreatorIdOrderByCreatedDateDesc(Long creatorId);

}
