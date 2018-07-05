package com.sparrow.ibe;

import com.sparrow.BaseTests;
import com.sparrow.app.config.IBEApi;
import com.sparrow.app.config.IBEApiConfig;
import com.sparrow.ibe.enums.IBEInterface;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 接口类别：预订服务
 * 接口名称：自动预订服务
 * 接口ID：JP011
 * 自动预订服务的单元测试类
 *
 * @author wangjianchun
 * @create 2018/7/4
 */
public class IBETests extends BaseTests {

    @Autowired
    private IBEApiConfig ibeApiConfig;

    @Test
    public void testIBEApiConfig(){
        testJP001();
        testJP002();
        testJP003();
        testJP004();
        testJP005();
        testJP006();
        testJP007();
        testJP008();
        testJP009();
        testJP010();
        testJP011();
        testJP012();
        testJP013();
        testJP014();
        testJP015();
        testJP016();
        testJP017();
        testJP018();
        testJP019();
    }

    private void testJP001(){
        IBEApi jp001 = ibeApiConfig.getIbeApi(IBEInterface.JP001.getId());
        Assert.assertEquals(IBEInterface.JP001.getId(), jp001.getId());
        logger.info("jp001: {}", jp001);
    }

    private void testJP002(){
        IBEApi jp002 = ibeApiConfig.getIbeApi(IBEInterface.JP002.getId());
        Assert.assertEquals(IBEInterface.JP002.getId(), jp002.getId());
        logger.info("jp002: {}", jp002);
    }

    private void testJP003(){
        IBEApi jp003 = ibeApiConfig.getIbeApi(IBEInterface.JP003.getId());
        Assert.assertEquals(IBEInterface.JP003.getId(), jp003.getId());
        logger.info("jp003: {}", jp003);
    }

    private void testJP004(){
        IBEApi jp004 = ibeApiConfig.getIbeApi(IBEInterface.JP004.getId());
        Assert.assertEquals(IBEInterface.JP004.getId(), jp004.getId());
        logger.info("jp004: {}", jp004);
    }

    private void testJP005(){
        IBEApi jp005 = ibeApiConfig.getIbeApi(IBEInterface.JP005.getId());
        Assert.assertEquals(IBEInterface.JP005.getId(), jp005.getId());
        logger.info("jp005: {}", jp005);
    }

    private void testJP006(){
        IBEApi jp006 = ibeApiConfig.getIbeApi(IBEInterface.JP006.getId());
        Assert.assertEquals(IBEInterface.JP006.getId(), jp006.getId());
        logger.info("jp006: {}", jp006);
    }

    private void testJP007(){
        IBEApi jp007 = ibeApiConfig.getIbeApi(IBEInterface.JP007.getId());
        Assert.assertEquals(IBEInterface.JP007.getId(), jp007.getId());
        logger.info("jp007: {}", jp007);
    }

    private void testJP008(){
        IBEApi jp008 = ibeApiConfig.getIbeApi(IBEInterface.JP008.getId());
        Assert.assertEquals(IBEInterface.JP008.getId(), jp008.getId());
        logger.info("jp008: {}", jp008);
    }

    private void testJP009(){
        IBEApi jp009 = ibeApiConfig.getIbeApi(IBEInterface.JP009.getId());
        Assert.assertEquals(IBEInterface.JP009.getId(), jp009.getId());
        logger.info("jp009: {}", jp009);
    }

    private void testJP010(){
        IBEApi jp010 = ibeApiConfig.getIbeApi(IBEInterface.JP010.getId());
        Assert.assertEquals(IBEInterface.JP010.getId(), jp010.getId());
        logger.info("jp010: {}", jp010);
    }

    private void testJP011(){
        IBEApi jp011 = ibeApiConfig.getIbeApi(IBEInterface.JP011.getId());
        Assert.assertEquals(IBEInterface.JP011.getId(), jp011.getId());
        logger.info("jp011: {}", jp011);
    }

    private void testJP012(){
        IBEApi jp012 = ibeApiConfig.getIbeApi(IBEInterface.JP012.getId());
        Assert.assertEquals(IBEInterface.JP012.getId(), jp012.getId());
        logger.info("jp012: {}", jp012);
    }

    private void testJP013(){
        IBEApi jp013 = ibeApiConfig.getIbeApi(IBEInterface.JP013.getId());
        Assert.assertEquals(IBEInterface.JP013.getId(), jp013.getId());
        logger.info("jp013: {}", jp013);
    }

    private void testJP014(){
        IBEApi jp014 = ibeApiConfig.getIbeApi(IBEInterface.JP014.getId());
        Assert.assertEquals(IBEInterface.JP014.getId(), jp014.getId());
        logger.info("jp014: {}", jp014);
    }

    private void testJP015(){
        IBEApi jp015 = ibeApiConfig.getIbeApi(IBEInterface.JP015.getId());
        Assert.assertEquals(IBEInterface.JP015.getId(), jp015.getId());
        logger.info("jp015: {}", jp015);
    }

    private void testJP016(){
        IBEApi jp016 = ibeApiConfig.getIbeApi(IBEInterface.JP016.getId());
        Assert.assertEquals(IBEInterface.JP016.getId(), jp016.getId());
        logger.info("jp016: {}", jp016);
    }

    private void testJP017(){
        IBEApi jp017 = ibeApiConfig.getIbeApi(IBEInterface.JP017.getId());
        Assert.assertEquals(IBEInterface.JP017.getId(), jp017.getId());
        logger.info("jp017: {}", jp017);
    }

    private void testJP018(){
        IBEApi jp018 = ibeApiConfig.getIbeApi(IBEInterface.JP018.getId());
        Assert.assertEquals(IBEInterface.JP018.getId(), jp018.getId());
        logger.info("jp018: {}", jp018);
    }

    private void testJP019(){
        IBEApi jp019 = ibeApiConfig.getIbeApi(IBEInterface.JP019.getId());
        Assert.assertEquals(IBEInterface.JP019.getId(), jp019.getId());
        logger.info("jp019: {}", jp019);
    }

}
