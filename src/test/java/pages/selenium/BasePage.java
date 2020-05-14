package pages.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage extends PageFactory {

    private static WebDriver driver;

   public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            setDriverOptions(driver);
        }
        return driver;
    }

    static void close() {
        getDriver().close();
        driver = null;
    }

    static WebDriverWait getWait(int timeOutInMilliSec) {
        return new WebDriverWait(driver, timeOutInMilliSec / 1000);
    }

    private static void setDriverOptions(WebDriver driver) {
        driver.manage().window().maximize();
    }
}
