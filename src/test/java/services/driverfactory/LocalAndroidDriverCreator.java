package services.driverfactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.constants.DataConstants;
import utils.constants.LinksConstants;

import java.net.MalformedURLException;
import java.net.URL;

import static enums.driver.MobileCapabilityType.APP_ACTIVITY;
import static enums.driver.MobileCapabilityType.APP_PACKAGE;

public class LocalAndroidDriverCreator implements WebDriverCreator {
    private Logger logger = LogManager.getRootLogger();

    @Override
    public WebDriver createWebDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, DataConstants.getPlatformName());
        capabilities.setCapability(MobileCapabilityType.VERSION, DataConstants.getPlatformVersion());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DataConstants.getDeviceName());
        capabilities.setCapability(APP_PACKAGE.getValue(), DataConstants.getAppPackage());
        capabilities.setCapability(APP_ACTIVITY.getValue(), DataConstants.getAppActivity());
        try {
            return new AndroidDriver<MobileElement>(new URL(LinksConstants.getLocalAppiumServerUrl()), capabilities);
        } catch (MalformedURLException e) {
            e.getMessage();
            logger.error("No legal protocol is found in a URL string or the string could not be parsed");
        }
        return null;
    }
}
