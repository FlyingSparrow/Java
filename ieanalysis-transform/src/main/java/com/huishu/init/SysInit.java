package com.huishu.init;

import com.huishu.constants.SysConst;
import com.huishu.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 系统初始化类，用于初始化系统数据
 *
 * @author wangjianchun
 * @create 2018/6/3
 */
public class SysInit {

    private static final Logger logger = LoggerFactory.getLogger(SysInit.class);

    private static final Map<String, String> INDEX_MAP = new HashMap<String, String>(16);

    static {
        try {
            String filePath = System.getProperty("user.dir") + "/analysis-data-temp.properties";
            File file = new File(filePath);
            if (!file.exists()) {
                FileUtils.createFileIfNotExists(file);

                String defaultValue = "0";
                INDEX_MAP.put(SysConst.WECHAT, defaultValue);
                INDEX_MAP.put(SysConst.VIDEO, defaultValue);
                INDEX_MAP.put(SysConst.NEWS, defaultValue);
                INDEX_MAP.put(SysConst.POLICY, defaultValue);
                INDEX_MAP.put(SysConst.ZONGHE, defaultValue);
                INDEX_MAP.put(SysConst.FORUM, defaultValue);
                INDEX_MAP.put(SysConst.RECRIUTMENT, defaultValue);
                INDEX_MAP.put(SysConst.INVESTMENT, defaultValue);
                INDEX_MAP.put(SysConst.MERGER, defaultValue);
                INDEX_MAP.put(SysConst.QUIT, defaultValue);
                INDEX_MAP.put(SysConst.INDUSTRY, defaultValue);
                FileUtils.writeProperties(filePath, INDEX_MAP);
            } else {
                Properties properties = FileUtils.getPropertiesByFilePath(filePath);

                INDEX_MAP.put(SysConst.WECHAT, properties.getProperty(SysConst.WECHAT));
                INDEX_MAP.put(SysConst.RECRIUTMENT, properties.getProperty(SysConst.RECRIUTMENT));
                INDEX_MAP.put(SysConst.VIDEO, properties.getProperty(SysConst.VIDEO));
                INDEX_MAP.put(SysConst.NEWS, properties.getProperty(SysConst.NEWS));
                INDEX_MAP.put(SysConst.FORUM, properties.getProperty(SysConst.FORUM));
                INDEX_MAP.put(SysConst.POLICY, properties.getProperty(SysConst.POLICY));
                INDEX_MAP.put(SysConst.ZONGHE, properties.getProperty(SysConst.ZONGHE));
                INDEX_MAP.put(SysConst.INVESTMENT, properties.getProperty(SysConst.INVESTMENT));
                INDEX_MAP.put(SysConst.MERGER, properties.getProperty(SysConst.MERGER));
                INDEX_MAP.put(SysConst.QUIT, properties.getProperty(SysConst.QUIT));
                INDEX_MAP.put(SysConst.INDUSTRY, properties.getProperty(SysConst.INDUSTRY));
            }
        } catch (Exception e) {
            logger.error("配置信息加载出错", e);
        }
    }

    public static Map<String, String> getIndexMap() {
        return Collections.unmodifiableMap(INDEX_MAP);
    }

}
