package tests.mobile;

import models.IngredientsSetModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.mobile.MobileCustomPizzaPage;
import pages.mobile.MobileMenuPage;
import services.creators.IngredientSetCreator;
import tests.BaseMobile;

import java.util.Set;

public class PizzaCustomizationTest extends BaseMobile {
    protected IngredientsSetModel ingredientsSetModel = IngredientSetCreator.getIngredientSet();

    @Test(groups = "mobile.local")
    public void deleteIngredientsFromPizzaTest() {
        MobileCustomPizzaPage page = new MobileMenuPage()
                .enterToStartMenu()
                .pressMenuButton()
                .changeLang()
                .chooseProduct(pizzaModel.getPizzaName())
                .pressChangeToppingsButton()
                .deleteIngredient(ingredientsSetModel.getCheeseIngredientSecond())
                .deleteIngredient(ingredientsSetModel.getCheeseIngredientFirst())
                .deleteIngredient(ingredientsSetModel.getVegetablesIngredientFirst())
                .getChangedIngredients();
         Set<String> deletedIngredientsInPreOrder = page.getSetOfDeletedIngredients();
         Set<String> deletedIngredientsInCart = page
                                                .pressSaveAndAddToTheOrderButton()
                                                .pressFilledCartButton()
                                                .getChangedIngredientsFromCart();
        Assert.assertTrue(deletedIngredientsInPreOrder.containsAll(deletedIngredientsInCart)
                && deletedIngredientsInCart.containsAll(deletedIngredientsInPreOrder), "Ingredients don't correspond!");
    }

    @Test(groups = "mobile.local")
    public void checkPizzaPriceAfterAddToppingsTest() {
        MobileMenuPage menuPage = new MobileMenuPage()
                .enterToStartMenu()
                .pressMenuButton()
                .changeLang()
                .chooseProduct(pizzaModel.getPizzaName())
                .chooseDefaultPizzaSize();
        Double priceWithoutToppings = menuPage.getPriceFromPreOrder();
        logger.info("Price without toppings " + priceWithoutToppings);
        MobileCustomPizzaPage customPizzaPage = new MobileMenuPage()
                .pressChangeToppingsButton()
                .addIngredient()
                .definePriceOfToppings();
        Double priceOfToppings = customPizzaPage.getToppingsCost();
        logger.info("Price for toppings " + priceOfToppings);
        Double priceFromCart =customPizzaPage
                .pressSaveAndAddToTheOrderButton()
                .pressFilledCartButton()
                .getTotalPriceFromCart();
        logger.info("Price in cart " + priceFromCart);
        Double sumOfCustomPizza = priceWithoutToppings + priceOfToppings;
        Assert.assertEquals(sumOfCustomPizza, priceFromCart);
    }

    @Test(groups = "mobile.local")
    public void addIngredientsToPizzaTest() {
        MobileCustomPizzaPage page = new MobileMenuPage()
                .enterToStartMenu()
                .pressMenuButton()
                .changeLang()
                .chooseProduct(pizzaModel.getPizzaName())
                .pressChangeToppingsButton()
                .changeIngredientType(ingredientsSetModel.getTypeCheese())
                .addIngredient()
                .getChangedIngredients();
        Set<String> addedIngredientsInPreOrder = page.getSetOfDeletedIngredients();
        Set<String> addedIngredientsInCart = page
                .pressSaveAndAddToTheOrderButton()
                .pressFilledCartButton()
                .getChangedIngredientsFromCart();

        Assert.assertTrue(addedIngredientsInPreOrder.containsAll(addedIngredientsInCart)
                && addedIngredientsInCart.containsAll(addedIngredientsInPreOrder), "Ingredients don't correspond!");
    }
}