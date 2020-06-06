package services;

import enums.driver.DriverType;
import enums.driver.EnvironmentType;

public interface IConfigFileReader {

    DriverType getBrowser();

    EnvironmentType getEnvironment();

    String getCookieEnabled();

}
