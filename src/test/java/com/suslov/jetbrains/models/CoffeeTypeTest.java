package com.suslov.jetbrains.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static com.suslov.jetbrains.models.Ingredient.*;

/**
 * @author Mikhail Suslov
 */
public class CoffeeTypeTest {
    private final HashMap<Ingredient, Integer> testStocks = new HashMap<>();

    @Before
    public void setUp() {
        testStocks.put(WATER, 400);
        testStocks.put(MILK, 540);
        testStocks.put(BEANS, 120);
        testStocks.put(CUPS, 9);
        testStocks.put(MONEY, 550);
    }

    @Test
    public void makeACoffeeEspressoWithEnoughIngredients() {
        CoffeeType.ESPRESSO.makeACoffee(testStocks);

        Assert.assertEquals(150, testStocks.get(WATER).intValue());
        Assert.assertEquals(540, testStocks.get(MILK).intValue());
        Assert.assertEquals(104, testStocks.get(BEANS).intValue());
        Assert.assertEquals(8, testStocks.get(CUPS).intValue());
        Assert.assertEquals(554, testStocks.get(MONEY).intValue());
    }

    @Test
    public void makeACoffeeEspressoNotEnoughIngredients() {
        testStocks.put(WATER, 200);

        CoffeeType.ESPRESSO.makeACoffee(testStocks);

        Assert.assertEquals(200, testStocks.get(WATER).intValue());
        Assert.assertEquals(540, testStocks.get(MILK).intValue());
        Assert.assertEquals(120, testStocks.get(BEANS).intValue());
        Assert.assertEquals(9, testStocks.get(CUPS).intValue());
        Assert.assertEquals(550, testStocks.get(MONEY).intValue());
    }

    @Test
    public void makeACoffeeLatteWithEnoughIngredients() {
        CoffeeType.LATTE.makeACoffee(testStocks);

        Assert.assertEquals(50, testStocks.get(WATER).intValue());
        Assert.assertEquals(465, testStocks.get(MILK).intValue());
        Assert.assertEquals(100, testStocks.get(BEANS).intValue());
        Assert.assertEquals(8, testStocks.get(CUPS).intValue());
        Assert.assertEquals(557, testStocks.get(MONEY).intValue());
    }

    @Test
    public void makeACoffeeLatteNotEnoughIngredients() {
        testStocks.put(WATER, 300);
        testStocks.put(MILK, 70);

        CoffeeType.LATTE.makeACoffee(testStocks);

        Assert.assertEquals(300, testStocks.get(WATER).intValue());
        Assert.assertEquals(70, testStocks.get(MILK).intValue());
        Assert.assertEquals(120, testStocks.get(BEANS).intValue());
        Assert.assertEquals(9, testStocks.get(CUPS).intValue());
        Assert.assertEquals(550, testStocks.get(MONEY).intValue());
    }

    @Test
    public void makeACoffeeCappuccinoWithEnoughIngredients() {
        CoffeeType.CAPPUCCINO.makeACoffee(testStocks);

        Assert.assertEquals(200, testStocks.get(WATER).intValue());
        Assert.assertEquals(440, testStocks.get(MILK).intValue());
        Assert.assertEquals(108, testStocks.get(BEANS).intValue());
        Assert.assertEquals(8, testStocks.get(CUPS).intValue());
        Assert.assertEquals(556, testStocks.get(MONEY).intValue());

    }

    @Test
    public void makeACoffeeCappuccinoNotEnoughIngredients() {
        testStocks.put(MONEY, 5);

        CoffeeType.CAPPUCCINO.makeACoffee(testStocks);

        Assert.assertEquals(400, testStocks.get(WATER).intValue());
        Assert.assertEquals(540, testStocks.get(MILK).intValue());
        Assert.assertEquals(120, testStocks.get(BEANS).intValue());
        Assert.assertEquals(9, testStocks.get(CUPS).intValue());
        Assert.assertEquals(5, testStocks.get(MONEY).intValue());
    }
}