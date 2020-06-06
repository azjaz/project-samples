package tests.api;

import io.restassured.http.Method;
import io.restassured.response.Response;
import models.api.responses.CityResponseModel;
import models.api.responses.Status;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.constants.DataConstants;
import utils.constants.LinksConstants;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CitiesListTest extends BaseApi {
    private Response response = null;
    private CityResponseModel[] cityResponseModel = null;

    @BeforeClass
    @Override
    protected void setup() {
        super.setup();
        response = getRestClient().getResponse(LinksConstants.getPathCitiesList(), Method.GET, getResponseParams);
        cityResponseModel = response.as(CityResponseModel[].class);

    }

    @Test
    public void checkStatusCode() {
        response
                .then()
                .assertThat()
                .statusCode(DataConstants.getStatusCodeSuccess());
    }

    @Test
    public void checkPhoneInResponse() {
        List<String> phones = new ArrayList<>();
        for (CityResponseModel model : cityResponseModel) {
            phones.add(model.getHelpNumber());
        }
        assertThat(phones,
                allOf(everyItem(containsString(DataConstants.getPhoneNumber()))));
    }

    @Test
    public void checkNamesInResponse() {
        List<String> cityNames = new ArrayList<>();
        for (CityResponseModel model : cityResponseModel) {
            cityNames.add(model.getName());
        }

        List<String> expectedNames = getRestClient().getExpectedValuesFromJsonFile(DataConstants.getPathNamesJson());
        assertThat(cityNames, equalTo(expectedNames));

    }

    @Test
    public void checkEmailsInResponse() {
        List<String> emails = new ArrayList<>();
        for (CityResponseModel model : cityResponseModel) {
            emails.add(model.getCvEmail());
        }
        List<String> expectedEmails = getRestClient().getExpectedValuesFromJsonFile(DataConstants.getPathEmailsJson());
        assertThat(emails, equalTo(expectedEmails));

    }
    @Test
    public void checkHttpResponseBodyStatusesNamesTest() {
        List<Status> statusesList = new ArrayList<>();
        List<String> statuses = new ArrayList<>();
        for (CityResponseModel model : cityResponseModel) {
            statusesList = model.getSailplay().getStatuses();
        }
        for (Status status : statusesList) {
            statuses.add(status.getName());
        }
        assertThat(statuses, allOf(containsInAnyOrder("Silver", "Gold", "Platinum")));
    }

    @Test
    public void httpResponseHeaderPresenceTest() {
        response
                .then()
                .assertThat()
                .header("Content-type", notNullValue());
    }

}
