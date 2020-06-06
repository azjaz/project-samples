package services.creators;

import models.BonusPizzaModel;

public class BonusPizzaCreator {
    private static final String TEST_DATA_PIZZA_NAME = "test.bonusPizzaName";
    private static final String TEST_DATA_PIZZA_SIZE = "test.bonusPizzaSize";

    private BonusPizzaCreator() {
    }

    public static BonusPizzaModel withPizzaSize() {
        return new BonusPizzaModel(ConfigurationReader.getValue(TEST_DATA_PIZZA_NAME), ConfigurationReader.getValue(TEST_DATA_PIZZA_SIZE));
    }
}
