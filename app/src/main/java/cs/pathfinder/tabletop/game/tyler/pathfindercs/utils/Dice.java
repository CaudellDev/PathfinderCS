package cs.pathfinder.tabletop.game.tyler.pathfindercs.utils;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Tyler on 4/14/2015.
 */
public class Dice {

    private Dice() {}

    public static int roll(int size, int count) {
        Random r = new Random();
        int result = 0;

        for (int i = 0; i < count; i++) {
            result += r.nextInt(size) + 1;
            System.out.println("Dice roll " + i + ": " + result);
        }

        return result;
    }

    public static int rollStat(String method) {
        System.out.println("rollStat method: " + method);
        if (method.equals("standard")) {
            int[] list = {roll(6, 1), roll(6, 1), roll(6, 1), roll(6, 1)};
            int smallestIndex = 0;
            int sum = 0;
            // Find the index with the smallest value
            for (int i = 0; i < 4; i++) {
                if (list[i] < list[smallestIndex]) {
                    smallestIndex = i;
                }
            }
            // Sum all of the values except for the smallest one
            for (int i = 0; i < 4; i++) {
                if (i != smallestIndex) {
                    sum += list[i];
                }
            }
            System.out.println(sum);
            return sum;
        } else if (method.equals("classic")) {
            int result = roll(6, 3);
            System.out.println("Dice classic roll: " + result);
            return result;
        } else if (method.equals("heroic")) {
            int result = roll(6, 2) + 6;
            System.out.println("Dice heroic roll: " + result);
            return result;
        }
        return 0;
    }
}
