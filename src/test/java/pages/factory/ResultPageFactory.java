package pages.factory;

import pages.factory.model.ResultPage;
import pages.selenide.ResultPageSelenide;
import pages.selenium.ResultPageSelenium;

public class ResultPageFactory {

    public static ResultPage get(String library) {
        switch (library) {
            case "selenide":
                return new ResultPageSelenide();
            case "selenium":
                return new ResultPageSelenium();
        }
        return null;
    }
}
