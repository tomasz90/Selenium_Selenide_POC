package pages.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.factory.model.AddExpertForm;

import static util.Constants.DEFAULT_WAIT;

public class AddExpertFormSelenium extends BasePageSelenium implements AddExpertForm {

    private By loader = By.cssSelector(".addon.loader");

    @FindBy(id = "position") 
    private WebElement positionField;
    
    @FindBy(id = "displayedName") 
    private WebElement nameField;
    
    @FindBy(id = "mainSkills") 
    private WebElement mainSkillsField;

    @FindBy(css = ".addon.loader") 
    private WebElement nameFieldLoader;

    @FindBy(css = "[type=submit]") 
    private WebElement nextButton;
    
    @FindBy(css = "esc-add-expert-controls .float-right [type=submit]") 
    private WebElement shareButton;

    @FindBy(css = "esc-expert-share .container h2") 
    private WebElement sharedSuccessfullyMessage;

    @FindBy(css = ".d-md-inline.h5.ng-star-inserted")
    private WebElement sectionElement;

    @Override
    public void skipSection(String section) {
        clickWhenClickable(nextButton, DEFAULT_WAIT);
        if(sectionElement.getText().equals(section)) {
            waitUntilHidden(sectionElement, DEFAULT_WAIT);
        }
    }

    @Override
    public void fillRequiredBasics(String position, String name) {
        waitUntilVisible(positionField, DEFAULT_WAIT).sendKeys(position);
        nameField.sendKeys(name);
        WebElement loaderElement = waitUntilPresent(loader, DEFAULT_WAIT);
        waitUntilHidden(loaderElement, DEFAULT_WAIT);
        skipSection("Basic data");
    }

    @Override
    public void fillRequiredSkills(String skill) {
        mainSkillsField.sendKeys(skill, Keys.ENTER);
        clickWhenClickable(nextButton, DEFAULT_WAIT);
    }

    @Override
    public void share() {
        clickWhenClickable(shareButton, DEFAULT_WAIT);
    }

    @Override
    public boolean expertShouldBeShared() {
        waitUntilVisible(sharedSuccessfullyMessage, DEFAULT_WAIT);
        return sharedSuccessfullyMessage.getText().equals("You shared expert successfully");
    }
}
