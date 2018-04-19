package com.sparrow.chapter8.test;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author wangjianchun
 * @create 2018/4/19
 */
public class Point {

    public static final Comparator<Point> compareByXAndThenY =
            Comparator.comparing(Point::getX).thenComparing(Point::getY);

    private final int x;
    private final int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point moveRightBy(int x){
        return new Point(this.x + x, this.y);
    }

    public static List<Point> moveAllPointsRightBy(List<Point> points, int x){
        return points.stream().map(point -> new Point(point.getX()+x, point.getY()))
                .collect(toList());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Point) {
            Point anotherPoint = (Point)obj;
            if (this.getX() == anotherPoint.getX()
                    && this.getY() == anotherPoint.getY()) {
                return true;
            }
        }
        return false;
    }
}
