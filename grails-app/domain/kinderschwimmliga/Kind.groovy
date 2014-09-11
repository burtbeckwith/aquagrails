class Kind {

  // Kinder m�ssen mindestens 5, hoechstens 17 Jahre alt sein, um teilnehmen zu d�rfen
  static Integer MINIMUM_AGE = 5
  static Integer MAXIMUM_AGE = 17

  String vorname
  String name
  Date geburtsdatum
  
  String strasse
  String plz
  String wohnort

  Verein verein


  static def hasMany = [anmeldungen: Anmeldung]

  static constraints = {
    vorname(  blank: false, nullable: false)
    name(     blank: false, nullable: false)


    // TODO: Pr�fen, ob zu alt (bisher pr�fen wir nur auf zu jung...)
    // TODO: eventuell die Alterspr�fung durch eine Range 5..MAXIMUM_AGE ersetzen

    geburtsdatum(validator: { birthdate, kid ->
                        AgeService.isOldEnough( birthdate, Kind.MINIMUM_AGE )})

    wohnort(  blank: false,  nullable: false)

    strasse(  blank: true,  nullable: true)
    plz(      blank: true,  nullable: true)
    verein(   blank: true,  nullable: true)

  }
  
  String toString() {
    name + ', ' + vorname + ': ' + geburtsdatum.format("dd.MM.yy")
  }
}
