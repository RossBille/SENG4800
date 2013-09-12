/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.resources;

import javax.vecmath.Vector2d;

import org.w3c.dom.Element;

import au.edu.newcastle.SENG48002013.game.engine.model.actions.*;

/**
 *
 * @author Ross
 */
public class ActionFactory
{
	public static IAction buildAction(Element actionElement)
	{
		BaseAction action = null;
		long id = (long)XmlUtils.getNumericValue(actionElement, "ACTION_ID");
		
		Element typeElement = (Element)actionElement.getElementsByTagName("ACTION_TYPE").item(0);
		//If change sprite action
		if(typeElement.getElementsByTagName("CHANGE_SPRITE").getLength() > 0)
		{
			Element changeSpriteElement = (Element)typeElement.getElementsByTagName("CHANGE_SPRITE").item(0);
			action = buildChangeSpriteAction(changeSpriteElement, id);
		}
		//If change score action
		else if(typeElement.getElementsByTagName("CHANGE_SCORE").getLength() > 0)
		{
			Element changeScoreElement = (Element)typeElement.getElementsByTagName("CHANGE_SCORE").item(0);
			action = buildChangeScoreAction(changeScoreElement, id);
		}
		//If change component action
		else if(typeElement.getElementsByTagName("CHANGE_COMPONENT").getLength() > 0)
		{
			Element changeComponentElement = (Element)typeElement.getElementsByTagName("CHANGE_COMPONENT").item(0);
			action = buildChangeComponentAction(changeComponentElement, id);
		}
		//If change level action
		else if(typeElement.getElementsByTagName("CHANGE_LEVEL").getLength() > 0)
		{
			Element changeLevelElement = (Element)typeElement.getElementsByTagName("CHANGE_LEVEL").item(0);
			action = buildChangeLevelAction(changeLevelElement, id);
		}
		//If reflect action
		else if(typeElement.getElementsByTagName("REFLECT").getLength() > 0)
		{
			Element reflectElement = (Element)typeElement.getElementsByTagName("REFLECT").item(0);
			action = buildReflectAction(reflectElement, id);
		}
		//If input action
		else if(typeElement.getElementsByTagName("INPUT").getLength() > 0)
		{
			Element inputElement = (Element)typeElement.getElementsByTagName("INPUT").item(0);
			action = buildInputAction(inputElement, id);
		}
		return action;
	}
	private static BaseAction buildChangeSpriteAction(Element changeSpriteElement, long id)
	{
		ChangeSpriteAction action = new ChangeSpriteAction(id);
		//Set the game object
		long objectId = (long)XmlUtils.getNumericValue(changeSpriteElement, "OBJECT_ID");
		action.setGameObject(GameResources.getGameObject(objectId));
		//Set the new sprite
		long spriteId = (long)XmlUtils.getNumericValue(changeSpriteElement, "SPRITE_ID");
		action.setSprite(GameResources.getSprite(spriteId));
		return action;
	}
	private static BaseAction buildChangeScoreAction(Element changeScoreElement, long id)
	{
		ChangeScoreAction action = new ChangeScoreAction(id);
		//Set the player
		long playerId = (long)XmlUtils.getNumericValue(changeScoreElement, "PLAYER_ID");
		action.setPlayer(GameResources.getPlayer(playerId));
		//Set the value
		double value = XmlUtils.getNumericValue(changeScoreElement, "VALUE");
		action.setValue(value);
		//Set the type
		String strType = XmlUtils.getTextValue(changeScoreElement, "TYPE");
		switch(strType.toUpperCase())
		{
			case "ADD": action.setType(ChangeScoreAction.Type.ADD);
			break;
			case "SUB": action.setType(ChangeScoreAction.Type.SUB);
			break;
			case "SET": action.setType(ChangeScoreAction.Type.SET);
			break;
		}
		return action;
	}
	private static BaseAction buildChangeComponentAction(Element changeComponentElement, long id)
	{
		ChangeComponentAction action = new ChangeComponentAction(id);
		//Set the game object
		long objectId = (long)XmlUtils.getNumericValue(changeComponentElement, "OBJECT_ID");
		action.setGameObject(GameResources.getGameObject(objectId));
		//Set the value
		Vector2d value = XmlUtils.getVectorValue(changeComponentElement, "VALUE");
		action.setValue(value);
		//Set the component
		String strComponent = XmlUtils.getTextValue(changeComponentElement, "COMPONENT");
		switch(strComponent.toUpperCase())
		{
			case "POS": action.setComponent(ChangeComponentAction.Component.POS);
			break;
			case "VEL": action.setComponent(ChangeComponentAction.Component.VEL);
			break;
			case "ACC": action.setComponent(ChangeComponentAction.Component.ACC);
			break;
		}
		//Set the type
		String strType = XmlUtils.getTextValue(changeComponentElement, "TYPE");
		switch(strType.toUpperCase())
		{
			case "ADD": action.setType(ChangeComponentAction.Type.ADD);
			break;
			case "SUB": action.setType(ChangeComponentAction.Type.SUB);
			break;
			case "SET": action.setType(ChangeComponentAction.Type.SET);
			break;
		}
		return action;
	}
	private static BaseAction buildChangeLevelAction(Element changeLevelElement, long id)
	{
		ChangeLevelAction action = new ChangeLevelAction(id);
		//Set the new level
		long levelId = (long)XmlUtils.getNumericValue(changeLevelElement, "LEVEL_ID");
		action.setLevelId(levelId);
		return action;
	}
	private static BaseAction buildReflectAction(Element reflectElement, long id)
	{
		ReflectAction action = new ReflectAction(id);
		//Set the game object
		long objectId = (long)XmlUtils.getNumericValue(reflectElement, "OBJECT_ID");
		action.setGameObject(GameResources.getGameObject(objectId));
		//Set the surface object
		if(reflectElement.getElementsByTagName("SURFACE_OBJECT_ID").getLength() > 0)
		{
			long surfaceObjectId = (long)XmlUtils.getNumericValue(reflectElement, "SURFACE_OBJECT_ID");
			action.setSurfaceObject(GameResources.getGameObject(surfaceObjectId));
		}
		//Or set the surface level
		else if(reflectElement.getElementsByTagName("SURFACE_LEVEL_ID").getLength() > 0)
		{
			long surfaceLevelId = (long)XmlUtils.getNumericValue(reflectElement, "SURFACE_LEVEL_ID");
			action.setLevel(GameResources.getLevel(surfaceLevelId));
		}
		return action;
	}
	private static BaseAction buildInputAction(Element inputElement, long id)
	{
		InputAction action = new InputAction(id);
		//Set the game object
		long objectId = (long)XmlUtils.getNumericValue(inputElement, "OBJECT_ID");
		action.setGameObject(GameResources.getGameObject(objectId));		
		//Set the player
		long playerId = (long)XmlUtils.getNumericValue(inputElement, "PLAYER_ID");
		action.setPlayer(GameResources.getPlayer(playerId));
		//Set the type
		String strType = XmlUtils.getTextValue(inputElement, "TYPE");
		switch(strType.toUpperCase())
		{
			case "ADD": action.setType(InputAction.Type.ADD);
			break;
			case "SUB": action.setType(InputAction.Type.SUB);
			break;
			case "SET": action.setType(InputAction.Type.SET);
			break;
		}
		return action;
	}	
}
