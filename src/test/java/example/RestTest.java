package example;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RestTest {
   /* @Epic("API")
    @Feature("Работа с корзиной")
    @Test
    public void restTest() {
        // Авторизация
        String authReq = "{\n" +
                "\"Email\": \"support@oksoft.ru\",\n" +
                "\"Пароль\": \"1\"\n" +
                "}";
        Response authResponse = RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(authReq)
                .when()
                .post("https://demo.oksoft.ru/user/login")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        String token = authResponse.jsonPath().getString("token");

        // Получение информации о корзине
        Response cartResponse = given()
                .log().all()
                .cookie("_ym_uid=1712390718788418299; _ym_d=1712390718; Language=ru; wid=340b78e26125429099ee6467739059d3; WarehousesShopCode=1; ASP.NET_SessionId=55qhogdv1wkph5vywylwipxk; PartnersTicket=28355769-cbbe-4972-bd1e-a10b35526428; _ga_NRZ0CMV0B4=GS1.1.1712989504.2.1.1712989731.60.0.0; _ga=GA1.2.1621499982.1712390718; UTckt=513fafc6-881c-4c97-8342-4cf16646482b; CartGuid=ec1a6b01-d52d-4437-adff-2ec800dbbf43; .ASPXAUTH=8769A34D3908E616D5049380A86B01C498394F0AC031689C3E4C9C1A7EECA031A275DA06B89FB4BAB8FAB810CC17610EFD00BBF53E905EB6CF56959764351E0641397078BAA1A4157B685BE2DEC0EBB749AE857A1BF0102989C2366EE1EE4C605ED406FCC7527281DFC9140F9ED451AD; _gid=GA1.2.1765681195.1715419000; _gat=1")
                .when()
                .post("https://demo.oksoft.ru/JsonAndSettingsController/CartOrderedInfoGet")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        int totalQty = cartResponse.jsonPath().getInt("totalQty");
        String totalSum = cartResponse.jsonPath().getString("totalSum");
        String totalDiscount = cartResponse.jsonPath().getString("totalDiscount");
        String totalVp = cartResponse.jsonPath().getString("totalVp");

        // Проверка совпадения данных с фронтендом
        // ...

        // Создание финансовой заявки (позитивный тест)
        String orderReq = "{" +
                "\"Sum\": 1000," +
                "\"Description\": \"Test order\"" +
                "}";
        Response orderResponse = given()
                .log().all()
                .cookie("_ym_uid=1712390718788418299; _ym_d=1712390718; Language=ru; wid=340b78e26125429099ee6467739059d3; WarehousesShopCode=1; ASP.NET_SessionId=55qhogdv1wkph5vywylwipxk; PartnersTicket=28355769-cbbe-4972-bd1e-a10b35526428; _ga_NRZ0CMV0B4=GS1.1.1712989504.2.1.1712989731.60.0.0; _ga=GA1.2.1621499982.1712390718; UTckt=513fafc6-881c-4c97-8342-4cf16646482b; CartGuid=ec1a6b01-d52d-4437-adff-2ec800dbbf43; .ASPXAUTH=8769A34D3908E616D5049380A86B01C498394F0AC031689C3E4C9C1A7EECA031A275DA06B89FB4BAB8FAB810CC17610EFD00BBF53E905EB6CF56959764351E0641397078BAA1A4157B685BE2DEC0EBB749AE857A1BF0102989C2366EE1EE4C605ED406FCC7527281DFC9140F9ED451AD; _gid=GA1.2.1765681195.1715419000; _gat=1")
                .contentType(ContentType.JSON)
                .body(orderReq)
                .when()
                .post("https://demo.oksoft.ru/User/FinOrdersCreate")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        int resultCode = orderResponse.jsonPath().getInt("resultCode");
        String resultText = orderResponse.jsonPath().getString("resultText");

        // Проверка результата создания заявки
        // ...

        // Создание финансовой заявки (негативный тест)
        String negativeOrderReq = "{" +
                "\"Sum\": -1000," +
                "\"Description\": \"Negative test order\"" +
                "}";
        Response negativeOrderResponse = given()
                .log().all()
                .cookie("_ym_uid=1712390718788418299; _ym_d=1712390718; Language=ru; wid=340b78e26125429099ee6467739059d3; WarehousesShopCode=1; ASP.NET_SessionId=55qhogdv1wkph5vywylwipxk; PartnersTicket=28355769-cbbe-4972-bd1e-a10b35526428; _ga_NRZ0CMV0B4=GS1.1.1712989504.2.1.1712989731.60.0.0; _ga=GA1.2.1621499982.1712390718; UTckt=513fafc6-881c-4c97-8342-4cf16646482b; CartGuid=ec1a6b01-d52d-4437-adff-2ec800dbbf43; .ASPXAUTH=8769A34D3908E616D5049380A86B01C498394F0AC031689C3E4C9C1A7EECA031A275DA06B89FB4BAB8FAB810CC17610EFD00BBF53E905EB6CF56959764351E0641397078BAA1A4157B685BE2DEC0EBB749AE857A1BF0102989C2366EE1EE4C605ED406FCC7527281DFC9140F9ED451AD; _gid=GA1.2.1765681195.1715419000; _gat=1")
                .contentType(ContentType.JSON)
                .body(negativeOrderReq)
                .when()
                .post("https://demo.oksoft.ru/User/FinOrdersCreate")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        int negativeResultCode = negativeOrderResponse.jsonPath().getInt("resultCode");
        String negativeResultText = negativeOrderResponse.jsonPath().getString("resultText");

        // Проверка результата негативного теста
        // ...
        }
    */

}
