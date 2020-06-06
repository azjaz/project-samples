package tests.criticalpath.menu;

import models.IngredientsSetModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CreateOwnPizzaPage;
import pages.NavigationBarPage;
import services.creators.IngredientSetCreator;
import tests.BaseUI;

public class CreateOwnPizzaTest extends BaseUI {
    protected IngredientsSetModel ingredientsSetModel = IngredientSetCreator.getIngredientSet();

    @Test
    public void createOwnPizzaTest() {

        CreateOwnPizzaPage page = new NavigationBarPage()
                .openPage()
                .switchToEnglishVersion()
                .pressCreateOwnPizzaButton()
                .pressTypeOfIngredientButton(ingredientsSetModel.getTypeCheese())
                .pressIngredientButton(ingredientsSetModel.getCheeseIngredientFirst())
                .pressTypeOfIngredientButton(ingredientsSetModel.getTypeMeat())
                .pressIngredientButton(ingredientsSetModel.getMeatIngredientFirst())
                .pressTypeOfIngredientButton(ingredientsSetModel.getTypeVegetables())
                .pressIngredientButton(ingredientsSetModel.getVegetablesIngredientFirst())
                .pressIngredientButton(ingredientsSetModel.getVegetablesIngredientSecond())
                .pressTrashIconForDeleteIngredient(ingredientsSetModel.getVegetablesIngredientSecond());
        Double totalPriceOfOwnPizza = page.getTotalPriceOfOwnPizza();

        Double priceOfOwnPizzaInCart = page.pressAddToCartOwnPizzaButton()
                .pressReturnToMenuButton()
                .getTotalPriceFromMenuCart();
        logger.info("price in page " + totalPriceOfOwnPizza);
        logger.info("price in cart " + priceOfOwnPizzaInCart);
        Assert.assertEquals(totalPriceOfOwnPizza, priceOfOwnPizzaInCart);

    }
}
