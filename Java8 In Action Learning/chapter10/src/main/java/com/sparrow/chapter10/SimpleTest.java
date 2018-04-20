package com.sparrow.chapter10;

import org.junit.Test;

import java.util.Optional;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

/**
 * @author wangjianchun
 * @create 2018/4/20
 */
public class SimpleTest {

    @Test
    public void test(){
        Properties properties = new Properties();
        properties.setProperty("a", "5");
        properties.setProperty("b", "true");
        properties.setProperty("c", "-3");

        assertEquals(5, readDuration(properties, "a"));
        assertEquals(0, readDuration(properties, "b"));
        assertEquals(0, readDuration(properties, "c"));
        assertEquals(0, readDuration(properties, "d"));

        assertEquals(5, readDurationWithOptional(properties, "a"));
        assertEquals(0, readDurationWithOptional(properties, "b"));
        assertEquals(0, readDurationWithOptional(properties, "c"));
        assertEquals(0, readDurationWithOptional(properties, "d"));
    }

    private String getCarInsuranceName(Optional<Person> person){
        return person.flatMap(Person::getCar).flatMap(Car::getInsurance)
                .map(Insurance::getName).orElse("Unknown");
    }

    private int readDuration(Properties props, String name){
        String value = props.getProperty(name);
        if(value != null){
            try {
                int i = Integer.parseInt(value);
                if(i > 0){
                    return i;
                }
            } catch (NumberFormatException e) {
            }
        }
        return 0;
    }

    private int readDurationWithOptional(Properties props, String name){
        int result = Optional.ofNullable(props.getProperty(name))
                .flatMap(OptionalUtility::stringToInt)
                .filter(number -> number > 0).orElse(0);
        return result;
    }
}
