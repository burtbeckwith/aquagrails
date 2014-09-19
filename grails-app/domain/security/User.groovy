package security

class User {
    String username
    String passwordHash
    
    static hasMany = [ roles: Role, permissions: String ]

    static constraints = {
        username(nullable: true, blank: false, unique: true)
        passwordHash(nullable:true)
    }
}
