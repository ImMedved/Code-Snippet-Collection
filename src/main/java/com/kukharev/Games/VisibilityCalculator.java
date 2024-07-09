package com.kukharev.Games;

public class VisibilityCalculator {

    /**
     * Calculates the visibility map for a game world.
     * The function returns a two-dimensional boolean array where true indicates that
     * the tile is visible to the player and false indicates that it is not.
     * The visibility is calculated as a square around the player's position.
     *
     * @param playerPosition a two-dimensional array representing the player's current position in the game world
     * @param visibilityRadius an integer representing the radius of visibility around the player's position
     * @return a two-dimensional boolean array representing the visibility map
     */
    public static boolean[][] calculateVisibility(int[][] playerPosition, int visibilityRadius) {
        int size = playerPosition.length;
        boolean[][] visibilityMap = new boolean[size][size];
        int playerX = playerPosition[0][0];
        int playerY = playerPosition[0][1];

        // Iterate over the game world grid
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Check if the current tile is within the visibility radius
                if (Math.abs(i - playerX) <= visibilityRadius && Math.abs(j - playerY) <= visibilityRadius) {
                    visibilityMap[i][j] = true;
                } else {
                    visibilityMap[i][j] = false;
                }
            }
        }
        return visibilityMap;
    }
}

