package pages;

import drivers.DriverSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.constants.DataConstants;
import java.util.Arrays;
import java.util.stream.Collectors;

public class BasePage {
    protected static final String TOTAL_PRICE_REGEX = "([0-9]*[.])?[0-9]+";
    protected WebDriver driver;
    protected final Logger logger;

    public BasePage() {
        this.driver = DriverSingleton.getDriver();
        this.logger = LogManager.getRootLogger();
        PageFactory.initElements(driver, this);
    }

    protected WebElement waiterVisibility(WebElement element) {
        return new WebDriverWait(driver, DataConstants.getDriverTimeout())
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected void waiterVisibilityAndSendKeys(WebElement element, String keys) {
        new WebDriverWait(driver, DataConstants.getDriverTimeout())
                .until(ExpectedConditions.visibilityOf(element)).sendKeys(keys);
    }

    protected void waiterTextToBePresentInElement(WebElement element, String text) {
        new WebDriverWait(driver, DataConstants.getDriverTimeout()).until(ExpectedConditions.
                textToBePresentInElement(element, text));
    }

    protected WebElement waiterOfPresence(String parameter) {
        return new WebDriverWait(driver, DataConstants.getDriverTimeout())
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(parameter)));
    }

    protected void waiterOfPresence(String parameter, int timeout) {
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(parameter)));
    }

    protected void waiterOfInvisibilityOfElementLocated(String parameter, int timeout) {
        new WebDriverWait(driver, timeout).until(ExpectedConditions.
                invisibilityOfElementLocated(By.xpath(parameter)));
    }

    protected WebElement waiterOfClickability(WebElement element) {
        return new WebDriverWait(driver, DataConstants.getDriverTimeout())
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    protected Double getPrice(WebElement element) {
        waiterVisibility(element);
        String totalInCart = Arrays.stream(element.getText().split(" "))
                .filter(p -> p.matches(TOTAL_PRICE_REGEX))
                .collect(Collectors.toList()).get(0);
        return Double.parseDouble(totalInCart);
    }

    protected Actions getActionInstance() {
        return new Actions(driver);
    }

    protected void tabSwitcher(String currentWindow) {
        for (String windowHandle : driver.getWindowHandles()) {
            if (!currentWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
}
