package services;

import enums.driver.DriverType;
import enums.driver.EnvironmentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.constants.DataConstants;

import java.io.*;
import java.util.Properties;

public class ConfigFileReader implements IConfigFileReader {
    private static Properties properties = new Properties();

    public ConfigFileReader() {
        try (InputStream input = new FileInputStream(DataConstants.getPathToDriverPropertyFile())) {
            properties.load(input);
        } catch (IOException e) {
            Logger logger = LogManager.getRootLogger();
            logger.warn("Driver.properties is not found at " + DataConstants.getPathToDriverPropertyFile());
        }
    }

    @Override
    public DriverType getBrowser() {
        String browserName = properties.getProperty("browser");
        return DriverType.valueOf(browserName.toUpperCase());
    }

    @Override
    public EnvironmentType getEnvironment() {
        String environmentType = properties.getProperty("environmentType");
        return EnvironmentType.valueOf(environmentType.toUpperCase());
    }

    @Override
    public String getCookieEnabled() {
        return properties.getProperty("cookieEnabled");
    }
}
