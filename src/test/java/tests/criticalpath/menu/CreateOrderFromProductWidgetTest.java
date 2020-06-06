package tests.criticalpath.menu;

import models.ProductWidgetModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MenuPage;
import pages.NavigationBarPage;
import services.creators.PizzaCreator;
import tests.BaseUI;

public class CreateOrderFromProductWidgetTest extends BaseUI {
    private ProductWidgetModel productModel = PizzaCreator.getProductWidgetModel();

    @Test
    public void createNonPizzaOrderTest() {
        MenuPage page = new NavigationBarPage()
                .openPage()
                .switchToEnglishVersion()
                .pressWidgetButton(productModel.getProductType())
                .choosePizzaType(productModel.getProductName(), productModel.getExtraOption())
                .addPizzaToCart(productModel.getProductName());
        logger.info("price on page " + page.getTotalPriceOnPage(productModel.getProductName()));
        logger.info("price in cart " + page.getTotalPriceFromMenuCart());
        Assert.assertEquals(page.getTotalPriceOnPage(productModel.getProductName()), page.getTotalPriceFromMenuCart());

    }
}
