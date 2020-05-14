package pages.factory;

import pages.factory.model.HomePage;
import pages.selenide.HomePageSelenide;
import pages.selenium.HomePageSelenium;

public class HomePageFactory {

    public static HomePage get(String library) {
        switch (library) {
            case "selenide":
                return new HomePageSelenide();
            case "selenium":
                return new HomePageSelenium();
        }
        return null;
    }
}
