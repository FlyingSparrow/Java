package com.huishu.ieanalysis.utils;

import java.text.DecimalFormat;
import java.util.*;

/**
 * <p>Title: NumberUtils</p>
 * <p>Description: 数字工具类</p>
 *
 * @author wjc
 * @date 2017年4月22日
 */
public class NumberUtils {

    private NumberUtils() {
    }

    public static Double formatDouble(Double num) {
        if (num == null) {
            return null;
        }
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.valueOf(df.format(num));
    }

    /**
     * <p>Description: 根据数量和边界值生成随机的数据，用于测试或者演示</p>
     *
     * @param quantity
     * @param bound
     * @return
     * @author wjc
     * @date 2017年4月22日
     */
    public static List<Integer> generateIntegerData(int quantity, int bound) {
        List<Integer> result = new ArrayList<Integer>();
        Random random = new Random();
        for (int i = 0; i < quantity; i++) {
            result.add(random.nextInt(bound));
        }

        return result;
    }

    /**
     * <p>Description: 根据数量和边界值生成随机的数据，用于测试或者演示</p>
     *
     * @param quantity
     * @param bound
     * @return
     * @author wjc
     * @date 2017年4月22日
     */
    public static List<Double> generateDoubleData(int quantity, int bound) {
        List<Double> result = new ArrayList<Double>();
        Random random = new Random();
        for (int i = 0; i < quantity; i++) {
            result.add(Double.valueOf(random.nextInt(bound)));
        }

        return result;
    }

    /**
     * <p>Description: 根据数量和边界值生成随机的数据，用于测试或者演示</p>
     *
     * @param quantity
     * @param bound
     * @return
     * @author wjc
     * @date 2017年4月22日
     */
    public static List<Long> generateLongData(int quantity, int bound) {
        List<Long> result = new ArrayList<Long>();
        Random random = new Random();
        for (int i = 0; i < quantity; i++) {
            result.add(Long.valueOf(random.nextInt(bound)));
        }

        return result;
    }

    /**
     * <p>Description: 根据数量和边界值生成随机的数据，生成的数据按照正序排序，用于测试或者演示</p>
     *
     * @param quantity
     * @param bound
     * @return
     * @author wjc
     * @date 2017年4月22日
     */
    public static List<Integer> generateDataOrderByAsc(int quantity, int bound) {
        List<Integer> result = generateIntegerData(quantity, bound);
        result.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        return result;
    }

    /**
     * <p>Description: 根据数量和边界值生成随机的数据，生成的数据按照降序排序，用于测试或者演示</p>
     *
     * @param quantity
     * @param bound
     * @return
     * @author wjc
     * @date 2017年4月22日
     */
    public static List<Integer> generateDataOrderByDesc(int quantity, int bound) {
        List<Integer> result = generateIntegerData(quantity, bound);
        result.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        return result;
    }

}
