
class Figur {


    String bezeichnung
    Schwierigkeit schwierigkeit = Schwierigkeit.MITTELSCHWER

    public Figur(String bezeichnung, Schwierigkeit schwer) {
        this.bezeichnung = bezeichnung
        this.schwierigkeit = schwer
    }

    static constraints = {
        bezeichnung(blank: false, nullable: false, unique: true)
        schwierigkeit(nullable: true)
    }

    String toString() {
        bezeichnung
    }

}

