package pages.selenium;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.FindBy;
import pages.factory.model.HomePage;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static tests.Constants.TOKEN;
import static tests.Constants.TOKEN_VALUE;
import static util.Constants.DEFAULT_WAIT;
import static util.Constants.SHORT_WAIT;

public class HomePageSelenium extends BasePageSelenium implements HomePage {
    
    private By acceptCookieButton = By.cssSelector("esc-cookie-alert button");

    private By profileButton = By.cssSelector("esc-nav-bar-desktop-dropdown button img");

    private By addExpertsButton = By.cssSelector(".btn[ng-reflect-authorities=MODIFY_EXPERTS]");

    @FindBy(css = "[type=submit]")
    private WebElement searchButton;
    
    @FindBy(css = "nav .navbar-link:nth-child(4)")
    private WebElement signInButton;
    
    @FindBy(css = "esc-login [type=submit]")
    private WebElement submitSignInButton;
    
    @FindBy(css = "button.btn-logout")
    private WebElement signOutButton;

    @FindBy(id = "who-search")
    private WebElement techField;
    
    @FindBy(id = "where-search")
    private WebElement locationField;
    
    @FindBy(id = "email")
    private WebElement emailField;
    
    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = ".categories .category-wrapper h3")
    private List<WebElement> devCategoryButtons;

    public void navigate() {
        getDriver().navigate().to("http://esc.tt.com.pl/");
        WebElement accept = findElementQuietly(acceptCookieButton, 1);
        if (accept != null) {
           accept.click();
        }
    }

    public void searchForDev(String tech, String location) {
        techField.sendKeys(tech);
        locationField.sendKeys(location);
        searchButton.click();
    }

    public void searchForDevInCategory(String category) {
        WebElement categoryButton = devCategoryButtons.stream()
                .filter(element -> element.getText().equals(category))
                .findAny()
                .get();

        Actions actions = new Actions(getDriver());
        actions.moveToElement(categoryButton);
        categoryButton.click();
    }

    public void signIn(String email, String password) {
        signInButton.click();
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        submitSignInButton.click();
    }

    public void signInWithoutUI() {
        WebStorage webStorage = (WebStorage) new Augmenter().augment(getDriver());
        webStorage.getLocalStorage().setItem(TOKEN, TOKEN_VALUE);
        navigate();
    }

    public void signOut() {
        getDriver().findElement(profileButton).click();
        signOutButton.click();
    }

    public void userProfileIconIs(Condition condition) {
        WebElement profile = findElementQuietly(profileButton, SHORT_WAIT);
        boolean shouldBeVisible = condition == visible;
        if (shouldBeVisible) {
            assertNotNull(profile);
        } else {
            assertNull(profile);
        }
    }

    public void addExpert() {
        waitUntilPreset(addExpertsButton, DEFAULT_WAIT).click();
    }
}
