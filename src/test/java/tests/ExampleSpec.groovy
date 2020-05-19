package tests


import pages.factory.AddExpertFormFactory
import pages.factory.HomePageFactory
import pages.factory.ResultPageFactory
import pages.factory.model.AddExpertForm
import pages.factory.model.HomePage
import pages.factory.model.ResultPage
import pages.selenium.BasePageSelenium
import spock.lang.Specification
import util.Repeat

import static com.codeborne.selenide.Condition.visible
import static tests.Util.makeUnique
import static util.Constants.NOT_VISIBLE
import static util.Constants.SELENIUM

/**
 * This test class can be used with both frameworks/libraries selenium and selenide.
 * To decide which framework will be used in this suite, just specify LIBRARY constant.
 * Every test method in this suite has annotation @Repeat - this allows to run test method multiple time.
 * This can be set inside Repeat interface for all methods or can be set for specific method like @Repeat(nTimes)
 * It is useful to check how stable tests are. Please note that in most cases assertions are inside page objects, 
 * because it was easier to implement "generic" solution for both frameworks.
 */
class ExampleSpec extends Specification {
    
    private static final String LIBRARY = SELENIUM

    private HomePage homePage = HomePageFactory.get(LIBRARY)
    private ResultPage resultPage = ResultPageFactory.get(LIBRARY)
    private AddExpertForm addExpertForm = AddExpertFormFactory.get(LIBRARY)

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
        resultPage.resultsAre(NOT_VISIBLE)

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
        homePage.userProfileIconIs(NOT_VISIBLE)
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