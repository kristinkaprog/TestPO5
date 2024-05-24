package CrossBrouzer;

import example.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;

import java.util.concurrent.TimeUnit;
@DisplayName("Making test")
@ExtendWith(TestListener.class)
public class CrossBrowserTesting {
    public static Actions actions;
    private TestListener testListener;


    ConfProperties confProperties;
    public static WebDriver driver ;
    public String FIO = "Кузнецова";

    public static LoginPage loginPage;

    public static ProfileClass profilePage;

    public static FinZayavPage finZayavPage;

    public static MyWallet myWallet;
    public static MyAwards myAwards;


    @ParameterizedTest
    @ValueSource(strings = {"Chrome","Edge","Firefox"})
    public void setup(String browserType) throws InterruptedException {
        switch (browserType) {
            case "Edge" -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            case "Chrome" -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            case "Firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            default -> System.out.println("Sorry, can't find browser type!");
        }

        actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demo.oksoft.ru");
        test(driver);


    }

    @Severity(SeverityLevel.NORMAL)
    @Story("LoginPage")
    public void test(WebDriver driver) throws InterruptedException {
        loginPage = new LoginPage(driver);
        profilePage = new ProfileClass(driver);
        finZayavPage = new FinZayavPage(driver);
        myAwards = new MyAwards(driver);
        myWallet = new MyWallet(driver);
        loginPage.clickLoginBtn();
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
        loginPage.LogInBtn();
        String user = profilePage.getUserName();
        Assert.assertEquals(FIO, user);
        finZayavPage.GotoFinZayavka();
        finZayavPage.NewZBtn();
        finZayavPage.MakeZayavkaSumm("123459");
        Thread.sleep(1000);
        finZayavPage.options();
        Thread.sleep(2000);
        finZayavPage.MakeZayavkaKomment("comments");
        Thread.sleep(1000);
/*
        finZayavPage.ClickCreate();
        Thread.sleep(5000);
        finZayavPage.findMyZayavka();
        finZayavPage.scroll();
        myAwards.OtchetPeriod();
        myWallet.goToMyWallet();
        myWallet.DoPeriod("23.04.2015","30.04.2024");

 */
       // after();
    }

        @AfterClass
        public void after () {
            driver.quit();
        }

}
