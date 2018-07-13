package com.sparrow.app.init;

import com.google.common.collect.Maps;
import com.sparrow.utils.FileUtils;
import com.sparrow.utils.XMLUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * IBE 接口配置类，读取 ibe-api.xml 文件，解析 IBE 接口信息
 *
 * @author wangjianchun
 * @create 2018/7/4
 */
@Component
public class IBEApiConfig implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(IBEApiConfig.class);

    private final Map<String, IBEApi> ibeApiMap = Maps.newHashMapWithExpectedSize(24);

    @Override
    public void run(String... strings) throws Exception {
        logger.info("解析 IBE 接口信息开始");

        long startTime = System.currentTimeMillis();

        String xmlContent = FileUtils.readContentFromClassPath("ibe-api.xml");
        Document document = XMLUtils.getInstance().readXmlFile(xmlContent);
        List<Element> apiList = document.getRootElement().selectNodes("apis/api");
        if(apiList != null){
            for(Element item : apiList){
                String type = item.attributeValue("type");
                Boolean enable = Boolean.valueOf(item.attributeValue("enable"));
                String id = item.elementText("id");
                String name = item.elementText("name");
                String url = item.elementText("url");
                String description = item.elementText("description");
                IBEApi ibeApi = new IBEApi(type, enable, id, name, url, description);
                ibeApiMap.put(id, ibeApi);
            }
        }

        logger.info("解析 IBE 接口信息完成，消耗了 {} 毫秒", (System.currentTimeMillis() - startTime));
    }

    public IBEApi getIbeApi(String apiId) {
        return ibeApiMap.get(apiId);
    }
}
