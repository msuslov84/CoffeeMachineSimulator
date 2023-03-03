package com.suslov.jetbrains;

import com.suslov.jetbrains.models.CoffeeMachine;

import java.util.Scanner;

/**
 * @author Mikhail Suslov
 */
public class CoffeeMachineSimulator {
    private final Scanner scanner;

    public static void main(String[] args) {
        CoffeeMachineSimulator simulator = new CoffeeMachineSimulator();
        simulator.launch();
    }

    public CoffeeMachineSimulator() {
        this(new Scanner(System.in));
    }

    public CoffeeMachineSimulator(Scanner scanner) {
        this.scanner = scanner;
    }

    public void launch() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        while (!coffeeMachine.isSwitchOff()) {
            coffeeMachine.requestAction();
            coffeeMachine.respondUserChoice(scanner.nextLine());
        }
    }
}
