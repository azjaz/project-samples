package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.JSUtils;
import utils.XpathCreatorForPizza;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteringPage extends BasePage {
    private String xpathForIngredientsOfPizza = "//ul[contains(@class, 'Ingredients')]//div[contains(@class, '2GuW6')]";
    private String xpathForItemToFilterOn = "//div[contains(text(), '%s')]//..//div[contains(text(), '%s')]";
    private List<String> textDescriptions = new ArrayList<>();
    private List<String> listOfPizzasWithListOfIngredients = new ArrayList<>();

    public FilteringPage pressOnItemToFilter(String ingredientType, String ingredient) {
        WebElement itemToFilterButton = waiterVisibility(driver.findElement(By.xpath(String.format(xpathForItemToFilterOn, ingredientType, ingredient))));
        JSUtils.highlightElement(itemToFilterButton);
        itemToFilterButton.click();
        return this;
    }

    public int collectFilteredElementsDescriptionsOnPage() {
        String optionToCollectFilteredElements = "subheading";
        waiterVisibility(driver.findElement(By.xpath(XpathCreatorForPizza.xpathForGettingListOfPizza(optionToCollectFilteredElements))));
        List<WebElement> descriptions = driver.findElements(By.xpath(XpathCreatorForPizza.xpathForGettingListOfPizza(optionToCollectFilteredElements)));
        for (WebElement description : descriptions) {
            textDescriptions.add(description.getText().toLowerCase());
        }
        return textDescriptions.size();
    }

    public int checkFilteredElementInPizzaDescription(String elementToCheck) {
        return addingApprovedIngredientsToResultList(textDescriptions, elementToCheck).size();
    }

    public FilteringPage getAllIngredientsFromDroppedList() {
        String optionForDroppingIngredientList = "_2kGgH";
        List<String> ingredientsAsString = new ArrayList<>();
        waiterVisibility(driver.findElement(By.xpath(XpathCreatorForPizza.xpathForGettingListOfPizza(optionForDroppingIngredientList))));
        List<WebElement> ingredientsDroppedList = driver.findElements(By.xpath(XpathCreatorForPizza.xpathForGettingListOfPizza(optionForDroppingIngredientList)));
        for (WebElement ingredientList : ingredientsDroppedList) {
            ingredientList.click();
            waiterVisibility(driver.findElement(By.xpath(xpathForIngredientsOfPizza)));
            List<WebElement> ingredients = driver.findElements(By.xpath(xpathForIngredientsOfPizza));
            for (WebElement ingredient : ingredients) {
                ingredientsAsString.add(ingredient.getText().toLowerCase());
            }
            listOfPizzasWithListOfIngredients.add(String.join(", ", ingredientsAsString));
        }
        return this;
    }

    public int checkFilteredElementInPizzasIngredients(String elementToCheck) {
        return addingApprovedIngredientsToResultList(listOfPizzasWithListOfIngredients, elementToCheck).size();
    }

    private List<String> addingApprovedIngredientsToResultList(List<String> listToCheck, String elementToCheck) {
        List<String> approvedItems = new ArrayList<>();
        for (String itemToCheck : listToCheck) {
            if (conditionOfIngredientPresence(itemToCheck, elementToCheck)) {
                approvedItems.add(itemToCheck);
            }
        }
        return approvedItems;
    }

    private boolean conditionOfIngredientPresence(String listOfIngredients, String ingredientToCheck) {
        return !Arrays.stream(listOfIngredients.split(", "))
                .filter(s -> s.contains(ingredientToCheck.toLowerCase()))
                .findFirst()
                .orElse("").isEmpty();
    }
    
}
