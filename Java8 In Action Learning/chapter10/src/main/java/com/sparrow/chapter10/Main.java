package com.sparrow.chapter10;

import java.util.Optional;

/**
 * @author wangjianchun
 * @create 2018/4/20
 */
public class Main {

    public static void main(String[] args) {
        testOptional();
    }

    private static void testOptional(){
        //声明一个空的 Optional
        Optional<Car> optCar = Optional.empty();

        //依据一个非空值创建 Optional
        Car car = new Car();
        Optional<Car> optCar2 = Optional.of(car);

        //创建一个可以接收 null的 Optional
        Optional<Car> optCar3 = Optional.ofNullable(car);

        Insurance insurance = new Insurance();
        Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
        Optional<String> name = optInsurance.map(Insurance::getName);
        System.out.println("name: "+name);
    }

}
