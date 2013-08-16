/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.seng48002013;

import java.util.ArrayList;

/**
 *
 * @author Bracks
 */
public class Action {

	// Variables
	private int action_id;
	private String operation;
	ArrayList<String> commands = new ArrayList<String>();
	
	
	// Mutators
	public int getAction_id() {
		return action_id;
	}
	public void setAction_id(int action_id) {
		this.action_id = action_id;
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
    

