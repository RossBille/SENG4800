/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.model.environment;

import javax.vecmath.Vector2d;




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
		this.size.set(size);
	}
	public Vector2d getPos()
	{
		return pos;
	}
	public void setPos(Vector2d pos)
	{
		this.pos.set(pos);
	}
	public Vector2d getVel()
	{
		return vel;
	}
	public void setVel(Vector2d vel)
	{
		this.vel.set(vel);
	}
	public Vector2d getAcc()
	{
		return acc;
	}
	public void setAcc(Vector2d acc)
	{
		this.acc.set(acc);
	}
	public Vector2d getNextPos()
	{
		return nextPos;
	}
	public void setNextPos(Vector2d nextPos)
	{
		committed = false;
		this.nextPos.set(nextPos);
	}
	public Vector2d getNextVel()
	{
		return nextVel;
	}
	public void setNextVel(Vector2d nextVel)
	{
		committed = false;
		this.nextVel.set(nextVel);
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
		nextAcc.set(acc);
		nextVel.scaleAdd((double)(dt/1000), nextAcc, nextVel);
		nextPos.scaleAdd((double)(dt/1000), nextVel, nextPos);
	}
	public void commit()
	{
		acc.set(nextAcc);
		vel.set(nextVel);
		pos.set(nextPos);
		committed = true;
	}
	public boolean isCommitted()
	{
		return committed;
	}
}