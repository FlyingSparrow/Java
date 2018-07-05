package com.sparrow;

import com.sparrow.utils.FileUtils;
import org.junit.Test;

/**
 * @author wangjianchun
 * @create 2018/7/4
 */
public class SimpleTest {

    @Test
    public void testFile(){
        String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><TSK_AirfareFlightShop><TSK_AirfareFlightShopRQ>见到你很高兴</TSK_AirfareFlightShopRQ></TSK_AirfareFlightShop>";
        FileUtils.writeContentToFile("D:/aa.xml", content);
    }

}
