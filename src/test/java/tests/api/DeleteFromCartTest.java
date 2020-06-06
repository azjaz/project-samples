package tests.api;

import io.restassured.http.Method;
import io.restassured.response.Response;
import models.api.responses.CartDataModel;
import models.api.responses.DeleteResponseModel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.constants.DataConstants;
import utils.constants.LinksConstants;

import static enums.query.QueryParameters.HASH;
import static enums.query.QueryParameters.UNAUTHORIZED_TOKEN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DeleteFromCartTest extends BaseApi {
    private Response response = null;
    private CartDataModel cartData;
    private DeleteResponseModel deleteResponseModel;

    @BeforeClass
    @Override
    public void setup() {
        super.setup();
        cartData = getRestClient().getCartData(LinksConstants.getPathAddToCart(), Method.POST, DataConstants.getAddToCartModelInstance());
        getResponseParams.put(UNAUTHORIZED_TOKEN.getParameter(), cartData.getUnauthorizedToken());
        getResponseParams.put(HASH.getParameter(), cartData.getComposition().get(0).getHash());
        response = getRestClient().getResponse(LinksConstants.getPathDeleteFromCart(), Method.DELETE, getResponseParams);
        deleteResponseModel = response.as(DeleteResponseModel.class);
    }

    @Test
    public void checkStatusOfDeleteRequest() {
        response
                .then()
                .assertThat()
                .statusCode(DataConstants.getStatusCodeSuccess());
    }

    @Test
    public void checkCartIdAfterDeleteTest() {
        Integer actualCartId = deleteResponseModel.getCartId();
        assertThat(actualCartId, is(cartData.getCartId()));
    }

    @Test
    public void checkTokenAfterDeleteTest() {
        String unauthorizedToken = deleteResponseModel.getUnauthorizedToken();
        assertThat(unauthorizedToken, is(cartData.getUnauthorizedToken()));
    }

    @Test
    public void checkPriceInCartAfterDelete() {
        int priceFromDeleteRespond = deleteResponseModel.getPrice();
        int expectedPriceFromDeleteResponse = 0;
        assertThat(priceFromDeleteRespond, is(expectedPriceFromDeleteResponse));
    }

}
