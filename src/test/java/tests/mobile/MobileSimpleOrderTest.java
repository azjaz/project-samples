package tests.mobile;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.mobile.MobileMenuPage;
import tests.BaseMobile;

public class MobileSimpleOrderTest extends BaseMobile {

    @Test(groups = "mobile.local")
    public void makeBasicOrderTest() {
        MobileMenuPage page = new MobileMenuPage()
                .enterToStartMenu()
                .pressMenuButton()
                .changeLang()
                .chooseProduct(pizzaModel.getPizzaName())
                .choosePizzaType(pizzaModel.getPizzaType())
                .choosePizzaSize(pizzaModel.getPizzaSize());
        Double priceInPreOrder = page.getPriceFromPreOrder();
        logger.info("Price in pre-order " + priceInPreOrder);
        Double priceInCart =   page
                    .pressAddToOrderButton()
                    .pressFilledCartButton()
                    .getTotalPriceFromCart();
        logger.info("Price in cart " + priceInCart);
        Assert.assertEquals(priceInPreOrder, priceInCart, "Houston, we have problem with price");
    }

}
