/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.resources.environment;

import au.edu.newcastle.SENG48002013.game.engine.util.Vector2d;



public class GameObject {
	private long id;
	private String name;
	private Sprite sprite;
	private Shape shape;
	private Vector2d size;
	private Vector2d pos;
	private Vector2d vel;
	private Vector2d acc;
	private Vector2d nextPos;
	private Vector2d nextVel;
	private Vector2d nextAcc;
	private boolean committed;
	public GameObject(long id)
	{
		this.id = id;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Sprite getSprite()
	{
		return sprite;
	}
	public void setSprite(Sprite sprite)
	{
		this.sprite = sprite;
	}
	public Shape getShape()
	{
		return shape;
	}
	public void setShape(Shape shape)
	{
		this.shape = shape;
	}
	public Vector2d getSize()
	{
		return this.size;
	}
	public void setSize(Vector2d size)
	{
		this.size.copyTo(size);
	}
	public Vector2d getPos()
	{
		return pos;
	}
	public void setPos(Vector2d pos)
	{
		this.pos.copyTo(pos);
	}
	public Vector2d getVel()
	{
		return vel;
	}
	public void setVel(Vector2d vel)
	{
		this.vel.copyTo(vel);
	}
	public Vector2d getAcc()
	{
		return acc;
	}
	public void setAcc(Vector2d acc)
	{
		this.acc.copyTo(acc);
	}
	public Vector2d getNextPos()
	{
		return nextPos;
	}
	public void setNextPos(Vector2d nextPos)
	{
		committed = false;
		this.nextPos.copyTo(nextPos);
	}
	public Vector2d getNextVel()
	{
		return nextVel;
	}
	public void setNextVel(Vector2d nextVel)
	{
		committed = false;
		this.nextVel.copyTo(nextVel);
	}
	public Vector2d getNextAcc()
	{
		return nextAcc;
	}
	public void setNextAcc(Vector2d nextAcc)
	{
		committed = false;
		this.nextAcc = nextAcc;
	}
	public void stepNext(long dt)
	{
		committed = false;
		nextAcc.copyTo(acc);
		nextVel.add(acc.scalarMul1((double)(dt/1000)));
		nextPos.add(vel.scalarMul1((double)(dt/1000)));
	}
	public void commit()
	{
		acc.copyTo(nextAcc);
		vel.copyTo(nextVel);
		pos.copyTo(nextPos);
		committed = true;
	}
	public boolean isCommitted()
	{
		return committed;
	}
}