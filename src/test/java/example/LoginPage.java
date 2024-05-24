package example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id=\"navbar-static-login\"]/div[2]")

    private WebElement loginBtn;
    //*[@id="navbar-static-login"]/div[2]

    @FindBy(xpath = "//*[@id=\"ModalLoginUserName\"]")
    private WebElement loginField;

    @FindBy(xpath = "//*[@id=\"ModalLoginPassword\"]")
    private WebElement passwdField;

    @FindBy(xpath = "//*[@id=\"LoginModalPanel1\"]/div[2]/div/div/button")
    private WebElement BtnLogIn;

    @FindBy(xpath = "/html/body/header/nav[2]/div/div[2]/div/a")
    private WebElement cart;

    public void inputLogin(String login) {
        loginField.sendKeys(login);
    }
    public void clickLoginBtn() {
        loginBtn.click(); }

    public void inputPasswd(String passwd) {
        passwdField.sendKeys(passwd); }

    public void LogInBtn(){
        BtnLogIn.click();
    }

    public void ClickToKorzina2(){
        cart.click();
    }
}




