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
		GameObject gameObject1 = new GameObject(1);
		gameObject1.setSprite(sprite);
		gameObject1.setPos(new Vector2d(100, 103));
		gameObject1.setVel(new Vector2d(3, 2));
		GameObject gameObject2 = new GameObject(2);
		gameObject2.setSprite(sprite);
		gameObject2.setPos(new Vector2d(150, 100));
		gameObject2.setVel(new Vector2d(-3, 2));
		GameObject gameObject3 = new GameObject(3);
		gameObject3.setSprite(sprite);
		gameObject3.setPos(new Vector2d(150, 150));
		gameObject3.setVel(new Vector2d(-3, -2));
		//Rectangle rect = new Rectangle(new Vector2d(10, 10));
		Circle circle = new Circle(20);
		//gameObject.setShape(rect);
		gameObject1.setShape(circle);
		gameObject2.setShape(circle);
		gameObject3.setShape(circle);
		Level level = new Level(1);
		level.setDimensions(new Vector2d(640, 480));
		level.addGameObject(gameObject1);
		level.addGameObject(gameObject2);
		level.addGameObject(gameObject3);
		ReflectAction reflectAction1 = new ReflectAction(1);
		reflectAction1.setGameObject(gameObject1);
		reflectAction1.setLevel(level);
		ReflectAction reflectAction2 = new ReflectAction(2);
		reflectAction2.setGameObject(gameObject2);
		reflectAction2.setLevel(level);
		ReflectAction reflectAction3 = new ReflectAction(3);
		reflectAction3.setGameObject(gameObject1);
		reflectAction3.setSurfaceObject(gameObject2);
		ReflectAction reflectAction4 = new ReflectAction(4);
		reflectAction4.setGameObject(gameObject2);
		reflectAction4.setSurfaceObject(gameObject1);
		ReflectAction reflectAction5 = new ReflectAction(5);
		reflectAction5.setGameObject(gameObject3);
		reflectAction5.setLevel(level);
		ReflectAction reflectAction6 = new ReflectAction(6);
		reflectAction6.setGameObject(gameObject3);
		reflectAction6.setSurfaceObject(gameObject1);
		ReflectAction reflectAction7 = new ReflectAction(7);
		reflectAction7.setGameObject(gameObject3);
		reflectAction7.setSurfaceObject(gameObject2);
		ReflectAction reflectAction8 = new ReflectAction(8);
		reflectAction8.setGameObject(gameObject1);
		reflectAction8.setSurfaceObject(gameObject3);
		ReflectAction reflectAction9 = new ReflectAction(9);
		reflectAction9.setGameObject(gameObject2);
		reflectAction9.setSurfaceObject(gameObject3);
		BoundaryEvent boundaryEvent1 = new BoundaryEvent(1);
		boundaryEvent1.setGameObject(gameObject1);
		boundaryEvent1.setEdge(BoundaryEvent.Edge.ALL);
		boundaryEvent1.setLevel(level);
		boundaryEvent1.addAction(reflectAction1);
		BoundaryEvent boundaryEvent2 = new BoundaryEvent(2);
		boundaryEvent2.setGameObject(gameObject2);
		boundaryEvent2.setEdge(BoundaryEvent.Edge.ALL);
		boundaryEvent2.setLevel(level);
		boundaryEvent2.addAction(reflectAction2);
		BoundaryEvent boundaryEvent3 = new BoundaryEvent(3);
		boundaryEvent3.setGameObject(gameObject3);
		boundaryEvent3.setEdge(BoundaryEvent.Edge.ALL);
		boundaryEvent3.setLevel(level);
		boundaryEvent3.addAction(reflectAction5);	
		CollisionEvent collisionEvent1 = new CollisionEvent(4);
		collisionEvent1.setObject1(gameObject1);
		collisionEvent1.setObject2(gameObject2);
		collisionEvent1.setAllowOverlap(false);
		collisionEvent1.addAction(reflectAction3);
		collisionEvent1.addAction(reflectAction4);
		CollisionEvent collisionEvent2 = new CollisionEvent(5);
		collisionEvent2.setObject1(gameObject1);
		collisionEvent2.setObject2(gameObject3);
		collisionEvent2.setAllowOverlap(false);
		collisionEvent2.addAction(reflectAction6);
		collisionEvent2.addAction(reflectAction8);
		CollisionEvent collisionEvent3 = new CollisionEvent(6);
		collisionEvent3.setObject1(gameObject2);
		collisionEvent3.setObject2(gameObject3);
		collisionEvent3.setAllowOverlap(false);
		collisionEvent3.addAction(reflectAction9);
		collisionEvent3.addAction(reflectAction7);
		level.addEvent(boundaryEvent1);
		level.addEvent(boundaryEvent2);
		level.addEvent(boundaryEvent3);
		level.addEvent(collisionEvent1);
		level.addEvent(collisionEvent2);
		level.addEvent(collisionEvent3);
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
