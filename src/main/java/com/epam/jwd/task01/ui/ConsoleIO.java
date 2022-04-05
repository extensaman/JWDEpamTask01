package com.epam.jwd.task01.ui;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

/**
 * Сlass for providing the ability to output information to the console
 */

public class ConsoleIO implements UserInterface {
    private static final ConsoleIO INSTANCE = new ConsoleIO();

    private ConsoleIO() {
    }

    /**
     * Method that provides one single existing instance of the class ConsoleIO
     *
     * @return one single existing instance of the class ConsoleIO
     */
    public static ConsoleIO getInstance() {
        return INSTANCE;
    }

    /**
     * This method returns Optional string entered from the console by the user
     * or empty Optional in case of exception
     *
     * @return Optional string entered by the user
     * or empty Optional in case of exception
     */
    @Override
    public Optional<String> input() {
        Scanner sc = new Scanner(System.in);
        String result = null;
        try {
            result = sc.nextLine();
            sc.nextLine();
        } catch (NoSuchElementException | IllegalStateException e) {
            return Optional.empty();
        }
        return Optional.of(result);
    }

    /**
     * Method to display text string in output console
     *
     * @param message text for display
     */
    @Override
    public void output(String message) {
        System.out.print(message);
    }
}
