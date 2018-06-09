package com.jdjr.opinion.utils;

import org.apache.commons.math3.distribution.NormalDistribution;

import java.math.BigDecimal;
import java.util.List;

public class NormalDistributionUtils {

    public static BigDecimal getPressureIndexs(BigDecimal x, List<BigDecimal> pressureIndexs) {
        BigDecimal averageValue = getAverageValue(pressureIndexs);
        BigDecimal standardDiviation = getStandardDiviation(pressureIndexs);
        BigDecimal s = BigDecimal.ZERO;
        if (standardDiviation.compareTo(BigDecimal.ZERO) != 0) {
            s = getNormalDistribution(averageValue, standardDiviation, x);
        }
        return s;
    }

    /**
     * 均值为 μ标准差σ的正态分布的具体实现
     *
     * @param averageValue      double型保留四位小数，表示正态分布均值
     * @param standardDiviation double型保留四位小数，表示正态分布标准差
     * @return x double型保留四位小数
     */
    public static BigDecimal getNormalDistribution(BigDecimal averageValue, BigDecimal standardDiviation, BigDecimal x) {
        BigDecimal s;
        NormalDistribution normalDistributioin = new NormalDistribution(0, 1);//新建一个标准正态分布对象
        BigDecimal z = x.subtract(averageValue).divide(standardDiviation, 4, BigDecimal.ROUND_HALF_UP);
        try {
            s = new BigDecimal(normalDistributioin.cumulativeProbability(z.doubleValue()))
                    .setScale(4, BigDecimal.ROUND_HALF_UP);
        } catch(Exception e) {
            // 这里的异常为所得的结果过小导致异常，直接将结果自动置0
            s = BigDecimal.ZERO;
        }
        return s;
    }

    /**
     * 算术平均数
     *
     * @param value
     * @return
     */
    public static BigDecimal getAverageValue(List<BigDecimal> value) {
        BigDecimal valSum = value.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal valSize = new BigDecimal(value.size());
        if (value.size() > 0) {
            return valSum.divide(valSize, 10, BigDecimal.ROUND_HALF_UP);
        }
        return BigDecimal.ZERO;
    }

    /**
     * 标准差σ=sqrt(s^2)
     *
     * @param value
     * @return
     */
    public static BigDecimal getStandardDiviation(List<BigDecimal> value) {
        BigDecimal minval = new BigDecimal("0.25");
        if (value.size() > 0) {
            BigDecimal dAve = getAverageValue(value); //求平均值
            BigDecimal valSize = new BigDecimal(value.size());
            BigDecimal dVar = value.stream().reduce(BigDecimal.ZERO, (sum, item) -> sum.add(item.subtract(dAve).pow(2)));
            BigDecimal d = dVar.divide(valSize, 10, BigDecimal.ROUND_HALF_UP);
            BigDecimal result = new BigDecimal(String.valueOf(Math.sqrt(Double.valueOf(d.toPlainString()))));
            result = result.compareTo(minval) == 1 ? result : minval;
            return result;
        }
        return minval;
    }
}
