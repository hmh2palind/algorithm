package com.xxx.yyy.designpatterns.structural;

import java.io.IOException;



/**
 * Decorator pattern allows a user to add new functionality to an existing
 * object without altering its structure.This pattern creates a decorator class
 * which wraps the original class and provides additional functionality keeping
 * class methods signature intact
 * 
 * @author hunghm5
 * 
 */
public class DecoratorPatternDemo {
	public static void main(String[] args) throws IOException {
//		FileInputStream fileInputStream = new FileInputStream("text.txt");
//		fileInputStream.read();
//		
//		BufferedInputStream bufferInputStream = new BufferedInputStream(new FileInputStream("text.txt"));
//		bufferInputStream.read();
//		
//		DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream("text.txt")));
//		dataInputStream.read();
//		
//		InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("text.txt"));
		
		
		Shape circle = new Circle();
		Shape redCircle = new RedShapeDecorator(new Circle());

		Shape redRectangle = new RedShapeDecorator(new Rectangle());
		System.out.println("Circle with normal border");
		circle.draw();

		System.out.println("\nCircle of red border");
		redCircle.draw();

		System.out.println("\nRectangle of red border");
		redRectangle.draw();
	}
}

interface Shape {
	public void draw();
}

class Circle implements Shape {

	@Override
	public void draw() {
		System.out.println("Shape: Circle");
	}
}

class Rectangle implements Shape {
	@Override
	public void draw() {
		System.out.println("Shape: Rectangle");
	}
}

abstract class ShapeDecorator implements Shape {
	protected Shape decoratedShape;

	public ShapeDecorator(Shape decoratedShape) {
		if (decoratedShape == null) {
			throw new IllegalArgumentException("Shape cannot not null");
//			throw new NullPointerException();
		}
		this.decoratedShape = decoratedShape;
	}

	@Override
	public void draw() {
		decoratedShape.draw();
	}
}

class RedShapeDecorator extends ShapeDecorator {

	public RedShapeDecorator(Shape decoratedShape) {
		super(decoratedShape);
	}

	@Override
	public void draw() {
		decoratedShape.draw();
		setRedBorder(decoratedShape);
	}

	private void setRedBorder(Shape decoratedShape) {
		System.out.println("Border Color: Red");
	}
}