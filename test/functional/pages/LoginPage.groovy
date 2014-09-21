package pages

import geb.Page


class LoginPage extends Page {
	static url = "auth/login"

	static at = {
		title ==~ /.*: Login.*/
	}
	
	static content = {
        loginButton() { $("input#bnt_signIn") }
        form() {$("form")}
	}

}