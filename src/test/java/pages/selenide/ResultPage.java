package pages.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.AbstractResultPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ResultPage extends AbstractResultPage {
    
    private SelenideElement clearFiltersButton = $(".btn-filter span.lnr-cross");
    private SelenideElement searchForm = $("esc-search-expert .container");
    private SelenideElement searchResults = $("esc-search-expert-list-item > .row");
    
    public void isLoaded() {
        $(searchForm).shouldBe(visible);
    }
    
    public void resultsAre(Condition condition) {
        searchResults.shouldBe(condition);
    }

    public void clearFilters() {
        clearFiltersButton.click();
    }
}
