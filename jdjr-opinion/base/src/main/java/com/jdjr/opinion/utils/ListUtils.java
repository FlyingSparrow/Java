package com.jdjr.opinion.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: ListUtils</p>
 * <p>Description: 集合工具类</p>
 *
 * @author wjc
 * @date 2017年7月\12日
 */
public class ListUtils {

    private ListUtils() {
    }

    public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
        List<List<T>> result = new ArrayList<List<T>>();
        int size = list.size();
        List<T> subList = new ArrayList<T>();
        for (int i = 0; i < size; i++) {
            T user = list.get(i);
            if (subList.size() == pageSize) {
                result.add(subList);
                subList = new ArrayList<T>();
                subList.add(user);
            } else {
                subList.add(user);
            }
        }
        result.add(subList);

        return result;
    }

}
