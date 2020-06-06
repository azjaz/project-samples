package tests.mobile;

import models.ProductWidgetModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.mobile.MobileMenuPage;
import services.creators.PizzaCreator;
import tests.BaseMobile;

public class NonPizzaProductOrderTest extends BaseMobile {
    private ProductWidgetModel widgetModel = PizzaCreator.getProductWidgetModel();

    @Test(groups = "mobile.local")
    public void orderDrinkTest() {
        MobileMenuPage page = new MobileMenuPage()
                .enterToStartMenu()
                .pressMenuButton()
                .changeLang()
                .chooseProductTypeFromWidgetBar(widgetModel.getProductType())
                .chooseProduct(widgetModel.getProductName())
                .choosePizzaSize(widgetModel.getExtraOption());
        Double productPriceInPreOrder = page.getPriceFromPreOrder();
        Double priceInCart = page
                                .pressAddToOrderButton()
                                .pressFilledCartButton()
                                .getTotalPriceFromCart();
        logger.info("Price in pre-order: " + productPriceInPreOrder);
        logger.info("Price in cart: " + priceInCart);
        Assert.assertEquals(productPriceInPreOrder, priceInCart, "Something goes wrong with price");
    }
}
