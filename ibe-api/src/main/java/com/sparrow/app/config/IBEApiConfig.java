package com.sparrow.app.config;

import com.google.common.collect.Maps;
import com.sparrow.utils.FileUtils;
import com.sparrow.utils.XMLUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 接口url配置，存放所有被封装的接口的URL
 *
 * @author wangjianchun
 * @create 2018/7/4
 */
@Component
public class IBEApiConfig implements CommandLineRunner {

    private final Map<String, IBEApi> ibeApiMap = Maps.newHashMapWithExpectedSize(24);

    @Override
    public void run(String... strings) throws Exception {
        String xmlContent = FileUtils.readContentFromClassPath("ibe-api.xml");
        Document document = XMLUtils.getInstance().readXMLFile(xmlContent);
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
    }

    public IBEApi getIbeApi(String apiId) {
        return ibeApiMap.get(apiId);
    }
}
