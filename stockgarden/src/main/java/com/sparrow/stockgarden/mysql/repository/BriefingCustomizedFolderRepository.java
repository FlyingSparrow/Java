package com.sparrow.stockgarden.mysql.repository;


import com.sparrow.stockgarden.mysql.model.BriefingCustomizedFolder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhang tong
 * date: 2018/11/06 17:27
 * description:
 */
public interface BriefingCustomizedFolderRepository extends CrudRepository<BriefingCustomizedFolder,Long> {

    List<BriefingCustomizedFolder> findByUserIdAndDeleteStateOrderByFolderType(Long userId, int deleteState);

    BriefingCustomizedFolder findByUserIdAndFolderTypeAndDeleteState(
            Long userId, int folderType, int deleteState);

}
