package com.huishu.init;

import com.huishu.constants.SysConst;
import com.huishu.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 系统初始化类，用于初始化系统数据
 *
 * @author wangjianchun
 * @create 2018/6/3
 */
@Component
public class SysInit implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SysInit.class);

    private final Map<String, String> indexMap = new HashMap<String, String>(16);

    @Override
    public void run(String... strings) throws Exception {
        try {
            long startTime = System.currentTimeMillis();

            logger.info("系统启动执行，执行加载数据和初始化配置等操作开始");

            String filePath = System.getProperty("user.dir") + "/analysis-data-temp.properties";
            File file = new File(filePath);
            if (!file.exists()) {
                FileUtils.createFileIfNotExists(file);

                String defaultValue = "0";
                indexMap.put(SysConst.WECHAT, defaultValue);
                indexMap.put(SysConst.VIDEO, defaultValue);
                indexMap.put(SysConst.NEWS, defaultValue);
                indexMap.put(SysConst.POLICY, defaultValue);
                indexMap.put(SysConst.ZONGHE, defaultValue);
                indexMap.put(SysConst.FORUM, defaultValue);
                indexMap.put(SysConst.RECRIUTMENT, defaultValue);
                indexMap.put(SysConst.INVESTMENT, defaultValue);
                indexMap.put(SysConst.MERGER, defaultValue);
                indexMap.put(SysConst.QUIT, defaultValue);
                indexMap.put(SysConst.INDUSTRY, defaultValue);
                FileUtils.writeProperties(filePath, indexMap);
            } else {
                Properties properties = FileUtils.getPropertiesByFilePath(filePath);

                indexMap.put(SysConst.WECHAT, properties.getProperty(SysConst.WECHAT));
                indexMap.put(SysConst.RECRIUTMENT, properties.getProperty(SysConst.RECRIUTMENT));
                indexMap.put(SysConst.VIDEO, properties.getProperty(SysConst.VIDEO));
                indexMap.put(SysConst.NEWS, properties.getProperty(SysConst.NEWS));
                indexMap.put(SysConst.FORUM, properties.getProperty(SysConst.FORUM));
                indexMap.put(SysConst.POLICY, properties.getProperty(SysConst.POLICY));
                indexMap.put(SysConst.ZONGHE, properties.getProperty(SysConst.ZONGHE));
                indexMap.put(SysConst.INVESTMENT, properties.getProperty(SysConst.INVESTMENT));
                indexMap.put(SysConst.MERGER, properties.getProperty(SysConst.MERGER));
                indexMap.put(SysConst.QUIT, properties.getProperty(SysConst.QUIT));
                indexMap.put(SysConst.INDUSTRY, properties.getProperty(SysConst.INDUSTRY));
            }

            logger.info("系统启动执行，执行加载数据和初始化配置等操作结束，耗时 {} 毫秒", (System.currentTimeMillis() - startTime));
        } catch (Exception e) {
            logger.error("配置信息加载出错", e);
        }
    }

    public Map<String, String> getIndexMap() {
        return indexMap;
    }

}
