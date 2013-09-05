/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.model.actions;

import au.edu.newcastle.SENG48002013.game.engine.connections.input.IInput;
import au.edu.newcastle.SENG48002013.game.engine.connections.input.InputManager;


public abstract class BaseInputAction extends BaseAction {
	private long inputId;

	public BaseInputAction(long id)
	{
		super(id);
		inputId = -1;
	}
	public long getInputId()
	{
		return inputId;
	}
	public void setInputId(long inputId)
	{
		this.inputId = inputId;
	}
	protected IInput getInput()
	{
		if(inputId < 0)
			return null;
		else
			return InputManager.getInput(inputId);
	}
}
