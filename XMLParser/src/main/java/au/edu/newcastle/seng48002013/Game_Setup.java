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

public class Game_Setup {

	// Config Variables
	private String gameName;
	private int canvasSizeX;
	private int canvasSizeY;
	private String borderState;
	private String borderColour;
        private int minPlayers;
        private int maxPlayers;
	private int borderSize;
	private int numLevels;
	private ArrayList<Game_Object> gameObjects = new ArrayList<Game_Object>();
	private ArrayList<Level> levels = new ArrayList<Level>();
	
	
	// Mutators
	public String getGameName() {
		return gameName;
	}
             
        public void setMinPlayers(int min) {
		this.minPlayers = min;
	}
        
        public int getMinPlayers() {
		return minPlayers;
	}
        
        public void setMaxPlayers(int max) {
		this.maxPlayers = max;
	}
        
        public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	public int getCanvasSizeX() {
		return canvasSizeX;
	}

	public void setCanvasSizeX(int canvasSizeX) {
		this.canvasSizeX = canvasSizeX;
	}

	public int getCanvasSizeY() {
		return canvasSizeY;
	}

	public void setCanvasSizeY(int canvasSizeY) {
		this.canvasSizeY = canvasSizeY;
	}

	public String getBorderState() {
		return borderState;
	}

	public void setBorderState(String borderState) {
		this.borderState = borderState;
	}

	public String getBorderColour() {
		return borderColour;
	}

	public void setBorderColour(String borderColour) {
		this.borderColour = borderColour;
	}

	public int getBorderSize() {
		return borderSize;
	}

	public void setBorderSize(int borderSize) {
		this.borderSize = borderSize;
	}

	public int getNumLevels() {
		return numLevels;
	}

	public void setNumLevels(int numLevels) {
		this.numLevels = numLevels;
	}

	public ArrayList<Game_Object> getGameObjects() {
		return gameObjects;
	}

	public void setGameObjects(ArrayList<Game_Object> gameObjects) {
		this.gameObjects = gameObjects;
	}

	public ArrayList<Level> getLevels() {
		return levels;
	}

	public void setLevels(ArrayList<Level> levels) {
		this.levels = levels;
	}
	
	public void addGameObject(Game_Object gameObj) {
		gameObjects.add(gameObj);
	}
	
	public void addLevel(Level newLevel) {
		levels.add(newLevel);
	}
}
