package pages.mobile;

public interface MobileMenuInterface {
    MobileMenuInterface enterToStartMenu();

    MobileMenuInterface pressMenuButton();

    MobileMenuInterface changeLang();

    MobileCustomPizzaPage chooseProductTypeFromWidgetBar(String productType);

    MobileMenuInterface choosePizzaSize(String size);

    Double getPriceFromPreOrder();

    MobileMenuInterface pressAddToOrderButton();

    MobileOrderPage pressFilledCartButton();

    MobileMenuInterface pressMenuButtonSecondTime();

    MobileStoresPage clickStores();
}
