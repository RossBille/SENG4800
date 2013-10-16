package au.edu.newcastle.seng48002013.xmlparser;

import java.util.ArrayList;

/**
 *
 * @author Bracks
 */
public class Action
{

	// Variables
	private int actionId;
	private String operation;
	ArrayList<String> commands = new ArrayList<String>();

	// Mutators
	/**
	 *
	 * @return
	 */
	public int getActionId()
	{
		return actionId;
	}

	/**
	 *
	 * @param actionId
	 */
	public void setActionId(int actionId)
	{
		this.actionId = actionId;
	}

	/**
	 *
	 * @return
	 */
	public String getOperation()
	{
		return operation;
	}

	/**
	 *
	 * @param operation
	 */
	public void setOperation(String operation)
	{
		this.operation = operation;
	}

	/**
	 *
	 * @param pos
	 * @return
	 */
	public String getCommand(int pos)
	{
		return commands.get(pos);
	}

	/**
	 *
	 * @param command
	 */
	public void addCommand(String command)
	{
		this.commands.add(command);
	}

	/**
	 *
	 * @return
	 */
	public int numCommands()
	{
		return this.commands.size();
	}
	/**
	 *
	 */
}
