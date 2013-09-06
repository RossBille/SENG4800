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
	private Vector2d size;
	private long speed;
	public Sprite(long id)
	{
		this.id = id;
		size = new Vector2d();
		speed = 40;
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
	public String getImageUrl(int index)
	{
		return imageUrls[index];
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
	public int length()
	{
		return imageUrls.length; 
	}
}