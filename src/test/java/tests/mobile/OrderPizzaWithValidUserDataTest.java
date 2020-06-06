package tests.mobile;

import models.UserModel;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.mobile.MobileAccountPage;
import pages.mobile.MobileMenuPage;
import services.creators.UserCreator;
import tests.BaseMobile;

public class OrderPizzaWithValidUserDataTest extends BaseMobile {
    private UserModel userModel = UserCreator.withValidUserData();

    @Test(groups = "mobile.local")
    public void checkOrderWithValidUserData() {
        MobileAccountPage page = new MobileMenuPage()
                .enterToStartMenu()
                .pressMenuButton()
                .changeLang()
                .chooseProduct(pizzaModel.getPizzaName())
                .choosePizzaType(pizzaModel.getPizzaType())
                .choosePizzaSize(pizzaModel.getPizzaSize())
                .pressAddToOrderButton()
                .pressFilledCartButton()
                .pressPlaceOrderButton()
                .fillInContactInformation(userModel);

        String phoneResult = page.getPhoneField();
        boolean emailResult = page.checkField(userModel.getEmail());
        boolean nameResult = page.checkField(userModel.getName());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(emailResult, "Actual email doesn't equal to " + userModel.getEmail());
        softAssert.assertTrue(nameResult, "Actual name doesn't equal to " + userModel.getName());
        softAssert.assertTrue(phoneResult.equals(userModel.getPhone()), "Actual phone number doesn't equal to " + userModel.getPhone());
        softAssert.assertAll();

    }
}
