/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.model.events;

import au.edu.newcastle.SENG48002013.game.engine.model.actions.IAction;


public abstract class BaseEvent implements IEvent {
	private long id;
	private IAction[] actions;
	public BaseEvent(long id)
	{
		this.id = id;
	}
	@Override
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	protected int doActions(long dt)
	{
		int returnCode = -1;
		for(int i = 0; i < actions.length; i++)
		{
			int newReturnCode = actions[i].doAction(dt);
			if(newReturnCode != -1)
			{
				returnCode = newReturnCode;
			}
		}
		return returnCode;
	}
	@Override
	public abstract int evaluate(long dt);
}
