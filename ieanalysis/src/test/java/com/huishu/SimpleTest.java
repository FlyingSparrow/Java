package com.huishu;

import com.huishu.ieanalysis.utils.DateUtils;
import com.huishu.ieanalysis.utils.FileUtils;
import org.junit.Test;

/**
 * @author wangjianchun
 * @create 2018/5/24
 */
public class SimpleTest {

    @Test
    public void testDate(){
        System.out.println(DateUtils.getCurrentYear());
    }

    @Test
    public void testFile(){
        //String path = "D:/work/pj_guoxinyouyi_suanchuang/Code/ieanalysis-data/src/main/resources/temp.properties";
		/*// writeProperties(path,"lisi","王五");
		Map<String, String> indexMap = new HashMap<String, String>();
		indexMap.put("news", "1");
		indexMap.put("forum", "1");
		indexMap.put("recriutment", "2");
		indexMap.put("wechat", "1");
		indexMap.put("investment", "1");
		writeProperties(path, indexMap);*/
        //readProperties(path);
        ///writeContentToFile("D:/test.txt", "做个测试aaaa");
        System.out.println(FileUtils.readFile("D:/test.txt"));
        System.out.println(FileUtils.copeFile("D:/test.txt", "D:/test123.txt"));
    }

}
