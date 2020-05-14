package pages.selenium;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.factory.model.HomePage;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static tests.Constants.TOKEN_VALUE;

public class HomePageSelenium extends BasePage implements HomePage {
    
    @FindBy(css = "esc-cookie-alert button")
    private WebElement acceptCookieButton;

    @FindBy(css = "[type=submit]")
    private WebElement searchButton;
    
    @FindBy(css = "nav .navbar-link:nth-child(4)")
    private WebElement signInButton;
    
    @FindBy(css = "esc-login [type=submit]")
    private WebElement submitSignInButton;
    
    @FindBy(css = "button.btn-logout")
    private WebElement signOutButton;
    
    @FindBy(css = "esc-nav-bar-desktop-dropdown button img")
    private WebElement profileButton;
    
    @FindBy(css = ".btn[ng-reflect-authorities=MODIFY_EXPERTS]")
    private WebElement addExpertsButton;

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
        if (acceptCookieButton.isDisplayed()) {
            acceptCookieButton.click();
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
        WebStorage webStorage = (WebStorage) new Augmenter().augment(WebDriverRunner.getWebDriver());
        webStorage.getLocalStorage().setItem("token", TOKEN_VALUE);
        refresh();
    }

    public void signOut() {
        profileButton.click();
        signOutButton.click();
    }

    public void userProfileIconIs(Condition condition) {
      //  profileButton.shouldBe(condition);
        getWait(3).until(ExpectedConditions.visibilityOf(profileButton));
    }

    public void addExpert() {
        addExpertsButton.click();
    }
}
