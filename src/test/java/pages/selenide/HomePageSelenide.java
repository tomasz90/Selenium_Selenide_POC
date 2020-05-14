package pages.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;
import pages.factory.HomePageFactory;
import pages.factory.model.HomePage;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;
import static tests.Constants.TOKEN_VALUE;

public class HomePageSelenide implements HomePage {

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
    
    @Override
    public void navigate() {
        open("http://esc.tt.com.pl/");
        if (acceptCookieButton.isDisplayed()) {
            acceptCookieButton.click();
        }
    }

    @Override
    public void searchForDev(String tech, String location) {
        techField.setValue(tech);
        locationField.setValue(location);
        searchButton.click();
    }

    @Override
    public void searchForDevInCategory(String category) {
        devCategoryButtons.stream()
                .filter(element -> element.getText().equals(category))
                .findAny()
                .get()
                .scrollIntoView(false)
                .click();
    }

    @Override
    public void signIn(String email, String password) {
        signInButton.click();
        emailField.setValue(email);
        passwordField.setValue(password);
        submitSignInButton.click();
    }

    @Override
    public void signInWithoutUI() {
        WebStorage webStorage = (WebStorage) new Augmenter().augment(WebDriverRunner.getWebDriver());
        webStorage.getLocalStorage().setItem("token", TOKEN_VALUE);
        refresh();
    }

    @Override
    public void signOut() {
        profileButton.click();
        signOutButton.click();
    }

    @Override
    public void userProfileIconIs(Condition condition) {
        profileButton.shouldBe(condition);
    }

    @Override
    public void addExpert() {
        addExpertsButton.click();
    }
}