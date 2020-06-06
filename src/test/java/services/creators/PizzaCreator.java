package services.creators;

import models.PizzaModel;
import models.ProductWidgetModel;

public class PizzaCreator {
    private static final String PIZZA_NAME = "test.pizzaName";
    private static final String PIZZA_TYPE = "test.pizzaType";
    private static final String PIZZA_SIZE = "test.pizzaSize";
    private static final String PRODUCT_TYPE = "test.productType";
    private static final String PRODUCT_NAME = "test.productName";
    private static final String PRODUCT_EXTRA_OPTION = "test.productOption";

    private PizzaCreator() {
    }

    public static PizzaModel getPizzaModel() {
        return new PizzaModel(ConfigurationReader.getValue(PIZZA_NAME),
                ConfigurationReader.getValue(PIZZA_TYPE),
                ConfigurationReader.getValue(PIZZA_SIZE));
    }

    public static ProductWidgetModel getProductWidgetModel() {
        return new ProductWidgetModel(ConfigurationReader.getValue(PRODUCT_TYPE),
                ConfigurationReader.getValue(PRODUCT_NAME),
                ConfigurationReader.getValue(PRODUCT_EXTRA_OPTION));
    }
}


