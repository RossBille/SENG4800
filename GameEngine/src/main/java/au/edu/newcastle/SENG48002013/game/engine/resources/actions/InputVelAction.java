/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.resources.actions;

import au.edu.newcastle.SENG48002013.game.engine.resources.environment.GameObject;


public class InputVelAction extends BaseInputAction {
	private GameObject gameObject;
	public InputVelAction(long id)
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
			gameObject.setNextVel(getInput().getPos());
		}
	}
}