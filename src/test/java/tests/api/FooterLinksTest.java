package tests.api;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import models.api.responses.LinkModel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.constants.DataConstants;
import utils.constants.LinksConstants;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayWithSize;

public class FooterLinksTest extends BaseApi {
    private Response response = null;
    private LinkModel[] linkModel = null;

    @BeforeClass
    @Override
    protected void setup() {
        super.setup();
        response = getRestClient().getResponse(LinksConstants.getPathFooterLinks(), Method.GET, getResponseParams);
        linkModel = response.as(LinkModel[].class);
    }

    @Test
    public void checkStatusCode() {
        response
                .then()
                .assertThat()
                .statusCode(DataConstants.getStatusCodeSuccess());
    }

    @Test
    public void checkResponseHeader() {
        response
                .then()
                .assertThat()
                .contentType(ContentType.JSON);
    }

    @Test
    public void checkResponseBody() {
        LinkModel[] links = linkModel;
        int expectedLinksSize = 6;
        assertThat(links, arrayWithSize(expectedLinksSize));
    }

}
