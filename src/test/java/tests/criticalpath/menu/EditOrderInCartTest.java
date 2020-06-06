package tests.criticalpath.menu;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.NavigationBarPage;
import tests.BaseUI;

public class EditOrderInCartTest extends BaseUI {
    private static final int PIZZA_AMOUNT = 2;

    @Test
    public void editOrderInCart() {
        boolean result = new NavigationBarPage().
                openPage().
                switchToEnglishVersion().
                choosePizzaSize(pizzaModel.getPizzaName(), pizzaModel.getPizzaSize()).
                addPizzaToCart(pizzaModel.getPizzaName()).
                clickPlusButton(PIZZA_AMOUNT).
                clickMinusButton(PIZZA_AMOUNT).
                clickClearButton().
                compareSavedPrices(PIZZA_AMOUNT);

        Assert.assertTrue(result, "Prices after clicking plus or minus buttons aren't right " +
                "or after clicking clear button there is any pizza into the cart");
    }

}


