package utils;

import drivers.DriverSingleton;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JSUtils {
    private JSUtils() {
    }

    public static void clickElement(WebElement element) {
        ((JavascriptExecutor) DriverSingleton.getDriver()).executeScript("arguments[0].click();", element);
    }

    public static void scrollByCoordinates(int xNumber, int yNumber) {
        ((JavascriptExecutor) DriverSingleton.getDriver()).executeScript(String.format("window.scrollBy(%d,%d)", xNumber, yNumber));
    }

    public static void scrollByYCoordinate(int yNumber) {
        scrollByCoordinates(0, yNumber);
    }

    public static void highlightElement(WebElement element) {
        ((JavascriptExecutor) DriverSingleton.getDriver()).executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public static void unHighlightElement(WebElement element) {
        ((JavascriptExecutor) DriverSingleton.getDriver()).executeScript("arguments[0].style.border='0px solid red'", element);
    }
}
