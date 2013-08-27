/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.connections.input;

import javax.vecmath.Vector2d;


/**
 *
 */
public class Input implements IInput{
	private long id;
	private Vector2d value;
	private boolean position;
	public Input(long id)
	{
		this.id = id;
		value = new Vector2d();
	}
	@Override
	public long getId()
	{
		return id;
	}
	@Override
	public void setId(long id)
	{
		this.id = id;
	}

	@Override
	public Vector2d getValue()
	{
		return value;
	}

	@Override
	public void setValue(Vector2d value)
	{
		this.value = value;
	}

	@Override
	public boolean isPosition()
	{
		return position;
	}

	@Override
	public void setPosition(boolean position)
	{
		this.position = position;	
	}
	@Override
	public Vector2d getPos()
	{
		return getValue();
	}
}