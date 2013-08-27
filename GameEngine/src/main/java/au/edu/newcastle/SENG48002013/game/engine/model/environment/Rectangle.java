/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.model.environment;

import javax.vecmath.Vector2d;




public class Rectangle extends Shape {
	private Vector2d size;
	public Rectangle(Vector2d size)
	{
		this.size = new Vector2d(size);
	}
	public Vector2d getSize()
	{
		return size;
	}
	public void setSize(Vector2d size)
	{
		this.size.set(size);
		this.area = size.x*size.y;
	}
}