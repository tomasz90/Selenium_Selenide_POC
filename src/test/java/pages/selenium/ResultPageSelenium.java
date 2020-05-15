package pages.selenium;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.factory.model.ResultPage;

import static com.codeborne.selenide.Condition.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static util.Constants.DEFAULT_WAIT;
import static util.Constants.SHORT_WAIT;

public class ResultPageSelenium extends BasePage implements ResultPage {

    @FindBy(css = ".btn-filter span.lnr-cross")
    private WebElement clearFiltersButton;

    @FindBy(css = "esc-search-expert .container")
    private WebElement searchForm;
    
    private By searchResults = By.cssSelector("esc-search-expert-list-item > .row");

    @Override
    public void isLoaded() {
        getWait(DEFAULT_WAIT).until(ExpectedConditions.visibilityOf(searchForm));
    }

    @Override
    public void resultsAre(Condition condition) {
        WebElement profile = findElementQuietly(searchResults, SHORT_WAIT);
        boolean shouldBeVisible = condition == visible;
        if (shouldBeVisible) {
            assertNotNull(profile);
        } else {
            assertNull(profile);
        }
    }

    @Override
    public void clearFilters() {
        clearFiltersButton.click();
    }
}
