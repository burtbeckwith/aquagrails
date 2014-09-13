package kinderschwimmliga

class Verein {

  String name
  Ort ort

  static hasMany = [mitglieder: Kind]

  static constraints = {
    name ( blank: false, nullable: false, unique: true )
    ort (blank: false, nullable: false)
    }

  String toString() {
    name
  }

  
  int anzahlMitglieder() {
    return Kind.countByVerein(this)
  }
}
