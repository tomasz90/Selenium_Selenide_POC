package pages.selenium;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.factory.model.ResultPage;

import static com.codeborne.selenide.Condition.visible;
import static util.Constants.DEFAULT_WAIT;

public class ResultPageSelenium extends BasePage implements ResultPage {

    @FindBy(css = ".btn-filter span.lnr-cross")
    private WebElement clearFiltersButton;

    @FindBy(css = "esc-search-expert .container")
    private WebElement searchForm;

    @FindBy(css = "esc-search-expert-list-item > .row")
    private WebElement searchResults;

    @Override
    public void isLoaded() {
        getWait(DEFAULT_WAIT).until(ExpectedConditions.visibilityOf(searchForm));
    }

    @Override
    public void resultsAre(Condition condition) {
        if(condition == visible) {
            getWait(3).until(ExpectedConditions.visibilityOf(searchResults));
        } else {
            getWait(3).until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(By.cssSelector("esc-search-expert-list-item > .row"))));
        }
    }

    @Override
    public void clearFilters() {
        clearFiltersButton.click();
    }
}
