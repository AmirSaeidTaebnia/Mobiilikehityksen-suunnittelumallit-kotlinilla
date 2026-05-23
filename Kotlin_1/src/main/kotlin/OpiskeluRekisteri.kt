package org.example

open class Ihminen(var nimi: String, var ikä: Int) {
    fun tuleVanhemmaksi() {
        ikä++
    }
}

class KurssiRekisteri(val nimi: String, val suoritusVuosi: Int, val krediitit: Int, val arvosana: Double)

class Opiskelija(nimi: String, ikä: Int) : Ihminen(nimi, ikä) {
    val kurssit = ArrayList<KurssiRekisteri>()

    fun lisääKurssi(kurssi: KurssiRekisteri) {
        kurssit.add(kurssi)
    }

    fun painotettuKeskiarvo(): Double {
        if (kurssit.isEmpty()) return 0.0
        var kokonaisPainotetutPisteet = 0.0
        var kokonaisKrediitit = 0
        for (kurssi in kurssit) {
            kokonaisPainotetutPisteet += kurssi.arvosana * kurssi.krediitit
            kokonaisKrediitit += kurssi.krediitit
        }
        return if (kokonaisKrediitit == 0) 0.0 else kokonaisPainotetutPisteet / kokonaisKrediitit
    }

    fun painotettuKeskiarvo(vuosi: Int): Double {
        val filtteroidutKurssit = kurssit.filter { it.suoritusVuosi == vuosi }
        if (filtteroidutKurssit.isEmpty()) return 0.0
        var kokonaisPainotetutPisteet = 0.0
        var kokonaisKrediitit = 0
        for (kurssi in filtteroidutKurssit) {
            kokonaisPainotetutPisteet += kurssi.arvosana * kurssi.krediitit
            kokonaisKrediitit += kurssi.krediitit
        }
        return if (kokonaisKrediitit == 0) 0.0 else kokonaisPainotetutPisteet / kokonaisKrediitit
    }

    fun minMaxArvosanat(): Pair<Double, Double> {
        if (kurssit.isEmpty()) return Pair(0.0, 0.0)
        var min = Double.MAX_VALUE
        var max = Double.MIN_VALUE
        for (kurssi in kurssit) {
            if (kurssi.arvosana < min) min = kurssi.arvosana
            if (kurssi.arvosana > max) max = kurssi.arvosana
        }
        return Pair(min, max)
    }
}

class Pääaine(val nimi: String) {
    val opiskelijat = ArrayList<Opiskelija>()

    fun lisääOpiskelija(opiskelija: Opiskelija) {
        opiskelijat.add(opiskelija)
    }

    fun statistiikka(): Triple<Double, Double, Double> {
        if (opiskelijat.isEmpty()) return Triple(0.0, 0.0, 0.0)
        val keskiarvot = opiskelijat.map { it.painotettuKeskiarvo() }
        val min = keskiarvot.minOrNull() ?: 0.0
        val max = keskiarvot.maxOrNull() ?: 0.0
        val avg = keskiarvot.average()
        return Triple(min, max, avg)
    }

    fun statistiikka(kurssiNimi: String): Triple<Double, Double, Double> {
        val merkittävätKeskiarvot = opiskelijat.mapNotNull { student ->
            val kurssiRekisterit = student.kurssit.filter { it.nimi == kurssiNimi }
            if (kurssiRekisterit.isEmpty()) {
                null
            } else {
                var kokonaisPainotetutPisteet = 0.0
                var kokonaisKrediitit = 0
                for (kurssi in kurssiRekisterit) {
                    kokonaisPainotetutPisteet += kurssi.arvosana * kurssi.krediitit
                    kokonaisKrediitit += kurssi.krediitit
                }
                if (kokonaisKrediitit == 0) 0.0 else kokonaisPainotetutPisteet / kokonaisKrediitit
            }
        }

        if (merkittävätKeskiarvot.isEmpty()) return Triple(0.0, 0.0, 0.0)
        
        val min = merkittävätKeskiarvot.minOrNull() ?: 0.0
        val max = merkittävätKeskiarvot.maxOrNull() ?: 0.0
        val avg = merkittävätKeskiarvot.average()
        return Triple(min, max, avg)
    }
}
