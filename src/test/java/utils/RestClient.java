package utils;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.api.requests.AddToCartModel;
import models.api.responses.CartDataModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestClient {
    private Logger logger = LogManager.getRootLogger();
    public void setBaseURI(String baseURI) {
        RestAssured.baseURI = baseURI;
    }

    public <T> Response getResponse(String basePath, Method method, Map<String, String> queryParams, T body) {
        RequestSpecification spec = given()
                .contentType(ContentType.JSON)
                .basePath(basePath);
        if (queryParams != null) {
            spec = spec.queryParams(queryParams);
        }
        if (body != null) {
            spec = spec.body(body);
        }
        switch (method) {
            case POST:
                return spec
                        .when()
                        .post()
                        .andReturn();
            case DELETE:
                return spec
                        .when()
                        .delete()
                        .andReturn();
            default:
                return spec
                        .when()
                        .request(method)
                        .andReturn();
        }
    }

    public <T> Response getResponse(String basePath, Method method, T body) {
        return getResponse(basePath, method, null, body);
    }

    public Response getResponse(String basePath, Method method, Map<String, String> queryParams) {
        return getResponse(basePath, method, queryParams, null);
    }

    public List<String> getExpectedValuesFromJsonFile(String fileName) {
        List<String> valuesFromJsonFile;
        StringBuilder result = new StringBuilder();
        try {
            valuesFromJsonFile = Files.readAllLines(Paths.get(fileName));
            for (String string : valuesFromJsonFile) {
                result.append(string);
            }
        } catch (IOException e) {
            logger.error("Json file is not read");
        }
        return new Gson().fromJson(result.toString().replace("\n", ""), List.class);
    }

    public CartDataModel getCartData(String basePath, Method method, AddToCartModel bodyRequestModel) {
        Response response = getResponse(basePath, method, bodyRequestModel);
        return response.getBody().as(CartDataModel.class);
    }

}
