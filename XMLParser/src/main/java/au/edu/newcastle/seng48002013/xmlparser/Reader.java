/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.seng48002013.xmlparser;
import java.io.IOException;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;
/**
 *
 * @author Josh Brackenbury
 */
public class Reader {

	// TO DO:
	//			ADD ACTION TYPE?
	//			SUPPORT FOR MULTIPLE ACTION TYPES? OR ONLY ONE
	
    // levels storage object
    public static ArrayList<Level> levels = new ArrayList<Level>();
    public static GameSetup gameSetup = new GameSetup();
    
    public static void runReader()
    {
        String fileName;        
        fileName = "Levels";
        parseLevelsXML(fileName);  
        fileName = "Game";
        parseGameXML(fileName);
    }
    
    public static ArrayList<Level> getLevels()
    {
        return levels;
    }
    
    public static GameSetup getGameSetup()
    {
        return gameSetup;
    }
    
    private static void parseGameXML(String loc)
    {
    	//DOM Object
        Document dom;
        
        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // parse using the builder to get the DOM mapping of the
            // XML file
            String location = loc + ".xml";
            dom = db.parse(location);

            Element docEle = dom.getDocumentElement();
            
            // get the setup
            NodeList nl = docEle.getElementsByTagName("Setup");
            
            // build info
            
            //get the element
            Element el = (Element)nl.item(0);
			
			//get the object
			GameSetup newSetup = getSetup(el);
        	
			// set GameSetup object
			gameSetup = newSetup;
          
        }
		catch (ParserConfigurationException pce)
		{
                    System.out.println(pce.getMessage());
                }
		catch (SAXException se)
		{
                    System.out.println(se.getMessage());
                }
		catch (IOException ioe)
		{
                    System.out.println(ioe.getMessage());
                }   
    }
    
    private static void parseLevelsXML(String loc) //Reads in the blackboard users
    {
          //DOM Object
        Document dom;
        
        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // parse using the builder to get the DOM mapping of the
            // XML file
            String location = loc + ".xml";
            dom = db.parse(location);

            Element docEle = dom.getDocumentElement();

            // get a list of the levels
            NodeList nl = docEle.getElementsByTagName("Level");
            
            // for each level, build info
            if(nl != null && nl.getLength() > 0)
            {
				for(int a = 0; a < nl.getLength(); a++)
				{          
					//get the element
		            Element el = (Element)nl.item(a);
					//get the object
					Level e = getLevel(el);
		        	//add to users
					levels.add(e);
				}
            }
        }
		catch (ParserConfigurationException pce)
		{
                    System.out.println(pce.getMessage());
                }
		catch (SAXException se)
		{
                    System.out.println(se.getMessage());
                }
		catch (IOException ioe)
		{
                    System.out.println(ioe.getMessage());
                }
    }
    
    private static GameSetup getSetup(Element el)
	{
		GameSetup newSetup = new GameSetup();
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		ArrayList<Background> backgrounds = new ArrayList<Background>();
		
		// Game Name
		String gameName = getTextValue(el, "Game_name");
		newSetup.setGameName(gameName);
		
		// Canvas Size
		NodeList nl = el.getElementsByTagName("Canvas_size");
		Element el2 = (Element)nl.item(0);
		int width = getIntValue(el2, "Width");
		int height = getIntValue(el2, "Height");
		newSetup.setCanvasWidth(width);
		newSetup.setCanvasHeight(height);
		
		// Starting Level
		int startLevel = getIntValue(el, "Starting_level");
		newSetup.setStartLevel(startLevel);
		
		// Players
		nl = el.getElementsByTagName("Players");
		el2 = (Element)nl.item(0);
		int min = getIntValue(el2, "Min");
		int max = getIntValue(el2, "Max");
		newSetup.setMinPlayers(min);
		newSetup.setMaxPlayers(max);
		
		// Sprites
    	NodeList nodeList = el.getElementsByTagName("Sprites");
    	Element elem = (Element)nodeList.item(0);
		nl = elem.getElementsByTagName("Sprite");
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {
				Element e2 = (Element)nl.item(i);
				Sprite newSprite = getSprite(e2);
				sprites.add(newSprite);
			}
		}
		newSetup.setSprites(sprites);
				
		// Backgrounds
		nodeList = el.getElementsByTagName("Backgrounds");
		elem = (Element)nodeList.item(0);
		nl = elem.getElementsByTagName("Background");
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i <nl.getLength(); i++) {
				Element e2 = (Element)nl.item(i);
				Background newBackground = getBackground(e2);
				backgrounds.add(newBackground);
			}
		}
		newSetup.setBackgrounds(backgrounds);
		
		return newSetup;
	}
    
    private static Sprite getSprite(Element el)
    {
    	Sprite newSprite = new Sprite();
    	newSprite.setId(getIntValue(el, "Sprite_ID"));
    	newSprite.setName(getTextValue(el, "Sprite_Name"));
    	newSprite.setImage(getTextValue(el, "Image"));
    	newSprite.setSpeed(getIntValue(el, "Speed"));
    	NodeList nl = el.getElementsByTagName("Offset");
    	Element el2 = (Element)nl.item(0);
		newSprite.setOffsetX(getIntValue(el2, "X"));
		newSprite.setOffsetY(getIntValue(el2, "Y"));
		return newSprite;
    }
    
    private static Background getBackground(Element el)
    {
    	Background newBackground = new Background();
    	newBackground.setId(getIntValue(el, "Background_Id"));
    	newBackground.setName(getTextValue(el, "Background_Name"));
    	newBackground.setImage(getTextValue(el, "Image"));
    	newBackground.setSpeed(getIntValue(el, "Speed"));
    	newBackground.setPositionType(getTextValue(el, "Position_Type"));
    	return newBackground;
    }
    
    private static Level getLevel(Element el)
    {
    	Level newLevel = new Level();
    	newLevel.setId(getIntValue(el, "Level_Id"));
    	newLevel.setName(getTextValue(el, "Level_Name"));
    	newLevel.setBackgroundId(getIntValue(el, "Background_Id"));
    	
    	// objects
    	ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    	NodeList nodeList = el.getElementsByTagName("Objects");
    	Element elem = (Element)nodeList.item(0);
    	NodeList nl = elem.getElementsByTagName("Object");
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {
				Element e2 = (Element)nl.item(i);
				GameObject newGameObject = getGameObject(e2);
				gameObjects.add(newGameObject);
			}
		}
		newLevel.setGameObjects(gameObjects);
    	
    	// events
    	ArrayList<Event> events = new ArrayList<Event>();
    	nodeList = el.getElementsByTagName("Events");
    	elem = (Element)nodeList.item(0);
    	nl = elem.getElementsByTagName("Event");
    	if (nl != null && nl.getLength() > 0) {
    		for (int i = 0; i < nl.getLength(); i++) {
    			Element e2 = (Element)nl.item(i);
    			Event newEvent = getEvent(e2);
    			events.add(newEvent);
    		}
    	}
    	newLevel.setEvents(events);
    	
    	return newLevel;
    }
    
    private static GameObject getGameObject(Element el)
    {
    	GameObject newGameObject = new GameObject();
    	
    	
    	return newGameObject;
    }
    
    private static Event getEvent(Element el)
    {
    	Event newEvent = new Event();
    	newEvent.setEventId(getIntValue(el, "Event_Id"));
    	
    	// Event Type
    	NodeList nl = el.getElementsByTagName("Event_Type");
    	Element el2 = (Element)nl.item(0);						// set element within event type
    	String eventType = getTextValue(el, "Event_Type");		// set event type
		newEvent.setEventType(eventType);
		if (eventType.equals("Collision")) {
			// Collision
			newEvent.setObjectId1(getIntValue(el2, "Object_Id1"));
			newEvent.setObjectId2(getIntValue(el2, "Object_Id2"));
			newEvent.setAllowOverlap(getTextValue(el2, "Allow_Overlap"));
		} else {
			if (eventType.equals("Boundary")) {
				// Boundary
				newEvent.setObjectId(getIntValue(el2, "Object_Id"));
				newEvent.setLevelId(getIntValue(el2, "Level_Id"));
				newEvent.setEdges(getTextValue(el2, "Edges"));
			} else {
				if (eventType.equals("Timer")) {
					// Timer
					newEvent.setLength(getIntValue(el2, "Length"));
					newEvent.setValue(getIntValue(el2, "Value"));
				} else {
					if (eventType.equals("Input")) {
						// Input
						newEvent.setLength(getIntValue(el2, "Length"));
						NodeList nl2 = el2.getElementsByTagName("Value");
						Element el3 = (Element)nl2.item(0);	
						newEvent.setInputValueX(getIntValue(el3, "X"));
						newEvent.setInputValueY(getIntValue(el3, "Y"));
						newEvent.setInputType(getTextValue(el2, "Type"));
					} else {
							// Step
							newEvent.setStep(getIntValue(el2, "Step"));
					}
				}
			}
		}
		
		// Actions
		ArrayList<Action> actions = new ArrayList<Action>();
    	NodeList nodeList = el.getElementsByTagName("Actions");
    	Element elem = (Element)nodeList.item(0);
    	nl = elem.getElementsByTagName("Action");
    	if (nl != null && nl.getLength() > 0) {
    		for (int i = 0; i < nl.getLength(); i++) {
    			Element e2 = (Element)nl.item(i);
    			Action newAction = getAction(e2);
    			actions.add(newAction);
    		}
    	}
    	newEvent.setActions(actions);
    	
    	return newEvent;
    }
    
    private static Action getAction(Element el)
    {
    	Action newAction = new Action();
    	newAction.setActionId(getIntValue(el, "Action_Id"));
    	NodeList nl = el.getElementsByTagName("Type");
    	Element el2 = (Element)nl.item(0);
    	
    	// Change Sprite
    	NodeList nl2 = el2.getElementsByTagName("Change_Sprite");
    	Integer objId = getIntValue(el2, "Object_Id");
    	if (objId != null) {
    		newAction.setChangeSprite(true);
    		newAction.setObjectId(getIntValue(el2, "Object_Id"));
    		newAction.setSpriteId(getTextValue(el2, "Sprite_Id"));
    	}
    	
    	// Change Score
    	nl2 = el2.getElementsByTagName("Change_Score");
    	objId = getIntValue(el2, "Object_Id");
    	if (objId != null) {
    		newAction.setChangeScore(true);
    		newAction.setObjectId(getIntValue(el2, "Object_Id"));
    		newAction.setPlayerId(getIntValue(el2, "Player_Id"));
    		newAction.setValue(getIntValue(el2, "Value"));
    		newAction.setScoreType(getTextValue(el2, "Type"));
    	}
    	
    	// Change Component
    	nl2 = el2.getElementsByTagName("Change_Component");
    	objId = getIntValue(el2, "Object_Id");
    	if (objId != null) {
    		newAction.setChangeComponent(true);
    		newAction.setObjectId(getIntValue(el2, "Object_Id"));
    		newAction.setPlayerId(getIntValue(el2, "Player_Id"));
    		newAction.setValue(getIntValue(el2, "Value"));
    		newAction.setComponent(getTextValue(el2, "Component"));
    		newAction.setComponentType(getTextValue(el2, "Type"));
    	}
    	
    	// Reflect
    	nl2 = el2.getElementsByTagName("Reflect");
    	objId = getIntValue(el2, "Object_Id");
    	if (objId != null) {
    		newAction.setReflect(true);
    		newAction.setObjectId(getIntValue(el2, "Object_Id"));
    		newAction.setSurfaceObjectId(getIntValue(el2, "Surface_Object_Id"));
    	}
    	
    	// Input
    	nl2 = el2.getElementsByTagName("Input");
    	objId = getIntValue(el2, "Object_Id");
    	if (objId != null) {
    		newAction.setInput(true);
    		newAction.setObjectId(getIntValue(el2, "Object_Id"));
    		newAction.setPlayerId(getIntValue(el2, "Player_Id"));
    		newAction.setInputType(getTextValue(el2, "Type"));
    	}
    	
    	return newAction;
    }
