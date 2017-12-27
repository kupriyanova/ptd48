package ru.stqa.pft.sandbox;

public class Point {

    public double x;
    public double y;

    public Point (double x, double y){
        this.x = x;
        this.y = y;
    }

    public double distance(Point A, Point B) {

        return Math.sqrt(((A.x-B.x)*(A.x-B.x))+((A.y-B.y)*(A.y-B.y)));
    }
}
