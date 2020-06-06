package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateOwnPizzaPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'Constructor')]//div[contains(@class, '_25gak')]")
    private WebElement totalPriceButton;

    @FindBy(xpath = "//div[contains(@class, '_3gv41')]//button")
    private WebElement addToCartOwnPizzaButton;

    @FindBy(xpath = "//div[contains(@class, '_2KYut')]//span[contains(text(), 'Menu')]")
    private WebElement returnToMenuButton;

    public CreateOwnPizzaPage pressTypeOfIngredientButton(String ingredientType) {
        ingredientTypeCreator(ingredientType).click();
        logger.info(ingredientType + " type was chosen");
        return this;
    }

    public CreateOwnPizzaPage pressIngredientButton(String ingredient) {
        ingredientElementCreator(ingredient).click();
        logger.info(ingredient + " was added");
        return this;
    }

    public CreateOwnPizzaPage pressTrashIconForDeleteIngredient(String ingredient) {
        ingredientDestroyer(ingredient).click();
        logger.info(ingredient + " was deleted");
        return this;
    }

    public Double getTotalPriceOfOwnPizza() {
        return  getPrice(waiterVisibility(totalPriceButton));
    }

    public CreateOwnPizzaPage pressAddToCartOwnPizzaButton() {
        waiterVisibility(addToCartOwnPizzaButton).click();
        return this;
    }

    public MenuPage pressReturnToMenuButton() {
        waiterVisibility(returnToMenuButton).click();
        return new MenuPage();
    }


    private WebElement ingredientTypeCreator(String ingredientType) {
        String ingredientTypeLocator = String.format("//li[contains(text(), '%s')]", ingredientType);
        return waiterOfPresence(ingredientTypeLocator);
    }

    private WebElement ingredientElementCreator(String ingredient) {
        String ingredientLocator = String.format("//div//h5[contains(text(), '%s')]", ingredient);
        return waiterOfPresence(ingredientLocator);
    }

    private WebElement ingredientDestroyer(String ingredient) {
        String destroyerLocator = String.format("//div//h5[contains(text(), '%s')]/../..//div[contains(@class, 'kBhc6')]", ingredient);
        return waiterOfPresence(destroyerLocator);
    }

}
