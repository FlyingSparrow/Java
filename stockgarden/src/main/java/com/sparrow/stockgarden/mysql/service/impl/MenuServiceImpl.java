package com.sparrow.stockgarden.mysql.service.impl;

import com.sparrow.stockgarden.mysql.repository.MenuRepository;
import com.sparrow.stockgarden.mysql.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Title: MenuServiceImpl</p>
 * <p>Description: </p>
 *
 * @author wjc
 * @date 2018/12/5
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;
}
