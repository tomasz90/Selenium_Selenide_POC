package pages;

public class AbstractResultPage {

    public static AbstractResultPage get(String library) {
        switch (library) {
            case "selenide":
                return new pages.selenide.ResultPage();
            case "selenium":
                return new pages.selenium.ResultPage();
        }
        return null;
    }
}
