package tests.api;

import io.restassured.http.Method;
import io.restassured.response.Response;
import models.api.requests.PromoModel;
import models.api.responses.ApplyPromoModel;
import models.api.responses.CartDataModel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.constants.DataConstants;
import utils.constants.LinksConstants;

import java.util.ArrayList;

import static enums.query.QueryCity.MINSK;
import static enums.query.QueryLang.ENGLISH;
import static enums.query.QueryPlatform.WEB;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PromoCodeTest extends BaseApi {
    private Response response = null;
    private CartDataModel cartData;
    private ApplyPromoModel applyPromoModel;

    @BeforeClass
    @Override
    public void setup() {
        super.setup();
        cartData = getRestClient().getCartData(LinksConstants.getPathAddToCart(), Method.POST, DataConstants.getAddToCartModelInstance());
        response = getRestClient().getResponse(LinksConstants.getPathStockApply(), Method.POST, new PromoModel(MINSK.getCityId(), new ArrayList<String>(), ENGLISH.getLang(), WEB.getPlatform(), DataConstants.getPromoCodeForTests(), cartData.getUnauthorizedToken()));
        applyPromoModel = response
                .as(ApplyPromoModel.class);
    }

    @Test
    public void checkStatusCode() {
        response
                .then()
                .assertThat()
                .statusCode(DataConstants.getStatusCodeSuccess());
    }

    @Test
    public void checkPromoCodeName() {
        String actualPromoCode = applyPromoModel.getCode();
        assertThat(actualPromoCode, equalTo(DataConstants.getPromoCodeForTests()));
    }

    @Test
    public void checkCartId() {
        int actualCartId = applyPromoModel.getCart().get(0).getCartId();
        assertThat(actualCartId, equalTo(cartData.getCartId()));
    }

    @Test
    public void checkMinOrderSum() {
        String expectedMinOrderSum = "32";
        String actualMinOrderSum = applyPromoModel.getDetails().get(0).getMinOrderSum();
        assertThat(actualMinOrderSum, equalTo(expectedMinOrderSum));
    }

    @Test
    public void checkFirstFreeItemId() {
        int freeFourCheesePizzaId = 442;
        int firstActualPizzaId = applyPromoModel.getFreePizzaId(0, response);
        assertThat(firstActualPizzaId, equalTo(freeFourCheesePizzaId));
    }

    @Test
    public void checkSecondFreeItemId() {
        int freeTangerinePizzaId = 717;
        int secondActualPizzaId = applyPromoModel.getFreePizzaId(1, response);
        assertThat(secondActualPizzaId, equalTo(freeTangerinePizzaId));
    }

}
