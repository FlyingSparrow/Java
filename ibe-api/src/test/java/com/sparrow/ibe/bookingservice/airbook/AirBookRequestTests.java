package com.sparrow.ibe.bookingservice.airbook;

import com.sparrow.BaseTests;
import com.sparrow.ibe.bookingservice.airbook.model.AirBookRequest;
import com.sparrow.ibe.bookingservice.airbook.stage.AirBookStage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 接口类别：预订服务
 * 接口名称：自动预订服务
 * 接口ID：JP011
 * 自动预订服务的请求单元测试类，用于测试根据不同的场景构造请求的xml文件
 *
 * @author wangjianchun
 * @create 2018/7/4
 */
public class AirBookRequestTests extends BaseTests {

    @Autowired
    private com.sparrow.ibe.bookingservice.airbook.builder.AirBookRequestBuilder airBookRequestBuilder;
    @Autowired
    private AirBookStage airBookStage01;
    @Autowired
    private AirBookStage airBookStage02;
    @Autowired
    private AirBookStage airBookStage03;
    @Autowired
    private AirBookStage airBookStage04;
    @Autowired
    private AirBookStage airBookStage05;
    @Autowired
    private AirBookStage airBookStage06;
    @Autowired
    private AirBookStage airBookStage07;
    @Autowired
    private AirBookStage airBookStage08;
    @Autowired
    private AirBookStage airBookStage09;
    @Autowired
    private AirBookStage airBookStage10;
    @Autowired
    private AirBookStage airBookStage11;
    @Autowired
    private AirBookStage airBookStage12;
    @Autowired
    private AirBookStage airBookStage13;
    @Autowired
    private AirBookStage airBookStage14;
    @Autowired
    private AirBookStage airBookStage15;
    @Autowired
    private AirBookStage airBookStage16;
    @Autowired
    private AirBookStage airBookStage17;
    @Autowired
    private AirBookStage airBookStage18;
    @Autowired
    private AirBookStage airBookStage19;
    @Autowired
    private AirBookStage airBookStage20;
    @Autowired
    private AirBookStage airBookStage21;

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

    /**
     * 测试场景7：国内航班+单个成人+单程+2儿童
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook07RequestXml(){
        AirBookRequest request = airBookStage07.buildRequest();
        String requestXml = airBookRequestBuilder.buildRequestXml(request);
        logger.info("测试场景7：国内航班+单个成人+单程+2儿童, requestXml: {}", requestXml);
    }

    /**
     * 测试场景8：国内航班+单个成人+单程+1儿童+1婴儿
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook08RequestXml(){
        AirBookRequest request = airBookStage08.buildRequest();
        String requestXml = airBookRequestBuilder.buildRequestXml(request);
        logger.info("测试场景8：国内航班+单个成人+单程+1儿童+1婴儿, requestXml: {}", requestXml);
    }

    /**
     * 测试场景9：国内航班+多个成人+单程+1儿童
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook09RequestXml(){
        AirBookRequest request = airBookStage09.buildRequest();
        String requestXml = airBookRequestBuilder.buildRequestXml(request);
        logger.info("测试场景9：国内航班+多个成人+单程+1儿童, requestXml: {}", requestXml);
    }

    /**
     * 测试场景10：国内航班+儿童+单程+SSR
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook10RequestXml(){
        AirBookRequest request = airBookStage10.buildRequest();
        String requestXml = airBookRequestBuilder.buildRequestXml(request);
        logger.info("测试场景10：国内航班+儿童+单程+SSR, requestXml: {}", requestXml);
    }

    /**
     * 测试场景11：国内航班+单个成人+往返
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook11RequestXml(){
        AirBookRequest request = airBookStage11.buildRequest();
        String requestXml = airBookRequestBuilder.buildRequestXml(request);
        logger.info("测试场景11：国内航班+单个成人+往返, requestXml: {}", requestXml);
    }

    /**
     * 测试场景12：国内航班+单个成人+儿童+往返
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook12RequestXml(){
        AirBookRequest request = airBookStage12.buildRequest();
        String requestXml = airBookRequestBuilder.buildRequestXml(request);
        logger.info("测试场景12：国内航班+单个成人+儿童+往返, requestXml: {}", requestXml);
    }

    /**
     * 测试场景13：国际航班+单个成人+单程
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook13RequestXml(){
        AirBookRequest request = airBookStage13.buildRequest();
        String requestXml = airBookRequestBuilder.buildRequestXml(request);
        logger.info("测试场景13：国际航班+单个成人+单程, requestXml: {}", requestXml);
    }

    /**
     * 测试场景14：国际航班+单个成人+单程+儿童
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook14RequestXml(){
        AirBookRequest request = airBookStage14.buildRequest();
        String requestXml = airBookRequestBuilder.buildRequestXml(request);
        logger.info("测试场景14：国际航班+单个成人+单程+儿童, requestXml: {}", requestXml);
    }

    /**
     * 测试场景15：国际航班+单个成人+单程+婴儿
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook15RequestXml(){
        AirBookRequest request = airBookStage15.buildRequest();
        String requestXml = airBookRequestBuilder.buildRequestXml(request);
        logger.info("测试场景15：国际航班+单个成人+单程+婴儿, requestXml: {}", requestXml);
    }

    /**
     * 测试场景16：国内航班+单个成人+单程+SSR
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook16RequestXml(){
        AirBookRequest request = airBookStage16.buildRequest();
        String requestXml = airBookRequestBuilder.buildRequestXml(request);
        logger.info("测试场景16：国内航班+单个成人+单程+SSR, requestXml: {}", requestXml);
    }

    /**
     * 测试场景17：国内航班+单个成人+单程+RMK
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook17RequestXml(){
        AirBookRequest request = airBookStage17.buildRequest();
        String requestXml = airBookRequestBuilder.buildRequestXml(request);
        logger.info("测试场景17：国内航班+单个成人+单程+RMK, requestXml: {}", requestXml);
    }

    /**
     * 测试场景18：国内航班+单个成人+自动添加地面航段
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook18RequestXml(){
        AirBookRequest request = airBookStage18.buildRequest();
        String requestXml = airBookRequestBuilder.buildRequestXml(request);
        logger.info("测试场景18：国内航班+单个成人+自动添加地面航段, requestXml: {}", requestXml);
    }

    /**
     * 测试场景19：国内航班+一个成人+单程+子舱位预定
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook19RequestXml(){
        AirBookRequest request = airBookStage19.buildRequest();
        String requestXml = airBookRequestBuilder.buildRequestXml(request);
        logger.info("测试场景19：国内航班+一个成人+单程+子舱位预定, requestXml: {}", requestXml);
    }

    /**
     * 测试场景20：国内航班+一个成人+婴儿+往返
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook20RequestXml(){
        AirBookRequest request = airBookStage20.buildRequest();
        String requestXml = airBookRequestBuilder.buildRequestXml(request);
        logger.info("测试场景20：国内航班+一个成人+婴儿+往返, requestXml: {}", requestXml);
    }

    /**
     * 测试场景21：国内航班+一个成人+单程+不验证航段状态
     * 说明：测试构造请求文件
     */
    @Test
    public void testBuildAirBook21RequestXml(){
        AirBookRequest request = airBookStage21.buildRequest();
        String requestXml = airBookRequestBuilder.buildRequestXml(request);
        logger.info("测试场景21：国内航班+一个成人+单程+不验证航段状态, requestXml: {}", requestXml);
    }

}
