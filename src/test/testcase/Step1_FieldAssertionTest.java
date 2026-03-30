package testcase;

import client.ApiClient;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class Step1_FieldAssertionTest {

    @Test
    void should_match_nested_fields() {
        // TODO: APIの実際のエンドポイントに合わせてパスを変更する
        String path = "/users/1"; 

        ApiClient.req()
                .when()
                .get(path)
                .then()
                .statusCode(200)
                // TODO: 実際のレスポンス構造に合わせてパスを変更する
                // 例: UserRegisteredResponse はトップレベルに id がある場合
                .body("id", equalTo(1));
    }
}
