//A function in Java named that simulates a day-night cycle for a game.

package com.kukharev.Games;

public class DayNightCycle {

    public static int progressDayNightCycle(int currentHour, int hoursToProgress) {
        return (currentHour + hoursToProgress) % 24;
    }

    public static String npcBehavior(int currentHour) {
        if (currentHour >= 0 && currentHour < 6) {
            return "Sleeping";
        } else if (currentHour >= 6 && currentHour < 12) {
            return "Eating";
        } else if (currentHour >= 12 && currentHour < 18) {
            return "Working";
        } else {
            return "Relaxing";
        }
    }

    public static void main(String[] args) {
        // Test the progressDayNightCycle method
        int currentHour = 10;
        int hoursToProgress = 5;
        int newHour = progressDayNightCycle(currentHour, hoursToProgress);
        System.out.println("New Hour: " + newHour);  // Should print 15

        // Test the npcBehavior method
        System.out.println("Behavior at hour 2: " + npcBehavior(2));  // Should print Sleeping
        System.out.println("Behavior at hour 8: " + npcBehavior(8));  // Should print Eating
        System.out.println("Behavior at hour 14: " + npcBehavior(14));  // Should print Working
        System.out.println("Behavior at hour 20: " + npcBehavior(20));  // Should print Relaxing
    }
}
