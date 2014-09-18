package competition

import usermanagement.Kind

class Anmeldung {

  Kind kind
  Anmeldestatus status = Anmeldestatus.VORLAEUFIG
  String startnummer
  Wettkampf wettkampf
  Date  dateCreated

  static def belongsTo = [kind: Kind]
  static hasMany = [gemeldeteFiguren: Figur]

  static constraints = {
    kind        (blank: false, nullable: false)
    wettkampf   (blank: false, nullable: false)
    status      (nullable:false)    
    startnummer (nullable: true)
  }
  String toString() {
      kind.toString()+': '+wettkampf.toString() + ', '+status
  }


}

enum Anmeldestatus {

  VORLAEUFIG,     // es konnte noch keine Startnummer vergeben werden ('Warteliste')
  BESTAETIGT,     // alles ok, Startnummer vergeben
  ZURUECKGEZOGEN, // Kind hat Anmeldung zurueckgezogen
  VERWEIGERT      // Kind konnte nicht angemeldet werden

}
