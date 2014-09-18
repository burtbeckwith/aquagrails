package usermanagement

class Adresse {

    String  strasse
    Ort     ort

    static constraints = {
        strasse (maxSize:128,minSize:2, blank: false, nullable: false)
    }

    String toString() {
        strasse+', '+ort
    }
  
}
