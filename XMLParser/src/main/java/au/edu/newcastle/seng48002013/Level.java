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
public class Level {
    
    private int ID;
    private int levelNo;
    private int endEventId;
    private int nextLevelId;
    ArrayList<Level_Event> levelEvents = new ArrayList<Level_Event>();     // will be <Event>   
    ArrayList<Level_Object> levelObjects = new ArrayList<Level_Object>();     // will be <Object>
    
    Level(String ID, String num)
    {
        this.ID = Integer.parseInt(ID);
        this.levelNo = Integer.parseInt(num);
    }
    
    public void addEvent(Level_Event event)
    {
        levelEvents.add(event);
    }
    
    public void addObject(Level_Object object)
    {
        levelObjects.add(object);
    }
    
    // Mutators
    public int getId() {
	return ID;
    }
	
    public void setId(int id) {
	this.ID = id;
    }
	
    public int getLevelNo() {
	return levelNo;
    }
	
    public void setLevelNo(int levelNo) {
	this.levelNo = levelNo;
    }
	
    public int getEndEventId() {
	return endEventId;
    }
	
    public void setEndEventId(int endEventId) {
	this.endEventId = endEventId;
    }
	
    public int getNextLevelId() {
	return nextLevelId;
    }
    public void setNextLevelId(int nextLevelId) {
	this.nextLevelId = nextLevelId;
    }
	
    public ArrayList<Level_Object> getLevelObjects() {
	return levelObjects;
    }
	
    public void setLevelObjects(ArrayList<Level_Object> levelObjects) {
	this.levelObjects = levelObjects;
    }
	
    public ArrayList<Level_Event> getLevelEvents() {
	return levelEvents;
    }
	
    public void setLevelEvents(ArrayList<Level_Event> levelEvents) {
	this.levelEvents = levelEvents;
    }
	
    public void addLevelObject(Level_Object levelObj) {
	levelObjects.add(levelObj);
    }
	
    public void addLevelEvent(Level_Event levelEvent) {
	levelEvents.add(levelEvent);
    }
}
