# ----------------------------------------------------------------
# 1. ユーザー登録 (POST /user/register)
# ----------------------------------------------------------------
POST {{base_url}}/users/register
# OpenAPIではパラメータ定義でしたが、POSTなので一般的にJSONかフォームで送ると仮定
Content-Type: application/json
{
    "firstName": "{{first_name}}",
    "lastName": "{{last_name}}",
    "password": "{{password}}"
}
HTTP 200
[Asserts]
jsonpath "$.firstName" == "{{first_name}}"
jsonpath "$.lastName" == "{{last_name}}"
jsonpath "$.userId" exists
[Captures]
my_user_id: jsonpath "$.userId"


GET {{base_url}}/users/{{my_user_id}}
HTTP 200
[Asserts]
jsonpath "$.lastName" == "{{last_name}}"

POST {{base_url}}/auth/login
Content-Type: application/json
{
    "firstName": "{{first_name}}",
    "lastName": "{{last_name}}",
    "password": "{{password}}"
}
HTTP 200
