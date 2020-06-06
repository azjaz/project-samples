package pages.mobile;

import drivers.DriverSingleton;
import enums.mobileparameters.MobilePageParameters;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.constants.DataConstants;
import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MobileBasicPage {
    protected static final String TOTAL_PRICE_REGEX = "([0-9]*[.])?[0-9]+";
    protected AndroidTouchAction touchAction;
    protected AppiumDriver driver;
    protected final Logger logger;

    protected MobileBasicPage() {
        this.driver = (AppiumDriver) DriverSingleton.getDriver();
        this.logger = LogManager.getRootLogger();
        this.touchAction = new AndroidTouchAction(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    protected AndroidElement waiterVisibility(AndroidElement element) {
        return (AndroidElement) new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected Boolean waiterInvisibility(AndroidElement element) {
        return new WebDriverWait(driver, 15)
                .until(ExpectedConditions.invisibilityOf(element));
    }

    protected void scrollScreen() {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        touchAction.press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(600)))
                .moveTo(PointOption.point(startX, endY)).release().perform();
    }

    protected AndroidElement waiterClickability(AndroidElement element) {
        return (AndroidElement) new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitSleep(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            logger.warn("Waiting is interrupted. Something goes wrong with threads synchronizing.");
            Thread.currentThread().interrupt();
        }
    }

    protected void waitWhetherAlertIsPresent() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.alertIsPresent());
    }

    protected Double getPrice(AndroidElement element) {
        waiterClickability(element);
        String totalInCart = Arrays.stream(element.getAttribute(MobilePageParameters.DESCRIPTION.getValue()).split(" "))
                .filter(p -> p.matches(TOTAL_PRICE_REGEX))
                .collect(Collectors.toList()).get(0);
        return Double.parseDouble(totalInCart);
    }
    protected void tapOnPointCoordinates(Point point) {
        touchAction.tap(TapOptions.tapOptions()
                .withPosition(PointOption.point(point)))
                .perform();
    }

    protected void tapOnBoldCoordinates(int xOffset, int yOffset) {
        Point point = new Point(xOffset, yOffset);
        tapOnPointCoordinates(point);
    }
}
