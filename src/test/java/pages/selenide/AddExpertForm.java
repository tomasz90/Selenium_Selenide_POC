package pages.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.AbstractAddExpertForm;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static util.Constants.DEFAULT_WAIT;

public class AddExpertForm extends AbstractAddExpertForm {

    private SelenideElement positionField = $(byId("position"));
    private SelenideElement nameField = $(byId("displayedName"));
    private SelenideElement mainSkillsField = $(byId("mainSkills"));
    
    private SelenideElement nameFieldLoader = $(".addon.loader");
    
    private SelenideElement nextButton = $("[type=submit]");
    private SelenideElement shareButton = $("esc-add-expert-controls .float-right [type=submit]");
    
    private SelenideElement sharedSuccessfullyMessage = $(byText("You shared expert successfully"));

    private Condition clickable = and("can be clicked", visible, enabled);

    public void skipSection(String section) {
        nextButton.shouldBe(clickable).click();
        $(withText(section)).waitUntil(hidden, DEFAULT_WAIT);
    }

    public void fillRequiredBasics(String position, String name) {
        positionField.setValue(position);
        nameField.setValue(name);
        nameFieldLoader.waitUntil(hidden, DEFAULT_WAIT);
        skipSection("Basic data");
    }

    public void fillRequiredSkills(String skill) {
        mainSkillsField.setValue(skill).pressEnter();
        nextButton.click();
    }

    public void share() {
        shareButton.click();
    }

    public void expertShouldBeShared() {
        sharedSuccessfullyMessage.isDisplayed();
    }
}
