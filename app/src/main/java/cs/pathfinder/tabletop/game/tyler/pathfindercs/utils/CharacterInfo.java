package cs.pathfinder.tabletop.game.tyler.pathfindercs.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import cs.pathfinder.tabletop.game.tyler.pathfindercs.utils.Dice;

/**
 * Created by Tyler on 4/14/2015.
 */
public class CharacterInfo {

    private int id;

    private String name;
    private String user;
    // Fluff
    private String race;
    private String cClass;
    private String alliance;
    private int age;
    private int height;
    private String deity;
    private String size;
    private String gender;
    private int weight;
    private String hair;
    private String eyes;

    // Ability Score
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    private int experience;
    private int nextLevel;
    private int currLevel;

    private int CMD;
    private int CMB;
    private int initiative;
    private int hitPoints;

    // Saves
    private int fortitude;
    private int reflex;
    private int will;

    // Speed
    private int base;
    private int armor;
    private int fly;
    private int swim;
    private int climb;
    private int burrow;

    private int AC;
    private int touch;
    private int flatFooted;
    private int spellResist;

    private int baseAttBon;

    private LinkedList<Item> inventory;
    private Map<String, Skill> skills;

    private Map<Skill, Integer> miscMods;

    public final static String[] detailTitles =
            {"Name", "Race", "Class", "Level",
             "Experience", "Gender", "Age",
             "Alliance", "Height", "Weight", "Size"};

    public enum ability {
        STR, DEX,
        CON, INT,
        WIS, CHA
    }

    public enum Skill {

    }

    public CharacterInfo() {
        init();
    }

    public CharacterInfo(String name) {
        init();
        this.name = name;
    }

    private void init() {
        name = "";
        currLevel = 1;
        race = "";
        cClass = "";
        gender = "";
        miscMods = new HashMap<Skill, Integer>();
    }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setRace(String race) { this.race = race; }
    public void setClass(String cClass) { this.cClass = cClass; }
    public void setGender(String gender) { this.gender = gender; }
    public void setExperience(int experience) { this.experience = experience; }
    public void addExperience(int gained) { experience += gained; }

    public int getId() { return id; }
    public String getName() { return name; }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAlliance() {
        return alliance;
    }

    public void setAlliance(String alliance) {
        this.alliance = alliance;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getRace() { return race; }
    public String getcClass() { return cClass; }
    public String getGender() { return gender; }
    public int getCurrLevel() { return currLevel; }
    public int getExperience() { return experience; }

    public int[] getAbilities() {
        int[] stats = {strength, dexterity, constitution,
                        intelligence, wisdom, charisma};
        return stats;
    }

    public int[] getAbilityMods() {
        int[] stats = {(int) Math.floor((strength - 10) / 2), (int) Math.floor((dexterity - 10) / 2),
                (int) Math.floor((constitution - 10) / 2), (int) Math.floor((intelligence - 10) / 2),
                (int) Math.floor((wisdom - 10) / 2), (int) Math.floor((charisma - 10) / 2)};
        return stats;
    }

    public void rollForStats(String method) {
        if (method.equals("manual")) {
            // TODO: Create manual dialog
        } else {
            strength = Dice.rollStat(method);
            dexterity = Dice.rollStat(method);
            constitution = Dice.rollStat(method);
            intelligence = Dice.rollStat(method);
            wisdom = Dice.rollStat(method);
            charisma = Dice.rollStat(method);
        }
    }

    public int getMiscMod(Skill skill) {
        if (!miscMods.containsKey(skill)) {
            return 0; // If it does not contain a misc mod, one must not be assigned.
        }
        return miscMods.get(skill);
    }

    public void setMiscMod(Skill skill, int mod) {
        miscMods.put(skill, mod);
        if (mod == 0) {
            miscMods.remove(skill); // Want to conserve memory by removing redundant data.
        }
    }

    public String[] getDetailsArray(boolean title) {
        ArrayList<String> result = new ArrayList<String>();
        if (title) {
            result.add("Name: " + name);
            result.add("Race: " + race);
            result.add("Class: " + cClass);
            result.add("Level: " + currLevel);
            result.add("Experience: " + experience);
            result.add("Gender: " + gender);
            result.add("Age: " + age);
            result.add("Alliance: " + alliance);
            result.add("Height: " + height);
            result.add("Weight: " + weight);
            result.add("Size: " + size);
        } else {
            result.add(name);
            result.add(race);
            result.add(cClass);
            result.add("" + currLevel);
            result.add("" + experience);
            result.add(gender);
            result.add("" + age);
            result.add(alliance);
            result.add("" + height);
            result.add("" + weight);
            result.add(size);
        }
        return result.toArray(new String[result.size()]);
    }

    public String toString() {
        return name + " - Level " + currLevel + " " + cClass + " " + race;
    }

    private class Item {

    }

    private class Race {

    }
}
