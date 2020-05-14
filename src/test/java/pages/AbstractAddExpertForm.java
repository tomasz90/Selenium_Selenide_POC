package pages;

public class AbstractAddExpertForm {

    public static AbstractAddExpertForm get(String library) {
        switch (library) {
            case "selenide":
                return new pages.selenide.AddExpertForm();
            case "selenium":
                return new pages.selenium.AddExpertForm();
        }
        return null;
    }
}
