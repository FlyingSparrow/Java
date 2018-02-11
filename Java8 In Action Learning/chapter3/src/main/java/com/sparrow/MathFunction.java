package com.sparrow;

import java.util.function.DoubleFunction;

/**
 * @author wangjianchun
 * @create 2018/2/11
 */
public class MathFunction {

    public Double integrate(DoubleFunction<Double> f, Double a, Double b){
        return (f.apply(a)+f.apply(b))*(b-a)/2.0D;
    }

}
