package com.suslov.jetbrains.models;

import com.suslov.jetbrains.utils.MessageUtil;

import java.util.HashMap;

import static com.suslov.jetbrains.models.CoffeeType.*;
import static com.suslov.jetbrains.utils.MessageUtil.INCORRECT_COMMAND;
import static com.suslov.jetbrains.utils.MessageUtil.OUTPUT_INGREDIENTS;

/**
 * @author Mikhail Suslov
 */
public class CoffeeMachine {
    private State currentState;
    private boolean switchOff;
    private final HashMap<Ingredient, Integer> ingredientStocks = new HashMap<>();

    public CoffeeMachine() {
        this.currentState = State.CHOOSE_ACTION;
    }

    public boolean isSwitchOff() {
        return switchOff;
    }

    public void requestAction() {
        MessageUtil.toConsole(currentState);
    }

    public void respondUserChoice(String userChoice) {
        switch (currentState) {
            case CHOOSE_ACTION:
                processActionChoice(userChoice);
                break;
            case CHOOSE_TYPE_COFFEE:
                processCoffeeTypeChoice(userChoice);
                break;
            case FILL_WATER:
            case FILL_MILK:
            case FILL_BEANS:
            case FILL_CUPS:
                processFillIngredientChoice(userChoice);
            default:
                MessageUtil.toConsole(INCORRECT_COMMAND);
                currentState = State.CHOOSE_ACTION;
        }
    }

    private void processActionChoice(String userChoice) {
        switch (userChoice) {
            case "buy":
                currentState = State.CHOOSE_TYPE_COFFEE;
                break;
            case "fill":
                currentState = State.FILL_WATER;
                break;
            case "take":
                takeOutAllMoney();
                break;
            case "remaining":
                outputAmountIngredients();
                break;
            case "exit":
                switchOff = true;
                break;
            default:
                System.out.println(INCORRECT_COMMAND);
        }
    }

    private void processCoffeeTypeChoice(String userChoice) {
        currentState = currentState.releaseState();
        switch (userChoice) {
            case "1":
                ESPRESSO.makeACoffee(ingredientStocks);
                break;
            case "2":
                LATTE.makeACoffee(ingredientStocks);
                break;
            case "3":
                CAPPUCCINO.makeACoffee(ingredientStocks);
                break;
            case "back":
                return;
            default:
                MessageUtil.toConsole(INCORRECT_COMMAND);
                currentState = State.CHOOSE_TYPE_COFFEE;
        }
    }

    private void processFillIngredientChoice(String userChoice) {
        switch (currentState) {
            case FILL_WATER:
                if (fillOutIngredient(Ingredient.WATER, userChoice)) {
                    currentState = State.FILL_MILK;
                }
                break;
            case FILL_MILK:
                if (fillOutIngredient(Ingredient.MILK, userChoice)) {
                    currentState = State.FILL_BEANS;
                }
                break;
            case FILL_BEANS:
                if (fillOutIngredient(Ingredient.BEANS, userChoice)) {
                    currentState = State.FILL_CUPS;
                }
                break;
            case FILL_CUPS:
                if (fillOutIngredient(Ingredient.CUPS, userChoice)) {
                    currentState = currentState.releaseState();
                }
                break;
            default:
                MessageUtil.toConsole(INCORRECT_COMMAND);
                currentState = currentState.releaseState();
        }
    }

    private void takeOutAllMoney() {
        int rest = ingredientStocks.getOrDefault(Ingredient.MONEY, 0);
        MessageUtil.toConsole(String.format("\nI gave you $%d\n", rest));
        ingredientStocks.put(Ingredient.MONEY, 0);
    }

    private void outputAmountIngredients() {
        MessageUtil.toConsole(OUTPUT_INGREDIENTS);
        for (Ingredient element : Ingredient.values()) {
            String ingredientRemainder = element.remainderRepresentation(ingredientStocks.get(element));
            MessageUtil.toConsole(ingredientRemainder);
        }
    }

    private boolean fillOutIngredient(Ingredient ingredient, String value) {
        if (ingredient != Ingredient.MONEY) {
            try {
                int newValue = ingredientStocks.getOrDefault(ingredient, 0) + Integer.parseInt(value);
                ingredientStocks.put(ingredient, newValue);
            } catch (NumberFormatException exp) {
                MessageUtil.toConsole(exp.getMessage());
                return false;
            }
        }
        return true;
    }

    public enum State {
        CHOOSE_ACTION {
            @Override
            public String requestNextUserAction() {
                return "\nWrite action (buy, fill, take, remaining, exit):";
            }
        },
        CHOOSE_TYPE_COFFEE {
            @Override
            public String requestNextUserAction() {
                return "\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:";
            }
        },
        FILL_WATER {
            @Override
            public String requestNextUserAction() {
                return "\nWrite how many ml of water you want to add:";
            }
        },
        FILL_MILK {
            @Override
            public String requestNextUserAction() {
                return "\nWrite how many ml of milk you want to add:";
            }
        },
        FILL_BEANS {
            @Override
            public String requestNextUserAction() {
                return "\nWrite how many grams of coffee beans you want to add:";
            }
        },
        FILL_CUPS {
            @Override
            public String requestNextUserAction() {
                return "\nWrite how many disposable cups you want to add:";
            }
        };

        public abstract String requestNextUserAction();

        public State releaseState() {
            return CHOOSE_ACTION;
        }
    }
}

