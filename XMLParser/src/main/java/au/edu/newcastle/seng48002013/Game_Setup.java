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
	private String game_name;
	private int canvas_size_x;
	private int canvas_size_y;
	private String border_state;
	private String border_colour;
        private int minPlayers;
        private int maxPlayers;
	private int border_size;
	private int no_of_levels;
	private ArrayList<Game_Object> gameObjects = new ArrayList<Game_Object>();
	private ArrayList<Level> levels = new ArrayList<Level>();
	
	
	// Mutators
	public String getGame_name() {
		return game_name;
	}
             
        public void setMin_players(int min) {
		this.minPlayers = min;
	}
        
        public int getMin_players() {
		return minPlayers;
	}
        
        public void setMax_players(int max) {
		this.maxPlayers = max;
	}
        
        public int getMax_players() {
		return maxPlayers;
	}

	public void setGame_name(String gameName) {
		this.game_name = gameName;
	}
	
	public int getCanvas_size_x() {
		return canvas_size_x;
	}

	public void setCanvas_size_x(int canvas_size_x) {
		this.canvas_size_x = canvas_size_x;
	}

	public int getCanvas_size_y() {
		return canvas_size_y;
	}

	public void setCanvas_size_y(int canvas_size_y) {
		this.canvas_size_y = canvas_size_y;
	}

	public String getBorder_state() {
		return border_state;
	}

	public void setBorder_state(String borderState) {
		this.border_state = borderState;
	}

	public String getBorder_colour() {
		return border_colour;
	}

	public void setBorder_colour(String borderColour) {
		this.border_colour = borderColour;
	}

	public int getBorder_size() {
		return border_size;
	}

	public void setBorder_size(int borderSize) {
		this.border_size = borderSize;
	}

	public int getNo_of_levels() {
		return no_of_levels;
	}

	public void setNo_of_levels(int no_of_levels) {
		this.no_of_levels = no_of_levels;
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
