/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.seng48002013.xmlparser;

/**
 *
 * @author Bracks
 */

import java.util.ArrayList;

public class Event {

	// Variables
	private int eventId;
	private String eventType;
	private int objectId1;
	private int objectId2;
	private boolean allowOverlap;		// set with String input
	private int objectId;
	private int levelId;
	private String edges;
	private int length;
	private int value;
	private int inputValueX;
	private int inputValueY;
	private String inputType;
	private int step;
	private ArrayList<Action> actions;
	
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
	public boolean isAllowOverlap() {
		return allowOverlap;
	}
	public void setAllowOverlap(String overlap) {
		if (overlap.equals("Yes")) {
			allowOverlap = true;
		} else {
			allowOverlap = false;
		}
	}
	public int getObjectId() {
		return objectId;
	}
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	public String getEdges() {
		return edges;
	}
	public void setEdges(String edges) {
		this.edges = edges;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getInputValueX() {
		return inputValueX;
	}
	public void setInputValueX(int inputValueX) {
		this.inputValueX = inputValueX;
	}
	public int getInputValueY() {
		return inputValueY;
	}
	public void setInputValueY(int inputValueY) {
		this.inputValueY = inputValueY;
	}
	public String getInputType() {
		return inputType;
	}
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public ArrayList<Action> getActions() {
		return actions;
	}
	public void setActions(ArrayList<Action> actions) {
		this.actions = actions;
	}
	
}
