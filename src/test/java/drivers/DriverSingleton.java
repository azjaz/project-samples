package drivers;

import org.openqa.selenium.WebDriver;

public class DriverSingleton {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            WebDriver driver = DriverSelector.createDriver();
            driverThreadLocal.set(driver);
        }
        return driverThreadLocal.get();
    }

    public static void closeDriver() {
        driverThreadLocal.get().quit();
        driverThreadLocal.remove();
    }
}

