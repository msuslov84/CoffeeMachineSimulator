package com.suslov.jetbrains;

import com.suslov.jetbrains.models.CoffeeMachineTest;
import com.suslov.jetbrains.models.CoffeeTypeTest;
import com.suslov.jetbrains.models.IngredientTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.runners.Suite.SuiteClasses;

/**
 * @author Mikhail Suslov
 */
@RunWith(Suite.class)
@SuiteClasses({
        CoffeeMachineTest.class,
        CoffeeTypeTest.class,
        IngredientTest.class
})
public class AllTestCases {
}
