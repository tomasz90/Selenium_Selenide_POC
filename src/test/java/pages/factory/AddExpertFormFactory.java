package pages.factory;

import pages.factory.model.AddExpertForm;
import pages.selenide.AddExpertFormSelenide;
import pages.selenium.AddExpertFormSelenium;

public class AddExpertFormFactory {

    public static AddExpertForm get(String library) {
        switch (library) {
            case "selenide":
                return new AddExpertFormSelenide();
            case "selenium":
                return new AddExpertFormSelenium();
        }
        return null;
    }
}
