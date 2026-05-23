package org.example

fun main() {
    val opiskelijat = ArrayList<Opiskelija>()

    var opiskelija = Opiskelija("Enzio Benzino", 21)
    opiskelija.lisääKurssi(KurssiRekisteri("Kotlinin alkeet", 2023, 5, 5.0))
    opiskelija.lisääKurssi(KurssiRekisteri("Javan perusta", 2023, 5, 1.0))
    opiskelija.lisääKurssi(KurssiRekisteri("Scalan perusta", 2023, 3, 2.0))
    opiskelijat.add(opiskelija)

    opiskelija = Opiskelija("Abebe Bikila", 23)
    opiskelija.lisääKurssi(KurssiRekisteri("Kotlinin alkeet", 2023, 5, 2.0))
    opiskelijat.add(opiskelija)

    opiskelija = Opiskelija("Günther Radulic", 20)
    opiskelija.lisääKurssi(KurssiRekisteri("Kotlinin alkeet", 2023, 5, 4.0))
    opiskelija.lisääKurssi(KurssiRekisteri("Kotlinin jatko", 2023, 5, 5.0))
    opiskelijat.add(opiskelija)

    val pääaine = Pääaine("Pelkkä ohjelmointi")
    for (o in opiskelijat) {
        pääaine.lisääOpiskelija(o)
    }

    println("Pääaine: ${pääaine.nimi}")
    println("Ylipäätäinen statistiikka (Minimi, Maximi ja Keskiarvo): ${pääaine.statistiikka()}")
    
    val kurssiNimi = "Kotlinin alkeet"
    println("Statistiikat kurssille:'$kurssiNimi' (Minimi, Maximi ja Keskiarvo): ${pääaine.statistiikka(kurssiNimi)}")
}