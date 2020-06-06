package tests.api;

import io.restassured.http.Method;
import io.restassured.response.Response;
import models.api.responses.CartDataModel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.constants.DataConstants;
import utils.constants.LinksConstants;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AddToCartTest extends BaseApi {
    private Response response = null;
    private CartDataModel cartData;

    @BeforeClass
    @Override
    protected void setup() {
        super.setup();
        response = getRestClient().getResponse(LinksConstants.getPathAddToCart(), Method.POST, DataConstants.getAddToCartModelInstance());
        cartData = response.as(CartDataModel.class);
    }

    @Test
    public void checkStatusCode() {
        response
                .then()
                .assertThat()
                .statusCode(DataConstants.getStatusCodeSuccess());
    }

    @Test
    public void checkTokenIsNotNullable() {
        String actualToken = cartData.getUnauthorizedToken();
        assertThat(actualToken, notNullValue());
    }

    @Test
    public void checkPizzaIdFromResponse() {
        int expectedPizzaID = 2511;
        int actualPizzaId = cartData.getComposition().get(0).getItem().get(0).getId();
        assertThat(actualPizzaId, is(expectedPizzaID));
    }

    @Test
    public void checkPizzaSizeFromResponse() {
        int expectedPizzaSize = 35;
        int actualPizzaSize = cartData.getComposition().get(0).getItem().get(0).getDiameter();
        assertThat(actualPizzaSize, is(expectedPizzaSize));
    }

    @Test
    public void checkPizzaCountFromResponse() {
        int expectedPizzaAmount = 1;
        int actualPizzaAmount = cartData.getComposition().get(0).getCount();
        assertThat(actualPizzaAmount, is(expectedPizzaAmount));
    }

    @Test
    public void checkPizzaPrice() {
        double expectedPizzaPrice = 22.50;
        double actualPizzaPrice = cartData.getPrice();
        assertThat(actualPizzaPrice, is(expectedPizzaPrice));
    }

}
