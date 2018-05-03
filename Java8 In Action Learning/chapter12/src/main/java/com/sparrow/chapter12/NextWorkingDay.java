package com.sparrow.chapter12;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

/**
 * @author wangjianchun
 * @create 2018/5/3
 */
public class NextWorkingDay implements TemporalAdjuster {

    @Override
    public Temporal adjustInto(Temporal date) {
        Temporal result = date;
        DayOfWeek dayOfWeek = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
        int dayToAdd = 1;
        if(DayOfWeek.FRIDAY == dayOfWeek){
            dayToAdd = 3;
        }else if(DayOfWeek.SATURDAY == dayOfWeek){
            dayToAdd = 2;
        }
        return result.plus(dayToAdd, ChronoUnit.DAYS);
    }
}
