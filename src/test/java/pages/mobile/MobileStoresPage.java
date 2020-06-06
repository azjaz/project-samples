package pages.mobile;

import enums.mobileparameters.MobilePageParameters;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import java.util.List;

public class MobileStoresPage extends MobileBasicPage {

    @AndroidFindBy(xpath = "//android.widget.Button[text()='ALLOW']")
    private AndroidElement allowButton;

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc, 'Store №')]")
    private List<AndroidElement> storesElements;

    @AndroidFindBy(xpath = "//android.view.View[@index='1']/android.view.View[@index='2']")
    private AndroidElement addressElement;

    @AndroidFindBy(xpath = "//android.view.View[@index='1']/android.view.View[@index='4']")
    private AndroidElement scheduleElement;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='SORT BY DISTANCE']")
    private AndroidElement sortByDistanceButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='SORT BY NUMBER']")
    private AndroidElement sortByNumberButton;

    public MobileStoresPage allowPermissionRequest() {
        waitWhetherAlertIsPresent();
        try {
            Alert alert = driver.switchTo().alert();
            logger.info(String.format("Alert is present with text: '%s'", alert.getText()));
            alert.accept();
        } catch (NoAlertPresentException e) {
            logger.warn("Alert is not present");
        }
        return this;
    }

    public int getStoresAmount() {
        waiterVisibility(storesElements.get(6));
        return storesElements.size();
    }

    public MobileStoresPage selectStore(String storeNumber) {
        AndroidElement store = waiterVisibility(storesElements
                .stream()
                .filter(p -> p.getAttribute(MobilePageParameters.DESCRIPTION.getValue()).equals(storeNumber))
                .findFirst()
                .orElse(null));
        store.click();
        return this;
    }

    public String getStoresAddress(String address, String storeNumber) {
        logger.info(String.format("Address of '%s' on the screen is %s", storeNumber, address));
        return waiterVisibility(addressElement)
                .getAttribute(MobilePageParameters.DESCRIPTION.getValue());
    }

    public String getStoresSchedule(String schedule, String storeNumber) {
        logger.info(String.format("Schedule of '%s' on the screen is %s", storeNumber, schedule));
        return waiterVisibility(scheduleElement)
                .getAttribute(MobilePageParameters.DESCRIPTION.getValue());
    }

    public MobileStoresPage clickSortByDistanceButton() {
        waiterVisibility(sortByDistanceButton).click();
        return this;
    }

    public String getSortedStores() {
        waiterInvisibility(storesElements.get(0));
        logger.info(String.format("The nearest store is %s", getFirstStoreInTheList()));
        return getFirstStoreInTheList();
    }

    private String getFirstStoreInTheList() {
        return waiterVisibility(storesElements.get(0))
                .getAttribute(MobilePageParameters.DESCRIPTION.getValue());
    }

    public MobileStoresPage clickSortByNumberButton() {
        waiterVisibility(sortByNumberButton).click();
        return this;
    }

}
