# ----------------------------------------------------------------
# 1. ユーザー登録 (POST /user/register)
# ----------------------------------------------------------------
POST {{base_url}}/users/register
# OpenAPIではパラメータ定義でしたが、POSTなので一般的にJSONかフォームで送ると仮定
Content-Type: application/json
{
    "firstName": "Osaka",
    "lastName": "Tomoe",
    "password": "password123"
}

HTTP 200
[Asserts]
jsonpath "$.firstName" == "Osaka"
jsonpath "$.lastName" == "Tomoe"
jsonpath "$.userId" exists

[Captures]
my_user_id: jsonpath "$.userId"


GET {{base_url}}/users/{{my_user_id}}

HTTP 200
[Asserts]
jsonpath "$.lastName" == "Tomoe"

