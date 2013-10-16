package au.edu.newcastle.SENG48002013.game.engine.model;

import java.util.LinkedList;
import java.util.Queue;

import javax.vecmath.Vector2d;

import au.edu.newcastle.SENG48002013.game.engine.model.environment.IGameObject;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Level;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Player;

/**
 *
 * @author Peter 
 */
public class Game implements IGame, IGameOutput {

    Level[] levels;
    Player[] players;
    int minPlayers;
    int maxPlayers;
    int currentLevel;
    Vector2d size;
    int activePlayers;
    Queue<Long> addPlayerQueue;
    Queue<Long> removePlayerQueue;

	/**
	 * 
	 */
    public Game() {
        levels = null;
        players = null;
        currentLevel = 0;
        size = new Vector2d();
        activePlayers = 0;
        addPlayerQueue = new LinkedList<Long>();
        removePlayerQueue = new LinkedList<Long>();
    }

	/**
	 *
	 * @param levels  
	 */
    public void setLevels(Level[] levels) {
        this.levels = levels;
    }

	/**
	 *
	 * @param levelId  
	 */
    public void setCurrentLevel(long levelId) {
        for (int i = 0; i < levels.length; i++) {
            if (levels[i].getId() == levelId) {
                currentLevel = i;
                break;
            }
        }
    }

	/**
	 *
	 * @param players  
	 */
    public void setPlayers(Player[] players) {
        this.players = players;
    }

	/**
	 *
	 * @return  
	 */
    public int getMaxPlayers() {
        return maxPlayers;
    }

	/**
	 *
	 * @param maxPlayers  
	 */
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

	/**
	 *
	 * @return  
	 */
    public int getMinPlayers() {
        return minPlayers;
    }

	/**
	 * 
	 * @param minPlayers 
	 */
    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

	/**
	 *
	 * @return  
	 */
    public Vector2d getSize() {
        return size;
    }

	/**
	 *
	 * @param size  
	 */
    public void setSize(Vector2d size) {
        this.size = size;
    }

	/**
	 *
	 * @param inputId
	 * @return  
	 */
    @Override
    public boolean addPlayer(long inputId) {
        boolean added = false;
        if (addPlayerQueue.size() + activePlayers - removePlayerQueue.size() < players.length) {
            addPlayerQueue.add(inputId);
            added = true;
        }
        return added;
    }

	/**
	 * 
	 * @param inputId 
	 */
    @Override
    public void removePlayer(long inputId) {
        removePlayerQueue.add(inputId);
    }

	/**
	 *
	 * @param dt  
	 */
    @Override
    public void step(double dt) {
        deactivatePlayers();
        activatePlayers();
        int returnCode = levels[currentLevel].step(dt);
        if (returnCode != -1) {
            setCurrentLevel(returnCode);
            //currentLevel = returnCode;
        }
    }

	/**
	 *
	 * @return  
	 */
    @Override
    public IGameObject[] getOutputObjects() {
        return levels[currentLevel].getOutputObjects();
    }

    private void activatePlayers() {
        while (addPlayerQueue.size() > 0) {
            players[activePlayers].setInputId(addPlayerQueue.remove());
            activePlayers++;
        }
    }
    //I think there's a logic error here, given that we're using arrays and stuff
    //I think it should probably be reordered after each deactivation, otherwise
    //stuff might get overwritten. Will look into later.

    private void deactivatePlayers() {
        while (removePlayerQueue.size() > 0) {
            for (int i = 0; i < players.length; i++) {
                long inputId = removePlayerQueue.peek();
                if (players[i].getInputId() == inputId) {
                    players[i].setInputId(-1);
                    removePlayerQueue.remove();
                    activePlayers--;
                }
            }
        }
    }
}
