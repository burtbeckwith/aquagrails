package kinderschwimmliga

class Schwimmbad {

    String  name
    Ort ort

    static constraints = {
      name(maxSize:64, blank:false, nullable: false)
      ort (nullable: false)
    }

    String toString() {
      name+' in '+ort
    }
}
