package tests

import com.codeborne.selenide.Condition
import pages.factory.AddExpertFormFactory
import pages.factory.HomePageFactory
import pages.factory.ResultPageFactory
import pages.factory.model.AddExpertForm
import pages.factory.model.HomePage
import pages.factory.model.ResultPage
import pages.selenium.BasePageSelenium
import spock.lang.Specification

import static com.codeborne.selenide.Condition.visible
import static constants.Constants.SELENIDE
import static constants.Constants.SELENIUM
import static tests.Util.makeUnique

class ExampleSpec extends Specification {

    private static final String LIBRARY = SELENIDE

    private HomePage homePage = HomePageFactory.get(LIBRARY)
    private ResultPage resultPage = ResultPageFactory.get(LIBRARY)
    private AddExpertForm addExpertForm = AddExpertFormFactory.get(LIBRARY)

    private Condition notVisible = Condition.hidden

    def setup() {
        homePage.navigate()
        homePage.prepareCleanState()
    }

    def cleanupSpec() {
        if (LIBRARY == SELENIUM) {
        BasePageSelenium.close()
        }
    }
    
    @Repeat
    def "Should find some devs when providing valid tech and location"() {
        when:
        homePage.searchForDev("java", "Warszawa, Poland")

        then:
        resultPage.isLoaded()
        resultPage.resultsAre(visible)
    }

    @Repeat
    def "Should find some devs after clearing filters on result page when search without results occurred"() {
        given:
        homePage.searchForDev("java", "invalid-city")
        resultPage.isLoaded()
        resultPage.resultsAre(notVisible)

        when:
        resultPage.clearFilters()

        then:
        resultPage.resultsAre(visible)
    }

    @Repeat
    def "Should be able to sign in"() {
        when:
        homePage.signIn("company.dev.benchout@ttpsc.pl", "Passw0rd")

        then:
        homePage.userProfileIconIs(visible)
    }

    @Repeat
    def "Should be able to sign out"() {
        given:
        homePage.signInWithoutUI()

        when:
        homePage.signOut()

        then:
        homePage.userProfileIconIs(notVisible)
    }

    @Repeat
    def "Should open result page when browsing devs by category"() {
        when:
        homePage.searchForDevInCategory(category)

        then:
        resultPage.isLoaded()

        where:
        category << [
                "AV VR developers",
                "Cloud developers",
                "Software developers",
                "IoT developers",
                "Security experts",
                "UX/UI designers",
                "Web developers",
                "Mobile developers"]
    }

    @Repeat
    def "Should add expert providing basic data"() {
        given:
        homePage.signInWithoutUI()
        homePage.addExpert()

        when:
        addExpertForm.fillRequiredBasics("JS developer", makeUnique("Adrian Duda"))
        addExpertForm.skipSection("Experience")
        addExpertForm.skipSection("Projects")
        addExpertForm.skipSection("Education")
        addExpertForm.fillRequiredSkills("Angular")
        addExpertForm.share()

        then:
        addExpertForm.expertShouldBeShared()
    }
}