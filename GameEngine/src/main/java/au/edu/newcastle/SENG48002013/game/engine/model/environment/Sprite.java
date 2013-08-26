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
	private BufferedImage[] images;
	private int currentImage;
	private Vector2d size;
	private long speed;
	public Sprite(long id)
	{
		this.id = id;
		currentImage = 0;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
}

