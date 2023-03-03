package com.suslov.jetbrains.models;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Mikhail Suslov
 */
public class IngredientTest {

    @Test
    public void remainderRepresentationForWater() {
        Assert.assertEquals("13 ml of water", Ingredient.WATER.remainderRepresentation(13));
    }

    @Test
    public void remainderRepresentationForMilk() {
        Assert.assertEquals("0 ml of milk", Ingredient.MILK.remainderRepresentation(0));
    }

    @Test
    public void remainderRepresentationForBeans() {
        Assert.assertEquals("100 g of coffee beans", Ingredient.BEANS.remainderRepresentation(100));
    }

    @Test
    public void remainderRepresentationForCups() {
        Assert.assertEquals("9 disposable cups", Ingredient.CUPS.remainderRepresentation(9));
    }

    @Test
    public void remainderRepresentationForMoney() {
        Assert.assertEquals("$505 of money", Ingredient.MONEY.remainderRepresentation(505));
    }
}