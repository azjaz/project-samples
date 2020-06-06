package tests.mobile;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.mobile.MobileMenuPage;
import tests.BaseMobile;

public class PizzaAddingTest extends BaseMobile {

    @Test(groups = "mobile.local")
    public void checkAddedPizzaPopUp() {
        boolean result = new MobileMenuPage()
                .enterToStartMenu()
                .pressMenuButton()
                .changeLang()
                .chooseProduct(pizzaModel.getPizzaName())
                .choosePizzaType(pizzaModel.getPizzaType())
                .choosePizzaSize(pizzaModel.getPizzaSize())
                .pressAddToOrderButton()
                .isPopUpPresent();

        Assert.assertTrue(result, "Pop up is not present");
    }

    @Test(groups = "mobile.local")
    public void checkCart() {
        boolean result = new MobileMenuPage()
                .enterToStartMenu()
                .pressMenuButton()
                .changeLang()
                .chooseProduct(pizzaModel.getPizzaName())
                .choosePizzaType(pizzaModel.getPizzaType())
                .choosePizzaSize(pizzaModel.getPizzaSize())
                .doesCartContainItems();

        Assert.assertTrue(result, "After clicking 'add to order' button the pizzas amount is not correct in the cart");
    }
}
