/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.seng48002013.xmlwriter;

import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;

/**
 *
 * @author Bracks
 */
public class tester {
    
    
    //XML File Name
        private String loc = "Game";
    
    
    //Form data strings
	private String gameName;
	private String width;
	private String height;
	private String state;
        private String colour;
	private String size;
	private String minPlayers;
	private String maxPlayers;
        
        public void main(String[] args)
        {
            gameName = "Pong";
            width = "360";
            height = "360";
            state = "solid";
            colour = "red";
            size = "1";
            minPlayers = "1";
            maxPlayers = "10";


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

                        e1 = dom.createElement("Canvas");
                        e.appendChild(e1); 

                            e2 = dom.createElement("Width");
                            e2.appendChild(dom.createTextNode(width));
                            e1.appendChild(e2);

                            e2 = dom.createElement("Height");
                            e2.appendChild(dom.createTextNode(height));
                            e1.appendChild(e2);

                        e1 = dom.createElement("Border");
                        e.appendChild(e1); 

                            e2 = dom.createElement("State");
                            e2.appendChild(dom.createTextNode(state));
                            e1.appendChild(e2);

                            e2 = dom.createElement("Colour");
                            e2.appendChild(dom.createTextNode(colour));
                            e1.appendChild(e2);

                            e2 = dom.createElement("Size");
                            e2.appendChild(dom.createTextNode(size));
                            e1.appendChild(e2);

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
