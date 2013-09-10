/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.seng48002013.xmlwriter;

import java.io.IOException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;

import java.io.*;
import java.io.File;
import javax.servlet.*;
import javax.servlet.http.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.w3c.dom.NodeList;

/**
 *
 * @author Bracks
 */
@WebServlet(name = "LevelWriter", urlPatterns = {"/LevelWriter"})
public class LevelWriter extends HttpServlet {

    
    boolean readSuccess;
    public static ArrayList<Level> levels = new ArrayList<Level>();
    public static String loc = "Levels";
    
    // Param input variables
    public static String levs;
    public static String levNo;
    public static String levId;
    public static String endEvent;
    public static String nextLev;
    // Objects...
    // Events...
    
    public static ArrayList<LevelEvent> events = new ArrayList<LevelEvent>();     // temp storage of event list   
    public static ArrayList<LevelObject> objects = new ArrayList<LevelObject>(); // temp storage of object list 
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        readSuccess = readLevels();
        
        if (readSuccess)
        {
            // get new level params
            if(request.getParameter("numLevs")!= null)
                levs = request.getParameter("numLevs");
            levId = request.getParameter("levId");
            levNo = request.getParameter("levNo");
            endEvent = request.getParameter("endEvent");
            nextLev = request.getParameter("nextLev");
            
            // write the new Levels.xml
            writeLevels();
        }
        else
        {
            System.out.println("Read error");
        }
        
        // REDIRECT BACK TO GUI CREATOR - level page
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() 
    {
        return "Level Writer Servlet, produces Levels.xml";
    }// </editor-fold>
    
    
    // read in (i.e. Parse) Levels.xml
    private boolean readLevels()
    {
        levels = new ArrayList<Level>();
        Document dom;
        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try 
        {
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // parse using the builder to get the DOM mapping of the
            // XML file
            String location = loc + ".xml";
            dom = db.parse(location);

            Element docEle = dom.getDocumentElement();
            
            // get number of levels
            levs = getTextValue(docEle, "No_of_levels");

            NodeList nl = docEle.getElementsByTagName("Level");
            if(nl != null && nl.getLength() > 0)
            {
            	for(int a = 0; a < nl.getLength(); a++)
		{
			//get the element
			Element el = (Element)nl.item(a);
			//get the user object
			Level e = getLevel(el);
			//add to users
			levels.add(e);
		}
            }
            return true;

        }
        catch (ParserConfigurationException pce)
        {}
	catch (SAXException se)
	{}
	catch (IOException ioe)
	{}

        return false;
    }
    
