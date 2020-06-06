package services;

import enums.driver.EnvironmentType;
import pages.cookie.CookieNavigationBarPage;
import pages.NavigationBarPage;
import pages.mobile.MobileMenuInterface;
import pages.mobile.MobileMenuPage;
import pages.mobile.clouds.MCloudMenuPage;
import pages.mobile.clouds.SLabsMenuPage;

import java.util.NoSuchElementException;

public class NavigationBarPageCreator {
    private final IConfigFileReader iConfigFileReader = new ConfigFileReader();

    public NavigationBarPage createNavigationBarPage() {
        String cookieEnable = iConfigFileReader.getCookieEnabled();
        if (cookieEnable.equals("true")) {
            return new CookieNavigationBarPage();
        }
        return new NavigationBarPage();
    }

    public MobileMenuInterface createMobileMenuPage() {
        EnvironmentType environmentType = iConfigFileReader.getEnvironment();
        switch (environmentType) {
            case SLABS:
                return new SLabsMenuPage();
            case MCLOUD:
                return new MCloudMenuPage();
            default:
                return new MobileMenuPage();
        }

    }
}
