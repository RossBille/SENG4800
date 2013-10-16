package au.edu.newcastle.SENG48002013.game.engine.resources;

import au.edu.newcastle.SENG48002013.output.SetupMessage;
import au.edu.newcastle.SENG48002013.game.engine.model.IGame;
import au.edu.newcastle.SENG48002013.game.engine.model.actions.IAction;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Background;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.GameObject;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Level;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Player;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Sprite;
import au.edu.newcastle.SENG48002013.game.engine.model.events.IEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Peter
 */
public class GameResources {

    private static HashMap<Long, Level> levels = new HashMap<Long, Level>();
    private static HashMap<Long, GameObject> gameObjects = new HashMap<Long, GameObject>();
    private static HashMap<Long, Sprite> sprites = new HashMap<Long, Sprite>();
    private static HashMap<Long, Background> backgrounds = new HashMap<Long, Background>();
    private static HashMap<Long, IAction> actions = new HashMap<Long, IAction>();
    private static HashMap<Long, IEvent> events = new HashMap<Long, IEvent>();
    private static HashMap<Long, Player> players = new HashMap<Long, Player>();
    private static IGame game;

	/**
	 * 
	 * @param level 
	 */
    public static void addLevel(Level level) {
        levels.put(level.getId(), level);
    }

	/**
	 *
	 * @param id
	 * @return  
	 */
    public static Level getLevel(long id) {
        return levels.get(id);
    }

	/**
	 *
	 * @param id  
	 */
    public static void removeLevel(long id) {
        levels.remove(id);
    }

	/**
	 *
	 * @return  
	 */
    public static Level[] getAllLevels() {
        return (Level[]) levels.values().toArray(new Level[0]);
    }

	/**
	 *
	 * @param gameObject  
	 */
    public static void addGameObject(GameObject gameObject) {
        gameObjects.put(gameObject.getId(), gameObject);
    }

	/**
	 *
	 * @param id
	 * @return  
	 */
    public static GameObject getGameObject(long id) {
        return gameObjects.get(id);
    }

	/**
	 *
	 * @param id  
	 */
    public static void removeGameObject(long id) {
        gameObjects.remove(id);
    }

	/**
	 *
	 * @param sprite  
	 */
    public static void addSprite(Sprite sprite) {
        sprites.put(sprite.getId(), sprite);
    }

	/**
	 *
	 * @param id
	 * @return  
	 */
    public static Sprite getSprite(long id) {
        return sprites.get(id);
    }

	/**
	 *
	 * @param id  
	 */
    public static void removeSprite(long id) {
        sprites.remove(id);
    }

	/**
	 *
	 * @param background  
	 */
    public static void addBackground(Background background) {
        backgrounds.put(background.getId(), background);
    }

	/**
	 *
	 * @param id
	 * @return  
	 */
    public static Background getBackground(long id) {
        return backgrounds.get(id);
    }

	/**
	 *
	 * @param id  
	 */
    public static void removeBackground(long id) {
        backgrounds.remove(id);
    }

	/**
	 *
	 * @param action  
	 */
    public static void addAction(IAction action) {
        actions.put(action.getId(), action);
    }

	/**
	 *
	 * @param id
	 * @return  
	 */
    public static IAction getAction(long id) {
        return actions.get(id);
    }

	/**
	 *
	 * @param id  
	 */
    public static void removeAction(long id) {
        actions.remove(id);
    }

	/**
	 *
	 * @param event  
	 */
    public static void addEvent(IEvent event) {
        events.put(event.getId(), event);
    }

	/**
	 *
	 * @param id
	 * @return  
	 */
    public static IEvent getEvent(long id) {
        return events.get(id);
    }

	/**
	 *
	 * @param id  
	 */
    public static void removeEvent(long id) {
        events.remove(id);
    }

	/**
	 *
	 * @param id
	 * @return  
	 */
    public static Player getPlayer(long id) {
        return players.get(id);
    }

	/**
	 * 
	 * @param player 
	 */
    public static void addPlayer(Player player) {
        players.put(player.getId(), player);
    }

	/**
	 *
	 * @return  
	 */
    public static IGame getGame() {
        return game;
    }

	/**
	 *
	 * @param game  
	 */
    public static void setGame(IGame game) {
        GameResources.game = game;
    }

	/**
	 *
	 * @return  
	 */
    public static SetupMessage getResources() {
        int objSize = gameObjects.values().size();
        Iterator<Sprite> spriteIter = sprites.values().iterator();
        List<String> imageUrls = new LinkedList<String>();
        while (spriteIter.hasNext()) {
            Sprite currentSprite = spriteIter.next();
            String[] urls = currentSprite.getImageUrls();
            imageUrls.addAll(Arrays.asList(urls));
        }
        long[] objectIds = new long[objSize];
        Iterator<GameObject> objIter = gameObjects.values().iterator();
        int i = 0;
        while (objIter.hasNext()) {
            objectIds[i] = objIter.next().getId();
            i++;
        }
        return new SetupMessage(objectIds, imageUrls.toArray(new String[0]));
    }
}
