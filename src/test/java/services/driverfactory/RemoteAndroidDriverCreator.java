package services.driverfactory;
import drivers.DriverSelector;
import enums.driver.EnvironmentType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.constants.DataConstants;
import java.net.MalformedURLException;
import java.net.URL;
import static enums.driver.MobileCapabilityType.*;

public class RemoteAndroidDriverCreator implements WebDriverCreator {
    private String url;
    private Logger logger = LogManager.getRootLogger();

    public RemoteAndroidDriverCreator(String url) {
        this.url = url;
    }

    @Override
    public WebDriver createWebDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.android();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, DataConstants.getPlatformName());
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");

        if (DriverSelector.configFileReader.getEnvironment() == EnvironmentType.SLABS) {
            capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, DataConstants.getSLabsAppiumVersion());
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DataConstants.getSLabsDeviceName());
            capabilities.setCapability(DEVICE_ORIENTATION.getValue(), DataConstants.getDeviceOrientation());
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0");
            capabilities.setCapability(MobileCapabilityType.APP, DataConstants.getSLabsAppStorage());
        } else {
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, DataConstants.getPlatformVersion());
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DataConstants.getMcloudDeviceName());
            capabilities.setCapability(APP_PACKAGE.getValue(), DataConstants.getAppPackage());
            capabilities.setCapability(APP_ACTIVITY.getValue(), DataConstants.getAppActivity());
        }

        try {
            return new AppiumDriver<>(new URL(url), capabilities);
        } catch (MalformedURLException e) {
            e.getMessage();
            logger.error("No legal protocol is found in a URL string or the string could not be parsed");
        }
        return null;
    }
}




