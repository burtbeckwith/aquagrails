import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        Environment.executeForCurrentEnvironment {
            development {
                devandtest.DummyDataGenerator.generate()
            }
        }
    }
    def destroy = {
    }
    
}
