package cs.pathfinder.tabletop.game.tyler.pathfindercs;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.LinkedList;

import cs.pathfinder.tabletop.game.tyler.pathfindercs.utils.CharacterInfo;
import cs.pathfinder.tabletop.game.tyler.pathfindercs.utils.Stats;
import cs.pathfinder.tabletop.game.tyler.pathfindercs.utils.XML_Helper;

/**
 * Created by Tyler on 4/22/2015.
 *
 * CharacterHelper class is where the list of characters
 * are stored. Eventually, it will parse XML files where
 * the characters are sored in onCreate(). Since we can't
 * create an instance of the application, all of the info
 * is static, so that the activities can access it without
 * having to store the instance. By extending Application,
 * we insure that there will be an instance, as long as
 * the app is running.
 */
public class CharacterHelper extends Application {

    private static LinkedList<CharacterInfo> characters;
    private static XML_Helper xml_helper;
    private static Context context;

    public static void addCharacter(CharacterInfo newCharacter) {
        characters.add(newCharacter);
        xml_helper.saveCharacter(newCharacter, characters.indexOf(newCharacter), context);
        System.out.println("#################   addCharacter: " + newCharacter + " index: " + characters.indexOf(newCharacter));
    }

    public static CharacterInfo getCharacter(int loc) {
        return characters.get(loc);
    }

    public static LinkedList<CharacterInfo> getCharacters() {
        return characters;
    }

    public static void deleteCharacter(int character) {
        xml_helper.deleteCharacter(character, context);
        characters.remove(character);
    }

    public static String[] getStringArray() {
        String[] strings = new String[characters.size()];
        for (int i = 0; i < characters.size(); i++) {
            strings[i] = characters.get(i).toString();
        }

        return strings;
    }

    public static boolean isEmpty() {
        return characters.size() == 0;
    }

    public static int getCharacterCount() {
        return characters.size();
    }

    @Override
    public void onCreate() {
        if (characters == null) {
            xml_helper = XML_Helper.getInstance();
            context = getApplicationContext();
            characters = getSavedCharacters();
        }
        super.onCreate();
    }

    public static void updateCharacter(Stats stat, int character) {
        xml_helper.updateCharacter(characters.get(character), stat, character, context);
    }

    private LinkedList<CharacterInfo> getSavedCharacters() {
        LinkedList<CharacterInfo> list = new LinkedList<CharacterInfo>();
        ArrayList<ArrayList<String>> saved_characters = xml_helper.getSavedCharacters(context);
        if (saved_characters == null || saved_characters.isEmpty()) {
            return list;
        }
        for (int i = 0; i < saved_characters.size(); i++) {
            ArrayList<String> character_vals = saved_characters.get(i);
            CharacterInfo character = new CharacterInfo();

            character.set(Stats.NAME, character_vals.get(0));

            // String[] abilities = character_vals.get(1).split(":");

            character.set(Stats.RACE, character_vals.get(2));
            character.set(Stats.CLASS, character_vals.get(3));

            // String[] level = character_vals.get(4).split(":");
            //character.set(Stats.NAME, character_vals.get(4));
            character.set(Stats.GENDER, character_vals.get(5));

            character.set(Stats.AGE, character_vals.get(6));
            character.set(Stats.ALLI, character_vals.get(7));
            character.set(Stats.HEIGHT, character_vals.get(8));
            character.set(Stats.WEIGHT, character_vals.get(9));
            character.set(Stats.SIZE, character_vals.get(10));

            //String[] hp = character_vals.get(11).split(":");
            //character.set(Stats.GENDER, character_vals.get(5));


            list.add(character);
        }

        return list;
    }

    private void saveCharacters() {

    }
}
