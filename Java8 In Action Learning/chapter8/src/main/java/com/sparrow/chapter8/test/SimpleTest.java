package com.sparrow.chapter8.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author wangjianchun
 * @create 2018/4/19
 */
public class SimpleTest {

    @Test
    public void testMoveRightBy(){
        Point p1 = new Point(5, 5);
        Point p2 = p1.moveRightBy(10);

        assertEquals(15, p2.getX());
        assertEquals(5, p2.getY());
    }

    @Test
    public void testMoveAllPointsRightBy(){
        List<Point> points = Arrays.asList(new Point(5, 5), new Point(10, 5));
        List<Point> expectedPoints = Arrays.asList(new Point(15, 5), new Point(20, 5));

        List<Point> newPoints = Point.moveAllPointsRightBy(points, 10);
        assertEquals(expectedPoints, newPoints);
    }
}
