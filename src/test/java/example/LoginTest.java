package example;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    public String FIO = "Кузнецова";

    public String ProductID = "a7956";
    public static LoginPage loginPage;
    public static ProfileClass profilePage;
    public static WebDriver driver;
    public static FinZayavPage finZayavPage;

    public static MyAwards myAwards;

    public static MyWallet myWallet;

    //public static CatalogPage catalogPage;
    /**
     * осуществление первоначальной настройки
     */
    @BeforeClass
    public static void setup() {
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kriss\\Downloads\\chromedriver-win64\\chromedriver\\chromedriver.exe");
        //создание экземпляра драйвера
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        profilePage = new ProfileClass(driver);
        finZayavPage = new FinZayavPage(driver);
        myAwards = new MyAwards(driver);
        myWallet = new MyWallet(driver);
        //catalogPage = new CatalogPage(driver);
        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //задержка на выполнение теста = 10 сек.
        //получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("loginpage")); }
    /**
     * тестовый метод для осуществления аутентификации
     */
    @Test
    public void loginTest() throws InterruptedException {
        //получение доступа к методам класса LoginPage для взаимодействия с элементами страницы
        //значение login/password берутся из файла настроек по аналогии с chromedriver
        //и loginpage
        //вводим логин
        loginPage.clickLoginBtn();
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        //вводим пароль
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
        //нажимаем кнопку входа
        loginPage.LogInBtn();
        //получаем отображаемый логин
        String user = profilePage.getUserName();
        //и сравниваем его с логином из файла настроек
        Assert.assertEquals(FIO, user);
        finZayavPage.GotoFinZayavka();
        finZayavPage.NewZBtn();
        finZayavPage.MakeZayavkaSumm("123459");
        Thread.sleep(1000);
        finZayavPage.options();
        Thread.sleep(2000);
        finZayavPage.MakeZayavkaKomment("comments");
        Thread.sleep(1000);

        finZayavPage.ClickCreate();
        Thread.sleep(5000);
        finZayavPage.findMyZayavka();
        finZayavPage.scroll();
        myAwards.OtchetPeriod();
        myWallet.goToMyWallet();
        myWallet.DoPeriod("23.04.2015","30.04.2024");





    }
    /**
     * осуществление выхода из аккаунта с последующим закрытием окна браузера
     */
    @AfterClass
    public static void tearDown() {
       // catalogPage.LogOut();
        driver.quit(); }

}
