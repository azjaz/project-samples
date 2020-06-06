package services.driverfactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteChromeDriverCreator implements WebDriverCreator {
    private String url;
    private Logger logger = LogManager.getRootLogger();

    public RemoteChromeDriverCreator(String url) {
        this.url = url;
    }

    @Override
    public RemoteWebDriver createWebDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        chromeOptions.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--window-size=1980,1080");
        try {
            return new RemoteWebDriver(new URL(url), chromeOptions);
        } catch (MalformedURLException e) {
            e.getMessage();
            logger.error("No legal protocol is found in a URL string or the string could not be parsed");
        }
        return null;
    }
}
