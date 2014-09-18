package competition

class Wettkampf {

  Date datum
  Schwimmbad schwimmbad
  Integer maxTeilnehmer

  static def hasMany = [anmeldungen: Anmeldung, figuren: Figur]

  static constraints = {
    maxTeilnehmer (min:10, max:200)
    schwimmbad (nullable:false)
    figuren (minsize:1)
  }

  String toString() {
    schwimmbad.toString() + ': ' + datum.format("dd.MM.yy")
  }

}
