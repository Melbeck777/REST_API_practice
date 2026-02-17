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


GET {{base_url}}/users/4

HTTP 200
[Asserts]
jsonpath "$.lastName" == "Tomoe"
