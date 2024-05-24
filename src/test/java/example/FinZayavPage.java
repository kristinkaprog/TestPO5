package example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FinZayavPage {
    public WebDriver driver;
    public static Actions actions ;
    private static WebElement myid;
    private static WebElement currentApp;

    public FinZayavPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        actions = new Actions(driver);
    }
    /**
     * определение локатора меню пользователя
     */
    @FindBy(xpath = "//*[@id=\"navbarDropdownMenuLink-20\"]")
    private WebElement MyCatalog;

    @FindBy(xpath = "//*[@id=\"navbarCollapse\"]/ul[2]/li[5]/div/a[8]")
    private WebElement FinZayavka;

    @FindBy(xpath = "/html/body/div[2]/section/div/div[2]/div/div/button")
    private WebElement NewZayavkaBtn;

    @FindBy(xpath = "//*[@id=\"Amount\"]")
    private WebElement Summ;

    @FindBy(xpath = "//*[@id=\"FinOrdersOperTypes\"]")
    private WebElement SpisokOperations;

    @FindBy(xpath = "//*[@id=\"FinOrdersOperTypes\"]/option[2]")
    private WebElement Depozit;


    @FindBy(xpath = "//*[@id=\"Comment\"]")
    private WebElement Kommentariy;

    @FindBy(xpath = "//*[@id=\"modalContactForm\"]/div/div/div[2]/div[10]/div[1]/button")
    private WebElement CreateZayavkaBtn;

    public void GotoFinZayavka(){
        MyCatalog.click();
        FinZayavka.click();
    }
    public void NewZBtn() throws InterruptedException {
        NewZayavkaBtn.click();
    }

    public void MakeZayavkaSumm(String summ) throws InterruptedException {
        Summ.sendKeys(summ);

    }
    public void MakeZayavkaKomment(String comment) throws InterruptedException {
        Kommentariy.sendKeys(comment);

    }
    public void options() throws InterruptedException {
        SpisokOperations.click();
        Thread.sleep(1000);
        Depozit.click();
    }

    public void ClickCreate(){
        CreateZayavkaBtn.click();
    }

    @FindBy(xpath = "//*[@id=\"wrapper\"]/div[1]/section/div/div[3]/section/div[1]/div/div/div[2]/div/div[1]/p[3]/span")
    private WebElement SummRez;

    @FindBy(xpath = "//*[@id=\"wrapper\"]/div[1]/section/div/div[3]/section/div[1]/div/div/div[2]/div/div[1]/p[5]/span")
    private WebElement CommentRes;

    @FindBy(xpath = "//*[@id=\"wrapper\"]/div[1]/section/div/div[3]/section/div[1]/div/div/div[2]/div/div[1]/p[4]/span")
    private WebElement DepozitRes;


    @FindBy(xpath = "//*[@id=\"wrapper\"]/div[1]/section/div/div[3]/section/div[1]/div/div/div[1]/h2")
    private WebElement IdZayav;

    @FindBy(xpath = "//*[@id=\"wrapper\"]/div[1]/section/div/div[3]/section/div[last]/div/div/div[1]/h2")
    private WebElement LastZayav;


    boolean flag = false;
    public void findMyZayavka(){
        driver.get("https://demo.oksoft.ru/user/finorders");
        driver.navigate().refresh();
        myid = driver.findElement(By.xpath("/html/body/div[2]/section/div/div[3]/section/div/div"));

        System.out.println(myid.getText());
        /*int i = 0;
        while (i<Integer.parseInt(LastZayav.getText())) {
            if ((SummRez.getText() == ConfProperties.getProperty("summ")) && (CommentRes.getText() == ConfProperties.getProperty("comment")) && (DepozitRes.getText() == "Взнос на депозит")) {
                flag = true;
                break;
            } else {
                flag = false;
            }
            i++;
        }
        if (flag){
            System.out.println(IdZayav);
        }else {
            System.out.println("Я не нашел....");
        }

         */
    }

    public void scroll() throws InterruptedException {
        currentApp = driver.findElement(By.xpath("/html/body/div[2]/section/div/div[3]/section/div/div"));
        WebElement firstApp = driver.findElement(By.xpath("/html/body/div[2]/section/div/div[3]/section/div/div/div[last()]"));
        actions.moveToElement(firstApp).click().perform();
        Thread.sleep(1500);
        actions.moveToElement(currentApp).click().perform();
        Thread.sleep(1500);
    }







}
