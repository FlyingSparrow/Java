package com.huishu.ieanalysis.mysql.resposity;

import com.huishu.ieanalysis.mysql.entity.KingBaseDgap;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 */
public interface KingBaseDgapRepository extends JpaRepository<KingBaseDgap, String> {

    int countByEnterpriseName(String enterpriseName);

}
