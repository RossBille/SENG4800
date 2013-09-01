/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.connections.input;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Ross
 */
public class InputManager
{

	private static HashMap<Long, Input> inputs = new HashMap<Long, Input>();

	public static void addInput(Input input)
	{
		if(!(inputs.containsKey(input.getId()) && inputs.get(input.getId()).isAccessed()))
		{
			inputs.put(input.getId(), input);
		}
	}

	public static IInput getInput(long id)
	{
		return (IInput)inputs.get(id);
	}
	
	public static void flush()
	{
		Iterator<Input> inputIter = inputs.values().iterator();
		while(inputIter.hasNext())
		{
			inputIter.next().flush();
		}
	}
}
