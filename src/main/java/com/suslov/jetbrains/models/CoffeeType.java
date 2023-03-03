package com.suslov.jetbrains.models;

import com.suslov.jetbrains.utils.MessageUtil;

import java.util.Map;

import static com.suslov.jetbrains.models.Ingredient.*;
import static com.suslov.jetbrains.utils.MessageUtil.ENOUGH;
import static com.suslov.jetbrains.utils.MessageUtil.NOT_ENOUGH;

/**
 * @author Mikhail Suslov
 */
enum CoffeeType {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6);

    private final int water;
    private final int milk;
    private final int beans;
    private final int cost;

    CoffeeType(int water, int milk, int beans, int cost) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cost = cost;
    }

    public void makeACoffee(Map<Ingredient, Integer> supplies) {
        boolean isEnough = true;
        Integer restOfWater = supplies.get(WATER);
        if (restOfWater < water) {
            MessageUtil.toConsole(String.format(NOT_ENOUGH, "water"));
            isEnough = false;
        }
        Integer restOfMilk = supplies.get(MILK);
        if (restOfMilk < milk) {
            MessageUtil.toConsole(String.format(NOT_ENOUGH, "milk"));
            isEnough = false;
        }
        Integer restOfBeans = supplies.get(BEANS);
        if (restOfBeans < beans) {
            MessageUtil.toConsole(String.format(NOT_ENOUGH, "coffee beans"));
            isEnough = false;
        }
        Integer restOfCups = supplies.get(CUPS);
        if (restOfCups < 1) {
            MessageUtil.toConsole(String.format(NOT_ENOUGH, "disposable cups"));
            isEnough = false;
        }
        Integer restOfMoney = supplies.get(MONEY);
        if (restOfMoney < cost) {
            MessageUtil.toConsole(String.format(NOT_ENOUGH, "money"));
            isEnough = false;
        }

        if (isEnough) {
            MessageUtil.toConsole(ENOUGH);
            supplies.put(WATER, supplies.get(WATER) - water);
            supplies.put(MILK, supplies.get(MILK) - milk);
            supplies.put(BEANS, supplies.get(BEANS) - beans);
            supplies.put(CUPS, supplies.get(CUPS) - 1);
            supplies.put(MONEY, supplies.get(MONEY) + cost);
        }
    }
}
