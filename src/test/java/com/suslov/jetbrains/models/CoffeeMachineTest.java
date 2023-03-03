package com.suslov.jetbrains.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static com.suslov.jetbrains.models.Ingredient.*;

/**
 * @author Mikhail Suslov
 */
public class CoffeeMachineTest {
    private final CoffeeMachine coffeeMachine = new CoffeeMachine();

    @Before
    public void setUp() {

    }

    @Test
    public void initialLoad() {
        coffeeMachine.initialLoad();
        HashMap<Ingredient, Integer> initializedStocks = coffeeMachine.getIngredientStocks();

        Assert.assertEquals(400, initializedStocks.get(WATER).intValue());
        Assert.assertEquals(540, initializedStocks.get(MILK).intValue());
        Assert.assertEquals(120, initializedStocks.get(BEANS).intValue());
        Assert.assertEquals(9, initializedStocks.get(CUPS).intValue());
        Assert.assertEquals(550, initializedStocks.get(MONEY).intValue());
    }

    @Test
    public void requestNextUserActionInChooseAction() {
        Assert.assertEquals("\nWrite action (buy, fill, take, remaining, exit):",
                coffeeMachine.getCurrentState().requestNextUserAction());
    }

    @Test
    public void requestNextUserActionInChooseCoffeeType() {
        coffeeMachine.setCurrentState(CoffeeMachine.State.CHOOSE_TYPE_COFFEE);

        Assert.assertEquals("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:",
                coffeeMachine.getCurrentState().requestNextUserAction());
    }

    @Test
    public void requestNextUserActionInFillWater() {
        coffeeMachine.setCurrentState(CoffeeMachine.State.FILL_WATER);

        Assert.assertEquals("\nWrite how many ml of water you want to add:",
                coffeeMachine.getCurrentState().requestNextUserAction());
    }

    @Test
    public void requestNextUserActionInFillMilk() {
        coffeeMachine.setCurrentState(CoffeeMachine.State.FILL_MILK);

        Assert.assertEquals("\nWrite how many ml of milk you want to add:",
                coffeeMachine.getCurrentState().requestNextUserAction());
    }

    @Test
    public void requestNextUserActionInFillBeans() {
        coffeeMachine.setCurrentState(CoffeeMachine.State.FILL_BEANS);

        Assert.assertEquals("\nWrite how many grams of coffee beans you want to add:",
                coffeeMachine.getCurrentState().requestNextUserAction());
    }

    @Test
    public void requestNextUserActionInFillCups() {
        coffeeMachine.setCurrentState(CoffeeMachine.State.FILL_CUPS);

        Assert.assertEquals("\nWrite how many disposable cups you want to add:",
                coffeeMachine.getCurrentState().requestNextUserAction());
    }

    @Test
    public void respondUserChoiceActionBuy() {
        coffeeMachine.respondUserChoice("buy");

        Assert.assertSame(CoffeeMachine.State.CHOOSE_TYPE_COFFEE, coffeeMachine.getCurrentState());
    }

    @Test
    public void respondUserChoiceActionFill() {
        coffeeMachine.respondUserChoice("fill");

        Assert.assertSame(CoffeeMachine.State.FILL_WATER, coffeeMachine.getCurrentState());
    }

    @Test
    public void respondUserChoiceActionTake() {
        coffeeMachine.respondUserChoice("take");

        Assert.assertEquals(0, coffeeMachine.getIngredientStocks().get(MONEY).intValue());
        Assert.assertSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
    }

    @Test
    public void respondUserChoiceActionRemaining() {
        coffeeMachine.initialLoad();

        coffeeMachine.respondUserChoice("remaining");

        Assert.assertSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
    }

    @Test
    public void respondUserChoiceActionExit() {
        coffeeMachine.respondUserChoice("exit");

        Assert.assertSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
        Assert.assertTrue(coffeeMachine.isSwitchOff());
    }

    @Test
    public void respondUserChoiceIncorrectActionInCaseSensitivity() {
        coffeeMachine.respondUserChoice("bUy");

        Assert.assertNotSame(CoffeeMachine.State.CHOOSE_TYPE_COFFEE, coffeeMachine.getCurrentState());
        Assert.assertSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
    }

    @Test
    public void respondUserChoiceUnsupportedAction() {
        coffeeMachine.respondUserChoice("unsupported");

        Assert.assertSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
    }

