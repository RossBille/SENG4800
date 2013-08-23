/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.seng48002013.xmlparser;
import java.io.IOException;
import java.util.*;
import java.lang.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;
/**
 *
 * @author Josh Brackenbury
 */
public class Reader {
    
    // levels storage object
    public static ArrayList<Level> levels = new ArrayList<Level>();
    public static ArrayList<Action> actions = new ArrayList<Action>();
    public static int numLevels = 0;
    public static GameSetup gameSetup = new GameSetup();
    
    public static void runReader()
    {
        String fileName;        
        fileName = "Levels";
        parseLevelsXML(fileName);
        fileName = "Actions";
        parseActionsXML(fileName);  
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
    
    public static ArrayList<Action> getActions()
    {
        return actions;
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
		{}
		catch (SAXException se)
		{}
		catch (IOException ioe)
		{}   
    }
    
//ADDED
	private static GameSetup getSetup(Element el)
	{
		GameSetup newSetup = new GameSetup();
		
		// Game Name
		String gameName = getTextValue(el, "Game_name");
		newSetup.setGameName(gameName);
		
		// Canvas Size
		NodeList nl = el.getElementsByTagName("Canvas_size");
		Element el2 = (Element)nl.item(0);
		int size_x = getIntValue(el2, "Width");
		int size_y = getIntValue(el2, "Height");
		newSetup.setCanvasSizeX(size_x);
		newSetup.setCanvasSizeY(size_y);
		
		// Border
		nl = el.getElementsByTagName("Border");
		el2 = (Element)nl.item(0);
		String state = getTextValue(el2, "State");
		String colour = getTextValue(el2, "Colour");
		int size = getIntValue(el2, "Size");
		newSetup.setBorderState(state);
		newSetup.setBorderColour(colour);
		newSetup.setBorderSize(size);
		
		// Players
		nl = el.getElementsByTagName("Players");
		el2 = (Element)nl.item(0);
		int min = getIntValue(el2, "Min");
		int max = getIntValue(el2, "Max");
		newSetup.setMinPlayers(min);
		newSetup.setMaxPlayers(max);
		
                //System.out.println("GAME SETUP: "+gameName+", Canvas = "+size_x+" by "+size_y+", Border = "+state+","+colour+","+size+", Players = "+min+"(min)"+max+"(max)");
                
		return newSetup;
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
            
            // get number of levels
            String levs = getTextValue(docEle, "No_of_levels");
            //System.out.println("Total Levels = "+levs);
//            numLevels = Integer.parseInt(levs);

            // get a list of the levels
            NodeList nl = docEle.getElementsByTagName("Level");
            
            // for each level, build info
            if(nl != null && nl.getLength() > 0)
            {
		for(int a = 0; a < nl.getLength(); a++)
		{
                        //System.out.println("LEVEL "+(a+1));
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
		{}
		catch (SAXException se)
		{}
		catch (IOException ioe)
		{}
    }
    
    private static void parseActionsXML(String loc) //Reads in the blackboard users
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

            // get a list of the actions
            NodeList nl = docEle.getElementsByTagName("Action");
            
            // for each level, build info
            if(nl != null && nl.getLength() > 0)
            {
		for(int a = 0; a < nl.getLength(); a++)
		{
                        //System.out.println("ACTION "+(a+1));
			//get the element
                	Element el = (Element)nl.item(a);
			//get the object
			Action e = getAction(el);
        		//add to users
			actions.add(e);
		}
            }

        }
		catch (ParserConfigurationException pce)
		{}
		catch (SAXException se)
		{}
		catch (IOException ioe)
		{}
    }

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
    
        
        private static Action getAction(Element el)
	{
            //for each <Action> element get text values of
		//actionID, Operation etc. and store commands
                Action newAction = new Action();
		int actionID = getIntValue(el, "Action_id");
                //System.out.println("ID = "+actionID);
                newAction.setActionId(actionID);
		String Operation = getTextValue(el, "Operation");
                //System.out.println("Operation = "+Operation);
                newAction.setActionId(actionID);
                
                String tempCommand = "";
                
                NodeList nl = el.getElementsByTagName("Command");
		if(nl != null && nl.getLength() > 0)
		{
			for(int a = 0; a < nl.getLength(); a++)
			{
				//get element
				Element elEl = (Element)nl.item(a);

				//get the command
				String Create = getTextValue(elEl, "Create");
                                String Change = getTextValue(elEl, "Change");
                                String Colour = getTextValue(elEl, "Colour");
                                String Destroy = getTextValue(elEl, "Destroy");
                                
                                if (!Create.equals(""))
                                    tempCommand = "CREATE "+Create;
                                else if (!Colour.equals(""))
                                    tempCommand = "COLOUR "+Colour;
                                else if (!Change.equals(""))
                                    tempCommand = "CHANGE "+Change;
                                else if (!Destroy.equals(""))
                                    tempCommand = "DESTROY "+Destroy;
                                else
                                    break;
                                
                                //System.out.println("Command = "+tempCommand);

				newAction.addCommand(tempCommand);
			}
		}
                return newAction;
        }
        
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
