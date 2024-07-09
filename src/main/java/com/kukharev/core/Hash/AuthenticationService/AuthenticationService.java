/*
Java program that provides an authentication service for an application.
The service handles user login by validating credentials against a mock database (with simple HashMap as a stand-in for the database).
The service is able to lock a user account after three unsuccessful login attempts and provides appropriate messages for successful login,
failed login, and account lock situations.
 */

package com.kukharev.core.Hash.AuthenticationService;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {

    private final Map<String, String> userDatabase = new HashMap<>();
    private final Map<String, Integer> loginAttempts = new HashMap<>();

    /**
     * Create new user.
     *
     * @param username user name
     * @param password user password
     */
    public void addUser(String username, String password) {
        userDatabase.put(username, password);
    }

    /**
     * Attempt to login user.
     *
     * @param username user name
     * @param password user password
     * @return response message
     */
    public String login(String username, String password) {
        if (loginAttempts.getOrDefault(username, 0) >= 3) {
            return "Account is locked. Contact admin.";
        }

        if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
            loginAttempts.remove(username);
            return "Login successful!";
        } else {
            loginAttempts.put(username, loginAttempts.getOrDefault(username, 0) + 1);
            if (loginAttempts.get(username) >= 3) {
                return "Account is locked. Contact admin.";
            }
            return "Invalid credentials. Please try again.";
        }
    }
}