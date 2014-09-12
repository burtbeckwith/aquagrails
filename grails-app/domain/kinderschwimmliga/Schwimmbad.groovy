package kinderschwimmliga

class Schwimmbad {

    String ort

    static constraints = {
      ort (blank:false, nullable: false)
    }

    String toString() {
      ort
    }
}
