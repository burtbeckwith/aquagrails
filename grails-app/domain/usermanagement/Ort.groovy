package usermanagement

class Ort {

    String name
    String plz

    static constraints = {
        name (maxSize:64,minSize:2, blank: false, nullable: false, unique: true )
        plz  (maxSize:5,minSize:5, blank: false, nullable: false)
    }

    String toString() {
        plz+' '+name
    }
  
}
