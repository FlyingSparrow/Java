package com.sparrow.stockgarden.mysql.repository;

import com.sparrow.stockgarden.mysql.model.BriefingCustomizedFile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhang tong
 * date: 2018/11/06 17:27
 * description:
 */
public interface BriefingCustomizedFileRepository extends CrudRepository<BriefingCustomizedFile,Long> {

    List<BriefingCustomizedFile> findByIdIn(List<Long> ids);

    List<BriefingCustomizedFile> findByUserIdAndFolderId(Long userId, Long folderId);

    List<BriefingCustomizedFile> findAllByCreatorIdOrderByCreatedDateDesc(Long creatorId);

}
