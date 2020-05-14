package tests;

import org.junit.Test;
import pages.factory.HomePageFactory;
import pages.factory.model.HomePage;

public class ExampleTest {

    private HomePage homePage = HomePageFactory.get("selenium");
    
    @Test
    public void shouldFindSomeDevWhenProvidingValidTechAndLocation() {
        homePage.navigate();
        homePage.searchForDev("java", "warsaw");
    }
}
