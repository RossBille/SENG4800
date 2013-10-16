package au.edu.newcastle.seng48002013.xmlparser;

/**
 *
 * @author Bracks
 */
import java.util.ArrayList;

public class GameSetup
{

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
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private ArrayList<Level> levels = new ArrayList<Level>();

	// Mutators
	/**
	 * 
	 * @return 
	 */
	public String getGameName()
	{
		return gameName;
	}

	/**
	 *
	 * @param min  
	 */
	public void setMinPlayers(int min)
	{
		this.minPlayers = min;
	}

	/**
	 *
	 * @return  
	 */
	public int getMinPlayers()
	{
		return minPlayers;
	}

	/**
	 *
	 * @param max  
	 */
	public void setMaxPlayers(int max)
	{
		this.maxPlayers = max;
	}

	/**
	 *
	 * @return  
	 */
	public int getMaxPlayers()
	{
		return maxPlayers;
	}

	/**
	 *
	 * @param gameName  
	 */
	public void setGameName(String gameName)
	{
		this.gameName = gameName;
	}

	/**
	 *
	 * @return  
	 */
	public int getCanvasSizeX()
	{
		return canvasSizeX;
	}

	/**
	 *
	 * @param canvasSizeX  
	 */
	public void setCanvasSizeX(int canvasSizeX)
	{
		this.canvasSizeX = canvasSizeX;
	}

	/**
	 *
	 * @return  
	 */
	public int getCanvasSizeY()
	{
		return canvasSizeY;
	}

	/**
	 *
	 * @param canvasSizeY  
	 */
	public void setCanvasSizeY(int canvasSizeY)
	{
		this.canvasSizeY = canvasSizeY;
	}

	/**
	 *
	 * @return  
	 */
	public String getBorderState()
	{
		return borderState;
	}

	/**
	 *
	 * @param borderState  
	 */
	public void setBorderState(String borderState)
	{
		this.borderState = borderState;
	}

	/**
	 *
	 * @return  
	 */
	public String getBorderColour()
	{
		return borderColour;
	}

	/**
	 *
	 * @param borderColour  
	 */
	public void setBorderColour(String borderColour)
	{
		this.borderColour = borderColour;
	}

	/**
	 *
	 * @return  
	 */
	public int getBorderSize()
	{
		return borderSize;
	}

	/**
	 *
	 * @param borderSize  
	 */
	public void setBorderSize(int borderSize)
	{
		this.borderSize = borderSize;
	}

	/**
	 *
	 * @return  
	 */

	public int getNumLevels()
	{
		return numLevels;
	}

	
	/**
	 *
	 * @param numLevels  
	 */
	public void setNumLevels(int numLevels)
	{
		this.numLevels = numLevels;
	}
	
	/**
	 *
	 * @return  
	 */
	public ArrayList<GameObject> getGameObjects()
	{
		return gameObjects;
	}
	
	/**
	 *
	 * @param gameObjects  
	 */
	public void setGameObjects(ArrayList<GameObject> gameObjects)
	{
		this.gameObjects = gameObjects;
	}
	
	/**
	 *
	 * @return  
	 */
	public ArrayList<Level> getLevels()
	{
		return levels;
	}
	
	/**
	 *
	 * @param levels  
	 */
	public void setLevels(ArrayList<Level> levels)
	{
		this.levels = levels;
	}
	
	/**
	 *
	 * @param gameObj  
	 */
	public void addGameObject(GameObject gameObj)
	{
		gameObjects.add(gameObj);
	}

	/**
	 *
	 * @param newLevel  
	 */
	public void addLevel(Level newLevel)
	{
		levels.add(newLevel);
	}
}
