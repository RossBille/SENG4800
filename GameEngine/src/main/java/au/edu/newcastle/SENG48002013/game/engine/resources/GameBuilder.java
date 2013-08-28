/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.resources;

import javax.vecmath.Vector2d;

import au.edu.newcastle.SENG48002013.game.engine.model.*;
import au.edu.newcastle.SENG48002013.game.engine.model.actions.*;
import au.edu.newcastle.SENG48002013.game.engine.model.events.*;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.*;


public class GameBuilder {
	public static IGame buildGame()
	{
		Game game = new Game();
		Sprite sprite = new Sprite(1);
		GameObject gameObject = new GameObject(1);
		gameObject.setSprite(sprite);
		gameObject.setPos(new Vector2d(100, 100));
		gameObject.setVel(new Vector2d(2, 2));
		Rectangle rect = new Rectangle(new Vector2d(10, 10));
		gameObject.setShape(rect);
		Level level = new Level(1);
		level.setDimensions(new Vector2d(640, 480));
		level.addGameObject(gameObject);
		ChangeVelAction changeVelAction1 = new ChangeVelAction(1);
		changeVelAction1.setGameObject(gameObject);
		changeVelAction1.setType(ChangeVelAction.Type.HORIZONTAL);
		changeVelAction1.setValue(new Vector2d(-2, 0));
		ChangeVelAction changeVelAction2 = new ChangeVelAction(2);
		changeVelAction2.setGameObject(gameObject);
		changeVelAction2.setType(ChangeVelAction.Type.HORIZONTAL);
		changeVelAction2.setValue(new Vector2d(2, 0));
		ChangeVelAction changeVelAction3 = new ChangeVelAction(3);
		changeVelAction3.setGameObject(gameObject);
		changeVelAction3.setType(ChangeVelAction.Type.VERTICAL);
		changeVelAction3.setValue(new Vector2d(0, 2));
		ChangeVelAction changeVelAction4 = new ChangeVelAction(4);
		changeVelAction4.setGameObject(gameObject);
		changeVelAction4.setType(ChangeVelAction.Type.VERTICAL);
		changeVelAction4.setValue(new Vector2d(0, -2));
		BoundaryEvent boundaryEvent1 = new BoundaryEvent(1);
		boundaryEvent1.setAllowOverlap(false);
		boundaryEvent1.setGameObject(gameObject);
		boundaryEvent1.setLevel(level);
		boundaryEvent1.setEdge(BoundaryEvent.Edge.RIGHT);
		boundaryEvent1.addAction(changeVelAction1);
		BoundaryEvent boundaryEvent2 = new BoundaryEvent(2);
		boundaryEvent2.setAllowOverlap(false);
		boundaryEvent2.setGameObject(gameObject);
		boundaryEvent2.setLevel(level);
		boundaryEvent2.setEdge(BoundaryEvent.Edge.LEFT);
		boundaryEvent2.addAction(changeVelAction2);
		BoundaryEvent boundaryEvent3 = new BoundaryEvent(3);
		boundaryEvent3.setAllowOverlap(false);
		boundaryEvent3.setGameObject(gameObject);
		boundaryEvent3.setLevel(level);
		boundaryEvent3.setEdge(BoundaryEvent.Edge.TOP);
		boundaryEvent3.addAction(changeVelAction3);
		BoundaryEvent boundaryEvent4 = new BoundaryEvent(4);
		boundaryEvent4.setAllowOverlap(false);
		boundaryEvent4.setGameObject(gameObject);
		boundaryEvent4.setLevel(level);
		boundaryEvent4.setEdge(BoundaryEvent.Edge.BOTTOM);
		boundaryEvent4.addAction(changeVelAction4);
		level.addEvent(boundaryEvent1);
		level.addEvent(boundaryEvent2);
		level.addEvent(boundaryEvent3);
		level.addEvent(boundaryEvent4);
		GameResources.addLevel(level);
		game.setLevels(GameResources.getAllLevels());
		return game;
		//TO DO
		//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	public static Level loadLevel()
	{
		//TO DO
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
