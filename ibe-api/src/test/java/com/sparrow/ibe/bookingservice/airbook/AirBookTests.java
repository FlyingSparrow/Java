package com.sparrow.ibe.bookingservice.airbook;

import com.sparrow.BaseTests;
import com.sparrow.app.config.IBEConfig;
import com.sparrow.ibe.bookingservice.airbook.dao.AirBookDao;
import com.sparrow.ibe.bookingservice.airbook.model.AirBookRequest;
import com.sparrow.ibe.bookingservice.airbook.model.AirBookResponse;
import com.sparrow.ibe.bookingservice.airbook.model.AirReservation;
import com.sparrow.ibe.model.DefaultError;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    private IBEConfig ibeConfig;
    @Autowired
    private com.sparrow.ibe.bookingservice.airbook.builder.AirBookRequestBuilder airBookRequestBuilder;

    /**
     * 测试场景：国内航班+单个成人+单程
     */
    @Test
    public void testAirBook01() {
        int type = 1;
        AirBookRequest request = new AirBookRequestBuilderV2().buildRequest(type);
        AirBookDao dao = new AirBookDao();
        AirBookResponse response = (AirBookResponse) dao.execute(ibeConfig.getUsername(), ibeConfig.getPassword(), request);

        Assert.assertNotNull(response);

        logger.info("response.getAirReservationList().size(): {}", response.getAirReservationList().size());

        List<AirReservation> airReservationList = response.getAirReservationList();
        for (AirReservation ar : airReservationList) {
            logger.info("CommentList:" + ar.getCommentList());
        }
        List<DefaultError> errorList = response.getErrorList();
        logger.info("errorList.size(): {}", errorList.size());
        for (DefaultError error : errorList) {
            logger.info("errorCode: {}", error.getCode());
            logger.info("error.getCnMessage(): {}", error.getCnMessage());
        }
    }

    /**
     * 测试场景1：国内航班+单个成人+单程
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook01RequestXml(){
        int type = 1;
        AirBookRequest request = new AirBookRequestBuilderV2().buildRequest(type);
        String requestXml = airBookRequestBuilder.buildRequestXML(request);
        logger.info("requestXml: {}", requestXml);
    }

    /**
     * 测试场景2：国内航班+单个成人+单程+OSI
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook02RequestXml(){
        int type = 2;
        AirBookRequest request = new AirBookRequestBuilderV2().buildRequest(type);
        String requestXml = airBookRequestBuilder.buildRequestXML(request);
        logger.info("requestXml: {}", requestXml);
    }

    /**
     * 测试场景3：国内航班+单个成人+单程+OSI+RMK
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook03RequestXml(){
        int type = 3;
        AirBookRequest request = new AirBookRequestBuilderV2().buildRequest(type);
        String requestXml = airBookRequestBuilder.buildRequestXML(request);
        logger.info("requestXml: {}", requestXml);
    }

    /**
     * 测试场景4：国内航班+单个成人+单程+OSI+SSR
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook04RequestXml(){
        int type = 4;
        AirBookRequest request = new AirBookRequestBuilderV2().buildRequest(type);
        String requestXml = airBookRequestBuilder.buildRequestXML(request);
        logger.info("requestXml: {}", requestXml);
    }

    /**
     * 测试场景5：国内航班+单个成人+单程+婴儿
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook05RequestXml(){
        int type = 5;
        AirBookRequest request = new AirBookRequestBuilderV2().buildRequest(type);
        String requestXml = airBookRequestBuilder.buildRequestXML(request);
        logger.info("requestXml: {}", requestXml);
    }

}
