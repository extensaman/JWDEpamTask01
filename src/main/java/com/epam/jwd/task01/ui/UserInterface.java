package com.epam.jwd.task01.ui;

import java.util.Optional;

/**
 * Interface that represents functionality for exchanging data with the user
 */

public interface UserInterface {
    Optional<String> input();

    void output(String message);
}
