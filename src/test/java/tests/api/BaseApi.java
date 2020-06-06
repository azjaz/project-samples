package tests.api;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.constants.DataConstants;
import utils.constants.LinksConstants;
import utils.RestClient;

import java.util.Map;

public class BaseApi {
    private ThreadLocal<RestClient> restClientThreadLocal = new ThreadLocal<>();
    protected Map<String, String> getResponseParams = DataConstants.getDataForQueryParameters();

    protected RestClient getRestClient() {
        return restClientThreadLocal.get();
    }

    @BeforeClass
    protected void setup() {
        RestClient restClient = new RestClient();
        restClientThreadLocal.set(restClient);
        getRestClient().setBaseURI(LinksConstants.getApiUri());
    }

    @AfterClass
    protected void afterClass() {
        restClientThreadLocal.remove();
    }

}
