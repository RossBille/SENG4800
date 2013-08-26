/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.resources;

import au.edu.newcastle.SENG48002013.game.engine.model.actions.IAction;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Background;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.GameObject;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Level;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Sprite;
import au.edu.newcastle.SENG48002013.game.engine.model.events.IEvent;
import java.util.HashMap;


public class GameResources {
	private static HashMap<Long, Level> levels = new HashMap<Long, Level>();
	private static HashMap<Long, GameObject> gameObjects = new HashMap<Long, GameObject>();
	private static HashMap<Long, Sprite> sprites = new HashMap<Long, Sprite>();
	private static HashMap<Long, Background> backgrounds = new HashMap<Long, Background>();
	private static HashMap<Long, IAction> actions = new HashMap<Long, IAction>();
	private static HashMap<Long, IEvent> events = new HashMap<Long, IEvent>();
	
	public static void addLevel(Level level)
	{
		levels.put(level.getId(), level);
	}
	public static Level getLevel(long id)
	{
		return levels.get(id);
	}
	public static void removeLevel(long id)
	{
		levels.remove(id);
	}

	public static void addGameObject(GameObject gameObject)
	{
		gameObjects.put(gameObject.getId(), gameObject);
	}
	public static GameObject getGameObject(long id)
	{
		return gameObjects.get(id);
	}
	public static void removeGameObject(long id)
	{
		gameObjects.remove(id);
	}
	
	public static void addSprite(Sprite sprite)
	{
		sprites.put(sprite.getId(), sprite);
	}
	public static Sprite getSprite(long id)
	{
		return sprites.get(id);
	}
	public static void removeSprite(long id)
	{
		sprites.remove(id);
	}
	
	public static void addBackground(Background background)
	{
		backgrounds.put(background.getId(), background);
	}
	public static Background getBackground(long id)
	{
		return backgrounds.get(id);
	}
	public static void removeBackground(long id)
	{
		backgrounds.remove(id);
	}
	
	public static void addAction(IAction action)
	{
		actions.put(action.getId(), action);
	}
	public static IAction getAction(long id)
	{
		return actions.get(id);
	}
	public static void removeAction(long id)
	{
		actions.remove(id);
	}
	
	public static void addEvent(IEvent event)
	{
		events.put(event.getId(), event);
	}
	public static IEvent getEvent(long id)
	{
		return events.get(id);
	}
	public static void removeEvent(long id)
	{
		events.remove(id);
	}
}
