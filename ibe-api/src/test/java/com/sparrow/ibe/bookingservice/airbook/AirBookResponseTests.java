package com.sparrow.ibe.bookingservice.airbook;

import com.alibaba.fastjson.JSON;
import com.sparrow.BaseTests;
import com.sparrow.ibe.bookingservice.airbook.transformer.AirBookResponseTransformer;
import com.sparrow.ibe.bookingservice.airbook.vo.AirBookResponseVO;
import com.sparrow.utils.FileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 接口类别：预订服务
 * 接口名称：自动预订服务
 * 接口ID：JP011
 * 自动预订服务的响应单元测试类，用于测试解析不同的场景返回的xml文件
 *
 * @author wangjianchun
 * @create 2018/7/4
 */
public class AirBookResponseTests extends BaseTests {

    @Autowired
    private AirBookResponseTransformer airBookResponseTransformer;

    /**
     * 测试场景1：国内航班+单个成人+单程
     * 说明：测试解析响应结果文件
     */
    @Test
    public void testParseAirBook01ResponseXml() {
        String filePath = "ibe-files/JP011/经过测试的请求和响应文件/JP011_自动预定服务_RS_20140826190852.xml";
        String content = FileUtils.readContentFromClassPath(filePath);
        AirBookResponseVO airBookResponseVO = airBookResponseTransformer.transform(content);

        logger.info("测试场景1：国内航班+单个成人+单程, airBookResponseVO: {}", JSON.toJSONString(airBookResponseVO));
    }

    /**
     * 测试场景2：国际航班+一个成人+儿童+多个航段+往返
     * 说明：测试解析响应结果文件
     */
    @Test
    public void testParseAirBook02ResponseXml() {
        String filePath = "ibe-files/JP011/经过测试的请求和响应文件/JP011_自动预订服务_RS_20150115120508.xml";
        String content = FileUtils.readContentFromClassPath(filePath);
        AirBookResponseVO airBookResponseVO = airBookResponseTransformer.transform(content);

        logger.info("测试场景2：国际航班+一个成人+儿童+多个航段+往返, airBookResponseVO: {}", JSON.toJSONString(airBookResponseVO));
    }

}
