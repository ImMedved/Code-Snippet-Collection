package com.kukharev.Games;

import java.util.List;
import java.util.Scanner;

public class GameMenu {

    /**
     * Displays a game menu with the given options and prompts the user to choose an option.
     * The method handles invalid inputs by prompting the user to try again.
     *
     * @param options List of menu options to display.
     * @return The number corresponding to the user's choice.
     */
    public int displayGameMenu(List<String> options) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        try {
            while (true) {
                // Display the menu options
                for (int i = 0; i < options.size(); i++) {
                    System.out.println((i + 1) + ". " + options.get(i));
                }
                System.out.print("Choose an option: ");

                // Handle user input
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if (choice >= 1 && choice <= options.size()) {
                        break; // valid choice, exit loop
                    } else {
                        System.out.println("Invalid option. Please try again.");
                    }
                } else {
                    scanner.next(); // consume the invalid token
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
        } finally {
            scanner.close(); // Ensure the scanner is closed to avoid resource leaks
        }
        return choice;
    }

    public static void main(String[] args) {
        GameMenu menu = new GameMenu();
        List<String> options = List.of("Start Game", "Load Game", "Options", "Exit");
        int choice = menu.displayGameMenu(options);
        System.out.println("You chose option: " + choice);
    }
}