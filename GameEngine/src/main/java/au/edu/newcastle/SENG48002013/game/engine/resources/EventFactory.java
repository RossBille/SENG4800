/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.resources;

import javax.vecmath.Vector2d;
import org.w3c.dom.*;

import au.edu.newcastle.SENG48002013.game.engine.model.actions.IAction;
import au.edu.newcastle.SENG48002013.game.engine.model.events.*;

/**
 *
 * @author Ross
 */
public class EventFactory {

    public static IEvent buildEvent(Element eventElement) {
        BaseEvent event = null;
        long id = (long) XmlUtils.getNumericValue(eventElement, "EVENT_ID");

        Element typeElement = (Element) eventElement.getElementsByTagName("EVENT_TYPE").item(0);
        //If Collision Event
        if (typeElement.getElementsByTagName("COLLISION").getLength() > 0) {
            Element collisionElement = (Element) typeElement.getElementsByTagName("COLLISION").item(0);
            event = buildCollisionEvent(collisionElement, id);
        } //If Boundary Event
        else if (typeElement.getElementsByTagName("BOUNDARY").getLength() > 0) {
            Element boundaryElement = (Element) typeElement.getElementsByTagName("BOUNDARY").item(0);
            event = buildBoundaryEvent(boundaryElement, id);
        } //If Timer Event
        else if (typeElement.getElementsByTagName("TIMER").getLength() > 0) {
            Element timerElement = (Element) typeElement.getElementsByTagName("TIMER").item(0);
            event = buildTimerEvent(timerElement, id);
        } //If Input Event
        else if (typeElement.getElementsByTagName("INPUT").getLength() > 0) {
            Element inputElement = (Element) typeElement.getElementsByTagName("INPUT").item(0);
            event = buildInputEvent(inputElement, id);
        } //If Step Event
        else if (typeElement.getElementsByTagName("STEP").getLength() > 0) {
            Element stepElement = (Element) typeElement.getElementsByTagName("STEP").item(0);
            event = buildStepEvent(stepElement, id);
        }

        //Call Action Factory to build actions
        NodeList actionNodes = eventElement.getElementsByTagName("ACTION");
        if (actionNodes != null && actionNodes.getLength() > 0) {
            for (int i = 0; i < actionNodes.getLength(); i++) {
                Element actionElement = (Element) actionNodes.item(i);
                IAction action = (ActionFactory.buildAction(actionElement));
                event.addAction(action);
            }
        }

        //Add event to game resources
        GameResources.addEvent(event);
        return event;
    }

    private static BaseEvent buildCollisionEvent(Element collisionElement, long id) {
        CollisionEvent event = new CollisionEvent(id);
        //Add the two game objects
        long objectId1 = (long) XmlUtils.getNumericValue(collisionElement, "OBJECT_ID1");
        long objectId2 = (long) XmlUtils.getNumericValue(collisionElement, "OBJECT_ID2");
        event.setObject1(GameResources.getGameObject(objectId1));
        event.setObject2(GameResources.getGameObject(objectId2));
        //Set the allow overlap flag
        boolean allowOverlap;
        String strAllowOverlap = XmlUtils.getTextValue(collisionElement, "ALLOW_OVERLAP");
        if (strAllowOverlap.toUpperCase().equals("YES")) {
            allowOverlap = true;
        } else {
            allowOverlap = false;
        }
        event.setAllowOverlap(allowOverlap);
        return event;
    }

    private static BaseEvent buildBoundaryEvent(Element boundaryElement, long id) {
        BoundaryEvent event = new BoundaryEvent(id);
        //Set the game object
        long objectId = (long) XmlUtils.getNumericValue(boundaryElement, "OBJECT_ID");
        event.setGameObject(GameResources.getGameObject(objectId));
        //Set the level
        long levelId = (long) XmlUtils.getNumericValue(boundaryElement, "LEVEL_ID");
        event.setLevel(GameResources.getLevel(levelId));
        //The which edge this collision is for
        String strEdges = XmlUtils.getTextValue(boundaryElement, "EDGES");
        switch (strEdges.toUpperCase()) {
            case "TOP":
                event.setEdge(BoundaryEvent.Edge.TOP);
                break;
            case "BOTTOM":
                event.setEdge(BoundaryEvent.Edge.BOTTOM);
                break;
            case "LEFT":
                event.setEdge(BoundaryEvent.Edge.LEFT);
                break;
            case "RIGHT":
                event.setEdge(BoundaryEvent.Edge.RIGHT);
                break;
            case "ALL":
                event.setEdge(BoundaryEvent.Edge.ALL);
                break;
        }
        //Set the allow overlap flag
        boolean allowOverlap;
        String strAllowOverlap = XmlUtils.getTextValue(boundaryElement, "ALLOW_OVERLAP");
        if (strAllowOverlap.toUpperCase().equals("YES")) {
            allowOverlap = true;
        } else {
            allowOverlap = false;
        }
        event.setAllowOverlap(allowOverlap);
        return event;
    }

    private static BaseEvent buildTimerEvent(Element timerElement, long id) {
        TimerEvent event = new TimerEvent(id);
        //Set timer length
        long length = (long) XmlUtils.getNumericValue(timerElement, "LEVEL_ID");
        event.setTotalTime(length);
        //Set repeat flag
        boolean repeat;
        String strRepeat = XmlUtils.getTextValue(timerElement, "REPEAT");
        if (strRepeat.toUpperCase().equals("YES")) {
            repeat = true;
        } else {
            repeat = false;
        }
        event.setRepeat(repeat);
        return event;
    }

    private static BaseEvent buildInputEvent(Element inputElement, long id) {
        InputEvent event = new InputEvent(id);
        //Set player
        long playerId = (long) XmlUtils.getNumericValue(inputElement, "PLAYER_ID");
        event.setPlayer(GameResources.getPlayer(playerId));
        //Set value
        Vector2d value = XmlUtils.getVectorValue(inputElement, "VALUE");
        event.setValue(value);
        //Set type
        String strType = XmlUtils.getTextValue(inputElement, "TYPE");
        switch (strType.toUpperCase()) {
            case "UP":
                event.setType(InputEvent.Type.UP);
                break;
            case "DOWN":
                event.setType(InputEvent.Type.DOWN);
                break;
            case "LEFT":
                event.setType(InputEvent.Type.LEFT);
                break;
            case "RIGHT":
                event.setType(InputEvent.Type.RIGHT);
                break;
            case "ANY":
                event.setType(InputEvent.Type.ANY);
                break;
        }
        return event;
    }

    private static BaseEvent buildStepEvent(Element eventElement, long id) {
        StepEvent event = new StepEvent(id);
        return event;
    }
}