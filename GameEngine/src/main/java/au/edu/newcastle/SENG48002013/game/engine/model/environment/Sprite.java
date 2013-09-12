/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.model.environment;

import javax.vecmath.Vector2d;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)

public class Sprite {
	private long id;
	private String[] imageUrls;
	private long speed;
	private Vector2d offset;
	public Sprite(long id)
	{
		this.id = id;
		speed = 40;
		offset = new Vector2d(0,0);
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
	public Vector2d getOffset()
	{
		return offset;
	}
	public void setOffset(Vector2d offset)
	{
		this.offset.set(offset);
	}
	public int length()
	{
		if(imageUrls != null)
		{
			return imageUrls.length;
		}
		else
		{
			return 0;
		}
	}
}