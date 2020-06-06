package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.JSUtils;
import utils.constants.LinksConstants;

public class NavigationBarPage extends BasePage {

    @FindBy(xpath = "//div/ul[contains(@class, '_1ddGJ')]")
    private WebElement productWidget;

    @FindBy(xpath = "//button[contains(@class, '_14Tmk')]")
    private WebElement languageSwitcher;

    @FindBy(xpath = "//button[text()='en']")
    private WebElement englishButton;

    @FindBy(xpath = "//button[text()='ru']")
    private WebElement russianButton;

    public NavigationBarPage openPage() {
        driver.get(LinksConstants.getHomepageUrl());
        waiterVisibility(productWidget);
        return this;
    }

    public MenuPage switchToEnglishVersion() {
        waiterVisibility(languageSwitcher);
        JSUtils.highlightElement(languageSwitcher);
        languageSwitcher.click();
        waiterVisibility(englishButton);
        JSUtils.highlightElement(englishButton);
        englishButton.click();
        return new MenuPage();
    }

    public ConsentAndUserAgreementPage switchToRussianVersion() {
        waiterVisibility(languageSwitcher);
        JSUtils.highlightElement(languageSwitcher);
        languageSwitcher.click();
        waiterVisibility(russianButton);
        JSUtils.highlightElement(russianButton);
        russianButton.click();
        return new ConsentAndUserAgreementPage();
    }

}
