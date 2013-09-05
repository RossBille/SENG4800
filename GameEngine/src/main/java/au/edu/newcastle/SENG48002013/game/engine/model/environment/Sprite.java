/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.model.environment;

import java.awt.image.BufferedImage;
import javax.vecmath.Vector2d;


public class Sprite {
	private long id;
	private String[] imageUrls;
	private int currentImage;
	private Vector2d size;
	private long speed;
	private final long FPS = 16;
	private long currentValue;
	public Sprite(long id)
	{
		this.id = id;
		size = new Vector2d();
		currentImage = 0;
		speed = 40;
		currentValue = 0;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String[] getImageUrls()
	{
		return imageUrls;
	}
	public void setImageUrls(String[] imageUrls)
	{
		this.imageUrls = imageUrls;
	}
	public long getSpeed()
	{
		return speed;
	}
	public void setSpeed(long speed)
	{
		this.speed = speed;
	}
	public void step(double dt)
	{
		currentValue += FPS;
		if(currentValue >= speed)
		{
			currentImage = (currentImage + 1)%imageUrls.length;
		}
	}
}

