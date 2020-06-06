package pages.mobile.clouds;

import enums.mobileparameters.MobilePageParameters;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import pages.mobile.*;
import utils.constants.DataConstants;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SLabsMenuPage extends MobileBasicPage implements MobileMenuInterface {

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='МОЙ ГОРОД — МИНСК']")
    private AndroidElement cityChoiceButton;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout//android.widget.Button[@content-desc='Меню']")
    private AndroidElement dropMenuButton;

    @AndroidFindBy(accessibility = "CLOSE")
    private AndroidElement closePopUpButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='ADD TO ORDER']")
    private AndroidElement addToOrderElement;

    private String xpathProductSize = "//android.widget.RadioButton[contains(@content-desc, '%s')]";
    private AndroidElement productSize;

    @Override
    public SLabsMenuPage enterToStartMenu() {
        waiterClickability(cityChoiceButton).click();
        return this;
    }

    @Override
    public SLabsMenuPage pressMenuButton() {
        waiterClickability(dropMenuButton).click();
        return this;
    }

    @Override
    public SLabsMenuPage changeLang() {
        tapOnBoldCoordinates(320, 1150);
        return this;

    }

    @Override
    public MobileCustomPizzaPage chooseProductTypeFromWidgetBar(String productType) {
        AndroidElement product = waiterClickability((AndroidElement) driver.findElementByAccessibilityId(productType.toUpperCase()));
        product.click();
        return new MobileCustomPizzaPage();
    }

    @Override
    public SLabsMenuPage choosePizzaSize(String size) {
        productSize = waiterClickability((AndroidElement) driver.findElement(MobileBy.xpath(String.format(xpathProductSize, size))));
        if (!Boolean.parseBoolean(productSize.getAttribute(MobilePageParameters.CHECK_STATUS.getValue()))) {
            tapOnPointCoordinates(productSize.getCenter().moveBy(100, -64));
        }
        logger.info("Size of chosen product is " + size);
        return this;
    }

    @Override
    public Double getPriceFromPreOrder() {
        waiterClickability(productSize);
        String totalInCart = Arrays.stream(productSize.getAttribute(MobilePageParameters.DESCRIPTION.getValue()).split("\\n"))
                .filter(p -> p.contains(DataConstants.getCurrencyCode()))
                .collect(Collectors.toList()).get(0);
        totalInCart = totalInCart.replace(DataConstants.getCurrencyCode(), "").trim();
        return Double.parseDouble(totalInCart);
    }

    @Override
    public SLabsMenuPage pressAddToOrderButton() {
        addToOrderElement.click();
        logger.info("Add to order button is clicked");
        return this;
    }

    @Override
    public MobileOrderPage pressFilledCartButton() {
        String xpathForCartA6 = "//android.widget.Button[@content-desc='Корзина1']";
        if (isPopUpPresent()) {
            closePopUpButton.click();
        }
        waiterClickability((AndroidElement) driver.findElementByXPath(xpathForCartA6)).click();
        return new MobileOrderPage();
    }

    @Override
    public SLabsMenuPage pressMenuButtonSecondTime() {
        waiterVisibility(dropMenuButton).click();
        tapOnPointCoordinates(dropMenuButton.getCenter());
        return this;
    }

    @Override
    public MobileStoresPage clickStores() {
        tapOnBoldCoordinates(125, 760);
        return new MobileStoresPage();
    }
    private boolean isPopUpPresent() {
        return closePopUpButton.isDisplayed();
    }
}
