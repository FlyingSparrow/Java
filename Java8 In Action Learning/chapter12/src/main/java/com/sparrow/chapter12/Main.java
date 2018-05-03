package com.sparrow.chapter12;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;

/**
 * @author wangjianchun
 * @create 2018/5/3
 */
public class Main {

    public static void main(String[] args) {
        localDateDemo();
        localTimeDemo();
        localDateTimeDemo();
        periodAndDurationDemo();
        zoneIdDemo();
    }

    private static void localDateDemo(){
        LocalDate date = LocalDate.of(2014, 3, 18);
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();

        System.out.println("date="+date);
        System.out.println("year="+year+", month="+month.getValue()+", day="+day
                +", dayOfWeek="+dow.getValue()+", lengthOfMonth="+len+", isLeapYear="+leap);

        LocalDate today = LocalDate.now();
        System.out.println("today="+today);

        LocalDate newDate = LocalDate.parse("2014-03-18");
        System.out.println("newDate="+newDate);
        System.out.println();
    }

    private static void localTimeDemo(){
        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        System.out.println("time="+time);
        System.out.println("hour="+hour+", minute="+minute+", second="+second);

        LocalTime newTime = LocalTime.parse("13:45:20");
        System.out.println("newTime="+newTime);
        System.out.println();
    }

    private static void localDateTimeDemo(){
        LocalDateTime dateTime1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        LocalDate date = LocalDate.of(2014, 3, 18);
        LocalTime time = LocalTime.of(13, 45, 20);
        LocalDateTime dateTime2 = LocalDateTime.of(date, time);
        LocalDateTime dateTime3 = date.atTime(13, 45, 20);
        LocalDateTime dateTime4 = date.atTime(time);
        LocalDateTime dateTime5 = time.atDate(date);

        System.out.println("dateTime1="+dateTime1);
        System.out.println("dateTime2="+dateTime2);
        System.out.println("dateTime3="+dateTime3);
        System.out.println("dateTime4="+dateTime4);
        System.out.println("dateTime5="+dateTime5);
        System.out.println();
    }

    private static void periodAndDurationDemo(){
        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);

        Period tenDays = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
        System.out.println();
    }

    private static void zoneIdDemo(){
        ZoneId romeZone = ZoneId.of("Europe/Rome");
        ZoneId zoneId = TimeZone.getDefault().toZoneId();

        LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
        ZonedDateTime zdt1 = date.atStartOfDay(romeZone);

        LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        ZonedDateTime zdt2 = dateTime.atZone(romeZone);

        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(romeZone);

        System.out.println("zdt1="+zdt1+", zdt2="+zdt2+", zdt3="+zdt3);
        System.out.println();
    }
}
