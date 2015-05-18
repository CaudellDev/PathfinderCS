package cs.pathfinder.tabletop.game.tyler.pathfindercs.utils;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import cs.pathfinder.tabletop.game.tyler.pathfindercs.utils.CharacterInfo;

/**
 * Created by Tyler on 4/15/2015.
 */
public class XML_Helper {

    // Singular class
    private XML_Helper() {};

    public static void updateCharacter(CharacterInfo character) {

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Root elements.
            Document doc = docBuilder.newDocument();

        } catch (ParserConfigurationException pce) {

        }
    }

    public static void deleteCharacter() {

    }

    public static CharacterInfo getCharacter() {

        return null;
    }
}
