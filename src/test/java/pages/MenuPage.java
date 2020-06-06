package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.JSUtils;
import utils.XpathCreatorForPizza;

import java.util.*;

public class MenuPage extends BasePage {
    private Deque<Double> stackWithPrices = new ArrayDeque<>();

    @FindBy(xpath = "//div[contains(@class, 'GfdtG')]//div[contains(@class, '_25gak')]")
    private WebElement totalPriceInCart;

    @FindBy(xpath = "//div[contains(@class, 'Cart__content')]//h5[contains(text(), '')]")
    private WebElement pizzaNameInCart;

    @FindBy(xpath = "//span[text()='Order now']")
    private WebElement orderNowButton;

    @FindBy(xpath = "//span[contains(text(), 'Create pizza')]")
    private WebElement createPizzaButton;

    @FindBy(xpath = "//span[text()='+']/parent::button")
    private WebElement plusButton;

    @FindBy(xpath = "//button[contains(@class,'_5uZDU') and contains(@class, 'Xgycu')]")
    private WebElement minusButton;

    @FindBy(xpath = "//span[text()='Clear']/parent::a")
    private WebElement clearButton;

    @FindBy(xpath = "//span[contains(text(), 'empty')]/parent::div")
    private WebElement expectedNotification;

    @FindBy(xpath = "//button[contains(@class, 'hPb0v') and contains(@class, 'uaNCO')]")
    private WebElement filterButton;

    @FindBy(xpath = "//button[contains(text(), 'chicken')]")
    private WebElement preDefinedFilterButton;

    @FindBy(xpath = "//div[contains(@class,'slide-active')]/child::img")
    private WebElement activeBanner;

    public MenuPage choosePizzaType(String name, String pizzaType) {
        WebElement pizzaTypeButton = waiterVisibility(driver.findElement(By.xpath(XpathCreatorForPizza.xpathForPizzaOption(name, pizzaType))));
        JSUtils.highlightElement(pizzaTypeButton);
        pizzaTypeButton.click();
        JSUtils.unHighlightElement(pizzaTypeButton);
        return this;
    }

    public MenuPage choosePizzaSize(String name, String pizzaSize) {
        WebElement pizzaSizeButton = waiterOfPresence(XpathCreatorForPizza.xpathForPizzaOption(name, pizzaSize));
        JSUtils.highlightElement(pizzaSizeButton);
        pizzaSizeButton.click();
        JSUtils.unHighlightElement(pizzaSizeButton);
        return this;
    }

    public CustomPizzaPage pressIngredientDropButton(String name) {
        String ingredientDropButtonLocator = XpathCreatorForPizza.xpathForPizzaName(name) + "/div[contains(@class, '_2kGgH')]";
        waiterVisibility(driver.findElement(By.xpath(ingredientDropButtonLocator))).click();
        return new CustomPizzaPage();
    }

    public MenuPage addPizzaToCart(String name) {
        WebElement addPizzaButton = waiterVisibility(driver.findElement(By.xpath(XpathCreatorForPizza.xpathForAddOrder(name))));
        JSUtils.highlightElement(addPizzaButton);
        addPizzaButton.click();
        JSUtils.unHighlightElement(addPizzaButton);
        return this;
    }

    public OrderPage openOrderScreen() {
        waiterVisibility(orderNowButton);
        JSUtils.highlightElement(orderNowButton);
        orderNowButton.click();
        return new OrderPage();
    }

    public CreateOwnPizzaPage pressCreateOwnPizzaButton() {
        waiterVisibility(createPizzaButton);
        JSUtils.highlightElement(createPizzaButton);
        createPizzaButton.click();
        return new CreateOwnPizzaPage();
    }

    public MenuPage pressWidgetButton(String product) {
        String productLocator = String.format("//a[contains(text(), '%s')]", product);
        waiterVisibility(driver.findElement(By.xpath(productLocator))).click();
        return this;
    }

