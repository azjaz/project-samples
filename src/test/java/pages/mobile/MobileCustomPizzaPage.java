package pages.mobile;

import enums.mobileparameters.MobilePageParameters;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import java.util.*;
import java.util.stream.Collectors;

public class MobileCustomPizzaPage extends MobileBasicPage {

    @AndroidFindBy(accessibility = "SAVE AND ADD TO THE ORDER")
    private AndroidElement saveAndAddToTheOrderButton;

    @AndroidFindBy(accessibility = "CLOSE")
    private AndroidElement closeButtonElement;

    private String xpathOfIngredient = "//android.view.View[contains(@content-desc,'%s')]/preceding-sibling::*[2]";
    private String xpathOfChanges = "//android.view.View[contains(@content-desc, 'My changes')]/following-sibling::*[1]" +
            "//android.view.View[contains(@content-desc, 'x')]";
    private String xpathForAddIngredient = "//android.view.View[contains(@content-desc, 'CAN BE ADDED')]/following-sibling::android.widget.ListView[1]" +
            "//android.view.View[contains(@content-desc, 'BYN')]";
    private String xpathOfChangesBox = "//android.view.View[contains(@content-desc, 'My changes')]";
    private String xpathForFilteredPizzaList = "//android.view.View[contains(@content-desc, 'FILTER')]/following-sibling::*[2]/android.view.View";
    private String xpathForPizzaDescription = "//android.view.View[@content-desc='CHOOSE CRUST']/preceding-sibling::*[2]";
    private Set<String> ingredients;
    private List<String> filteredPizzaNames;
    private Double toppingsCost;


    public MobileCustomPizzaPage deleteIngredient(String ingredient) {
        AndroidElement ingredientParams = waiterClickability((AndroidElement) driver.findElement(MobileBy.xpath(String.format(xpathOfIngredient, ingredient.toLowerCase()))));
        tapOnPointCoordinates(ingredientParams.getCenter().moveBy(90, 0));
        waitSleep(1000);
        return this;
    }

    public MobileCustomPizzaPage getChangedIngredients() {
        List<WebElement> ingredientParams = driver.findElementsByXPath(xpathOfChanges);
        String regexSign = "-";
        ingredients = new HashSet<>();
        for (WebElement ingredient : ingredientParams) {
            String ingredientValue = ingredient.getAttribute(MobilePageParameters.DESCRIPTION.getValue()).replaceAll(" x1", "").trim();
            if(!ingredientValue.contains(regexSign)) {
                ingredients.add(ingredientValue.replace("\\+", ""));
            } else {
                ingredients.add(ingredientValue.replace("-", ""));
            }
        }
        logger.info("Changed ingredients in pre-order: " + ingredients.toString());
        return this;
    }

    public Set<String> getSetOfDeletedIngredients() {
        return ingredients;
    }

    public MobileMenuPage pressSaveAndAddToTheOrderButton() {
        waiterClickability(saveAndAddToTheOrderButton).click();
        return new MobileMenuPage();
    }

    public MobileCustomPizzaPage addIngredient() {
        waitSleep(3000);
        AndroidElement ingredientToAdd = waiterClickability((AndroidElement) driver.findElement(MobileBy.xpath(xpathForAddIngredient)));
        tapOnPointCoordinates(ingredientToAdd.getCenter().moveBy(160, 0));
        return this;
    }

    public MobileCustomPizzaPage changeIngredientType(String type) {
        AndroidElement ingredientType = waiterClickability((AndroidElement) driver.findElementByAccessibilityId(type.toUpperCase()));
        ingredientType.click();
        return this;
    }

    public MobileCustomPizzaPage definePriceOfToppings() {
        waitSleep(3000);
        AndroidElement changesBox = (AndroidElement) driver.findElementByXPath(xpathOfChangesBox);
        String[] priceOfAddedToppings = waiterClickability(changesBox)
                .getAttribute(MobilePageParameters.DESCRIPTION.getValue()).split(" ");
        String priceForToppings = Arrays.stream(priceOfAddedToppings)
                .filter(p -> p.contains("changes"))
                .collect(Collectors.toList()).get(0).replace("changes", "");
        toppingsCost = Double.parseDouble(priceForToppings);
        return this;
    }

    public Double getToppingsCost() {
        return toppingsCost;
    }

    public MobileMenuPage chooseProduct(String product) {
        AndroidElement ingredientType = waiterClickability((AndroidElement) driver.findElementByAccessibilityId(product));
        ingredientType.click();
        return new MobileMenuPage();
    }

    public MobileCustomPizzaPage pressIngredientButtonToFilter(String ingredientToFilter) {
        waitSleep(3000);
        AndroidElement ingredientType = waiterClickability((AndroidElement) driver.findElementByAccessibilityId(ingredientToFilter));
        ingredientType.click();
        return this;
    }

    public MobileCustomPizzaPage getFilteredProducts() {
        waitSleep(3000);
        filteredPizzaNames = new ArrayList<>();
        List<WebElement> filteredIngredients = driver.findElementsByXPath(xpathForFilteredPizzaList);
        for(WebElement pizzaElement : filteredIngredients) {
            if (!pizzaElement.getAttribute(MobilePageParameters.DESCRIPTION.getValue()).equals("")) {
                filteredPizzaNames.add(pizzaElement.getAttribute(MobilePageParameters.DESCRIPTION.getValue()));
            }
        }
        logger.info("Pizzas were filtered: " + filteredPizzaNames.toString());
        return this;
    }

    public int getSizeOfListWithFilteredPizza() {
        return filteredPizzaNames.size();
    }

    public MobileCustomPizzaPage chooseFirstFilteredPizza() {
        AndroidElement firstFilteredPizza = (AndroidElement) driver.findElementByAccessibilityId(filteredPizzaNames.get(0));
        firstFilteredPizza.click();
        return this;
    }

    public int getFilterValueFromPizzaDescription(String filterValue) {
        waitSleep(4000);
        String[] pizzaDescription = driver.findElementByXPath(xpathForPizzaDescription)
                .getAttribute(MobilePageParameters.DESCRIPTION.getValue()).toLowerCase().split(", ");
        List<String> filteredElement = Arrays.stream(pizzaDescription)
                .filter(p -> p.contains(filterValue.toLowerCase())).collect(Collectors.toList());
        return filteredElement.size();
    }
}
