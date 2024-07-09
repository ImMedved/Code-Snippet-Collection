/*
Java method that validates the name of a character in a game. The name should have a length between 3 and 15 characters.
It should only consist of alphabets, numbers, hyphens (-), and underscores (_). The method should return true if the name is valid and false otherwise.
 */

package com.kukharev.Games;

/**
 * A utility class for validating character names in a game or application.
 */
public class CharacterNameValidator {

    /**
     * Validates the character name based on specific rules.
     * The name must be between 3 to 15 characters long and can only contain
     * letters (both uppercase and lowercase), numbers, underscores, and hyphens.
     *
     * @param name the character name to validate
     * @return true if the name is valid, false otherwise
     */
    public boolean validateCharacterName(String name) {
        // Regular expression to match the valid character name pattern
        return name.matches("^[a-zA-Z0-9_-]{3,15}$");
    }
}
