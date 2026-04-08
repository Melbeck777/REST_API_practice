package testcase;

import client.ApiClient;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

public class Step1_GetFieldAssertionTest {

    @Test
    void should_match_nested_fields() {
        String path = "http://localhost:8080/users/2";

        ApiClient.req()
                .when()
                .get(path)
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("userId", equalTo(2))
                .body("firstName", equalTo("yamada"))
                .body("lastName", equalTo("hanako"));
    }
}
