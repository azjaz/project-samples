package pages.mobile;

import enums.mobileparameters.MobilePageParameters;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;

public class MobileMenuPage extends MobileBasicPage implements MobileMenuInterface {
    private String xPathForCart = "//android.widget.Button[@content-desc='Корзина %s']";
    private String xpathProductSize = "//android.widget.RadioButton[contains(@content-desc, '%s')]";
    private String xpathBasicSizeOfProduct = "//android.widget.RadioButton[contains(@content-desc, 'sm Small')]";
    private AndroidElement productSize;

    @AndroidFindBy(xpath = "/hierarchy//android.widget.Button[contains(@text, 'МОЙ ГОРОД')]")
    private AndroidElement cityChoiceButton;

    @AndroidFindBy(xpath = "/hierarchy//android.widget.FrameLayout//android.widget.Button[contains(@text, 'Меню')]")
    private AndroidElement dropMenuButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='ADD TO ORDER']")
    private AndroidElement addToOrderElement;

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'BYN')]")
    private AndroidElement checkPizzaAreaLoaded;

    @AndroidFindBy(accessibility = "CLOSE")
    private AndroidElement closePopUpButton;

    @AndroidFindBy(xpath = "//android.webkit.WebView[contains(@content-desc,'Papa')]/android.view.View/android.view.View[2]/android.view.View[3]")
    private AndroidElement changeToppingsButton;

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc, 'FILTER')]")
    private AndroidElement filterButton;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='ENG']")
    private AndroidElement engButton;

    @Override
    public MobileMenuPage enterToStartMenu() {
        waiterClickability(cityChoiceButton).click();
        return this;
    }

    @Override
    public MobileMenuPage pressMenuButton() {
        waiterClickability(dropMenuButton).click();
        return this;
    }


    @Override
    public MobileMenuPage pressMenuButtonSecondTime() {
        waitSleep(2000);
        waiterVisibility(dropMenuButton).click();
        tapOnPointCoordinates(dropMenuButton.getCenter());
        return this;
    }

    @Override
    public MobileMenuPage changeLang() {
        waitSleep(5000);
        tapOnBoldCoordinates(350, 1513);
        return this;
    }

    public MobileMenuPage chooseProduct(String productName) {
        waitSleep(4000);
        scrollScreen();
        AndroidElement pizzaName = waiterClickability((AndroidElement) driver.findElementByAccessibilityId(productName));
        pizzaName.click();
        logger.info("Pizza " + productName + " was chosen");
        return this;
    }


    public MobileMenuPage choosePizzaType(String productType) {
        AndroidElement pizzaType = waiterClickability((AndroidElement) driver.findElementByAccessibilityId(productType));
        if (!Boolean.parseBoolean(pizzaType.getAttribute(MobilePageParameters.CHECK_STATUS.getValue()))) {
            tapOnPointCoordinates(pizzaType.getCenter().moveBy(100, 0));
        }
        logger.info(productType + " type was chosen");
        return this;
    }

    @Override
    public MobileMenuPage choosePizzaSize(String size) {
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
    public MobileMenuPage pressAddToOrderButton() {
        addToOrderElement.click();
        logger.info("Add to order button is clicked");
        return this;
    }

    public MobileOrderPage pressFilledCartButton() {
        if (isPopUpPresent()) {
            closePopUpButton.click();
        }
        waitSleep(2000);
        waiterClickability((AndroidElement) driver.findElementByXPath(String.format(xPathForCart, "1"))).click();
        return new MobileOrderPage();
    }


    public MobileCustomPizzaPage pressChangeToppingsButton() {
        waiterVisibility(changeToppingsButton).click();
        return new MobileCustomPizzaPage();
    }

    public boolean isPopUpPresent() {
        return closePopUpButton.isDisplayed();
    }

    public MobileStoresPage clickStores() {
        waitSleep(5000);
        tapOnBoldCoordinates(125, 1000);
        return new MobileStoresPage();
    }

    public boolean doesCartContainItems() {
        for (int i = 1; i < 4; i++) {
            pressAddToOrderButton();
            if (isPopUpPresent()) {
                closePopUpButton.click();
                waiterClickability((AndroidElement) driver.findElement(By.xpath(String.format(xPathForCart, i))));
                if (i == 1) {
                    logger.info(String.format("There is %s pizza into the cart", i));
                } else logger.info(String.format("There are %s pizzas into the cart", i));
            } else return false;
        }
        return true;
    }

    public MobileMenuPage chooseDefaultPizzaSize() {
        productSize = waiterClickability((AndroidElement) driver.findElement(MobileBy.xpath(xpathBasicSizeOfProduct)));
        logger.info("Size of chosen pizza is default");
        return this;
    }

    @Override
    public MobileCustomPizzaPage chooseProductTypeFromWidgetBar(String productType) {
        AndroidElement product = waiterClickability((AndroidElement) driver.findElementByAccessibilityId(productType.toUpperCase()));
        product.click();
        return new MobileCustomPizzaPage();
    }

    public MobileCustomPizzaPage pressFilterButton() {
        waitSleep(4000);
        waiterClickability(filterButton).click();
        return new MobileCustomPizzaPage();
    }

}
