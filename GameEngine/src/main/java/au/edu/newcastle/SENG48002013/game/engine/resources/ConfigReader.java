package au.edu.newcastle.SENG48002013.game.engine.resources;

/**
 *
 * @author Peter 
 */
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;

public class ConfigReader {

	//the following gets changed as soon as context is initialised
    public static String BASEDIR = "/home/clint/Projects/git/SENG4800/config";
    public static String LEVELS = "levels.xml";
    public static String GAME = "game.xml";

	/**
	 * 
	 * @return 
	 */
    public static Element readGame() {
        Element docEle = loadXmlFile(GAME);
        return docEle;
    }

	/**
	 *
	 * @return  
	 */
    public static NodeList readLevels() {
        Element docEle = loadXmlFile(LEVELS);
        // get a list of the levels
        NodeList levelNodes = docEle.getElementsByTagName("LEVEL");
        return levelNodes;
    }

    private static Element loadXmlFile(String loc) {
        Element docEle = null;
        //DOM Object
        Document dom;

        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // parse using the builder to get the DOM mapping of the
            // XML file
            String location = (new File(BASEDIR, loc)).getPath();
            dom = db.parse(location);

            docEle = dom.getDocumentElement();

        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return docEle;
    }
}
