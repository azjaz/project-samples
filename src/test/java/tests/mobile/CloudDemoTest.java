package tests.mobile;

import models.ProductWidgetModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.mobile.MobileMenuInterface;
import pages.mobile.MobileMenuPage;
import services.NavigationBarPageCreator;
import services.creators.PizzaCreator;
import tests.BaseMobile;

public class CloudDemoTest extends BaseMobile {
    private ProductWidgetModel widgetModel = PizzaCreator.getProductWidgetModel();

    @Test(groups = {"mobile.cloud", "mobile.slabs"})
    public void nonPizzaOrderTest() {
        MobileMenuInterface page = new NavigationBarPageCreator()
                .createMobileMenuPage()
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

    @Test(groups = {"mobile.cloud", "mobile.slabs"})
    public void checkVisibleStoresAmount() {
        int expectedStoresAmount = 12;
        int storesAmount = new NavigationBarPageCreator()
                .createMobileMenuPage()
                .enterToStartMenu()
                .pressMenuButton()
                .changeLang()
                .pressMenuButtonSecondTime()
                .clickStores()
                .allowPermissionRequest()
                .getStoresAmount();

        Assert.assertEquals(storesAmount, expectedStoresAmount);
    }
}
