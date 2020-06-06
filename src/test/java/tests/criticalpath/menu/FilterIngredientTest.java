package tests.criticalpath.menu;

import models.IngredientsSetModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FilteringPage;
import pages.NavigationBarPage;
import services.creators.IngredientSetCreator;
import tests.BaseUI;

public class FilterIngredientTest extends BaseUI {
    private IngredientsSetModel ingredientsSetModel = IngredientSetCreator.getIngredientSet();
    private static final String INGREDIENT_FOR_PREDEFINED_FILTER = "chicken";
    private static final String SUCCESS_MESSAGE = "Pizzas was filtered - ";
    private static final String FAIL_MESSAGE = "Filtering is not correct";

    @Test
    public void checkPresenceOfFilteredIngredientInPizzaDescription() {
        FilteringPage page = new NavigationBarPage()
                .openPage()
                .switchToEnglishVersion()
                .pressFilterButton()
                .pressOnItemToFilter(ingredientsSetModel.getTypeMeat(), ingredientsSetModel.getMeatIngredientSecond());
        int descriptionsFromElements = page.collectFilteredElementsDescriptionsOnPage();
        int elementsAfterCheck = page.checkFilteredElementInPizzaDescription(ingredientsSetModel.getMeatIngredientSecond());
        logger.info(SUCCESS_MESSAGE + descriptionsFromElements);
        logger.info("Pizzas contain filtered element in ingredient list - " + elementsAfterCheck);

        Assert.assertEquals(descriptionsFromElements, elementsAfterCheck, FAIL_MESSAGE);
    }

    @Test
    public void checkPresenceOfFilteredElementInPizzaIngredientList() {
        FilteringPage page = new NavigationBarPage()
                .openPage()
                .switchToEnglishVersion()
                .pressFilterButton()
                .pressOnItemToFilter(ingredientsSetModel.getTypeVegetables(), ingredientsSetModel.getVegetablesIngredientFirst())
                .getAllIngredientsFromDroppedList();
        int elementsAfterFilteringOnPage = page.collectFilteredElementsDescriptionsOnPage();
        int pizzasListAfterFiltering = page.checkFilteredElementInPizzasIngredients(ingredientsSetModel.getVegetablesIngredientFirst());
        logger.info(SUCCESS_MESSAGE + elementsAfterFilteringOnPage);
        logger.info("Pizzas contain filtered element in description - " + pizzasListAfterFiltering);

        Assert.assertEquals(elementsAfterFilteringOnPage, pizzasListAfterFiltering, FAIL_MESSAGE);

    }

    @Test
    public void checkFilteringWithPreDefinedFilter() {
        FilteringPage page = new NavigationBarPage()
                .openPage()
                .switchToEnglishVersion()
                .pressPreDefinedFilterButton();
        int descriptionsOfFilteredPizzas = page.collectFilteredElementsDescriptionsOnPage();
        int listOfPizzasAfterFiltering = page.checkFilteredElementInPizzaDescription(INGREDIENT_FOR_PREDEFINED_FILTER);
        logger.info(SUCCESS_MESSAGE + descriptionsOfFilteredPizzas);
        logger.info("Pizzas contain filtered element in description - " + listOfPizzasAfterFiltering);

        Assert.assertEquals(descriptionsOfFilteredPizzas, listOfPizzasAfterFiltering, FAIL_MESSAGE);

    }
}
