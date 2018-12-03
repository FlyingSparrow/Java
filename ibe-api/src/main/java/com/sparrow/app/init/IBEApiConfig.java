package com.sparrow.app.init;

import com.google.common.collect.Maps;
import com.sparrow.utils.FileUtils;
import com.sparrow.utils.XMLUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.profiles.active}")
    private String springProfilesActive;

    @Override
    public void run(String... strings) throws Exception {
        logger.info("解析 IBE 接口信息开始");

        long startTime = System.currentTimeMillis();

        String fileName = "ibe-api.xml";
        if("develop".equals(springProfilesActive)){
            fileName = "ibe-api-develop.xml";
        }else if("production".equals(springProfilesActive)){
            fileName = "ibe-api-production.xml";
        }

        String xmlContent = FileUtils.readContentFromClassPath(fileName);
        Document document = XMLUtils.getInstance().readXmlFile(xmlContent);
        List<Node> apiList = document.getRootElement().selectNodes("apis/api");
        if(apiList != null){
            apiList.forEach(item ->{
                Element element = (Element) item;
                String type = element.attributeValue("type");
                Boolean enable = Boolean.valueOf(element.attributeValue("enable"));
                String id = element.elementText("id");
                String name = element.elementText("name");
                String url = element.elementText("url");
                String description = element.elementText("description");
                IBEApi ibeApi = new IBEApi(type, enable, id, name, url, description);
                ibeApiMap.put(id, ibeApi);
            });
        }

        logger.info("解析 IBE 接口信息完成，消耗了 {} 毫秒", (System.currentTimeMillis() - startTime));
    }

    public IBEApi getIbeApi(String apiId) {
        return ibeApiMap.get(apiId);
    }
}
