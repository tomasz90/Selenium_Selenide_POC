package pages.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.factory.model.AddExpertForm;

import static org.junit.Assert.assertEquals;
import static util.Constants.DEFAULT_WAIT;

public class AddExpertFormSelenium extends BasePage implements AddExpertForm {

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

    @Override
    public void skipSection(String section) {
        getWait(DEFAULT_WAIT)
                .until(ExpectedConditions.elementToBeClickable(nextButton))
                .click();
        WebElement sectionElement = getDriver().findElement(By.cssSelector("[class=\"d-md-inline h5 ng-star-inserted\"]"));
        if(sectionElement.getText().equals(section)) {
            getWait(DEFAULT_WAIT)
                    .until(ExpectedConditions.invisibilityOf(sectionElement));
        }
    }

    @Override
    public void fillRequiredBasics(String position, String name) {
        getWait(DEFAULT_WAIT).until(ExpectedConditions.visibilityOf(positionField)).sendKeys(position);
        nameField.sendKeys(name);
        WebElement loader = getWait(DEFAULT_WAIT).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".addon.loader")));
        getWait(DEFAULT_WAIT).until(ExpectedConditions.invisibilityOf(loader));
        skipSection("Basic data");
    }

    @Override
    public void fillRequiredSkills(String skill) {
        mainSkillsField.sendKeys(skill);
        mainSkillsField.sendKeys(Keys.ENTER);
        getWait(DEFAULT_WAIT)
                .until(ExpectedConditions.elementToBeClickable(nextButton))
                .click();
    }

    @Override
    public void share() {
        getWait(DEFAULT_WAIT)
                .until(ExpectedConditions.elementToBeClickable(shareButton))
                .click();
    }

    @Override
    public void expertShouldBeShared() {
        getWait(DEFAULT_WAIT).until(ExpectedConditions.visibilityOf(sharedSuccessfullyMessage));
        sharedSuccessfullyMessage.isDisplayed();
        assertEquals("You shared expert successfully", sharedSuccessfullyMessage.getText());
    }
}
