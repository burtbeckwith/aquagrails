package pages

import geb.Page


class MainMenuPage extends Page {
	static url = "menu/main"

	static at = {
		title ==~ /.*: Menu.*/
	}
	
	static content = {
	}

}