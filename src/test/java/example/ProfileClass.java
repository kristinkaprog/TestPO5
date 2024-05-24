package example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfileClass {

    public WebDriver driver;
    public ProfileClass(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }
    /**
     * определение локатора меню пользователя
     */
    @FindBy(xpath = "//*[@id=\"navbarCollapse\"]/ul[1]/li/a")
    private WebElement userMenu;

    @FindBy(xpath = "//*[@id=\"navbarCollapse\"]/ul[1]/li/div/div[1]/div[1]/div/ul/li[2]/a")
    private WebElement MaleClothes;


    @FindBy(xpath = "//*[@id=\"navbarDropdownMenuLink-4\"]/div[2]")
    private WebElement user;

    /**
     * определение локатора кнопки выхода из аккаунта
     */
    @FindBy(xpath = "/html/body/header/nav[2]/div/div[2]/ul/li/a")
    private WebElement logoutBtn;
    /**
     * метод для получения имени пользователя из меню пользователя
     */
    public String getUserName() {
        String userName = user.getText();
        return userName; }

    /**
     * метод для нажатия кнопки меню пользователя
     */
    public void entryMenu() {
        userMenu.click();
        MaleClothes.click();
         }
    /**
     * метод для нажатия кнопки выхода из аккаунта
     */
    public void userLogout() {
        user.click();
        logoutBtn.click(); } }
