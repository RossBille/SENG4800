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

public class GameSetup {

	// Config Variables
	private String gameName;
	private int canvasWidth;
	private int canvasHeight;
	private int startLevel;
	private int minPlayers;
	private int maxPlayers;
	private ArrayList<Sprite> sprites;
	private ArrayList<Background> backgrounds;
	
	// Mutators
	public String getGameName() {
		return gameName;
	}
	
	public int getCanvasWidth() {
		return canvasWidth;
	}

	public void setCanvasWidth(int canvasWidth) {
		this.canvasWidth = canvasWidth;
	}

	public int getCanvasHeight() {
		return canvasHeight;
	}

	public void setCanvasHeight(int canvasHeight) {
		this.canvasHeight = canvasHeight;
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

	public ArrayList<Sprite> getSprites() {
		return sprites;
	}

	public void setSprites(ArrayList<Sprite> sprites) {
		this.sprites = sprites;
	}

	public ArrayList<Background> getBackgrounds() {
		return backgrounds;
	}

	public void setBackgrounds(ArrayList<Background> backgrounds) {
		this.backgrounds = backgrounds;
	}

	public int getStartLevel() {
		return startLevel;
	}

	public void setStartLevel(int startLevel) {
		this.startLevel = startLevel;
	}
}
