package pages.mobile;

import enums.mobileparameters.MobilePageParameters;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import models.UserModel;

public class MobileAccountPage extends MobileBasicPage {

    private String xpathForFieldWithEnteredValue = "//android.widget.EditText[@content-desc='%s']";

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='tel']")
    private AndroidElement phoneField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='email']")
    private AndroidElement emailField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='name']")
    private AndroidElement nameField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='pass']")
    private AndroidElement passwordField;

    public MobileAccountPage fillInContactInformation(UserModel userModel) {
        waiterVisibility(phoneField).sendKeys(userModel.getPhone());
        logger.info("Phone number is entered");
        waiterVisibility(emailField).sendKeys(userModel.getEmail());
        logger.info("Email is entered");
        waiterVisibility(nameField).sendKeys(userModel.getName());
        logger.info("Name is entered");
        waiterVisibility(passwordField).sendKeys(userModel.getPassword());
        logger.info("Password is entered");
        return this;
    }

    public boolean checkField(String value) {
        return driver.findElementByXPath(String.format(xpathForFieldWithEnteredValue, value)).isEnabled();
    }

    public String getPhoneField() {
        return phoneField
                .getAttribute(MobilePageParameters.DESCRIPTION.getValue())
                .replaceAll("\\D", "");
    }

}
