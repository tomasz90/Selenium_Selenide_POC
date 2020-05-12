import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ExampleTest {
    
    @Test
    public void testGoogleSearch() {
        open("http://google.pl");
        $(By.name("q")).setValue("example test")
            .pressEnter();
    }
}
