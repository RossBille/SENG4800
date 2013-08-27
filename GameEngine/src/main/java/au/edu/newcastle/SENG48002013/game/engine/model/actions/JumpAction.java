/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.model.actions;

import au.edu.newcastle.SENG48002013.game.engine.model.environment.GameObject;
import javax.vecmath.Vector2d;


public class JumpAction extends BaseAction {
	private Vector2d jumpPos;
	private GameObject gameObject;
	public JumpAction(long id)
	{
		super(id);
		jumpPos = new Vector2d();
	}
	public int doAction(long dt)
	{
		gameObject.setPos(jumpPos);
		gameObject.setNextPos(jumpPos);
		return -1;
	}
}
