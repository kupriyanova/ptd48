package ru.stqa.pft.sandbox;

import com.sun.org.apache.regexp.internal.RE;

public class MyFirstProgram {
	public static void main(String[] args) {

		System.out.println("Hello, world!!");

		Square s = new Square(6);
		System.out.println("Площадь квадрата, со стороной " +s.l +" = " + s.area());

		Rectangle r = new Rectangle(6, 2);
		System.out.println("Площадь прямоугольника, со сторонами " + r.a + " и " + r.b + " = " + r.area());

		Point A = new Point(4,4);
		Point B = new Point(2.5,2.5);
		System.out.println("Расстояние между точками (" + A.x +", "+ A.y + ") и (" + B.x +", "+ B.y + ") = "+ A.distance(A, B));
	}
}