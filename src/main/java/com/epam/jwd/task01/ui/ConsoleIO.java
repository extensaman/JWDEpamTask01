package com.epam.jwd.task01.ui;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

/**
 * Сlass for providing the ability to output information to the console
 */

public class ConsoleIO implements UserInterface{
    private static final ConsoleIO INSTANCE = new ConsoleIO();

    private ConsoleIO(){}

    public static ConsoleIO getInstance(){
        return INSTANCE;
    }

    @Override
    public Optional<String> input() {
        Scanner sc = new Scanner(System.in);
        String result = null;
        try {
            result = sc.nextLine();
            sc.nextLine();
        }
        catch (NoSuchElementException | IllegalStateException e) {
           return Optional.empty();
        }
        return Optional.of(result);
    }

    @Override
    public void output(String message) {
        System.out.print(message);
    }
}
