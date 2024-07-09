/*
Java command-line application that takes a list of GPS coordinates (latitude, longitude) and a specified distance in kilometers,
then filters and returns a list of coordinates that are within the specified distance from the starting point.
Assume the Earth is a perfect sphere with a radius of 6371 km for calculations.
 */

package com.kukharev.core.cmd.GPSToKm;

import java.util.ArrayList;
import java.util.List;

public class ProximityFilter {

    // Method to filter coordinates that are within a given radius from the starting point
    public static List<double[]> filterCoordinates(List<double[]> coordinates, double[] startingPoint, double distanceKm) {
        List<double[]> nearbyCoordinates = new ArrayList<>();
        for (double[] coordinate : coordinates) {
            // Check if the current coordinate is within the given radius
            if (isWithinDistance(startingPoint, coordinate, distanceKm)) {
                nearbyCoordinates.add(coordinate);
            }
        }
        return nearbyCoordinates;
    }

    // Method to check if the end point is within the given distance from the start point
    private static boolean isWithinDistance(double[] start, double[] end, double distanceKm) {
        double earthRadiusKm = 6371.0;
        double dLat = Math.toRadians(end[0] - start[0]);
        double dLon = Math.toRadians(end[1] - start[1]);
        double lat1 = Math.toRadians(start[0]);
        double lat2 = Math.toRadians(end[0]);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadiusKm * c;

        // Return true if the distance is less than or equal to the given radius
        return distance <= distanceKm;
    }

    public static void main(String[] args) {
        // List of coordinates (latitude and longitude)
        List<double[]> coordinates = new ArrayList<>();
        coordinates.add(new double[]{51.5074, -0.1278}); // London
        coordinates.add(new double[]{48.8566, 2.3522});  // Paris
        coordinates.add(new double[]{52.5200, 13.4050}); // Berlin

        // Starting point (London)
        double[] startingPoint = {51.5074, -0.1278};

        // Radius in kilometers from the starting point
        double distanceKm = 350; // 350 km from London

        // Filter coordinates that are within the given radius
        List<double[]> nearbyCoordinates = filterCoordinates(coordinates, startingPoint, distanceKm);

        // Print the results
        for (double[] coord : nearbyCoordinates) {
            System.out.println("Latitude: " + coord[0] + ", Longitude: " + coord[1]);
        }
    }
}
