package models;

import org.openqa.selenium.By;

public class NavigationBarModel {
    private String language;
    private String contacts;

    public NavigationBarModel(String languageButton, String contactsButton) {
        this.language = languageButton;
        this.contacts = contactsButton;
    }

    public By getLanguageButton() {
        return By.xpath(String.format("//button[text()='%s']", language));
    }

    public By getContactsButton() {
        return By.xpath(String.format("//span[text()='%s']", contacts));
    }
}