    private void writeLevels()
    {
        ArrayList<LevelEvent> tempEvents = new ArrayList<LevelEvent>(); // 
        ArrayList<LevelObject> tempObjects = new ArrayList<LevelObject>(); // 
        LevelEvent tempEve = new LevelEvent();
        LevelObject tempObj = new LevelObject(); 
                                
        Document dom;
	Element e = null;
	Element e1 = null;
        Element e2 = null;
        Element e3 = null;

	// instance of a DocumentBuilderFactory
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try
	{
	        // use factory to get an instance of document builder
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        // create instance of DOM
	        dom = db.newDocument();

	        // create the root element
	        Element rootEle = dom.createElement("Levels");
                dom.appendChild(rootEle);
                
                // LOOP THROUGH EXISTING CREATED LEVELS AND WRITE TO FILE
                
                e = dom.createElement("No_of_levels"); // num levels tag
                e.appendChild(dom.createTextNode(levs));
		rootEle.appendChild(e);
                
                    //load all current levels
                    if(levels != null && levels.size() > 0)
                    {
			for(int a = 0; a < levels.size(); a++) // for each existing level
			{
                                // create level tag
				e = dom.createElement("Level");
				rootEle.appendChild(e);
                                
                                    // create ID tag
                                    e1 = dom.createElement("Level_ID");
                                    e1.appendChild(dom.createTextNode(""+levels.get(a).getId())); // get the level ID and convert to string
                                    e.appendChild(e1); // append to Level

                                    // create Level number tag
                                    e1 = dom.createElement("Level_no");
                                    e1.appendChild(dom.createTextNode(""+levels.get(a).getLevelNo())); // get the level num and convert to string
                                    e.appendChild(e1); // append to level
                                    
                                    // create Level end tag
                                    e1 = dom.createElement("Level_end");
                                    e.appendChild(e1); // append to level
                                    
                                        // create level end event number tag
                                        e2 = dom.createElement("On_event");
                                        e2.appendChild(dom.createTextNode(""+levels.get(a).getEndEventId())); // get the level end event id and convert to string
                                        e1.appendChild(e2); // append to level end
                                        
                                        // create level next level tag
                                        e2 = dom.createElement("Next_level_id");
                                        e2.appendChild(dom.createTextNode(""+levels.get(a).getNextLevelId())); // get the next level and convert to string
                                        e1.appendChild(e2); // append to level end
                                        
                                    // create Objects tag
                                    e1 = dom.createElement("Objects");
                                    e.appendChild(e1); // append to level 
                                    
                                    // DEAL WITH WRITING OBJECTS   
                                        tempObjects = levels.get(a).getLevelObjects(); // get the Levels list of objects
                                        for(int o=0; o<tempObjects.size(); o++)
                                        {
                                            // create object id tag
                                            e2 = dom.createElement("Object");
                                            e1.appendChild(e2); // append to Objects tag
                                            
                                                // create object id tag
                                                e3 = dom.createElement("Object_id");
                                                e3.appendChild(dom.createTextNode(""+tempObjects.get(o).getId())); // get the ID and convert to string
                                                e2.appendChild(e3); // append to Object tag
                                                
                                                // create object name tag
                                                e3 = dom.createElement("Object_name");
                                                e3.appendChild(dom.createTextNode(""+tempObjects.get(o).getName())); // get the Name and convert to string
                                                e2.appendChild(e3); // append to Object tag
                                                
                                                // create object type tag
                                                e3 = dom.createElement("Object_type");
                                                e3.appendChild(dom.createTextNode(""+tempObjects.get(o).getObjectType())); // get the type and convert to string
                                                e2.appendChild(e3); // append to Object tag
                                                
                                                // create object shape tag
                                                e3 = dom.createElement("Object_shape");
                                                e3.appendChild(dom.createTextNode(""+tempObjects.get(o).getShape())); // get the shape and convert to string
                                                e2.appendChild(e3); // append to Object tag
                                                
                                                // create object solid tag
                                                e3 = dom.createElement("Object_solid");
                                                e3.appendChild(dom.createTextNode(""+tempObjects.get(o).isSolid())); // get the boolean solid and convert to string
                                                e2.appendChild(e3); // append to Object tag
                                                
                                                // create object visible tag
                                                e3 = dom.createElement("Object_visible");
                                                e3.appendChild(dom.createTextNode(""+tempObjects.get(o).isVisible())); // get the boolean visible and convert to string
                                                e2.appendChild(e3); // append to Object tag
                                                
                                                // create object colour tag
                                                e3 = dom.createElement("Object_colour");
                                                e3.appendChild(dom.createTextNode(""+tempObjects.get(o).getColour())); // get the colour and convert to string
                                                e2.appendChild(e3); // append to Object tag
                                                
                                                // create initial speed tag
                                                e3 = dom.createElement("Initial_speed");
                                                e3.appendChild(dom.createTextNode(""+tempObjects.get(o).getInitSpeed())); // get the init speed and convert to string
                                                e2.appendChild(e3); // append to Object tag
                                                
                                                // create max speed tag
                                                e3 = dom.createElement("Max_speed");
                                                e3.appendChild(dom.createTextNode(""+tempObjects.get(o).getMaxSpeed())); // get the Max speed and convert to string
                                                e2.appendChild(e3); // append to Object tag
                                                
                                                // create position tag
                                                e3 = dom.createElement("Position");
                                                e3.appendChild(dom.createTextNode(""+tempObjects.get(o).getPosition())); // get the position and convert to string
                                                e2.appendChild(e3); // append to Object tag
                                                
                                                // create direction tag
                                                e3 = dom.createElement("Direction");
                                                e3.appendChild(dom.createTextNode(""+tempObjects.get(o).getDirection())); // get the direction and convert to string
                                                e2.appendChild(e3); // append to Object tag
                                                
                                                // create sprite speed tag
                                                e3 = dom.createElement("Sprite_speed");
                                                e3.appendChild(dom.createTextNode(""+tempObjects.get(o).getSpriteSpeed())); // get the Sprite speed and convert to string
                                                e2.appendChild(e3); // append to Object tag
                                                
                                                // DEAL WITH SPRITES
                                        }
                        
                                    
                                    // create Objects tag
                                    e1 = dom.createElement("Events");
                                    e.appendChild(e1); // append to level 
                                    
                                    
                                // DEAL WITH WRITING EVENTS
                                
                                
                                
                        }
                    }
                
                // WRITE NEW LEVEL TO FILE
                
                // create new level tag
		e = dom.createElement("Level");
		rootEle.appendChild(e);
                
                    // create ID tag
                    e1 = dom.createElement("Level_ID");
                    e1.appendChild(dom.createTextNode(""+levId)); // get the level ID and convert to string
                    e.appendChild(e1); // append to Level

                    // create Level number tag
                    e1 = dom.createElement("Level_no");
                    e1.appendChild(dom.createTextNode(""+levNo)); // get the level num and convert to string
                    e.appendChild(e1); // append to level
                                    
                    // create Level end tag
                    e1 = dom.createElement("Level_end");
                    e.appendChild(e1); // append to level
                                    
                        // create level end event number tag
                        e2 = dom.createElement("On_event");
                        e2.appendChild(dom.createTextNode(""+endEvent)); // get the level end event id and convert to string
                        e1.appendChild(e2); // append to level end
                                        
                        // create level next level tag
                        e2 = dom.createElement("Next_level_id");
                        e2.appendChild(dom.createTextNode(""+nextLev)); // get the next level and convert to string
                        e1.appendChild(e2); // append to level end
                                        
                    // create Objects tag
                    e1 = dom.createElement("Objects");
                    e.appendChild(e1); // append to level
                    
                    //etc...
                
                
			
                // CREATE FILE
                        Transformer tr = TransformerFactory.newInstance().newTransformer();
			tr.setOutputProperty(OutputKeys.INDENT, "yes");
			tr.setOutputProperty(OutputKeys.METHOD, "xml");
			tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			String dtd = loc + ".dtd";
			tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, dtd);
			tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			// send DOM to file
			String location = loc + ".xml";
			FileOutputStream file = new FileOutputStream(location);

			StreamResult sr = new StreamResult(file);

			DOMSource ds = new DOMSource(dom);

			tr.transform(ds, sr);
			file.close();
		}
		catch (TransformerException te)
		{}
		catch (ParserConfigurationException pce)
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
                
		ArrayList<LevelEvent> events = new ArrayList<LevelEvent>(); // 
                ArrayList<LevelObject> objects = new ArrayList<LevelObject>(); // 
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
