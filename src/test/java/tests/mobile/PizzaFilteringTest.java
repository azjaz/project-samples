package tests.mobile;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.mobile.MobileCustomPizzaPage;
import pages.mobile.MobileMenuPage;
import tests.BaseMobile;
import utils.constants.DataConstants;

public class PizzaFilteringTest extends BaseMobile {

    @Test(groups = "mobile.local")
    public void filterReturnsPizzasListTest() {
        MobileCustomPizzaPage page = new MobileMenuPage()
                .enterToStartMenu()
                .pressMenuButton()
                .changeLang()
                .pressFilterButton()
                .pressIngredientButtonToFilter(DataConstants.getIngredientToFilterMobile())
                .getFilteredProducts();
        int numberOfFilteredPizzas = page.getSizeOfListWithFilteredPizza();
        Assert.assertTrue(numberOfFilteredPizzas > 0, "Filtering returns nothing!");
    }

    @Test(groups = "mobile.local")
    public void checkPresenceOfFilteringValueInPizzaFiltered() {
        MobileCustomPizzaPage page = new MobileMenuPage()
                .enterToStartMenu()
                .pressMenuButton()
                .changeLang()
                .pressFilterButton()
                .pressIngredientButtonToFilter(DataConstants.getIngredientToFilterMobile())
                .getFilteredProducts()
                .chooseFirstFilteredPizza();
        int presenceOfFilteredValueInPizzaDescription = page.getFilterValueFromPizzaDescription(DataConstants.getIngredientToFilterMobile());
        Assert.assertTrue(presenceOfFilteredValueInPizzaDescription > 0, "Filtered pizza does not contain searched ingredient.");
    }
}