    @Test
    public void respondUserChoiceTypeEspresso() {
        coffeeMachine.initialLoad();
        coffeeMachine.setCurrentState(CoffeeMachine.State.CHOOSE_TYPE_COFFEE);

        coffeeMachine.respondUserChoice("1");

        Assert.assertSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
        HashMap<Ingredient, Integer> ingredientStocks = coffeeMachine.getIngredientStocks();
        Assert.assertEquals(150, ingredientStocks.get(WATER).intValue());
        Assert.assertEquals(540, ingredientStocks.get(MILK).intValue());
        Assert.assertEquals(104, ingredientStocks.get(BEANS).intValue());
        Assert.assertEquals(8, ingredientStocks.get(CUPS).intValue());
        Assert.assertEquals(554, ingredientStocks.get(MONEY).intValue());
    }

    @Test
    public void respondUserChoiceTypeLatte() {
        coffeeMachine.initialLoad();
        coffeeMachine.setCurrentState(CoffeeMachine.State.CHOOSE_TYPE_COFFEE);

        coffeeMachine.respondUserChoice("2");

        Assert.assertSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
        HashMap<Ingredient, Integer> ingredientStocks = coffeeMachine.getIngredientStocks();
        Assert.assertEquals(50, ingredientStocks.get(WATER).intValue());
        Assert.assertEquals(465, ingredientStocks.get(MILK).intValue());
        Assert.assertEquals(100, ingredientStocks.get(BEANS).intValue());
        Assert.assertEquals(8, ingredientStocks.get(CUPS).intValue());
        Assert.assertEquals(557, ingredientStocks.get(MONEY).intValue());
    }

    @Test
    public void respondUserChoiceTypeCappuccino() {
        coffeeMachine.initialLoad();
        coffeeMachine.setCurrentState(CoffeeMachine.State.CHOOSE_TYPE_COFFEE);

        coffeeMachine.respondUserChoice("3");

        Assert.assertSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
        HashMap<Ingredient, Integer> ingredientStocks = coffeeMachine.getIngredientStocks();
        Assert.assertEquals(200, ingredientStocks.get(WATER).intValue());
        Assert.assertEquals(440, ingredientStocks.get(MILK).intValue());
        Assert.assertEquals(108, ingredientStocks.get(BEANS).intValue());
        Assert.assertEquals(8, ingredientStocks.get(CUPS).intValue());
        Assert.assertEquals(556, ingredientStocks.get(MONEY).intValue());
    }

    @Test
    public void respondUserChoiceTypeBack() {
        coffeeMachine.initialLoad();
        coffeeMachine.setCurrentState(CoffeeMachine.State.CHOOSE_TYPE_COFFEE);
        HashMap<Ingredient, Integer> ingredientStocks = coffeeMachine.getIngredientStocks();

        coffeeMachine.respondUserChoice("back");

        Assert.assertSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
        Assert.assertEquals(400, ingredientStocks.get(WATER).intValue());
        Assert.assertEquals(540, ingredientStocks.get(MILK).intValue());
        Assert.assertEquals(120, ingredientStocks.get(BEANS).intValue());
        Assert.assertEquals(9, ingredientStocks.get(CUPS).intValue());
        Assert.assertEquals(550, ingredientStocks.get(MONEY).intValue());
    }

    @Test
    public void respondUserChoiceIncorrectType() {
        coffeeMachine.setCurrentState(CoffeeMachine.State.CHOOSE_TYPE_COFFEE);

        coffeeMachine.respondUserChoice("44");

        Assert.assertNotSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
        Assert.assertSame(CoffeeMachine.State.CHOOSE_TYPE_COFFEE, coffeeMachine.getCurrentState());
    }

    @Test
    public void respondUserChoiceFillWater() {
        coffeeMachine.setCurrentState(CoffeeMachine.State.FILL_WATER);

        coffeeMachine.respondUserChoice("150");

        Assert.assertEquals(150, coffeeMachine.getIngredientStocks().get(WATER).intValue());
        Assert.assertSame(CoffeeMachine.State.FILL_MILK, coffeeMachine.getCurrentState());
    }

    @Test
    public void respondUserChoiceFillMilk() {
        coffeeMachine.setCurrentState(CoffeeMachine.State.FILL_MILK);

        coffeeMachine.respondUserChoice("200");

        Assert.assertEquals(200, coffeeMachine.getIngredientStocks().get(MILK).intValue());
        Assert.assertSame(CoffeeMachine.State.FILL_BEANS, coffeeMachine.getCurrentState());
    }

    @Test
    public void respondUserChoiceFillBeans() {
        coffeeMachine.setCurrentState(CoffeeMachine.State.FILL_BEANS);

        coffeeMachine.respondUserChoice("100");

        Assert.assertEquals(100, coffeeMachine.getIngredientStocks().get(BEANS).intValue());
        Assert.assertSame(CoffeeMachine.State.FILL_CUPS, coffeeMachine.getCurrentState());
    }

