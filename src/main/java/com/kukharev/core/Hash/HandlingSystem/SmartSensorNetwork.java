/*
Java application that simulates an event processing system for an agricultural network of smart sensors (temperature, humidity, etc).
The system should read a JSON file containing sensor data and when the temperature value is below a threshold value the heating should be turned on,
and when the soil moisture is below a threshold value the irrigation system should be turned on.
The programme should log events and actions taken to the console.
 */

package com.kukharev.core.Hash.HandlingSystem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class SmartSensorNetwork {

    /**
     * This method handles events based on the sensor data and predefined thresholds.
     * It parses the input JSON string to extract sensor data and thresholds,
     * and then takes actions based on whether the sensor values fall below the thresholds.
     *
     * @param jsonInput JSON string containing sensor data and thresholds.
     * @return Map of sensor IDs to actions taken.
     */
    public Map<String, String> handleEvents(String jsonInput) throws JSONException {
        // Parse the input JSON string
        JSONObject input = new JSONObject(jsonInput);
        JSONArray sensors = input.getJSONArray("sensors");
        JSONObject thresholds = input.getJSONObject("thresholds");

        // Initialize a map to store the actions taken for each sensor
        Map<String, String> actionsTaken = new HashMap<>();

        // Iterate through each sensor in the sensors array
        for (int i = 0; i < sensors.length(); i++) {
            JSONObject sensor = sensors.getJSONObject(i);
            String sensorId = sensor.getString("id");
            String sensorType = sensor.getString("type");
            double sensorValue = sensor.getDouble("value");

            // Get the corresponding threshold for the sensor type
            JSONObject threshold = thresholds.getJSONObject(sensorType);
            double minThreshold = threshold.getDouble("min");

            // Check if the sensor value is below the minimum threshold
            if (sensorValue < minThreshold) {
                // Take action based on the type of sensor
                switch (sensorType) {
                    case "temperature" -> {
                        System.out.println("Heating activated for " + sensorId);
                        actionsTaken.put(sensorId, "Heating activated.");
                    }
                    case "soil_moisture" -> {
                        System.out.println("Irrigation started for " + sensorId);
                        actionsTaken.put(sensorId, "Irrigation started.");
                    }
                    // Add other cases for different sensor types as needed
                }
            }
        }

        return actionsTaken;
    }

    public static void main(String[] args) throws JSONException {
        // Sample JSON input string
        String jsonInput = """
                {
                  "sensors": [
                    { "id": "sensor1", "type": "temperature", "value": 5 },
                    { "id": "sensor2", "type": "humidity", "value": 45 },
                    { "id": "sensor3", "type": "soil_moisture", "value": 20 }
                  ],
                  "thresholds": {
                    "temperature": { "min": 10, "max": 30 },
                    "humidity": { "min": 30, "max": 60 },
                    "soil_moisture": { "min": 30, "max": 70 }
                  }
                }""";

        // Create an instance of SmartSensorNetwork
        SmartSensorNetwork system = new SmartSensorNetwork();

        // Handle events using the sample JSON input
        Map<String, String> actionsTaken = system.handleEvents(jsonInput);

        // Log the actions taken
        for (Map.Entry<String, String> entry : actionsTaken.entrySet()) {
            System.out.println("Sensor ID: " + entry.getKey() + ", Action: " + entry.getValue());
        }
    }
}
