package usermanagement

import  grails.transaction.Transactional
import  org.apache.shiro.crypto.hash.Sha256Hash
import  security.User
import  security.Role

@Transactional
class UserService {

    def createAdminUser() {
        def roleAdmin = new Role(name:'ROLE_ADMIN')
        roleAdmin.addToPermissions('*:*')
        roleAdmin.save(flush:true, failOnError: true)
        def adminUser = new User(username:'admin',passwordHash:new Sha256Hash("admin").toHex())
        adminUser.addToRoles(roleAdmin)
        adminUser.save(flush:true, failOnError: true)    }
}
