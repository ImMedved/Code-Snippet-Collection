package com.kukharev.Games;

import java.util.*;

class HealthSystem {

    // //HashMap of values {Entity(String) and Health(Integer)}
    private Map<String, Integer> entities = new HashMap<>();

    /*
     * Add a new entity to the system with specified health.
     *
     * @param entityType The type of entity ("Player" or "Enemy") to add to the system
     * @param health The health value to assign to the entity
     */
    public void addEntity(String entityType, int health) {
        // Add the entity and its health to the system
        entities.put(entityType, health);
    }

    /*
     * Reduces health from an entity based on damage taken.
     *
     * @param entityType The type of entity from which to reduce health
     * @param damage The amount of health to reduce from the entity
     */
    public void reduceHealth(String entityType, int damage) {
        // Check if the entity exists in the system
        if (entities.containsKey(entityType)) {
            int newHealth = entities.get(entityType) - damage;
            // If the entity's health remains above 0 after taking damage
            if (newHealth > 0) {
                // Update the entity's health
                entities.put(entityType, newHealth);
            } else {
                // Remove the entity if its health drops to 0 or below
                entities.remove(entityType);
                System.out.println(entityType + " has been removed");
            }
        }
    }

    /*
     * Returns the current health of an entity.
     *
     * @param entityType The type of entity from which to get health
     * @return The health value of the specified entity
     */
    public Integer getHealth(String entityType) {
        // Return the health of the entity
        return entities.get(entityType);
    }

    /*
     * Checks if an entity is still alive.
     *
     * @param entityType The type of entity to check
     * @return true if the entity is in the system, false otherwise
     */
    public boolean isEntityAlive(String entityType) {
        // Check if the entity exists in the system
        return entities.containsKey(entityType);
    }
}

class AINavigation {
    /*
     * Calculate and returns a path from the start point to the end point.
     *
     * @param start The starting point of the path
     * @param end The ending point of the path
     * @return A list of integers representing the path from start to end
     */
    public List<Integer> findPath(int start, int end) {
        List<Integer> path = new ArrayList<>();
        // Add each point to the path from start to end
        for (int i = start; i <= end; i++) {
            path.add(i);
        }
        return path;
    }
}