package parsing;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import util.Logger;

/**
 * Parses JSoup Document from oscar into ClassData Object
 * @author Matthew O'Shaughnessy
 *
 */
public class Parser
{

    public Parser() {
    	
    }

    /**
     * Parses a jsoup Document from the oscar URL into a ClassData Object
     * @param doc - jsoup Document from the oscar URL for the class
     * @return - ClassData object representing class info and registration numbers
     */
    public static ClassData parseData(Document doc) {
        ClassData classData = new ClassData();
        Element el = doc.select("[summary=This layout table is used to present the seating numbers.]").first();
        if (el == null) {
        	Logger.error("Null document retrieved. Did you enter a valid CRN?");
        	System.exit(1);
        }
        String text = el.text().substring(60);
        classData.setCapacity(Integer.parseInt(text.substring(0, text.indexOf(" "))));
        text = text.substring(text.indexOf(" "));
        text = text.substring(1);
        classData.setActual(Integer.parseInt(text.substring(0, text.indexOf(" "))));
        text = text.substring(text.indexOf(" "));
        text = text.substring(1);
        classData.setRemaining(Integer.parseInt(text.substring(0, text.indexOf(" "))));
        return classData;
    }
}
