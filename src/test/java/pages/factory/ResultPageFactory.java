package pages.factory;

import org.openqa.selenium.support.PageFactory;
import pages.factory.model.ResultPage;
import pages.selenide.ResultPageSelenide;
import pages.selenium.ResultPageSelenium;

import static pages.selenium.BasePageSelenium.getDriver;
import static util.Constants.SELENIDE;
import static util.Constants.SELENIUM;

public class ResultPageFactory {

    public static ResultPage get(String library) {
        switch (library) {
            case SELENIDE:
                return new ResultPageSelenide();
            case SELENIUM:
                return PageFactory.initElements(getDriver(), ResultPageSelenium.class);
        }
        return null;
    }
}
