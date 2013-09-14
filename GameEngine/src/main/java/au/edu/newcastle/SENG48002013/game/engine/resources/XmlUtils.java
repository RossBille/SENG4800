package au.edu.newcastle.SENG48002013.game.engine.resources;

import javax.vecmath.Vector2d;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XmlUtils {
	public static Element getElement(Element doc, String tag)
	{
		Element element = null;
		NodeList nl = doc.getElementsByTagName(tag);
		if(nl != null && nl.getLength() > 0)
		{
			element = (Element)nl.item(0);
		}
		return element;
	}
	
	public static double getNumericValue(Element doc, String tag)
	{
		double value = 0;
		NodeList nl = doc.getElementsByTagName(tag);
		if(nl != null && nl.getLength() > 0)
		{
			Element el = (Element)nl.item(0);
			if(el != null && el.getFirstChild() != null)
				value = Double.parseDouble(el.getFirstChild().getNodeValue());
		}
		return value;
	}
	public static String getTextValue(Element doc, String tag)
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
	public static Vector2d getVectorValue(Element doc, String tag)
    {
    	Vector2d vector = new Vector2d();
    	NodeList nl = doc.getElementsByTagName(tag);
		if(nl != null && nl.getLength() > 0)
		{
			Element el = (Element)nl.item(0);
			if(el != null && el.getElementsByTagName("*").getLength() >= 2)
			{
				NodeList children = el.getElementsByTagName("*");
				double value1;
				double value2;
				value1 = Double.parseDouble(((Element)children.item(0)).getTextContent());
				value2 = Double.parseDouble(((Element)children.item(1)).getTextContent());
				vector.set(value1, value2);
			}
		}
		return vector;
    }
}
