/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.input;

import java.util.HashMap;

/**
 *
 * @author Ross
 */
public class InputManager
{

	private static HashMap<Long, IInput> inputs = new HashMap<Long, IInput>();

	public static void addInput(IInput input)
	{
		inputs.put(input.getId(), input);
	}

	public static IInput getInput(long id)
	{
		return inputs.get(id);
	}
}
