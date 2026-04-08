package testcase;

import client.ApiClient;
import client.DbClient;
import com.booksite.BookSiteApplication;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes= BookSiteApplication.class)
@ActiveProfiles("test")
@Import(DbClient.class)
public class Step3_GetDBValueFieldAssertionTest {
    @Autowired
    private DbClient dbClient;

    @Test
    void should_match_db_value() {
        Integer userId = 1;
        String path = "/users/{id}";

        Response response = ApiClient.req()
                .log().all()
                .when()
                .get(path, userId)
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        Map<String, Object> row = dbClient.selectOne(
                "Select * from users where id = ?",
                userId
        );

        System.out.println("response");
        System.out.println(response.jsonPath());

        System.out.println("db value");
        System.out.println(row);
        assertThat(response.jsonPath().getInt("userId"))
                .isEqualTo((Integer) row.get("id"));
        assertThat(response.jsonPath().getString("firstName"))
                .isEqualTo((String) row.get("first_name"));
        assertThat(response.jsonPath().getString("lastName"))
                .isEqualTo((String) row.get("last_name"));
    }
}
