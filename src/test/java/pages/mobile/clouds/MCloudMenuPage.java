package pages.mobile.clouds;

import enums.mobileparameters.MobilePageParameters;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import pages.mobile.*;

public class MCloudMenuPage extends MobileBasicPage implements MobileMenuInterface {

    @AndroidFindBy(xpath = "/hierarchy//android.widget.Button[contains(@text, 'МОЙ ГОРОД')]")
    private AndroidElement cityChoiceButton;

    @AndroidFindBy(xpath = "/hierarchy//android.widget.FrameLayout//android.widget.Button[contains(@text, 'Меню')]")
    private AndroidElement dropMenuButton;

    @AndroidFindBy(xpath = "/hierarchy//android.widget.Button[@text='CLOSE']")
    private AndroidElement closePopUpButton;

    @AndroidFindBy(xpath = "/hierarchy//android.widget.Button[@text='ADD TO ORDER']")
    private AndroidElement addToOrderElement;

    private String xpathForDrinksButton = "/hierarchy//android.widget.ListView/android.view.View[contains(@text, '%s')]";
    private String xpathProductSize = "/hierarchy//android.widget.RadioButton[contains(@text, '%s')]";
    private String xpathForCart = "/hierarchy//android.widget.Button[@text='Корзина1']";
    private AndroidElement productSize;

    @Override
    public MCloudMenuPage enterToStartMenu() {
        waiterClickability(cityChoiceButton).click();
        return this;
    }

    @Override
    public MCloudMenuPage pressMenuButton() {
        waiterClickability(dropMenuButton).click();
        return this;
    }

    @Override
    public MCloudMenuPage changeLang() {
        tapOnBoldCoordinates(700, 2200);
        return this;
    }

    @Override
    public MobileCustomPizzaPage chooseProductTypeFromWidgetBar(String productType) {
        AndroidElement product = waiterClickability((AndroidElement) driver.findElementByXPath(String.format(xpathForDrinksButton, productType.toUpperCase())));
        product.click();
        return new MobileCustomPizzaPage();
    }

    @Override
    public MCloudMenuPage choosePizzaSize(String size) {
        scrollScreen();
        productSize = waiterClickability((AndroidElement) driver.findElement(MobileBy.xpath(String.format(xpathProductSize, size))));
        if (!Boolean.parseBoolean(productSize.getAttribute(MobilePageParameters.CHECK_STATUS.getValue()))) {
            tapOnPointCoordinates(productSize.getCenter().moveBy(100, -100));
        }
        logger.info("Size of chosen pizza is " + size);
        return this;
    }

    @Override
    public Double getPriceFromPreOrder() {
        return getPrice(productSize);
    }

    @Override
    public MCloudMenuPage pressAddToOrderButton() {
        addToOrderElement.click();
        logger.info("Add to order button is clicked");
        return this;
    }

    @Override
    public MobileOrderPage pressFilledCartButton() {
        if (isPopUpPresent()) {
            closePopUpButton.click();
        }
        waiterClickability((AndroidElement) driver.findElementByXPath(xpathForCart)).click();
        return new MobileOrderPage();
    }

    @Override
    public MCloudMenuPage pressMenuButtonSecondTime() {
        waiterVisibility(dropMenuButton).click();
        tapOnPointCoordinates(dropMenuButton.getCenter());
        return this;
    }

    @Override
    public MobileStoresPage clickStores() {
        tapOnBoldCoordinates(125, 1000);
        return new MobileStoresPage();
    }

    private boolean isPopUpPresent() {
        return closePopUpButton.isDisplayed();
    }
}
