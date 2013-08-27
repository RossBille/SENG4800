/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.model.actions;

import au.edu.newcastle.SENG48002013.game.engine.model.environment.GameObject;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Sprite;


public class ChangeSpriteAction extends BaseAction {
	private Sprite newSprite;
	private GameObject gameObject;
	public ChangeSpriteAction(long id)
	{
		super(id);
	}
	public Sprite getSprite()
	{
		return newSprite;
	}
	public void setSprite(Sprite sprite)
	{
		this.newSprite = sprite;
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
		gameObject.setSprite(newSprite);
		return -1;
	}
}
