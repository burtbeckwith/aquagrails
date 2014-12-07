import grails.util.Environment
import usermanagement.UserService

class BootStrap {
    def userService
    
    def init = { servletContext ->
        Environment.executeForCurrentEnvironment {
            development {
                devandtest.DummyDataGenerator.generate()
            }
            test {
                devandtest.DummyDataGenerator.generate()
            }
            
        }
        userService.createAdminUser()
    }
    def destroy = {
    }
    
}
