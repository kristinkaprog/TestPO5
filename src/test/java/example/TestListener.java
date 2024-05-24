package example;

import CrossBrouzer.CrossBrowserTesting;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;

import lombok.SneakyThrows;
import io.qameta.allure.Allure;

@Epic("Frontend")
@Feature("User Interface")
public class TestListener implements TestWatcher {

    @SneakyThrows
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        WebDriver driver = CrossBrowserTesting.driver;
        if (driver != null) {
            Allure.getLifecycle().addAttachment("Скриншот на месте падения теста", "image/png", "png",
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            Allure.addAttachment("Логи после падения теста: ", String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
            Thread.sleep(2000);
            driver.quit();
        }

    }

    @SneakyThrows
    @Override
    public void testSuccessful(ExtensionContext context) {
        WebDriver driver = CrossBrowserTesting.driver;
        if (driver != null) {
            Allure.getLifecycle().addAttachment("Скриншот после успешного прохождения теста", "image/png", "png",
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            Allure.addAttachment("Логи после успешного прохождения теста: ", String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
            Thread.sleep(2000);
        }

    }


}
