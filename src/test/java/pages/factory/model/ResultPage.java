package pages.factory.model;

import com.codeborne.selenide.Condition;

public interface ResultPage {
    
    void isLoaded();

    void resultsAre(Condition condition);

    void clearFilters();
}
