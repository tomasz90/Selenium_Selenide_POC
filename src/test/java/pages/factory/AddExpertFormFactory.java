package pages.factory;

import org.openqa.selenium.support.PageFactory;
import pages.factory.model.AddExpertForm;
import pages.selenide.AddExpertFormSelenide;
import pages.selenium.AddExpertFormSelenium;

import static pages.selenium.BasePageSelenium.getDriver;
import static constants.Constants.SELENIDE;
import static constants.Constants.SELENIUM;

public class AddExpertFormFactory {

    public static AddExpertForm get(String library) {
        switch (library) {
            case SELENIDE:
                return new AddExpertFormSelenide();
            case SELENIUM:
                return PageFactory.initElements(getDriver(), AddExpertFormSelenium.class);
        }
        return null;
    }
}
