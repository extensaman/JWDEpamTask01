package com.epam.jwd.task01.ui;

import java.util.Optional;

/**
 * Interface that represents functionality for exchanging data with the user
 */

public interface UserInterface {
    /**
     * This method returns Optional string entered by the user
     * or empty Optional in case of exception
     *
     * @return Optional string entered by the user
     * or empty Optional in case of exception
     */
    Optional<String> input();

    /**
     * Method for text output
     *
     * @param message text for display
     */
    void output(String message);
}