    @Test
    public void respondUserChoiceFillCups() {
        coffeeMachine.setCurrentState(CoffeeMachine.State.FILL_CUPS);

        coffeeMachine.respondUserChoice("15");

        Assert.assertEquals(15, coffeeMachine.getIngredientStocks().get(CUPS).intValue());
        Assert.assertSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
    }

    @Test
    public void respondUserChoiceFillWhenIncorrectInputData() {
        coffeeMachine.setCurrentState(CoffeeMachine.State.FILL_WATER);

        coffeeMachine.respondUserChoice("15 liters");

        Assert.assertEquals(0, coffeeMachine.getIngredientStocks().getOrDefault(CUPS, 0).intValue());
        Assert.assertNotSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
        Assert.assertSame(CoffeeMachine.State.FILL_WATER, coffeeMachine.getCurrentState());
    }

    @Test
    public void processActionChoiceBuy() {
        coffeeMachine.processActionChoice("buy");

        Assert.assertSame(CoffeeMachine.State.CHOOSE_TYPE_COFFEE, coffeeMachine.getCurrentState());
    }

    @Test
    public void processActionChoiceFill() {
        coffeeMachine.processActionChoice("fill");

        Assert.assertSame(CoffeeMachine.State.FILL_WATER, coffeeMachine.getCurrentState());
    }

    @Test
    public void processActionChoiceTake() {
        coffeeMachine.processActionChoice("take");

        Assert.assertEquals(0, coffeeMachine.getIngredientStocks().get(MONEY).intValue());
        Assert.assertSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
    }

    @Test
    public void processActionChoiceRemaining() {
        coffeeMachine.initialLoad();

        coffeeMachine.processActionChoice("remaining");

        Assert.assertSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
    }

    @Test
    public void processActionChoiceExit() {
        coffeeMachine.processActionChoice("exit");

        Assert.assertSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
        Assert.assertTrue(coffeeMachine.isSwitchOff());
    }

    @Test
    public void processActionIncorrectChoice() {
        coffeeMachine.processActionChoice("112");

        Assert.assertSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
    }

    @Test
    public void processCoffeeTypeChoiceEspresso() {
        coffeeMachine.initialLoad();

        coffeeMachine.processCoffeeTypeChoice("1");

        Assert.assertSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
        HashMap<Ingredient, Integer> ingredientStocks = coffeeMachine.getIngredientStocks();
        Assert.assertEquals(150, ingredientStocks.get(WATER).intValue());
        Assert.assertEquals(540, ingredientStocks.get(MILK).intValue());
        Assert.assertEquals(104, ingredientStocks.get(BEANS).intValue());
        Assert.assertEquals(8, ingredientStocks.get(CUPS).intValue());
        Assert.assertEquals(554, ingredientStocks.get(MONEY).intValue());
    }

    @Test
    public void processCoffeeTypeChoiceLatte() {
        coffeeMachine.initialLoad();

        coffeeMachine.processCoffeeTypeChoice("2");

        Assert.assertSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
        HashMap<Ingredient, Integer> ingredientStocks = coffeeMachine.getIngredientStocks();
        Assert.assertEquals(50, ingredientStocks.get(WATER).intValue());
        Assert.assertEquals(465, ingredientStocks.get(MILK).intValue());
        Assert.assertEquals(100, ingredientStocks.get(BEANS).intValue());
        Assert.assertEquals(8, ingredientStocks.get(CUPS).intValue());
        Assert.assertEquals(557, ingredientStocks.get(MONEY).intValue());
    }

    @Test
    public void processCoffeeTypeChoiceCappuccino() {
        coffeeMachine.initialLoad();

        coffeeMachine.processCoffeeTypeChoice("3");

        Assert.assertSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
        HashMap<Ingredient, Integer> ingredientStocks = coffeeMachine.getIngredientStocks();
        Assert.assertEquals(200, ingredientStocks.get(WATER).intValue());
        Assert.assertEquals(440, ingredientStocks.get(MILK).intValue());
        Assert.assertEquals(108, ingredientStocks.get(BEANS).intValue());
        Assert.assertEquals(8, ingredientStocks.get(CUPS).intValue());
        Assert.assertEquals(556, ingredientStocks.get(MONEY).intValue());
    }

