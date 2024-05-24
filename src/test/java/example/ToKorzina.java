package example;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ToKorzina {

    public WebDriver driver;
    private List<WebElement> orders;
    public static Actions actions ;
    private static WebElement myid;

    private static WebElement myid2;

    public ToKorzina(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        actions = new Actions(driver);
    }

    @FindBy(xpath = "/html/body/header/nav[2]/div/div[2]/div/a")
    private WebElement KorzinaBtn;

    @FindBy(xpath = "//*[@id=\"cartContent\"]/div[1]")
    private WebElement FirstElement;

    @FindBy(xpath = "//*[@id=\"cartContent\"]/div[5]")
    private WebElement LastElement;


    @FindBy(xpath = "//*[@id=\"wholeTotalFirst\"]")
    private WebElement Price;

    public void clickToKorzina(){
        try {
            // Попытка найти элемент на странице
            myid = driver.findElement(By.id("/html/body/header/nav[2]/div/div[2]/div/a"));
            // Взаимодействие с элементом
            myid.click();
        } catch (StaleElementReferenceException e) {
            // Если произошла ошибка StaleElementReferenceException, повторный поиск элемента
            myid = driver.findElement(By.id("/html/body/header/nav[2]/div/div[2]/div/a"));
            // Повторное взаимодействие с элементом
            myid.click();
        }
    }

    public void findOrders() {
        orders = driver.findElements(By.xpath("//*[@id=\"cartContent\"]/div"));
    }

    public int getOrdersCount() {
        findOrders();
        return orders.size()-1;
    }

    public String getSumm(){

     //   myid2 = driver.findElement(By.id("//*[@id=\"cartContent\"]/div["+getOrdersCount()+"]"));
        return Price.getText();
    }




}
