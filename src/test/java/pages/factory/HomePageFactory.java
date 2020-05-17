package pages.factory;

import org.openqa.selenium.support.PageFactory;
import pages.factory.model.HomePage;
import pages.selenide.HomePageSelenide;
import pages.selenium.HomePageSelenium;

import static pages.selenium.BasePageSelenium.getDriver;
import static util.Constants.SELENIDE;
import static util.Constants.SELENIUM;

public class HomePageFactory {

    public static HomePage get(String library) {
        switch (library) {
            case SELENIDE:
                return new HomePageSelenide();
            case SELENIUM:
                return PageFactory.initElements(getDriver(), HomePageSelenium.class);
        }
        return null;
    }
}
