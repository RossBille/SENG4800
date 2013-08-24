/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.resources.actions;

import au.edu.newcastle.SENG48002013.game.engine.resources.environment.GameObject;
import au.edu.newcastle.SENG48002013.game.engine.util.Vector2d;

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

	public void doAction(long dt)
	{
		if(getInput() != null)
		{
			Vector2d pos = getInput().getPos();
			gameObject.setPos(pos);
			gameObject.setNextPos(pos);
		}
	}
}