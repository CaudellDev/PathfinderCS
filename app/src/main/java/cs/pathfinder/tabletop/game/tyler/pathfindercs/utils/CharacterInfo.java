package cs.pathfinder.tabletop.game.tyler.pathfindercs.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import cs.pathfinder.tabletop.game.tyler.pathfindercs.utils.Dice;

public class CharacterInfo {

    private String name;
    private String user;
    // Fluff
    private String race;
    private String cClass;
    private boolean autoImportRace;  // If true, automatically adds data from
    private boolean autoImportClass; // custom or preset race and class data.
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
    private int currHP;
    private int maxHP;

    // Saves
    private int fortitude;
    private int reflex;
    private int will;

    // Speed
    private int baseSp;
    private int armor;
    private int fly;
    private int swim;
    private int climb;
    private int burrow;

    private int AC;
    private int touch;
    private int flatFooted;
    private int spellResist;

    private int BAB;

    private Map<String, String> skills;
    private Map<String, String> feats;
    private Map<String, String> inventory;

    private Map<Stats, Integer> miscMods;

    public final static String empty = "Empty";

    public CharacterInfo() {
        init();
    }

    private void init() {
        name = empty;
        currLevel = 1;
        race = empty;
        cClass = empty;
        gender = empty;
        alliance = empty;

        miscMods = new HashMap<Stats, Integer>();
    }

    public void setAutoImportRace(boolean autoImport) { this.autoImportRace = autoImport; }
    public boolean autoImportRace() { return autoImportRace; }

    public void setAutoImportClass(boolean autoImport) { this.autoImportClass = autoImport; }
    public boolean autoImportClass() { return autoImportClass; }

    public void set(Stats stat, String value) {
        switch (stat) {
            case EYES: case HAIR:
            case ARM_SPD: case BURR_SPD: case CLIMB_SPD:
            case BIO:
            case FLY_SPD: case SWM_SPD:
            case SPL_RES: case SKIN: case SIZE:
                // Ignore these stats for now...
                break;
            case NAME:
                name = value;
                break;
            case GENDER:
                gender = value;
                break;
            case AGE:
                age = Integer.valueOf(value);
                break;
            case ALLI:
                alliance = value;
                break;
            case HEIGHT:
                height = Integer.valueOf(value);
                break;
            case WEIGHT:
                weight = Integer.valueOf(value);
                break;
            case MAX_HP:
                maxHP = Integer.valueOf(value);
                break;
            case CURR_HP:
                currHP = Integer.valueOf(value);
                break;
            case XP:
                experience = Integer.valueOf(value);
                break;
            case LVL:
                currLevel = Integer.valueOf(value);
                break;
            case NEXT_LVL:
                nextLevel = Integer.valueOf(value);
                break;
            case BAB:
                BAB = Integer.valueOf(value);
                break;
            case CMD:
                CMD = Integer.valueOf(value);
                break;
            case CMB:
                CMB = Integer.valueOf(value);
                break;
            case FORT:
                fortitude = Integer.valueOf(value);
                break;
            case REFLEX:
                reflex = Integer.valueOf(value);
                break;
            case WILL:
                will = Integer.valueOf(value);
                break;
            case INIT:
                initiative = Integer.valueOf(value);
                break;
            case FLAT:
                flatFooted = Integer.valueOf(value);
                break;
            case TOUCH:
                touch = Integer.valueOf(value);
                break;
            case STR:
                strength = Integer.valueOf(value);
                break;
            case DEX:
                dexterity = Integer.valueOf(value);
                break;
            case CON:
                constitution = Integer.valueOf(value);
                break;
            case INT:
                intelligence = Integer.valueOf(value);
                break;
            case WIS:
                wisdom = Integer.valueOf(value);
                break;
            case CHA:
                charisma = Integer.valueOf(value);
                break;
            case RACE:
                race = value;
                break;
            case CLASS:
                cClass = value;
                break;
            case BASE_SPD:
                baseSp = Integer.valueOf(value);
                break;
            default:
                System.out.println("############ Stat " + stat.toString() + " was not checked for.");
        }
    }

    public String get(Stats stat) {
        switch (stat) {
            case EYES: case HAIR:
            case ARM_SPD: case BURR_SPD: case CLIMB_SPD:
            case BIO:
            case FLY_SPD: case SWM_SPD:
            case SPL_RES: case SKIN:
                // Ignore these stats for now...
                break;
            case NAME:
                return name;
            case GENDER:
                return gender;
            case AGE:
                return age + "";
            case ALLI:
                return alliance;
            case HEIGHT:
                return height + "";
            case WEIGHT:
                return weight + "";
            case MAX_HP:
                return maxHP + "";
            case CURR_HP:
                return currHP + "";
            case XP:
                return experience + "";
            case LVL:
                return currLevel + "";
            case NEXT_LVL:
                return nextLevel + "";
            case BAB:
                return BAB + "";
            case CMD:
                return CMD + "";
            case CMB:
                return CMB + "";
            case FORT:
                return fortitude + "";
            case REFLEX:
                return reflex + "";
            case WILL:
                return will + "";
            case INIT:
                return initiative + "";
            case FLAT:
                return flatFooted + "";
            case TOUCH:
                return touch + "";
            case STR:
                return strength + "";
            case DEX:
                return dexterity + "";
            case CON:
                return constitution + "";
            case INT:
                return intelligence + "";
            case WIS:
                return wisdom + "";
            case CHA:
                return charisma + "";
            case RACE:
                return race;
            case CLASS:
                return cClass;
            case BASE_SPD:
                return baseSp + "";
            default:
                return "";
        }
        return "";
    }

    public int[] getAbilities() {
        return new int[]{strength, dexterity, constitution,
                        intelligence, wisdom, charisma};
    }

    /**
     * The ability modifier is (ability - 10) / 2, rounded down.
     * @return is an array of ability mods, going in order from strength to charisma.
     */
    public int[] getAbilityMods() {
        return new int[]{(int) Math.floor((strength - 10) / 2), (int) Math.floor((dexterity - 10) / 2),
                (int) Math.floor((constitution - 10) / 2), (int) Math.floor((intelligence - 10) / 2),
                (int) Math.floor((wisdom - 10) / 2), (int) Math.floor((charisma - 10) / 2)};
    }

    /**
     * TODO: Give the user the choice of placing the rolls into their own stat.
     * @param method is the way the dice are rolled. Different methods have different outcomes and probabilities.
     */
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

    public int getMiscMod(Stats stat) {
        if (!miscMods.containsKey(stat)) {
            return 0; // If it does not contain a misc mod, one must not be assigned.
        }
        return miscMods.get(stat);
    }

    public void setMiscMod(Stats stat, int mod) {
        miscMods.put(stat, mod);
        if (mod == 0) {
            miscMods.remove(stat); // Want to conserve memory by removing redundant data.
        }
    }

    // For debugging purposes now...
    public String toString() {
        return name + " - Level " + currLevel + " " + cClass + " " + race
                + " {" + super.toString() + "}";
    }
}
