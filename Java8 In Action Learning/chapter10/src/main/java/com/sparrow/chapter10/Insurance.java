package com.sparrow.chapter10;

import java.util.Optional;

/**
 * @author wangjianchun
 * @create 2018/4/20
 */
public class Insurance {

    private String name;

    public String getName() {
        return name;
    }

    public Insurance findCheapestInsurance(Person person, Car car){
        Insurance cheapestCompany = null;
        // 不同的保险公司提供的查询服务
        // 对比所有数据
        return cheapestCompany;
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car){
//        if(person.isPresent() && car.isPresent()){
//            return Optional.of(findCheapestInsurance(person.get(), car.get()));
//        }else{
//            return Optional.empty();
//        }
        return person.flatMap(person1 -> car.map(car1 -> findCheapestInsurance(person1, car1)));
    }

    public String getCarInsuranceName(Optional<Person> person, int minAge){
        String insuranceName = person.filter(person1 -> person1.getAge() >= minAge).flatMap(Person::getCar)
            .flatMap(Car::getInsurance).map(Insurance::getName).orElse("Unknown");
        return insuranceName;
    }
}
