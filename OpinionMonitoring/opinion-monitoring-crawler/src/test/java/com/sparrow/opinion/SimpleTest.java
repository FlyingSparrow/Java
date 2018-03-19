package com.sparrow.opinion;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangjianchun
 * @create 2018/3/19
 */
public class SimpleTest {

    private static final Logger logger = LoggerFactory.getLogger(SimpleTest.class);

    @Test
    public void testGetOSName(){
        logger.info("os.name: {}", System.getProperty("os.name"));
    }

}
