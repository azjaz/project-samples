package services.driverfactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteFirefoxDriverCreator implements WebDriverCreator {
    private String url;
    private Logger logger = LogManager.getRootLogger();

    public RemoteFirefoxDriverCreator(String url) {
        this.url = url;
    }

    @Override
    public RemoteWebDriver createWebDriver() {
//        RemoteWebDriver driver = null;
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        firefoxOptions.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
        firefoxOptions.addArguments("--headless");
        firefoxOptions.addArguments("--window-size=1980,1080");
        try {
            new RemoteWebDriver(new URL(url), firefoxOptions);
        } catch (MalformedURLException e) {
            e.getMessage();
            logger.error("No legal protocol is found in a URL string or the string could not be parsed");
        }
        return null;
    }
}
