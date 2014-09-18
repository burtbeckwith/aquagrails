package devandtest

import competition.*
import usermanagement.*

/**
 *
 * @author rdmueller
 */
class DummyDataGenerator {
    
    static void generate() {
        def orte=[
            [name:'Entenhausen',plz:'12345'],
            [name:'Harryhausen',plz:'96669'],
            [name:'Metropolis',plz:'24242']
        ]
        def vereinsnamen=['The Ducklings', 'kleine Fische', 'die Papageientaucher']
        def strassennamen=[
            'Sesamstrasse', 
            'Frankfurter Strasse', 
            'Bonner Platz', 
            'Berliner Str.', 
            'Münchner Chaussee'
        ]
        def vornamen= ['Hans', 'Peter', 'Anne', 'Michael', 'Susanne']
        def familiennamen = ['Müller', 'Schmidt', 'Mayer', 'Schulz', 'Heisenberg', 'Schrödinger', 'Fuchs']
        def schwimmbadnamen = ['Freibad', 'Hallenbad', 'Naturbad']
        def figurennamen = ['sterbender Schwan', 'fideler Fisch', 'einarmiger Bandit']
        
        orte.each { ort ->
            new Ort(ort).save(flush:true, failOnError: true)
        }
        def vereine = []
        vereinsnamen.eachWithIndex { vereinsname, i ->
            vereine << new Verein(name:vereinsname,ort:Ort.read((i%3)+1)).save(flush:true, failOnError: true)
        }
        orte = Ort.list()
        //if we combine all first and last names, we get 35 combinations
        //now, let's create 37 names. This will result in two times the same
        //name but hopefully another birthdate...
        37.times { i ->
            def vorname = vornamen[i%vornamen.size()]
            def familienname = familiennamen[i%familiennamen.size()]
            // let's distribute all children evenly across the cities
            def ort = orte[i%orte.size()]
            // does a child have to be member in a club located in the
            // same city as it lives? currently not...
            def verein = vereine[i%vereine.size()]
            // and assume that those generic streetnames are in all those cities
            def strassenname = strassennamen[i%strassennamen.size()]
            Integer hausnummer = Math.random()*200
            def adresse = new Adresse(strasse:strassenname+' '+hausnummer, ort: ort)
                                    .save(flush:true, failOnError: true)
            //now calculate a birthdate
            //TODO: fetch max- and minage from config
            def ageOfMaxAge = new Date()-365*17
            def geburtstag = ageOfMaxAge+(365*12*Math.random() as Integer)
            def kind = new Kind(
                            vorname:    vorname,
                            name:       familienname,
                            adresse:    adresse,
                            verein:     verein,
                            geburtsdatum:   geburtstag
                        ).save(flush:true, failOnError: true)
        }
        def schwimmbaeder=[]
        orte.eachWithIndex { ort, i ->
            def schwimmbadname = schwimmbadnamen[i%schwimmbadnamen.size()]
            schwimmbaeder << new Schwimmbad(name:schwimmbadname, ort: ort)
                                        .save(flush:true, failOnError: true)
        }
        //create some contests
        def contests = []
        4.times{ i ->
            contests << new Wettkampf(
                // somewhere in the future
                datum:          new Date()+14+(8*7*Math.random() as Integer),
                schwimmbad:     schwimmbaeder[i%schwimmbaeder.size()],
                maxTeilnehmer:  10+(190*Math.random() as Integer)
            ).save(flush:true, failOnError: true)
        }
        def figuren = []
        figurennamen.each { figurenname ->
            figuren << new Figur(bezeichnung:figurenname).save(flush:true, failOnError: true)
        }
        //register each child for one contest
        Kind.list().each { kind ->
            def contest = contests[contests.size()*Math.random() as Integer]
            def registeredFigures = []
            def numFigures = figuren.size()*Math.random() as Integer
            numFigures.times {
                registeredFigures << figuren [figuren.size()*Math.random() as Integer]
            }
            new Anmeldung(
                    wettkampf: contest,
                    kind: kind,
                    gemeldeteFiguren: registeredFigures.unique()
            ).save(flush:true, failOnError: true)
        }
    }
	
}

