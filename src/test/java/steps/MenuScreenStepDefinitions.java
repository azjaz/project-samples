package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CreateOwnPizzaPage;
import pages.HalvesCombinerPage;
import pages.MenuPage;
import pages.NavigationBarPage;

import java.util.List;

public class MenuScreenStepDefinitions {
    private String currentBanner;

    @Given("^The user opens Papajohns site$")
    public void openPage() {
        new NavigationBarPage().openPage();
    }

    @And("^The user switches to English version$")
    public void switchToEnglishVersion() {
        new NavigationBarPage().switchToEnglishVersion();
    }

    @When("^The user chooses (\\d+\\s?[c]?[m]?) of (\\w\\s?\\w*)$")
    public void choosePizzaSize(String pizzaSize, String pizzaName) {
        new MenuPage().choosePizzaSize(pizzaName, pizzaSize);
    }

    @Then("^The user sees (\\d+[-]*\\d*) for selected size of (\\w\\s?\\w*)$")
    public void checkPersonsNumber(String expectedPersonsNumber, String pizzaName) {
        String actualPersonsNumber = new MenuPage().checkPersonsNumber(pizzaName, expectedPersonsNumber);
        Assert.assertEquals(actualPersonsNumber, expectedPersonsNumber,
                String.format("Actual persons number %s doesn't equal expected persons number %s for selected pizza size", actualPersonsNumber, expectedPersonsNumber));
    }

    @When("^The user clicks (\\D+)$")
    public void clickNextBannerButton(String button) {
        currentBanner = new MenuPage().clickBannerButton(button);
    }

    @Then("^The user sees the new banner after clicking (\\D+)$")
    public void checkBanner(String button) {
        String changedBanner = new MenuPage().compareBanners(button);
        Assert.assertNotEquals(currentBanner, changedBanner,
                String.format("Current banner %s equals changed banner %s", currentBanner, changedBanner));
    }


    @When("^Button (\\w+\\s?\\w*) is pressed$")
    public void buttonIsPressed(String button) {
        new MenuPage().pressChoosePizzaPage(button);
    }

    @And("^Left half of pizza (\\w+\\s?\\w*) is pressed$")
    public void leftHalfOfPizzaPizzaOneIsPressed(String pizzaOne) {
        new HalvesCombinerPage().pressLeftHalfPizzaButton(pizzaOne);
    }

    @And("^Right half of pizza (\\w+\\s?\\w*) is pressed$")
    public void rightHalfOfPizzaPizzaTwoIsPressed(String pizzaTwo) {
        new HalvesCombinerPage().pressRightHalfPizzaButton(pizzaTwo);
    }

    @And("^Add to Cart button is pressed in combiner$")
    public void addToCartButtonIsPressedInCombiner() {
        new HalvesCombinerPage().addToCartButton();
    }

    @And("^Menu button is pressed$")
    public void menuButtonIsPressed() {
        new CreateOwnPizzaPage().pressReturnToMenuButton();
    }

    @Then("^Combined pizza (\\w+\\s?\\w*) and (\\w+\\s?\\w*) is added to Cart$")
    public void combinedPizzaPizzaOnePizzaTwoIsAddedToCart(String pizzaOne, String pizzaTwo) {
        List<String> pizzaNameFromCart = new MenuPage().getPizzaNameFromCart();
        Assert.assertTrue(pizzaNameFromCart.contains(pizzaOne) && pizzaNameFromCart.contains(pizzaTwo));
    }


    @When("^Pizza's (\\w+\\s?\\w*) size button (\\w+) is pressed$")
    public void pizzaSSizeButtonIsPressed(String pizzaName, String size) {
        new MenuPage().choosePizzaSize(pizzaName, size);
    }


    @And("^Pizza's (\\w+\\s?\\w*) cheese crust button is pressed$")
    public void pizzaSCheeseCrustButtonIsPressed(String name) {
        new HalvesCombinerPage().pressPizzaCrustButton(name);
        new HalvesCombinerPage().pressCheeseCrustButton(name);
    }

    @And("^Pizza (\\w+\\s?\\w*) with cheese crust button is added to cart$")
    public void pizzaWithCheeseCrustButtonIsAddedToCart(String name) {
        new MenuPage().addPizzaToCart(name);
    }

    @Then("^Pizza description in cart has (\\w+\\s?\\w*)$")
    public void pizzaDescriptionInCartHasCheeseCrust(String crust) {
        List<String> pizzaDescriptionInCart = new MenuPage().getPizzaDescriptionFromCart();
        Assert.assertTrue(pizzaDescriptionInCart.contains(crust.toLowerCase()));
    }


}