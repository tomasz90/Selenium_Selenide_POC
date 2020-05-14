package pages.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.factory.model.ResultPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ResultPageSelenide implements ResultPage {
    
    private SelenideElement clearFiltersButton = $(".btn-filter span.lnr-cross");
    private SelenideElement searchForm = $("esc-search-expert .container");
    private SelenideElement searchResults = $("esc-search-expert-list-item > .row");

    @Override
    public void isLoaded() {
        $(searchForm).shouldBe(visible);
    }

    @Override
    public void resultsAre(Condition condition) {
        searchResults.shouldBe(condition);
    }

    @Override
    public void clearFilters() {
        clearFiltersButton.click();
    }
}
