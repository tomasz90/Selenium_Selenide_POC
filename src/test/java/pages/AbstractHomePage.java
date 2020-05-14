package pages;

public abstract class AbstractHomePage {

    public static AbstractHomePage get(String library) {
        switch (library) {
            case "selenide":
                return new pages.selenide.HomePage();
            case "selenium":
                return new pages.selenium.HomePage();
        }
        return null;
    }
}
