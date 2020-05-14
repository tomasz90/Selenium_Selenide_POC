package pages.factory;

import org.openqa.selenium.support.PageFactory;
import pages.factory.model.AddExpertForm;
import pages.selenide.AddExpertFormSelenide;
import pages.selenium.AddExpertFormSelenium;
import pages.selenium.HomePageSelenium;

import static pages.selenium.BasePage.getDriver;

public class AddExpertFormFactory {

    public static AddExpertForm get(String library) {
        switch (library) {
            case "selenide":
                return new AddExpertFormSelenide();
            case "selenium":
                return PageFactory.initElements(getDriver(), AddExpertFormSelenium.class);
        }
        return null;
    }
}
