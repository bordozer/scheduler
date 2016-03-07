package scheduler.rest.common;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.Response;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNot.not;
import static scheduler.rest.common.Route.buildRoute;

public class RestTestHelper {

    public static String readJson(final String resourcePath) {
        try {
            return IOUtils.toString(RestTestHelper.class.getResource(resourcePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Response doJsonPost(final String jsonBody, final UserRoutes route, final int expectedStatusCode) {
        RestAssured.defaultParser = Parser.JSON;
        return given()
                .log()
                .ifValidationFails()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .response().log().ifStatusCodeMatches(not(equalTo(expectedStatusCode)))
                .then().statusCode(expectedStatusCode)
                .post(buildRoute(route));
    }

    public static Response doJsonPut(final String jsonBody, final UserRoutes route, final int expectedStatusCode) {
        RestAssured.defaultParser = Parser.JSON;
        return given()
                .log()
                .ifValidationFails()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .response().log().ifStatusCodeMatches(not(equalTo(expectedStatusCode)))
                .then().statusCode(expectedStatusCode)
                .put(buildRoute(route));
    }
}
