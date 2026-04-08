package testcase;

import client.ApiClient;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import static org.hamcrest.Matchers.*;

public class Step2_PostFieldAssertionTest {

    @Test
    void should_register_user() throws IOException {
        String path = "/users/register";
        InputStream is = getClass().getClassLoader().getResourceAsStream("request/success_user_register.json");

        String requestBody = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        ApiClient.req()
                    .body(requestBody)
                    .log().all()
                .when()
                    .post(path)
                .then()
                    .log().all()
                    .statusCode(200)
                    .body("userId", notNullValue())
                    .body("firstName", equalTo("kamada"))
                    .body("lastName", equalTo("taro"));
    }
}
