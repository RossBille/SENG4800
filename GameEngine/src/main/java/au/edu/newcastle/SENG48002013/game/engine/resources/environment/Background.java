/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.resources.environment;

public class Background extends Sprite {
	public static final int TILED = 1;
	public static final int STRETCH = 2;
	public static final int FILL = 3;
	public static final int CENTER = 4;
	private int positionType;
	public Background(long id)
	{
		super(id);
	}
	public int getPositionType()
	{
		return positionType;
	}
	public void setPositionType(int positionType)
	{
		this.positionType = positionType;
	}
}

