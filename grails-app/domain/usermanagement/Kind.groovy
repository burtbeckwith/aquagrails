package usermanagement

import competition.Anmeldung

class Kind {

    // Kinder muessen mindestens 5, hoechstens 17 Jahre alt sein, um teilnehmen zu duerfen
    static Integer MINIMUM_AGE = 5
    static Integer MAXIMUM_AGE = 17

    Date    dateCreated
    String  vorname
    String  name
    Date    geburtsdatum
  
    Adresse adresse

    Verein  verein


    static def hasMany = [anmeldungen: Anmeldung]

    static constraints = {
        vorname(  blank: false, nullable: false)
        name(     blank: false, nullable: false)


        // TODO: Pruefen, ob zu alt (bisher pruefen wir nur auf zu jung...)
        // TODO: eventuell die Alterspruefung durch eine Range 5..MAXIMUM_AGE ersetzen

        //geburtsdatum(validator: { birthdate, kid ->
        //        AgeService.isOldEnough( birthdate, Kind.MINIMUM_AGE )})

        verein(   blank: true,  nullable: true)

    }
  
    String toString() {
        name + ', ' + vorname + ': ' + geburtsdatum.format("dd.MM.yy")
    }
}
