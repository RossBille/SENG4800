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
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	protected void doActions(long dt)
	{
		for(int i = 0; i < actions.length; i++)
		{
			actions[i].doAction(dt);
		}
	}
	public abstract void evaluate(long dt);
}
