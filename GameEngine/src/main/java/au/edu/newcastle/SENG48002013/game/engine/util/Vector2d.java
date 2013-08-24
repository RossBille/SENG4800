/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.util;


/**
 *
 * @author rossbille
 */
public class Vector2d extends javax.vecmath.Vector2d
{

	public Vector2d(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public void copyFrom(Vector2d v2)
	{
		v2.x = this.x;
		v2.y = this.y;
	}
	public void copyTo(Vector2d v2)
	{
		this.x = v2.x;
		this.y = v2.y;
	}

	public Vector2d scalarMul1(double scalar)
	{
		return new Vector2d(x*scalar, y*scalar);
	}
	public void scalarMul2(double scalar)
	{
		x *= scalar;
		y *= scalar;
	}

	public Vector2d difference(Vector2d v2)
	{
		return new Vector2d(x - v2.x, y - v2.y);
	}

	public Vector2d scalarDiv1(double scalar)
	{
		return new Vector2d(x/scalar, y/scalar);
	}
	public void scalarDiv2(double scalar)
	{
		x /= scalar;
		y /= scalar;
	}	
	public Vector2d unit()
	{
		return scalarDiv1(length());
	}
	
}
