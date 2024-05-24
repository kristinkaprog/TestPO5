package example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyWallet {
    public WebDriver driver;
    public Actions actions;


    public MyWallet(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        actions = new Actions(driver);
    }
    @FindBy(xpath = "//*[@id=\"navbarDropdownMenuLink-20\"]")
    private WebElement MyCatalog;


    @FindBy(xpath = "//*[@id=\"navbarCollapse\"]/ul[2]/li[5]/div/a[7]")
    private WebElement MyWallet;

    @FindBy(xpath = "//*[@id=\"Accounts2051\"]/div/div[4]/button")
    private WebElement ShowOperationsBtn;

    @FindBy(xpath = "//*[@id=\"date_from\"]")
    private WebElement Firstdate;

    @FindBy(xpath = "//*[@id=\"date_to\"]")
    private WebElement Seconddate;

    @FindBy(xpath = "//*[@id=\"ReportForm\"]/div[1]/div[2]/div/div[2]/button")
    private WebElement ShowBtn;


    public void goToMyWallet() throws InterruptedException {
        actions.moveToElement(MyCatalog).perform();
        MyCatalog.click();
        Thread.sleep(1000);
        MyWallet.click();
        Thread.sleep(1000);
        ShowOperationsBtn.click();
        Thread.sleep(1000);
    }

    public void DoPeriod(String date1, String date2) throws InterruptedException {
        Firstdate.sendKeys(date1);
        Thread.sleep(2000);
        Seconddate.sendKeys(date2);
        Thread.sleep(2000);
        ShowBtn.click();
        Thread.sleep(2000);
    }






}
