package com.sparrow;

import com.sparrow.utils.FileUtils;
import com.sparrow.utils.HttpClientUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangjianchun
 * @create 2018/7/4
 */
public class SimpleTest {

    private static final Logger logger = LoggerFactory.getLogger(SimpleTest.class);

    @Test
    public void testFile() {
        String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><TSK_AirfareFlightShop><TSK_AirfareFlightShopRQ>见到你很高兴</TSK_AirfareFlightShopRQ></TSK_AirfareFlightShop>";
        FileUtils.writeContentToFile("D:/aa.xml", content);
    }

    @Test
    public void testHttp() {
        String url = "http://127.0.0.1:8080/testjobs1";
        String result = HttpClientUtils.getInstance().httpPost(url);
        logger.info("result: {}", result);
    }

}
