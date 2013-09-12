/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.seng48002013.xmlparser;

import java.util.ArrayList;

/**
 *
 * @author Bracks
 */
public class Action {

	// Variables
	private int actionId;
	private String operation;
	ArrayList<String> commands = new ArrayList<String>();
	
	
	// Mutators
	public int getActionId() {
		return actionId;
	}
	public void setActionId(int actionId) {
		this.actionId = actionId;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getCommand(int pos) {
		return commands.get(pos);
	}
	public void addCommand(String command) {
		this.commands.add(command);
	}
	public int numCommands()
        {
            return this.commands.size();
        }
        
}
    

