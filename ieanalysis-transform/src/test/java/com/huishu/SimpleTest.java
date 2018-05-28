package com.huishu;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;

/**
 * @author wangjianchun
 * @create 2018/5/28
 */
public class SimpleTest {

    @Test
    public void testArray(){
        String[] areas = "北京,上海,广州,深圳".split(",");
        String title = "北京出新政！平房住宅不得拆分炒卖";
        String content = "从去年开始，经常传出来北京二手房交易中出现的个别住宅拆分炒卖现象。知事打探到，针对于此，市住建委会同市规划国土委叫停了已取得不动产所有权的住宅房产拆分测绘成果审核和不动产权利拆分登记，坚决遏制炒房行为。";
        for (String area : areas) {
            if (StringUtils.isNotEmpty(title) && title.indexOf(area) >= 0) {
                List<String> city = Lists.newArrayList();
                city.add(area);
                if (city != null && city.size() > 0) {
//                    siteLib.setProvince(city.get(0).getProvince());
                    System.out.println("==");
                }
                break;
            }
            if (StringUtils.isNotEmpty(content) && content.indexOf(area) >= 0) {
                List<String> city = Lists.newArrayList();
                if (city != null && city.size() > 0) {
                    System.out.println("====");
                }
                break;
            }
        }
    }

}
