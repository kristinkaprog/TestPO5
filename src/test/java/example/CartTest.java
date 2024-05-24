package example;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class CartTest {

    public static LoginPage loginPage;
    public static ToKorzina toKorzina ;
    public static WebDriver driver;
    public static double price;
    private static final String BASE_URL = "https://demo.oksoft.ru";
    private static final String VALID_REQUEST_BODY = "{\"amount\": 1000, \"description\": \"Valid request\"}";
    private static final String INVALID_REQUEST_BODY = "{\"amount\": -1000, \"description\": \"Invalid request\"}";

    private static RequestSpecification requestSpec;
    private static final String COOKIES = "Language=ru; wid=128beb606a014b779800c4a2cbf760f9; WarehousesShopCode=1; ASP.NET_SessionId=v3ed4wrwmlwjhky4ayncu153; UTckt=d943dbb0-b811-43fc-a8ac-ba2ae1ded135; PartnersTicket=ac414930-6d0a-4084-aacd-e168081e2763; CartGuid=44883da5-8f74-4234-81c7-047d90d926e8; .ASPXAUTH=48856120291388A37CAF52CE10F199DFC33D80EAD097A698247B05493AE01589FCA8A32779479D73038E03EDC70CD08EBE2F80FD02B72F6C6AE628B819C8BD66D13F81CA7E69A240AB24672F8CDEEE930642854962CA4D914EB5D399F1F285A58892630524E4D96ED60849AD0C2701C5";

    @BeforeClass
    public static void setup() {
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kriss\\Downloads\\chromedriver-win64\\chromedriver\\chromedriver.exe");
        //создание экземпляра драйвера
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        toKorzina = new ToKorzina(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //задержка на выполнение теста = 10 сек.
        //получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("loginpage"));
    }
    @Epic("API")
    @Feature("Работа с корзиной")
    @Test
    public void getCartData() throws InterruptedException {
        Response response = given()
                .cookies("Cookie", COOKIES)
                .when()
                .post("https://demo.oksoft.ru/JsonAndSettingsController/CartOrderedInfoGet")
                .then()
                .statusCode(200)
                .extract().response();

        int totalQty = response.jsonPath().getInt("totalQty");
        String totalSumStr = response.jsonPath().get("totalSum");
        String cleanTotalSumStr = totalSumStr.replaceAll("[^0-9.,]", "");
        double totalSum = Double.parseDouble(cleanTotalSumStr.replace(",", "."));

        //System.out.println("Total Quantity: " + totalQty);
       // System.out.println("Total Sum: " + totalSum);

        loginPage.clickLoginBtn();
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        //вводим пароль
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
        //нажимаем кнопку входа
        loginPage.LogInBtn();
        Thread.sleep(2000);
        loginPage.ClickToKorzina2();
        //System.out.println(toKorzina.getOrdersCount());
        Thread.sleep(2000);
        //price = Double.parseDouble(toKorzina.getSumm());
        String one;
        one = toKorzina.getSumm().replace(" ","");
        one = one.replaceAll("0*$", "");
        String two;
        two = one.replace(",",".");
        //System.out.println("Сумма = "+two);
        String totalSummAPI = String.valueOf(totalSum);
        Assert.assertEquals(totalQty, toKorzina.getOrdersCount());
        Assert.assertEquals(totalSummAPI, two);



    }

    @Epic("API")
    @Feature("Работа с корзиной")
    @Test
    public void testCreateValidFinanceRequest() {
        Map<String, String> cookies = new HashMap<>();
        cookies.put("PHPSESSID", "");
        cookies.put("Language", "ru");
        cookies.put("wid", "128beb606a014b779800c4a2cbf760f9");
        cookies.put("WarehousesShopCode", "1");
        cookies.put("ASP.NET_SessionId", "v3ed4wrwmlwjhky4ayncu153");
        cookies.put("UTckt", "d943dbb0-b811-43fc-a8ac-ba2ae1ded135");
        cookies.put("PartnersTicket", "ac414930-6d0a-4084-aacd-e168081e2763");
        cookies.put("CartGuid", "44883da5-8f74-4234-81c7-047d90d926e8");
        cookies.put(".ASPXAUTH", "48856120291388A37CAF52CE10F199DFC33D80EAD097A698247B05493AE01589FCA8A32779479D73038E03EDC70CD08EBE2F80FD02B72F6C6AE628B819C8BD66D13F81CA7E69A240AB24672F8CDEEE930642854962CA4D914EB5D399F1F285A58892630524E4D96ED60849AD0C2701C5");

        RequestSpecification requestSpec = new RequestSpecBuilder()
                .addCookies(cookies)
                .setBaseUri("https://demo.oksoft.ru")
                .build();

        given()
                .spec(requestSpec)
                .formParam("sum", 1000)
                .formParam("description", "Valid finance request")
                .log().all()
                .post("/user/finorders")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Epic("API")
    @Feature("Работа с заказом")
    @Test
    public void testCreateInvalidFinanceRequest() {
        Map<String, String> cookies = new HashMap<>();
        cookies.put("PHPSESSID", "");
        cookies.put("Language", "ru");
        cookies.put("wid", "128beb606a014b779800c4a2cbf760f9");
        cookies.put("WarehousesShopCode", "1");
        cookies.put("ASP.NET_SessionId", "v3ed4wrwmlwjhky4ayncu153");
        cookies.put("UTckt", "d943dbb0-b811-43fc-a8ac-ba2ae1ded135");
        cookies.put("PartnersTicket", "ac414930-6d0a-4084-aacd-e168081e2763");
        cookies.put("CartGuid", "44883da5-8f74-4234-81c7-047d90d926e8");
        cookies.put(".ASPXAUTH", "48856120291388A37CAF52CE10F199DFC33D80EAD097A698247B05493AE01589FCA8A32779479D73038E03EDC70CD08EBE2F80FD02B72F6C6AE628B819C8BD66D13F81CA7E69A240AB24672F8CDEEE930642854962CA4D914EB5D399F1F285A58892630524E4D96ED60849AD0C2701C5");

        RequestSpecification requestSpec = new RequestSpecBuilder()
                .addCookies(cookies)
                .setBaseUri("https://demo.oksoft.ru")
                .build();

        given()
                .spec(requestSpec)
                .formParam("sum", -1000)
                .formParam("description", "Invalid finance request")
                .log().all()
                .post("/user/finorders")
                .then()
                .log().all()
                .statusCode(200)
                .body(containsString("Ошибка!"));
    }

   /* @Test
    public void testValidFinanceRequest() {
        given()
                .contentType(ContentType.JSON)
                .body(VALID_REQUEST_BODY)
                .post("/user/finorders")
                .then()
                .statusCode(200)
                .body("resultCode", equalTo(0))
                .body("resultText", equalTo("Success"));
    }

    @Test
    public void testInvalidFinanceRequest() {
        given()
                .contentType(ContentType.JSON)
                .body(INVALID_REQUEST_BODY)
                .post("/user/finorders")
                .then()
                .statusCode(200)
                .body("resultCode", equalTo(-2))
                .body("resultText", equalTo("Указана отрицательная сумма"));
    }
*/
    @AfterClass
    public static void tearDown() {
        // catalogPage.LogOut();
        driver.quit(); }
}

