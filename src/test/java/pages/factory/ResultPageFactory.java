package pages.factory;

import org.openqa.selenium.support.PageFactory;
import pages.factory.model.ResultPage;
import pages.selenide.ResultPageSelenide;
import pages.selenium.ResultPageSelenium;

import static pages.selenium.BasePage.getDriver;

public class ResultPageFactory {

    public static ResultPage get(String library) {
        switch (library) {
            case "selenide":
                return new ResultPageSelenide();
            case "selenium":
                return PageFactory.initElements(getDriver(), ResultPageSelenium.class);
        }
        return null;
    }
}
