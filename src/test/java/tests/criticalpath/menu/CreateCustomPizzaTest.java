package tests.criticalpath.menu;

import models.IngredientsSetModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CreateOwnPizzaPage;
import pages.CustomPizzaPage;
import pages.NavigationBarPage;
import services.creators.IngredientSetCreator;
import tests.BaseUI;

public class CreateCustomPizzaTest extends BaseUI {
    protected IngredientsSetModel ingredientsSetModel = IngredientSetCreator.getIngredientSet();

    @Test
    public void deleteIngredientsFromPizzaTest() {
        CustomPizzaPage page = new NavigationBarPage()
                .openPage()
                .switchToEnglishVersion()
                .choosePizzaType(pizzaModel.getPizzaName(), pizzaModel.getPizzaType())
                .choosePizzaSize(pizzaModel.getPizzaName(), pizzaModel.getPizzaSize())
                .pressIngredientDropButton(pizzaModel.getPizzaName())
                .deleteIngredients(pizzaModel.getPizzaName(),
                        ingredientsSetModel.getCheeseIngredientFirst(),
                        ingredientsSetModel.getCheeseIngredientSecond(),
                        ingredientsSetModel.getVegetablesIngredientFirst())
                .addPizzaToCart();
        logger.info("deleted in cart " + page.getDeletedIngredientsInCart().toString());
        logger.info("deleted on page " + page.getIngredientsToDelete().toString());

        Assert.assertTrue(page.getDeletedIngredientsInCart().containsAll(page.getIngredientsToDelete())
                && page.getIngredientsToDelete().containsAll(page.getDeletedIngredientsInCart()), "Ingredients don't correspond!");
    }

    @Test
    public void addIngredientToPizzaTest() {
        CreateOwnPizzaPage page = new NavigationBarPage()
                .openPage()
                .switchToEnglishVersion()
                .choosePizzaType(pizzaModel.getPizzaName(), pizzaModel.getPizzaType())
                .choosePizzaSize(pizzaModel.getPizzaName(), pizzaModel.getPizzaSize())
                .pressIngredientDropButton(pizzaModel.getPizzaName())
                .pressAddIngredientsButton()
                .pressTypeOfIngredientButton(ingredientsSetModel.getTypeMeat())
                .pressIngredientButton(ingredientsSetModel.getMeatIngredientFirst());
        Double totalPriceOfOwnPizza = page.getTotalPriceOfOwnPizza();
        Double priceOfOwnPizzaInCart = page.pressAddToCartOwnPizzaButton()
                .pressReturnToMenuButton()
                .getTotalPriceFromMenuCart();
        logger.info("price on page " + totalPriceOfOwnPizza);
        logger.info("price in cart " + priceOfOwnPizzaInCart);
        Assert.assertEquals(totalPriceOfOwnPizza, priceOfOwnPizzaInCart, "Price does not correspond! Customer's full of fury!");
    }
}
