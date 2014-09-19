class SecurityFilters {
    def filters = {
        all(uri: "/**") {
            before = {
                // Ignore direct views (e.g. the default main index page).
                if (!controllerName) return true
                // Ignore assets - layout and JavaScript should always be available
                if (controllerName=='assets') return true
                // Access control by convention. 
                accessControl() 
            } 
        } 
    } 
}