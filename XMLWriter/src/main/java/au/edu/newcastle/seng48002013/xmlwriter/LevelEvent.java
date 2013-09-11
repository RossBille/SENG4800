/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.seng48002013.xmlwriter;

/**
 *
 * @author Bracks
 */

import java.util.ArrayList;

public class LevelEvent {

	// Variables
	
        private int eventId;
	private String eventType;
	private String rule;
	private int triggerActionId;
	private boolean collision;
        private int objectId1;
	private int objectId2;
	
	
	        
        // Mutators
	public int getEventId() {
		return eventId;
	}
	
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	
	public String getEventType() {
		return eventType;
	}
	
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	public String getRule() {
		return rule;
	}
	
	public void setRule(String rule) {
		this.rule = rule;
	}
	
	public int getTriggerActionId() {
		return triggerActionId;
	}
	
	public void setTriggerActionId(int triggerActionId) {
		this.triggerActionId = triggerActionId;
	}
	
	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}
        
        // Mutators
	public int getObjectId1() {
		return objectId1;
	}
	public void setObjectId1(int objectId1) {
		this.objectId1 = objectId1;
	}
	public int getObjectId2() {
		return objectId2;
	}
	public void setObjectId2(int objectId2) {
		this.objectId2 = objectId2;
	}
	
}
