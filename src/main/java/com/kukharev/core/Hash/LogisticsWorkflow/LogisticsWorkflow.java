/*
A Java program to manage a logistics workflow. The program is able to receive a list of orders, each with a unique ID and a status.
 */

package com.kukharev.core.Hash.LogisticsWorkflow;

import java.util.HashMap;
import java.util.Map;

public class LogisticsWorkflow {

    // A map to store orders and their statuses
    private final Map<String, String> orders;

    // Array of valid statuses for an order
    private static final String[] VALID_STATUSES = {"Received", "Processing", "Shipped", "Delivered"};

    // Constructor initializes the orders map
    public LogisticsWorkflow() {
        orders = new HashMap<>(); // Initialize the orders map
    }

    // Method to add a new order with a given status
    public void addOrder(String id, String status) {
        validateStatus(status); // Validate the status before adding
        orders.put(id, status); // Add the order to the map
    }

    // Method to get the status of an order by its ID
    public String getOrderStatus(String id) {
        // Check if the order exists in the map
        if (!orders.containsKey(id)) {
            throw new IllegalArgumentException("Order not found: " + id); // Throw an exception if not found
        }
        return orders.get(id); // Return the status of the order
    }

    // Method to update the status of an existing order
    public void updateOrderStatus(String id, String newStatus) {
        validateStatus(newStatus); // Validate the new status before updating
        // Check if the order exists in the map
        if (!orders.containsKey(id)) {
            throw new IllegalArgumentException("Order not found: " + id); // Throw an exception if not found
        }
        orders.put(id, newStatus); // Update the status of the order
    }

    // Private helper method to validate the status of an order
    private void validateStatus(String status) {
        // Check if the provided status is one of the valid statuses
        for (String validStatus : VALID_STATUSES) {
            if (validStatus.equals(status)) {
                return; // Status is valid
            }
        }
        throw new IllegalArgumentException("Invalid status: " + status); // Throw an exception if status is invalid
    }
}