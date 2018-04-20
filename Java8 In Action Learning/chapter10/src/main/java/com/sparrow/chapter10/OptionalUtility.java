package com.sparrow.chapter10;

import java.util.Optional;

/**
 * @author wangjianchun
 * @create 2018/4/20
 */
public class OptionalUtility {

    public static Optional<Integer> stringToInt(String string){
        try {
            return Optional.of(Integer.parseInt(string));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
