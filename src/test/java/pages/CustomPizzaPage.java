package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.JSUtils;
import utils.XpathCreatorForPizza;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomPizzaPage extends BasePage {
    private Set<String> ingredientsToDelete = new HashSet<>();

    @FindBy(xpath = "//div[contains(@class, '_2TCm2')]")
    private WebElement deletedIngredients;

    @FindBy(xpath = "//a/span[contains(text(), 'Add ingredients')]")
    private WebElement addIngredientsButton;

    @FindBy(xpath = "//div[contains(@class, '_2ZyNu')]//span[contains(text(), 'Add to cart')]")
    private WebElement addToCartButtonOnPopUp;

    public CustomPizzaPage deleteIngredients(String name, String ... properties) {
        List<WebElement> ingredientButtons = new ArrayList<>();
        for (String property : properties) {
            ingredientButtons.add(driver.findElement(By.xpath(XpathCreatorForPizza.xpathForPizzaOption(name, property))));
        }
        for (WebElement ingredientButton : ingredientButtons) {
            ingredientsToDelete.add(ingredientButton.getText());
            ingredientButton.click();
        }
        return this;
    }

    public CustomPizzaPage addPizzaToCart() {
        waiterVisibility(addToCartButtonOnPopUp).click();
        return this;
    }

    public Set<String> getDeletedIngredientsInCart() {
        Set<String> ingredients = new HashSet<>();
        for (String ingredient : waiterVisibility(deletedIngredients).getText().split(", ")) {
            ingredient = ingredient.replace("- ", "").trim();
            ingredients.add(ingredient);
        }
        return ingredients;
    }

    public Set<String> getIngredientsToDelete() {
        return ingredientsToDelete;
    }

    public CreateOwnPizzaPage pressAddIngredientsButton() {
        waiterVisibility(addIngredientsButton);
        JSUtils.highlightElement(addIngredientsButton);
        addIngredientsButton.click();
        return new CreateOwnPizzaPage();
    }

}
