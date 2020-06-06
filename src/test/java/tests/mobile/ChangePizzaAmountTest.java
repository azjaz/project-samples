package tests.mobile;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.mobile.MobileMenuPage;
import pages.mobile.MobileOrderPage;
import tests.BaseMobile;

public class ChangePizzaAmountTest extends BaseMobile {
    private int givenPizzaAmount = 3;

    @Test(groups = "mobile.local")
    public void checkChangePizzaAmount() {
        MobileOrderPage page = new MobileMenuPage()
                .enterToStartMenu()
                .pressMenuButton()
                .changeLang()
                .chooseProduct(pizzaModel.getPizzaName())
                .choosePizzaType(pizzaModel.getPizzaType())
                .choosePizzaSize(pizzaModel.getPizzaSize())
                .pressAddToOrderButton()
                .pressFilledCartButton();

        double totalPriceForOnePizza = page.getTotalPriceFromCart();
        logger.info("Price for one added pizza in the cart is " + totalPriceForOnePizza);
        double totalPriceForGivenPizzaAmountInCart = page
                .selectPizzaAmount(givenPizzaAmount)
                .getTotalPriceFromCart();
        logger.info(String.format("Price for %d added pizzas is ", givenPizzaAmount) + totalPriceForGivenPizzaAmountInCart);

        double totalPriceForGivenPizzaAmount = totalPriceForOnePizza * givenPizzaAmount;

        Assert.assertEquals(totalPriceForGivenPizzaAmount, totalPriceForGivenPizzaAmountInCart,
                String.format("Total price for %d pizzas (%s) doesn't equal to price for one pizza (%s) multiplied by given amount", givenPizzaAmount, totalPriceForGivenPizzaAmount, totalPriceForOnePizza));

    }
}