    @Test
    public void processCoffeeTypeChoiceBack() {
        coffeeMachine.initialLoad();
        HashMap<Ingredient, Integer> ingredientStocks = coffeeMachine.getIngredientStocks();

        coffeeMachine.processCoffeeTypeChoice("back");

        Assert.assertSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
        Assert.assertEquals(400, ingredientStocks.get(WATER).intValue());
        Assert.assertEquals(540, ingredientStocks.get(MILK).intValue());
        Assert.assertEquals(120, ingredientStocks.get(BEANS).intValue());
        Assert.assertEquals(9, ingredientStocks.get(CUPS).intValue());
        Assert.assertEquals(550, ingredientStocks.get(MONEY).intValue());
    }

    @Test
    public void processCoffeeTypeChoiceIncorrect() {
        coffeeMachine.processCoffeeTypeChoice("44");

        Assert.assertNotSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
        Assert.assertSame(CoffeeMachine.State.CHOOSE_TYPE_COFFEE, coffeeMachine.getCurrentState());
    }

    @Test
    public void processFillIngredientChoiceWater() {
        coffeeMachine.setCurrentState(CoffeeMachine.State.FILL_WATER);

        coffeeMachine.processFillIngredientChoice("150");

        Assert.assertEquals(150, coffeeMachine.getIngredientStocks().get(WATER).intValue());
        Assert.assertSame(CoffeeMachine.State.FILL_MILK, coffeeMachine.getCurrentState());
    }

    @Test
    public void processFillIngredientChoiceMilk() {
        coffeeMachine.setCurrentState(CoffeeMachine.State.FILL_MILK);

        coffeeMachine.processFillIngredientChoice("200");

        Assert.assertEquals(200, coffeeMachine.getIngredientStocks().get(MILK).intValue());
        Assert.assertSame(CoffeeMachine.State.FILL_BEANS, coffeeMachine.getCurrentState());
    }

    @Test
    public void processFillIngredientChoiceBeans() {
        coffeeMachine.setCurrentState(CoffeeMachine.State.FILL_BEANS);

        coffeeMachine.processFillIngredientChoice("100");

        Assert.assertEquals(100, coffeeMachine.getIngredientStocks().get(BEANS).intValue());
        Assert.assertSame(CoffeeMachine.State.FILL_CUPS, coffeeMachine.getCurrentState());
    }

    @Test
    public void processFillIngredientChoiceCups() {
        coffeeMachine.setCurrentState(CoffeeMachine.State.FILL_CUPS);

        coffeeMachine.processFillIngredientChoice("15");

        Assert.assertEquals(15, coffeeMachine.getIngredientStocks().get(CUPS).intValue());
        Assert.assertSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
    }

    @Test
    public void processFillIngredientIncorrectInputDataForWater() {
        coffeeMachine.setCurrentState(CoffeeMachine.State.FILL_WATER);

        coffeeMachine.processFillIngredientChoice("Kilo");

        Assert.assertEquals(0, coffeeMachine.getIngredientStocks().getOrDefault(CUPS, 0).intValue());
        Assert.assertNotSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
        Assert.assertSame(CoffeeMachine.State.FILL_WATER, coffeeMachine.getCurrentState());
    }

    @Test
    public void processFillIngredientChoiceWithIncorrectState() {
        coffeeMachine.setCurrentState(CoffeeMachine.State.CHOOSE_TYPE_COFFEE);

        coffeeMachine.processFillIngredientChoice("Kilo");

        Assert.assertSame(CoffeeMachine.State.CHOOSE_ACTION, coffeeMachine.getCurrentState());
    }

    @Test
    public void takeOutAllMoneyWhenStocksAre() {
        coffeeMachine.initialLoad();

        coffeeMachine.takeOutAllMoney();

        Assert.assertEquals(0, coffeeMachine.getIngredientStocks().get(MONEY).intValue());
    }

    @Test
    public void takeOutAllMoneyWhenStocksAreNot() {
        coffeeMachine.takeOutAllMoney();

        Assert.assertEquals(0, coffeeMachine.getIngredientStocks().get(MONEY).intValue());
    }

    @Test
    public void fillOutIngredientNotMoney() {
        Assert.assertTrue(coffeeMachine.fillOutIngredient(WATER, "1000"));
        Assert.assertEquals(1000, coffeeMachine.getIngredientStocks().get(WATER).intValue());
    }

    @Test
    public void fillOutIngredientNotMoneyIncorrectInputData() {
        Assert.assertFalse(coffeeMachine.fillOutIngredient(WATER, "Liter"));
        Assert.assertNull(coffeeMachine.getIngredientStocks().get(WATER));
    }

    @Test
    public void fillOutIngredientMoney() {
        Assert.assertTrue(coffeeMachine.fillOutIngredient(MONEY, "150"));
        Assert.assertNull(coffeeMachine.getIngredientStocks().get(MONEY));
    }
}