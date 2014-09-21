import geb.spock.GebReportingSpec

import spock.lang.*

import pages.*

@Stepwise
class AdminSpec extends GebReportingSpec {
    
    def adminUsername = "admin"
    def adminPassword = "admin"

    def "login as admin"() {
        setup: "navigate to login page"
            to LoginPage
            report "login page"
        when: "login as admin"
            form.username = adminUsername
            form.password = adminPassword
            report "input credentials"
            //loginButton.click()
            report "click button"
		then: "show main menu"
            at MainMenuPage
            report "main menu"
        cleanup: "logout"
            logout()
    }
    private void logout() {
        $("a#signOut").click()
        report "logged out"
    }
}