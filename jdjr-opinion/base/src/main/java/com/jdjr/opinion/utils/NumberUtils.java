package com.jdjr.opinion.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Title: NumberUtils</p>
 * <p>Description: 数值处理工具类</p>
 *
 * @author Wangjianchun
 * @date 2017年6月29日
 */
public class NumberUtils {

    private NumberUtils() {
    }

    public static double defaultDouble(Double value) {
        return (value == null ? 0D : value);
    }

    public static float defaultFloat(Float value) {
        return (value == null ? 0F : value);
    }

    public static long defaultLong(Long value) {
        return (value == null ? 0L : value);
    }

    /**
     * 如果value == null，那么返回0，否则返回value
     *
     * @param value
     * @return
     */
    public static int defaultInt(Integer value) {
        return (value == null ? 0 : value);
    }

    public static int defaultInt(Integer value,Integer def) {
        return (value == null ? def : value);
    }

    public static short defaultShort(Short value) {
        return (value == null ? 0 : value);
    }

    public static double bigDecimalToDouble(BigDecimal value) {
        return Double.valueOf(value.toPlainString());
    }

    public static String formatDouble(double number) {
        BigDecimal bigDecimal = new BigDecimal(number);
        bigDecimal = bigDecimal.setScale(3, RoundingMode.HALF_UP);
        return bigDecimal.toString();
    }

    public static String formatDouble(double number, int newScale) {
        BigDecimal bigDecimal = new BigDecimal(number);
        bigDecimal = bigDecimal.setScale(newScale, RoundingMode.HALF_UP);
        return bigDecimal.toString();
    }

    public static Double formatDoubleNewScale(double number, int newScale) {
        BigDecimal bigDecimal = new BigDecimal(number);
        bigDecimal = bigDecimal.setScale(newScale, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    /**
     * 计算负面舆情占比
     *
     * @param totalOpinion         舆情总数
     * @param negativeOpinionCount 负面舆情数
     * @return 负面舆情占比，精确到小数点后两位
     */
    public static Double calculateNegativeProportion(int totalOpinion,
                                                     int negativeOpinionCount) {
        if (totalOpinion == 0 || negativeOpinionCount == 0) {
            return 0D;
        }
        BigDecimal tempTotalOpinion = new BigDecimal(totalOpinion + "");
        BigDecimal tempNegativeOpinionCount = new BigDecimal(negativeOpinionCount + "");
        Double result = tempNegativeOpinionCount.divide(tempTotalOpinion)
                .setScale(2, RoundingMode.HALF_UP).doubleValue();
        return result;
    }

    public static Double doubleAdd(List<Double> doubles) {
        List<BigDecimal> bigDecimals = doubles.stream()
                .map(String::valueOf)
                .map(BigDecimal::new).collect(Collectors.toList());
        return bigDecimals.stream().reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue();
    }

    /**
     * @param dividend 被除数
     * @param divisor  除数
     * @param newScale 小数点位数
     * @return
     */
    public static Double divide(Number dividend, Number divisor, int newScale) {
        BigDecimal dividendB = new BigDecimal(String.valueOf(dividend));
        BigDecimal divisorB = new BigDecimal(String.valueOf(divisor));
        BigDecimal result = BigDecimal.ZERO;
        if (divisorB.compareTo(BigDecimal.ZERO) != 0) {
            result = dividendB.divide(divisorB, newScale, BigDecimal.ROUND_HALF_DOWN);
        }

        return result.doubleValue();
    }

    public static Double divideMultiply(Number dividend, Number divisor, int newScale) {
        BigDecimal dividendB = new BigDecimal(String.valueOf(dividend));
        BigDecimal divisorB = new BigDecimal(String.valueOf(divisor));
        BigDecimal result = BigDecimal.ZERO;
        if (divisorB.compareTo(BigDecimal.ZERO) != 0) {
            result = dividendB.divide(divisorB, newScale, BigDecimal.ROUND_HALF_DOWN)
                    .multiply(new BigDecimal(100));
        }

        return result.doubleValue();
    }

    public static Double changeProportion(Number num1, Number num2) {
        Double result = 0D;
        BigDecimal numBig1 = new BigDecimal(String.valueOf(num1));
        BigDecimal numBig2 = new BigDecimal(String.valueOf(num2));
        if (numBig2.compareTo(BigDecimal.ZERO) == 1) {
            BigDecimal calcResult = numBig1.divide(numBig2, 10, BigDecimal.ROUND_HALF_DOWN)
                    .subtract(BigDecimal.ONE)
                    .abs()
                    .multiply(new BigDecimal(100));
            result = calcResult.doubleValue();
        }
        return result;
    }

    public static Double changeProportion(Number num1, Number num2, int newScale) {
        Double result = 0D;
        BigDecimal numBig1 = new BigDecimal(String.valueOf(num1));
        BigDecimal numBig2 = new BigDecimal(String.valueOf(num2));
        if (numBig2.compareTo(BigDecimal.ZERO) == 1) {
            BigDecimal calcResult = numBig1.divide(numBig2, 10, BigDecimal.ROUND_HALF_DOWN)
                    .subtract(BigDecimal.ONE)
                    .abs()
                    .multiply(new BigDecimal(100)).setScale(newScale, BigDecimal.ROUND_HALF_DOWN);
            result = calcResult.doubleValue();
        }
        return result;
    }

}
