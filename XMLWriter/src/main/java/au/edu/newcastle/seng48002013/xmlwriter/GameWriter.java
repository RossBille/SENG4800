/*
 * Game Writer servlet to process info from GUI and build Game.xml
 */
package au.edu.newcastle.seng48002013.xmlwriter;


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

/**
 *
 * @author Bracks
 */
public class GameWriter extends HttpServlet {
    
    //XML File Name
        private String loc = "Game";
    
    
    //Form data strings
	private String gameName;
	private String width;
	private String height;
	private String startLevel;
	private String minPlayers;
	private String maxPlayers;
        
        
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
	doPost(request, response);	
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        gameName = request.getParameter("gameName");
	width = request.getParameter("width");
	height = request.getParameter("height");
	startLevel = request.getParameter("startLevel");
	minPlayers = request.getParameter("minPlayers");
        maxPlayers = request.getParameter("maxPlayers");
        
        
        Document dom;
	Element e = null;
	Element e1 = null;
	Element e2 = null;

	    // instance of a DocumentBuilderFactory
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    try
            {
	        // use factory to get an instance of document builder
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        // create instance of DOM
	        dom = db.newDocument();

	        // create the root element
	        Element rootEle = dom.createElement("Game");
		dom.appendChild(rootEle);
                
                // create the setup element
                e = dom.createElement("Setup");
		rootEle.appendChild(e);        
                        
                    e1 = dom.createElement("Game_name");
                    e1.appendChild(dom.createTextNode(gameName));
                    e.appendChild(e1);        

                    e1 = dom.createElement("Canvas_size");
                    e.appendChild(e1); 
                    
                        e2 = dom.createElement("Width");
                        e2.appendChild(dom.createTextNode(width));
                        e1.appendChild(e2);
                        
                        e2 = dom.createElement("Height");
                        e2.appendChild(dom.createTextNode(height));
                        e1.appendChild(e2);
                        
                    e1 = dom.createElement("Starting_level");
                    e1.appendChild(dom.createTextNode(startLevel));
                    e.appendChild(e1); 
                        
                    e1 = dom.createElement("Players");
                    e.appendChild(e1); 
                    
                        e2 = dom.createElement("Min");
                        e2.appendChild(dom.createTextNode(minPlayers));
                        e1.appendChild(e2);
                        
                        e2 = dom.createElement("Max");
                        e2.appendChild(dom.createTextNode(maxPlayers));
                        e1.appendChild(e2);
                
                        
                        
                        
                        // CREATE XML FILE
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
}
