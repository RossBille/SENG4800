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
    private int level_no;
    private int end_event_id;
    private int next_level_id;
    ArrayList<Level_Event> levelEvents = new ArrayList<Level_Event>();     // will be <Event>   
    ArrayList<Level_Object> levelObjects = new ArrayList<Level_Object>();     // will be <Object>
    
    Level(String ID, String num)
    {
        this.ID = Integer.parseInt(ID);
        this.level_no = Integer.parseInt(num);
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
	
    public int getLevel_no() {
	return level_no;
    }
	
    public void setLevel_no(int level_no) {
	this.level_no = level_no;
    }
	
    public int getEnd_event_id() {
	return end_event_id;
    }
	
    public void setEnd_event_id(int end_event_id) {
	this.end_event_id = end_event_id;
    }
	
    public int getNext_level_id() {
	return next_level_id;
    }
    public void setNext_level_id(int next_level_id) {
	this.next_level_id = next_level_id;
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
