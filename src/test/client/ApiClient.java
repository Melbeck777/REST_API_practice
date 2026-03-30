package client;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiClient {

    private static String baseUrl() {
        String env = System.getenv("BASE_URL");
        return (env == null || env.isBlank()) ? "http://localhost:8080" : env;
    }

    static {
        RestAssured.baseURI = baseUrl();
        RestAssured.config = RestAssured.config()
                .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails());
    }

    public static RequestSpecification req() {
        return given()
                .contentType("application/json")
                .accept("application/json");
    }
}
