package tests.mobile;

import models.CarryoutAddressModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.mobile.MobileMenuPage;
import pages.mobile.MobileStoresPage;
import services.creators.AddressCreator;
import tests.BaseMobile;

public class StoresTest extends BaseMobile {
    private CarryoutAddressModel carryoutAddressModel = AddressCreator.withCarryoutAddress();

    @Test(groups = "mobile.local")
    public void checkStoresAmount() {
        int expectedStoresAmount = 7;
        int storesAmount = new MobileMenuPage()
                .enterToStartMenu()
                .pressMenuButton()
                .changeLang()
                .pressMenuButtonSecondTime()
                .clickStores()
                .allowPermissionRequest()
                .getStoresAmount();

        Assert.assertEquals(storesAmount, expectedStoresAmount);
    }

    @Test(groups = "mobile.local")
    public void checkInformationAboutStore() {

        MobileStoresPage page = new MobileMenuPage()
                .enterToStartMenu()
                .pressMenuButton()
                .changeLang()
                .pressMenuButtonSecondTime()
                .clickStores()
                .allowPermissionRequest()
                .selectStore(carryoutAddressModel.getStoreNumber());
        String addressResult = page.getStoresAddress(carryoutAddressModel.getAddress(), carryoutAddressModel.getStoreNumber());
        String scheduleResult = page.getStoresSchedule(carryoutAddressModel.getSchedule(), carryoutAddressModel.getStoreNumber());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(addressResult.contains(carryoutAddressModel.getAddress()), "Address is wrong");
        softAssert.assertTrue(scheduleResult.contains(carryoutAddressModel.getSchedule()),  "Schedule is wrong");
        softAssert.assertAll();

    }

    @Test(groups = "mobile.local")
    public void checkSortByDistance() {
        String expectedNearestStore = "Store №13";

        String nearestStore = new MobileMenuPage()
                .enterToStartMenu()
                .pressMenuButton()
                .changeLang()
                .pressMenuButtonSecondTime()
                .clickStores()
                .allowPermissionRequest()
                .clickSortByDistanceButton()
                .getSortedStores();

        Assert.assertEquals(expectedNearestStore, nearestStore, String.format("The nearest store is not %s", expectedNearestStore));
    }

    @Test(groups = "mobile.local")
    public void checkSortByNumber() {
        String expectedNearestStore = "Store № 1";

        String nearestStore = new MobileMenuPage()
                .enterToStartMenu()
                .pressMenuButton()
                .changeLang()
                .pressMenuButtonSecondTime()
                .clickStores()
                .allowPermissionRequest()
                .clickSortByDistanceButton()
                .clickSortByNumberButton()
                .getSortedStores();

        Assert.assertEquals(nearestStore, expectedNearestStore, String.format("The nearest store is not %s", expectedNearestStore));
    }
}
