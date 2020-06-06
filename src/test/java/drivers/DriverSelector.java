package drivers;

import enums.driver.DriverType;
import enums.driver.EnvironmentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import services.ConfigFileReader;
import services.IConfigFileReader;
import services.driverfactory.*;
import utils.constants.LinksConstants;

public class DriverSelector {
    public static IConfigFileReader configFileReader = new ConfigFileReader();
    private static Logger logger = LogManager.getRootLogger();

    private DriverSelector() {
    }

    public static WebDriver createDriver() {
        EnvironmentType environmentType = configFileReader.getEnvironment();
        switch (environmentType) {
            case LOCAL:
                return createLocalDriver();
            case REMOTE:
                return createRemoteDriver(LinksConstants.getRemoteHubUrl());
            case MCLOUD:
                return createRemoteDriver(LinksConstants.getVirtualMcloudUrl());
            case SLABS:
                return createRemoteDriver(LinksConstants.getVirtualSLabsUrl());
            default:
                return createRemoteDriver(LinksConstants.getVirtualHubUrl());
        }
    }

    public static WebDriver createRemoteDriver(String url) {
        DriverType driverType = configFileReader.getBrowser();
        WebDriverCreator driverCreator;
        switch (driverType) {
            case ANDROID:
                driverCreator = new RemoteAndroidDriverCreator(url);
                break;
            case FIREFOX:
                driverCreator = new RemoteFirefoxDriverCreator(url);
                break;
            default:
                driverCreator = new RemoteChromeDriverCreator(url);
                break;
        }
        try {
            return driverCreator.createWebDriver();
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("Remote web driver was not created");
        }
        return null;
    }

    public static WebDriver createLocalDriver() {
        DriverType driverType = configFileReader.getBrowser();
        WebDriverCreator driverCreator;
        switch (driverType) {
            case ANDROID:
                driverCreator = new LocalAndroidDriverCreator();
                break;
            case FIREFOX:
                driverCreator = new LocalFirefoxDriverCreator();
                break;
            default:
                driverCreator = new LocalChromeDriverCreator();
                break;
        }
        return driverCreator.createWebDriver();
    }
}
