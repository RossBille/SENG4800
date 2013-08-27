/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.model.actions;

import au.edu.newcastle.SENG48002013.game.engine.model.environment.GameObject;
import javax.vecmath.Vector2d;

public class InputPosAction extends BaseInputAction {
	private GameObject gameObject;
	public InputPosAction(long id)
	{
		super(id);
	}
	public GameObject getGameObject()
	{
		return gameObject;
	}
	public void setGameObject(GameObject gameObject)
	{
		this.gameObject = gameObject;
	}

	public int doAction(long dt)
	{
		if(getInput() != null)
		{
			Vector2d pos = getInput().getPos();
			gameObject.setPos(pos);
			gameObject.setNextPos(pos);
		}
		return -1;
	}
}