package tests.mobile;

import models.BonusPizzaModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.mobile.MobileMenuPage;
import pages.mobile.MobileOrderPage;
import services.creators.BonusPizzaCreator;
import tests.BaseMobile;
import utils.constants.DataConstants;

public class PromoCodeTest extends BaseMobile {
    private int givenPizzaAmount = 2;
    private double minSumForUsingPromoCode = 32.00;

    @Test(groups = "mobile.local")
    public void checkPromoCodeAvailability() {
        MobileOrderPage page = new MobileMenuPage()
                .enterToStartMenu()
                .pressMenuButton()
                .changeLang()
                .chooseProduct(pizzaModel.getPizzaName())
                .choosePizzaType(pizzaModel.getPizzaType())
                .choosePizzaSize(pizzaModel.getPizzaSize())
                .pressAddToOrderButton()
                .pressFilledCartButton();

        double totalPriceFromCart = page.getTotalPriceFromCart();
        boolean promoCodeAvailability = page
                .sendPromoCode(DataConstants.getPromoCodeForTests())
                .isPromoCodeAvailable(totalPriceFromCart, minSumForUsingPromoCode);

        Assert.assertTrue(promoCodeAvailability,
                String.format("Promo code doesn't exist anymore or there is not a notification about an ability to use %s promo code", DataConstants.getPromoCodeForTests()));
    }

    @Test(groups = "mobile.local")
    public void checkPromoCodeUsing() {
        MobileOrderPage page = new MobileMenuPage()
                .enterToStartMenu()
                .pressMenuButton()
                .changeLang()
                .chooseProduct(pizzaModel.getPizzaName())
                .choosePizzaType(pizzaModel.getPizzaType())
                .choosePizzaSize(pizzaModel.getPizzaSize())
                .pressAddToOrderButton()
                .pressFilledCartButton()
                .selectPizzaAmount(givenPizzaAmount);

        double totalPriceBeforeUsingPromo = page.getTotalPriceFromCart();
        double totalPriceAfterUsingPromo = page
                .sendPromoCode(DataConstants.getPromoCodeForTests())
                .selectFreePizza()
                .getTotalPriceFromCart();

        boolean indicatorResult = page.isIndicatorDisplayed();
        BonusPizzaModel actualFreePizza = page.getActualFreePizza();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(totalPriceBeforeUsingPromo, totalPriceAfterUsingPromo, String.format("Promo code doesn't use correctly. Total price before using promo code(%s) doesn't equal to total price after using promo code(%s)", totalPriceBeforeUsingPromo, totalPriceAfterUsingPromo));
        softAssert.assertTrue(indicatorResult, "Gift indicator is not present");
        softAssert.assertEquals(actualFreePizza, BonusPizzaCreator.withPizzaSize(), String.format("Free pizza from the cart %s doesn't equal to free pizza from a property file %s", actualFreePizza, BonusPizzaCreator.withPizzaSize()));
        softAssert.assertAll();

    }
}
