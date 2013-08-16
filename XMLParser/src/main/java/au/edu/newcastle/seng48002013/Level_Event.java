/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.seng48002013;

/**
 *
 * @author Bracks
 */

import java.util.ArrayList;

public class Level_Event {

	// Variables
	
        private int event_id;
	private String event_type;
	private String rule;
	private int trigger_action_id;
	private boolean collision;
        private int object_id_1;
	private int object_id_2;
	
	
	        
        // Mutators
	public int getEvent_id() {
		return event_id;
	}
	
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	
	public String getEvent_type() {
		return event_type;
	}
	
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	
	public String getRule() {
		return rule;
	}
	
	public void setRule(String rule) {
		this.rule = rule;
	}
	
	public int getTrigger_action_id() {
		return trigger_action_id;
	}
	
	public void setTrigger_action_id(int trigger_action_id) {
		this.trigger_action_id = trigger_action_id;
	}
	
	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}
        
        // Mutators
	public int getObject_id_1() {
		return object_id_1;
	}
	public void setObject_id_1(int object_id_1) {
		this.object_id_1 = object_id_1;
	}
	public int getObject_id_2() {
		return object_id_2;
	}
	public void setObject_id_2(int object_id_2) {
		this.object_id_2 = object_id_2;
	}
	
}
