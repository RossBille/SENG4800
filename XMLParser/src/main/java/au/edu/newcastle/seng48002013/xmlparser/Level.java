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
public class Level {
    
    private int id;
    private String name;
    private int backgroundId;
    private ArrayList<GameObject> gameObjects;
    private ArrayList<Event> events;
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBackgroundId() {
		return backgroundId;
	}

	public void setBackgroundId(int backgroundId) {
		this.backgroundId = backgroundId;
	}

	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}

	public void setGameObjects(ArrayList<GameObject> gameObjects) {
		this.gameObjects = gameObjects;
	}

	public ArrayList<Event> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}
    
}
