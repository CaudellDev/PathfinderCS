package cs.pathfinder.tabletop.game.tyler.pathfindercs;

import android.app.Application;

import java.util.LinkedList;

import cs.pathfinder.tabletop.game.tyler.pathfindercs.utils.CharacterInfo;

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

    public static void addCharacter(CharacterInfo newCharacter) {
        characters.add(newCharacter);
    }

    public static CharacterInfo getCharacter(int loc) {
        return characters.get(loc);
    }

    public static LinkedList<CharacterInfo> getCharacters() {
        return characters;
    }

    public static void removeCharacter(int character) {
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
            characters = new LinkedList<CharacterInfo>();
        }
        super.onCreate();
    }
}