/*    
	private static Level getLevel(Element el)
	{
		//for each <Level> element get text values of
		//levelID, levelName etc. and store objects/events
		String levelID = getTextValue(el, "Level_ID");
		String levelNo = getTextValue(el, "Level_no");
                    //System.out.println("ID = "+levelID+", Num = "+levelNo); // Working.
                
		ArrayList<LevelEvent> events = new ArrayList<LevelEvent>(); // to be changed to event type
                ArrayList<LevelObject> objects = new ArrayList<LevelObject>(); // to be changed to object type
                LevelEvent tempEve = new LevelEvent();
                LevelObject tempObj = new LevelObject(); 
                 
                //get list of objects
		NodeList nl = el.getElementsByTagName("Object");
		if(nl != null && nl.getLength() > 0)
		{
			for(int a = 0; a < nl.getLength(); a++)
			{
				//get element
				Element elEl = (Element)nl.item(a);
                                
				//get the object id
				int tempObjID = getIntValue(elEl, "Object_id");
                                // store it in tempObj
                                tempObj.setId(tempObjID);
                                //System.out.println("object ID = "+tempObjID);
                                
                                //get the object name
                                String tempObjName = getTextValue(elEl, "Object_name");
                                // store it in tempObj
                                tempObj.setName(tempObjName);
                                //System.out.println("object name = "+tempObjName);
                                
                                //get the object type
                                String tempObjType = getTextValue(elEl, "Object_type");
                                // store it in tempObj
                                tempObj.setObjectType(tempObjType);
                                //System.out.println("object type = "+tempObjType);
                                
                                //get the object shape
                                String tempObjShape = getTextValue(elEl, "Object_shape");
                                // store it in tempObj
                                tempObj.setShape(tempObjShape);
                                //System.out.println("object shape = "+tempObjShape);
                                
                                //is the object solid?
                                String tempObjSolid = getTextValue(elEl, "Object_solid");
                                // store it in tempObj
                                tempObj.setSolid(tempObjSolid);
                                //System.out.println("object solid = "+tempObjSolid);
                                
                                //is the object visible?
                                String tempObjVisible = getTextValue(elEl, "Object_visible");
                                // store it in tempObj
                                tempObj.setVisible(tempObjVisible);
                                //System.out.println("object visible = "+tempObjVisible);
                                
                                //get the object colour
                                String tempObjColour = getTextValue(elEl, "Object_colour");
                                // store it in tempObj
                                tempObj.setColour(tempObjColour);
                                //System.out.println("object colour = "+tempObjColour);
                                
                                //get the object init speed
                                int tempObjInitSpeed = getIntValue(elEl, "Initial_speed");
                                // store it in tempObj
                                tempObj.setInitSpeed(tempObjInitSpeed);
                                //System.out.println("object initial speed = "+tempObjInitSpeed);
                                
                                //get the object max speed
                                int tempObjMaxSpeed = getIntValue(elEl, "Max_speed");
                                // store it in tempObj
                                tempObj.setMaxSpeed(tempObjMaxSpeed);
                                //System.out.println("object maximum speed = "+tempObjMaxSpeed);
                                
                                //get the object position
                                int tempObjPosition = getIntValue(elEl, "Position");
                                // store it in tempObj
                                tempObj.setPosition(tempObjPosition);
                                //System.out.println("object position = "+tempObjPosition);
                                
                                //get the object direction
                                int tempObjDirection = getIntValue(elEl, "Direction");
                                // store it in tempObj
                                tempObj.setDirection(tempObjDirection);
                                //System.out.println("object direction = "+tempObjDirection);
                                
                                //GET SPRITE
                                
                                //get the object sprite speed
                                int tempSprSpeed = getIntValue(elEl, "Sprite_speed");
                                // store it in tempObj
                                tempObj.setSpriteSpeed(tempSprSpeed);
                                //System.out.println("Sprite speed = "+tempSprSpeed);
                                
                                NodeList images = elEl.getElementsByTagName("Sprite");
                                if(nl != null && nl.getLength() > 0)
                                {
                                        for(int b = 0; b < images.getLength(); b++)
                                        {                                       
                                            //get element
                                            Element elImage = (Element)images.item(b);
                                            
                                            //get the sprite image
                                            String tempSprite = getTextValue(elImage, "Image");
                                            // store it in tempObj
                                            tempObj.addSprite(tempSprite);
                                            //System.out.println("Sprite "+(b+1)+" = "+tempSprite);
                                        }
                                }
                                
				//add to object list
				objects.add(tempObj);
			}
		}
                
                
		//get list of events
		nl = el.getElementsByTagName("Event");
		if(nl != null && nl.getLength() > 0)
		{
			for(int a = 0; a < nl.getLength(); a++)
			{
				//get element
				Element elEl = (Element)nl.item(a);
                                
				//get the event id
				int tempEveID = getIntValue(elEl, "Event_id");
                                // store it in tempObj
                                tempEve.setEventId(tempEveID);
                                //System.out.println("event ID = "+tempEveID);
                                
                                //get the event type
				String tempEveType = getTextValue(elEl, "Event_type");
                                // store it in tempObj
                                tempEve.setEventType(tempEveType);
                                //System.out.println("event type = "+tempEveType);
                                   
                                // if it is a collision event, 
                                if (tempEveType.equalsIgnoreCase("Collision"))
                                {
                                    int tempOb = getIntValue(elEl, "Object_id1");
                                    //System.out.println("Object 1 = "+tempOb);
                                    //store tempOb
                                    tempEve.setObjectId1(tempOb);
                                    
                                    tempOb = getIntValue(elEl, "Object_id2");
                                    //System.out.println("Object 2 = "+tempOb);
                                    //store tempOb
                                    tempEve.setObjectId2(tempOb);
                                }    
                                else if (tempEveType.equalsIgnoreCase("Time")) // if it is a time event
                                {
                                    String tempEveRule = getTextValue(elEl, "Rule");
                                    // store it in tempObj
                                    tempEve.setRule(tempEveRule);
                                    //System.out.println("Rule = "+tempEveRule);
                                }
                                
                                                               
                                int tempEveTrig = getIntValue(elEl, "Trigger_action_id");
                                // store it in tempObj
                                tempEve.setTriggerActionId(tempEveTrig);
                                //System.out.println("Trigger Action = "+tempEveTrig);
                                
				//add temp event to events
				events.add(tempEve);
			}
		}
		Level newLevel = new Level(levelID, levelNo);
                for(int a = 0; a < objects.size(); a++)
		{
			newLevel.addObject(objects.get(a));
		}
		for(int a = 0; a < events.size(); a++)
		{
			newLevel.addEvent(events.get(a));
		}
		return newLevel;
	}
*/  
        
    private static String getTextValue(Element doc, String tag)
	{
		String value = "";
		NodeList nl = doc.getElementsByTagName(tag);
		if(nl != null && nl.getLength() > 0)
		{
			Element el = (Element)nl.item(0);
			if(el != null && el.getFirstChild() != null)
				value = el.getFirstChild().getNodeValue();
		}
		return value;
	}

	private static int getIntValue(Element doc, String tag)
	{
		int value = 0;
		NodeList nl = doc.getElementsByTagName(tag);
		if(nl != null && nl.getLength() > 0)
		{
			Element el = (Element)nl.item(0);
			if(el != null && el.getFirstChild() != null)
				value = Integer.parseInt(el.getFirstChild().getNodeValue());
		}
		return value;
	}
}
