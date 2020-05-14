package pages.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;
import pages.AbstractHomePage;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;
import static tests.Constants.TOKEN_VALUE;

public class HomePage extends AbstractHomePage {

    private SelenideElement acceptCookieButton = $("esc-cookie-alert button");
    private SelenideElement searchButton = $("[type=submit]");
    private SelenideElement signInButton = $("nav .navbar-link:nth-child(4)");
    private SelenideElement submitSignInButton = $("esc-login [type=submit]");
    private SelenideElement signOutButton = $("button.btn-logout");
    private SelenideElement profileButton = $("esc-nav-bar-desktop-dropdown button img");
    private SelenideElement addExpertsButton = $(".btn[ng-reflect-authorities=MODIFY_EXPERTS]");

    private SelenideElement techField = $(byId("who-search"));
    private SelenideElement locationField = $(byId("where-search"));
    private SelenideElement emailField = $(byId("email"));
    private SelenideElement passwordField = $(byId("password"));

    private ElementsCollection devCategoryButtons = $$(".categories .category-wrapper h3");
    
    public void navigate() {
        open("http://esc.tt.com.pl/");
        if (acceptCookieButton.isDisplayed()) {
            acceptCookieButton.click();
        }
    }

    public void searchForDev(String tech, String location) {
        techField.setValue(tech);
        locationField.setValue(location);
        searchButton.click();
    }

    public void searchForDevInCategory(String category) {
        devCategoryButtons.stream()
                .filter(element -> element.getText().equals(category))
                .findAny()
                .get()
                .scrollIntoView(false)
                .click();
    }

    public void signIn(String email, String password) {
        signInButton.click();
        emailField.setValue(email);
        passwordField.setValue(password);
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
        profileButton.shouldBe(condition);
    }

    public void addExpert() {
        addExpertsButton.click();
    }
}