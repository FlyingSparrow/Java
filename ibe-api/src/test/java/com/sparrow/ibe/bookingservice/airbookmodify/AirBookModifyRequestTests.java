package com.sparrow.ibe.bookingservice.airbookmodify;

import com.sparrow.BaseTests;
import com.sparrow.ibe.bookingservice.airbookmodify.builder.AirBookModifyRequestBuilder;
import com.sparrow.ibe.bookingservice.airbookmodify.model.AirBookModifyRequest;
import com.sparrow.ibe.bookingservice.airbookmodify.stage.AirBookModifyStage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 接口类别：预订服务
 * 接口名称：自动修改服务
 * 接口ID：JP012
 * 自动修改服务的请求单元测试类，用于测试根据不同的场景构造请求的xml文件
 *
 * @author wangjianchun
 * @create 2018/7/4
 */
public class AirBookModifyRequestTests extends BaseTests {

    @Autowired
    private AirBookModifyRequestBuilder airBookModifyRequestBuilder;
    @Autowired
    private AirBookModifyStage airBookModifyStage01;
    @Autowired
    private AirBookModifyStage airBookModifyStage02;
    @Autowired
    private AirBookModifyStage airBookModifyStage03;
    @Autowired
    private AirBookModifyStage airBookModifyStage04;

    /**
     * 测试场景1：K位信息确认
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook01RequestXml(){
        AirBookModifyRequest request = airBookModifyStage01.buildRequest();
        String requestXml = airBookModifyRequestBuilder.buildRequestXml(request);
        logger.info("测试场景1：K位信息确认, requestXml: {}", requestXml);
    }

    /**
     * 测试场景2：添加 EI
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook02RequestXml(){
        AirBookModifyRequest request = airBookModifyStage02.buildRequest();
        String requestXml = airBookModifyRequestBuilder.buildRequestXml(request);
        logger.info("测试场景2：添加 EI, requestXml: {}", requestXml);
    }

    /**
     * 测试场景3：添加 OSI（其它服务信息）
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook03RequestXml(){
        AirBookModifyRequest request = airBookModifyStage03.buildRequest();
        String requestXml = airBookModifyRequestBuilder.buildRequestXml(request);
        logger.info("测试场景3：添加 OSI（其它服务信息）, requestXml: {}", requestXml);
    }

    /**
     * 测试场景4：添加RMK（备注信息）
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook04RequestXml(){
        AirBookModifyRequest request = airBookModifyStage04.buildRequest();
        String requestXml = airBookModifyRequestBuilder.buildRequestXml(request);
        logger.info("测试场景4：添加RMK（备注信息）, requestXml: {}", requestXml);
    }

}
