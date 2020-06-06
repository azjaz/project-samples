package pages.mobile;

import enums.mobileparameters.MobilePageParameters;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import models.BonusPizzaModel;
import java.util.HashSet;
import java.util.Set;

public class MobileOrderPage extends MobileBasicPage {
    private String xpathForDeletedIngredients = "//android.webkit.WebView[contains(@content-desc,'Papa')]/android.view.View/android.view.View[2]" +
            "/android.widget.ListView/android.view.View[1]/android.view.View[4]";
    private String xpathForPizzaAmountButton = "//android.widget.Spinner[@content-desc='%s']";
    private String xpathForPizzaAmountSpinner = "//android.widget.CheckedTextView[@text='%s']";

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc, 'promo')]")
    private AndroidElement promoCodeButton;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='PROMO CODE']")
    private AndroidElement promoCodeField;

    @AndroidFindBy(xpath = "//android.widget.Button[@index='1']")
    private AndroidElement sendEnteredPromoCodeButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='GET A GIFT']")
    private AndroidElement getGiftButton;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='prom']")
    private AndroidElement promoCodeConditionPopUp;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='TOTAL']/following-sibling::*[1]")
    private AndroidElement totalPriceInCart;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Cheesy Chicken Cordon Bleu']")
    private AndroidElement freePizzaItemElement;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='traditional crust 23cm']")
    private AndroidElement typeOfFreePizzaItemElement;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='CHOOSE YOUR GIFT']")
    private AndroidElement chooseGiftButton;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Gift']")
    private AndroidElement giftIndicatorElement;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='PLACE MY ORDER']")
    private AndroidElement placeOrderButton;

    public Double getTotalPriceFromCart() {
        waitSleep(3000);
        return getPrice(totalPriceInCart);
    }

    public Set<String> getChangedIngredientsFromCart() {
        String regexSign = "-";
        String[] deletedIngredientsInCart = waiterClickability((AndroidElement) driver.findElementByXPath(xpathForDeletedIngredients))
                .getAttribute(MobilePageParameters.DESCRIPTION.getValue()).split(" ,");
        Set<String> setOfDeletedIngredients = new HashSet<>();
        for (String ingredient : deletedIngredientsInCart) {
            if(!ingredient.contains(regexSign)) {
                ingredient = ingredient.replaceAll("\\+", "").replaceAll("\u00A0", "");
            } else {
                ingredient = ingredient.replace("-", "").replaceAll("\u00A0", "");
            }
            setOfDeletedIngredients.add(ingredient);
        }
        logger.info("Changed ingredients in cart: " + setOfDeletedIngredients.toString());
        return setOfDeletedIngredients;
    }

    public MobileOrderPage sendPromoCode(String promoCode) {
        waiterVisibility(promoCodeButton).click();
        waiterVisibility(promoCodeField).sendKeys(promoCode);
        logger.info("Promo code is entered");
        waiterVisibility(sendEnteredPromoCodeButton).click();
        return this;
    }

    public boolean isPromoCodeAvailable(double totalPrice, double condition) {
        if (totalPrice >= condition) {
            return waiterVisibility(getGiftButton).isDisplayed();
        } else return waiterVisibility(promoCodeConditionPopUp).isDisplayed();
    }

    public MobileOrderPage selectPizzaAmount(int pizzaAmount) {
        waiterVisibility((AndroidElement) driver.findElementByXPath(String.format(xpathForPizzaAmountButton, 1))).click();
        waitWhetherAlertIsPresent();
        driver.switchTo().alert();
        waiterVisibility((AndroidElement) driver.findElementByXPath(String.format(xpathForPizzaAmountSpinner, pizzaAmount))).click();
        return this;
    }


    public MobileOrderPage selectFreePizza() {
        waiterVisibility(getGiftButton).click();
        waiterVisibility(freePizzaItemElement).click();
        waiterVisibility(chooseGiftButton).click();
        return this;
    }

    public boolean isIndicatorDisplayed() {
        return waiterVisibility(giftIndicatorElement).isDisplayed();
    }

    public BonusPizzaModel getActualFreePizza() {
        String pizzaType = "traditional crust ";
        return new BonusPizzaModel(waiterVisibility(freePizzaItemElement).getAttribute(MobilePageParameters.DESCRIPTION.getValue()),
                waiterVisibility(typeOfFreePizzaItemElement).getAttribute(MobilePageParameters.DESCRIPTION.getValue())
                        .replace(pizzaType, "").replaceAll("(\\d.)([cm])", "$1 $2"));
    }

    public MobileAccountPage pressPlaceOrderButton() {
        waiterVisibility(placeOrderButton).click();
        return new MobileAccountPage();
    }
}
