package com.sparrow.ibe.bookingservice.airbook;

import com.sparrow.BaseTests;
import com.sparrow.ibe.bookingservice.airbook.model.AirBookRequest;
import com.sparrow.ibe.bookingservice.airbook.stage.AirBookStage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 接口类别：预订服务
 * 接口名称：自动预订服务
 * 接口ID：JP011
 * 自动预订服务的单元测试类
 *
 * @author wangjianchun
 * @create 2018/7/4
 */
public class AirBookTests extends BaseTests {

    @Autowired
    private com.sparrow.ibe.bookingservice.airbook.builder.AirBookRequestBuilder airBookRequestBuilder;

    @Autowired
    @Qualifier("airBookStage01")
    private AirBookStage airBookStage01;

    @Autowired
    @Qualifier("airBookStage02")
    private AirBookStage airBookStage02;

    @Autowired
    @Qualifier("airBookStage03")
    private AirBookStage airBookStage03;

    @Autowired
    @Qualifier("airBookStage04")
    private AirBookStage airBookStage04;

    @Autowired
    @Qualifier("airBookStage05")
    private AirBookStage airBookStage05;

    @Autowired
    @Qualifier("airBookStage06")
    private AirBookStage airBookStage06;

    /**
     * 测试场景1：国内航班+单个成人+单程
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook01RequestXml(){
        AirBookRequest request = airBookStage01.buildRequest();
        String requestXml = airBookRequestBuilder.buildRequestXml(request);
        logger.info("测试场景1：国内航班+单个成人+单程, requestXml: {}", requestXml);
    }

    /**
     * 测试场景2：国内航班+单个成人+单程+OSI
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook02RequestXml(){
        AirBookRequest request = airBookStage02.buildRequest();
        String requestXml = airBookRequestBuilder.buildRequestXml(request);
        logger.info("测试场景2：国内航班+单个成人+单程+OSI, requestXml: {}", requestXml);
    }

    /**
     * 测试场景3：国内航班+单个成人+单程+OSI+RMK
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook03RequestXml(){
        AirBookRequest request = airBookStage03.buildRequest();
        String requestXml = airBookRequestBuilder.buildRequestXml(request);
        logger.info("测试场景3：国内航班+单个成人+单程+OSI+RMK, requestXml: {}", requestXml);
    }

    /**
     * 测试场景4：国内航班+单个成人+单程+OSI+SSR
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook04RequestXml(){
        AirBookRequest request = airBookStage04.buildRequest();
        String requestXml = airBookRequestBuilder.buildRequestXml(request);
        logger.info("测试场景4：国内航班+单个成人+单程+OSI+SSR, requestXml: {}", requestXml);
    }

    /**
     * 测试场景5：国内航班+单个成人+单程+婴儿
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook05RequestXml(){
        AirBookRequest request = airBookStage05.buildRequest();
        String requestXml = airBookRequestBuilder.buildRequestXml(request);
        logger.info("测试场景5：国内航班+单个成人+单程+婴儿, requestXml: {}", requestXml);
    }

    /**
     * 测试场景6：国内航班+单个成人+单程+儿童
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook06RequestXml(){
        AirBookRequest request = airBookStage06.buildRequest();
        String requestXml = airBookRequestBuilder.buildRequestXml(request);
        logger.info("测试场景6：国内航班+单个成人+单程+儿童, requestXml: {}", requestXml);
    }

}
