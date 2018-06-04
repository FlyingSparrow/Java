package com.huishu;

import com.huishu.constants.SysConst;
import com.huishu.utils.DateUtils;
import com.huishu.utils.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author wangjianchun
 * @create 2018/5/28
 */
public class SimpleTest {

    @Test
    public void testArray(){
        String[] areas = "北京,上海,广州,深圳".split(SysConst.COMMA);
        String title = "北京出新政！平房住宅不得拆分炒卖";
        String content = "从去年开始，经常传出来北京二手房交易中出现的个别住宅拆分炒卖现象。知事打探到，针对于此，市住建委会同市规划国土委叫停了已取得不动产所有权的住宅房产拆分测绘成果审核和不动产权利拆分登记，坚决遏制炒房行为。";
        for (String area : areas) {
            if (StringUtils.isNotEmpty(title) && title.indexOf(area) >= 0) {
                List<String> cityList = Lists.newArrayList();
                cityList.add(area);
                if (cityList.size() > 0) {
//                    siteLib.setProvince(city.get(0).getProvince());
                    System.out.println("==");
                }
                break;
            }
            if (StringUtils.isNotEmpty(content) && content.indexOf(area) >= 0) {
                List<String> cityList = Lists.newArrayList();
                if (cityList.size() > 0) {
                    System.out.println("====");
                }
                break;
            }
        }
    }

    @Test
    public void testDate(){
        String str = "9小时前";
        String str2 = "16小时前";
        String aaResult = parseDate(str);
        String aaResult2 = parseDate2(str);
        Assert.assertEquals(aaResult, aaResult2);

        String aaResult3 = parseDate(str2);
        String aaResult4 = parseDate2(str2);
        Assert.assertEquals(aaResult3, aaResult4);

        int currentMonth = DateUtils.getCurrentMonth();
        Assert.assertEquals(6, currentMonth);
    }

    @Test
    public void testStringIsNumber() {
        String str1 = "1122.2.2";
        String str2 = "111";
        String str3 = "111.2";
        String str4 = "111s";
        String str5 = "111.s";
        String str6 = "1s11";
        String str7 = "- -";
        String str8 = "-313465";
        String str9 = "+2424234";

        System.out.println(str1 + ":" + StringUtils.isNumber(str1));
        System.out.println(str2 + ":" + StringUtils.isNumber(str2));
        System.out.println(str3 + ":" + StringUtils.isNumber(str3));
        System.out.println(str4 + ":" + StringUtils.isNumber(str4));
        System.out.println(str5 + ":" + StringUtils.isNumber(str5));
        System.out.println(str6 + ":" + StringUtils.isNumber(str6));
        System.out.println(str7 + ":" + StringUtils.isNumber(str7));
        System.out.println(str7 + ":" + StringUtils.isNumber(str8));
        System.out.println(str7 + ":" + StringUtils.isNumber(str9));

        Assert.assertFalse(StringUtils.isNumber(str1));
        Assert.assertTrue(StringUtils.isNumber(str2));
        Assert.assertTrue(StringUtils.isNumber(str3));
        Assert.assertFalse(StringUtils.isNumber(str4));
        Assert.assertFalse(StringUtils.isNumber(str5));
        Assert.assertFalse(StringUtils.isNumber(str6));
        Assert.assertFalse(StringUtils.isNumber(str7));
        Assert.assertTrue(StringUtils.isNumber(str8));
        Assert.assertTrue(StringUtils.isNumber(str9));
    }

    private String parseDate(String str){
        Date currentDate = DateUtils.currentDate();
        String day = str.replaceAll("小时前", "");
        Integer valueOf = Integer.valueOf(day);
        int hours = currentDate.getHours();
        String result;
        if (valueOf > hours) {
            result = DateUtils.getFormatDate(DateUtils.getYesterdayNow(currentDate));
        } else {
            result = DateUtils.getFormatDate(currentDate);
        }

        return result;
    }

    private String parseDate2(String str){
        Date currentDate = DateUtils.currentDate();
        int hours = Integer.parseInt(str.replaceAll("小时前", ""));
        int hour = DateUtils.getHour(currentDate);
        String result;
        if (hours > hour) {
            result = DateUtils.getFormatDate(DateUtils.getYesterdayNow(currentDate));
        } else {
            result = DateUtils.getFormatDate(currentDate);
        }

        return result;
    }

}
