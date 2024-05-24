package example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAwards {

    public WebDriver driver;
    public Actions actions;

    public MyAwards(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        actions = new Actions(driver);
    }


    @FindBy(xpath = "//*[@id=\"navbarDropdownMenuLink-20\"]")
    private WebElement MyCatalog;

    @FindBy(xpath = "//*[@id=\"navbarCollapse\"]/ul[2]/li[5]/div/a[2]")
    private WebElement MyAwards;

    @FindBy(xpath = "//*[@id=\"pivotGrid_sortedpgHeader4T\"]")

    private WebElement PeriodBtn;

    public void OtchetPeriod() throws InterruptedException {
        actions.moveToElement(MyCatalog).perform();
        MyCatalog.click();
        Thread.sleep(1000);
        MyAwards.click();
        Thread.sleep(1000);
        PeriodBtn.click();
        Thread.sleep(2500);
        PeriodBtn.click();
        Thread.sleep(2500);
    }



}
