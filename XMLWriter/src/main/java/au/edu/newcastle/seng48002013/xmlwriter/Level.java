/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.seng48002013.xmlwriter;

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
    ArrayList<LevelEvent> levelEvents = new ArrayList<LevelEvent>();     // will be <Event>   
    ArrayList<LevelObject> levelObjects = new ArrayList<LevelObject>();     // will be <Object>
    
    Level(String ID, String num)
    {
        this.ID = Integer.parseInt(ID);
        this.levelNo = Integer.parseInt(num);
    }
    
    public void addEvent(LevelEvent event)
    {
        levelEvents.add(event);
    }
    
    public void addObject(LevelObject object)
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
	
    public ArrayList<LevelObject> getLevelObjects() {
	return levelObjects;
    }
	
    public void setLevelObjects(ArrayList<LevelObject> levelObjects) {
	this.levelObjects = levelObjects;
    }
	
    public ArrayList<LevelEvent> getLevelEvents() {
	return levelEvents;
    }
	
    public void setLevelEvents(ArrayList<LevelEvent> levelEvents) {
	this.levelEvents = levelEvents;
    }
	
    public void addLevelObject(LevelObject levelObj) {
	levelObjects.add(levelObj);
    }
	
    public void addLevelEvent(LevelEvent levelEvent) {
	levelEvents.add(levelEvent);
    }
}
