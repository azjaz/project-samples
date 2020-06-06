package tests;

import drivers.DriverSingleton;
import models.PizzaModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import services.creators.PizzaCreator;

public class BaseUI {
    protected PizzaModel pizzaModel = PizzaCreator.getPizzaModel();
    protected Logger logger = LogManager.getRootLogger();
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetUp() {
        driver = DriverSingleton.getDriver();
    }

    public void assertEquals(Object actual, Object expected, String messageFormat) {
        String message = String.format(messageFormat, actual.toString(), expected.toString());
        Assert.assertEquals(actual, expected, message);
    }

    public void assertEquals(Object actual, Object expected, String messageFormat, SoftAssert softAssert) {
        String message = String.format(messageFormat, actual.toString(), expected.toString());
        softAssert.assertEquals(actual, expected, message);
    }

    @AfterMethod(alwaysRun = true)
    public void browserTermination() {
        DriverSingleton.closeDriver();
    }
}
