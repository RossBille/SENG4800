/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.resources.actions;

import au.edu.newcastle.SENG48002013.game.engine.resources.environment.GameObject;
import au.edu.newcastle.SENG48002013.game.engine.util.Vector2d;


public class JumpAction extends BaseAction {
	private Vector2d jumpPos;
	private GameObject gameObject;
	public JumpAction(long id)
	{
		super(id);
	}
	public void doAction(long dt)
	{
		gameObject.setPos(jumpPos);
		gameObject.setNextPos(jumpPos);
	}

}
