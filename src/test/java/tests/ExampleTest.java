package tests;

import org.junit.Test;
import pages.selenide.HomePage;

public class ExampleTest {

    private HomePage homePage = new HomePage();
    
    @Test
    public void shouldFindSomeDevWhenProvidingValidTechAndLocation() {
        homePage.navigate();
        homePage.searchForDev("java", "warsaw");
    }
}
