package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointsTest {

    @Test
    public void PointsTest0(){
        Point a = new Point(1,1);
        Point b = new Point(1,1);
        Assert.assertEquals(a.distance(a,b), 0.0);
    }

    @Test
    public void PointsTest(){
        Point a = new Point(4,4);
        Point b = new Point(2.5,2.5);
        Assert.assertEquals(a.distance(a,b), 2.1213203435596424);
    }
}
