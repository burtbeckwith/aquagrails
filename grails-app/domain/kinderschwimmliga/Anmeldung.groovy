package kinderschwimmliga
class Anmeldung {

  Kind kind
  Anmeldestatus status = Anmeldestatus.VORLAEUFIG
  String startnummer
  Wettkampf wettkampf
  Date  anmeldeDatum

static def belongsTo = [Kind]
static hasMany = [gemeldeteFiguren: Figur]

  static constraints = {
    kind(blank: false, nullable: false)
    wettkampf(blank: false, nullable: false)
    status  (nullable:false)    
  }



}

enum Anmeldestatus {

  VORLAEUFIG,     // es konnte noch keine Startnummer vergeben werden ('Warteliste')
  BESTAETIGT,     // alles ok, Startnummer vergeben
  ZURUECKGEZOGEN, // Kind hat Anmeldung zurückgezogen
  VERWEIGERT      // Kind konnte nicht angemeldet werden

}
