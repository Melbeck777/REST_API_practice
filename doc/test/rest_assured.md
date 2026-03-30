# REST Assured 内部結合テスト（API × DB）コンテキスト（Step1→4で段階的に作る）

## 前提

- ローカルで API を起動する
- ローカルで PostgreSQL を docker compose で起動する
- 認証なし
- APIレスポンスはネスト（例: `data.user.id` みたいな形）
- 比較は段階的（まず id、次に他フィールド）

## 環境変数（ローカルデフォルトあり）

- BASE_URL（未指定なら `http://localhost:8080`）
- DB_URL（未指定なら `jdbc:postgresql://localhost:5432/app`）
- DB_USER（未指定なら `app`）
- DB_PASSWORD（未指定なら `app`）

## Step1: APIレスポンスの特定フィールド比較（ハードコード期待値）

- ゴール: `statusCode == 200` + JSON Path でネストフィールドを検証できる
- API詳細（path/query）は TODO のまま差し替え可能にしておく

### ApiClient（失敗時のみログ）

```java
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
```

### Step1テスト（ネストJSONを特定フィールドだけ比較）

```java
package testcase;

import client.ApiClient;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class Step1_FieldAssertionTest {

    @Test
    void should_match_nested_fields() {
        String path = "/TODO"; // GEMINI.CLI で確定後に差し替え

        ApiClient.req()
                .when()
                .get(path)
                .then()
                .statusCode(200)
                // ネスト例（ここはAPI仕様に合わせて差し替え）
                .body("data.user.id", equalTo(123));
                // 段階的に増やす:
                // .body("data.user.name", equalTo("expected-name"))
                // .body("data.user.active", is(true));
    }
}
```

## Step2: DBの値を取得する（固定INSERT）

- ゴール: テストから SELECT して値を取れる

### docker-compose（PostgreSQL）

```yaml
services:
  db:
    image: postgres:16
    environment:
      POSTGRES_DB: app
      POSTGRES_USER: app
      POSTGRES_PASSWORD: app
    ports:
      - "5432:5432"
    volumes:
      - ./db/init:/docker-entrypoint-initdb.d
```

### init SQL（固定データ）

```sql
-- ./db/init/001_schema.sql
create table if not exists users (
  id int primary key,
  name text not null,
  active boolean not null
);
```

```sql
-- ./db/init/002_seed.sql
insert into users (id, name, active) values
  (123, 'expected-name', true)
on conflict (id) do update
  set name = excluded.name,
      active = excluded.active;
```

### DbClient（JDBC最小）

```java
package db;

import java.sql.*;
import java.util.*;

public class DbClient {

    private static String envOrDefault(String key, String def) {
        String v = System.getenv(key);
        return (v == null || v.isBlank()) ? def : v;
    }

    private static String url() { return envOrDefault("DB_URL", "jdbc:postgresql://localhost:5432/app"); }
    private static String user() { return envOrDefault("DB_USER", "app"); }
    private static String pass() { return envOrDefault("DB_PASSWORD", "app"); }

    public static Map<String, Object> selectOne(String sql, Object... params) throws SQLException {
        try (Connection con = DriverManager.getConnection(url(), user(), pass());
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) ps.setObject(i + 1, params[i]);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;

                ResultSetMetaData md = rs.getMetaData();
                Map<String, Object> row = new LinkedHashMap<>();
                for (int c = 1; c <= md.getColumnCount(); c++) {
                    row.put(md.getColumnLabel(c), rs.getObject(c));
                }
                return row;
            }
        }
    }
}
```

### Step2テスト（DBから取れる確認）

```java
package testcase;

import db.DbClient;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class Step2_DbSelectTest {

    @Test
    void should_select_fixed_seed() throws Exception {
        Map<String, Object> row = DbClient.selectOne(
                "select id, name, active from users where id = ?",
                123
        );

        assertThat(row).isNotNull();
        assertThat(row.get("id")).isEqualTo(123);
    }
}
```

## Step3: DBの値とAPIレスポンスを比較する（まず id）

- ゴール: `APIのネストid` と `DBのid` を同じテストで突合できる

### Step3テスト（[API.id](http://API.id) == [DB.id](http://DB.id)）

```java
package testcase;

import client.ApiClient;
import db.DbClient;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class Step3_ApiVsDbJoinTest {

    @Test
    void should_match_api_id_with_db_id() throws Exception {
        // 1) DBから期待値取得（固定seed）
        Map<String, Object> row = DbClient.selectOne(
                "select id, name, active from users where id = ?",
                123
        );

        int dbId = ((Number) row.get("id")).intValue();

        // 2) API実行してネストフィールドをDB値と比較
        String path = "/TODO"; // GEMINI.CLI で確定後に差し替え

        ApiClient.req()
                .when()
                .get(path)
                .then()
                .statusCode(200)
                .body("data.user.id", equalTo(dbId));

        // 段階的に増やす（次の一手）:
        // String dbName = (String) row.get("name");
        // boolean dbActive = (Boolean) row.get("active");
        // .body("data.user.name", equalTo(dbName))
        // .body("data.user.active", equalTo(dbActive));
    }
}
```

## Step4: テストケース追加だけで実行できる状態にする（型を決める）

- ゴール: 新しいケースで変えるのは「path」「SQL」「比較するJSON Path」だけ

### テストの型（テンプレ方針）

- Given: path / params（TODO差し替え）
- When: API実行
- Then: status + JSON Path 検証
- And: DB SELECT（固定seed or 条件）
- And: API vs DB の比較を追加していく（まず id）
