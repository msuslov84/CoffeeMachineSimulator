package com.suslov.jetbrains.utils;

import com.suslov.jetbrains.models.CoffeeMachine;

/**
 * @author Mikhail Suslov
 */
public class MessageUtil {
    public static final String OUTPUT_INGREDIENTS = "\nThe coffee machine has:";
    public static final String NOT_ENOUGH = "Sorry, not enough %s!\n";
    public static final String ENOUGH = "I have enough resources, making you a coffee!\n";
    public static final String INCORRECT_COMMAND = "Incorrect input command";

    public static void toConsole(String message) {
        System.out.println(message);
    }
}
