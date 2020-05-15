package pages.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.InvocationHandler;

import static util.Constants.DEFAULT_WAIT;

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

    public static void close() {
        getDriver().close();
        driver = null;
    }
    
    public WebElement waitUntilVisible(WebElement element) {
       return getWait(DEFAULT_WAIT).until(ExpectedConditions.visibilityOf(element));
    }

    protected static WebElement findElementQuietly(By by, long timeOut) {
        try {
            WebElement element = new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(by));
            return element;
        } catch (Exception e) {
            return null;
        }
    }

    static WebDriverWait getWait(int timeOutInMilliSec) {
        return new WebDriverWait(driver, timeOutInMilliSec / 1000);
    }

    private static void setDriverOptions(WebDriver driver) {
        driver.manage().window().maximize();
    }
}
