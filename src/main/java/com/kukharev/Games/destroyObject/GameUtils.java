package com.kukharev.Games.destroyObject;

/**
 * Utility class for handling game objects.
 */
public class GameUtils {

    /**
     * Creates a new game object with specified hit points and destructibility.
     *
     * @param hitPoints      the initial hit points of the game object
     * @param isDestructible true if the object can be destroyed, false otherwise
     * @return the created GameObject instance
     */
    public static GameObject createObject(int hitPoints, boolean isDestructible) {
        GameObject obj = new GameObject();
        obj.setHitPoints(hitPoints);
        obj.setDestructible(isDestructible);
        return obj;
    }

    /**
     * Applies damage to the game object and checks if it is destroyed.
     *
     * @param obj    the game object to be damaged
     * @param damage the amount of damage to apply
     * @return true if the object is destroyed, false otherwise
     */
    public static boolean destroyObject(GameObject obj, int damage) {
        if (obj.isDestructible()) {
            obj.setHitPoints(obj.getHitPoints() - damage);
            // Check if the object's hit points are zero or less
            if (obj.getHitPoints() <= 0) {
                obj.setHitPoints(0);  // Ensure hit points do not go below zero
                return true;  // Object is destroyed
            }
        }
        return false;  // Object is not destroyed
    }
}