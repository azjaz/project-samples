package services.creators;

import models.IngredientsSetModel;

public class IngredientSetCreator {
    private static final String CHEESE_TYPE = "test.cheeseType";
    private static final String MEAT_TYPE = "test.meatType";
    private static final String VEGGIES_TYPE = "test.veggiesType";
    private static final String CHEESE_INGREDIENT_FIRST = "test.cheeseIngredientFirst";
    private static final String CHEESE_INGREDIENT_SECOND = "test.cheeseIngredientSecond";
    private static final String MEAT_INGREDIENT_FIRST = "test.meatIngredientFirst";
    private static final String MEAT_INGREDIENT_SECOND = "test.meatIngredientSecond";
    private static final String VEGGIE_INGREDIENT_FIRST = "test.veggieIngredientFirst";
    private static final String VEGGIE_INGREDIENT_SECOND = "test.veggieIngredientSecond";

    private IngredientSetCreator() {
    }

    public static IngredientsSetModel getIngredientSet() {
        return new IngredientsSetModel(ConfigurationReader.getValue(CHEESE_TYPE),
                ConfigurationReader.getValue(MEAT_TYPE),
                ConfigurationReader.getValue(VEGGIES_TYPE),
                ConfigurationReader.getValue(CHEESE_INGREDIENT_FIRST),
                ConfigurationReader.getValue(CHEESE_INGREDIENT_SECOND),
                ConfigurationReader.getValue(MEAT_INGREDIENT_FIRST),
                ConfigurationReader.getValue(MEAT_INGREDIENT_SECOND),
                ConfigurationReader.getValue(VEGGIE_INGREDIENT_FIRST),
                ConfigurationReader.getValue(VEGGIE_INGREDIENT_SECOND)
        );
    }
}
