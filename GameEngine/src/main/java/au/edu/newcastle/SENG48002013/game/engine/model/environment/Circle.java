/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.model.environment;

public class Circle extends Shape {
	double radius;
	public Circle(double radius)
	{
		this.radius = radius;
	}
	public double getRadius()
	{
		return radius;
	}
	public void setRadius(double radius)
	{
		this.radius = radius;
		this.area = Math.PI*radius*radius;
	}
}