    public Double getTotalPriceOnPage(String name) {
        return getPrice(waiterVisibility(driver.findElement(By.xpath(XpathCreatorForPizza.xpathForPizzaPagePrice(name)))));
    }

    public Double getTotalPriceFromMenuCart() {
        return getPrice(waiterVisibility(totalPriceInCart));
    }

    public void saveTotalPriceFromCart() {
        stackWithPrices.push(getTotalPriceFromMenuCart());
    }

    public MenuPage clickPlusButton(int pizzaAmount) {
        saveTotalPriceFromCart();
        String expectedText = String.valueOf(getTotalPriceFromMenuCart() * pizzaAmount);
        waiterVisibility(plusButton).click();
        logger.info("Plus button is clicked");
        waiterTextToBePresentInElement(totalPriceInCart, expectedText);
        return this;
    }

    public MenuPage clickMinusButton(int pizzaAmount) {
        saveTotalPriceFromCart();
        String expectedText = String.valueOf(getTotalPriceFromMenuCart() / pizzaAmount);
        waiterVisibility(minusButton).click();
        logger.info("Minus button is clicked");
        waiterTextToBePresentInElement(totalPriceInCart, expectedText);
        saveTotalPriceFromCart();
        logger.info("Total prices in stack: pizza price from cart, after click plus button, after click minus button " + stackWithPrices.toString());
        return this;
    }

    public MenuPage clickClearButton() {
        waiterVisibility(clearButton);
        JSUtils.highlightElement(clearButton);
        clearButton.click();
        logger.info("Clear button is clicked");
        return this;
    }

    public boolean compareSavedPrices(int pizzaAmount) {
        waiterVisibility(expectedNotification);
        return stackWithPrices.getFirst().equals(stackWithPrices.pop()) &&
                stackWithPrices.pop() == stackWithPrices.pop() * pizzaAmount && expectedNotification.isDisplayed();
    }

    public FilteringPage pressFilterButton() {
        waiterVisibility(filterButton).click();
        return new FilteringPage();
    }

    public FilteringPage pressPreDefinedFilterButton() {
        waiterVisibility(preDefinedFilterButton).click();
        return new FilteringPage();
    }
    public void pressChoosePizzaPage(String button) {
        waiterVisibility(driver.findElement(By.xpath(String.format("//span[contains(text(), '%s')]", button)))).click();
    }

    public List<String> getPizzaNameFromCart() {
        return Arrays.asList(waiterVisibility(pizzaNameInCart).getText().split(" \\+ "));
    }

    public List<String> getPizzaDescriptionFromCart() {
        String productDescriptionInCart = "//div[contains(@class, 'VariationInfo _27cpJ')]";
        return Arrays
                .asList(waiterOfPresence(productDescriptionInCart)
                        .getText()
                        .toLowerCase()
                        .split(", "));
    }


    public String checkPersonsNumber(String name, String personsNumber) {
        return waiterVisibility(driver.findElement(By.xpath(XpathCreatorForPizza.xpathForPersonsNumber(name, personsNumber)))).getText();
    }
    public String clickBannerButton(String button) {
        String currentBanner = getCurrentBanner();
        WebElement bannerButton = waiterVisibility(driver.findElement(By.xpath(XpathCreatorForPizza.xpathForBannersSlideShowButtons(button))));
        JSUtils.highlightElement(bannerButton);
        bannerButton.click();
        logger.info("Current banner is " + currentBanner);
        return currentBanner;
    }

    private String getCurrentBanner() {
        return waiterVisibility(activeBanner).getAttribute("src");
    }

    public String compareBanners(String button) {
        if (button.contains("next")) {
            String nextBanner = getCurrentBanner();
            logger.info("Next banner is " + nextBanner);
            return nextBanner;
        } else {
            String previousBanner = getCurrentBanner();
            logger.info("Previous banner is " + previousBanner);
            return previousBanner;
        }
    }

}
