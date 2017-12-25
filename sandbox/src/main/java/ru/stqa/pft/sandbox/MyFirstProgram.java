package ru.stqa.pft.sandbox;

import com.sun.org.apache.regexp.internal.RE;

public class MyFirstProgram {
	public static void main(String[] args) {

		System.out.println("Hello, world!!");

		Square s = new Square(6);
		System.out.println("Площадь квадрата, со стороной " +s.l +" = " + s.area());

		Rectangle r = new Rectangle(6, 2);
		System.out.println("Площадь прямоугольника, со сторонами " + r.a + " и " + r.b + " = " + r.area());

		Point p = new Point(1,2, 3, 5);
		System.out.println("Расстояние между точками (" + p.x1 +", "+ p.y1 + ") и (" + p.x2 +", "+ p.y2 + ") = "+ p.distance());
	}
}