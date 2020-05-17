package pages.factory;

import org.openqa.selenium.support.PageFactory;
import pages.factory.model.HomePage;
import pages.selenide.HomePageSelenide;
import pages.selenium.HomePageSelenium;

import static pages.selenium.BasePageSelenium.getDriver;

public class HomePageFactory {

    public static HomePage get(String library) {
        switch (library) {
            case "selenide":
                return new HomePageSelenide();
            case "selenium":
                return PageFactory.initElements(getDriver(), HomePageSelenium.class);
        }
        return null;
    }
}
