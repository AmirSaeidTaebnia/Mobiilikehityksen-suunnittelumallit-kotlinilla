/*
Nimi: Amir Saeid Taebnia
Opiskelijanumero: 2322852
*/

import kotlin.math.min

class Kahvinkeitin(val vesiKapasiteetti: Int = 300, val kahviKapasiteetti: Int = 100) {
    var vedenMaara: Int = 0
        private set
    var kahvinMaara: Int = 0
        private set
    var onkoPaalla: Boolean = false
        private set

    val espressoja: Int
        get() = min(vedenMaara / 30, kahvinMaara / 16)

    val tavallisia: Int
        get() = min(vedenMaara / 200, kahvinMaara / 15)

    fun taytaVesiSailio() {
        vedenMaara = vesiKapasiteetti
    }

    fun taytaKahviSailio() {
        kahvinMaara = kahviKapasiteetti
    }

    fun painaOnOffNappia() {
        onkoPaalla = !onkoPaalla
    }

    fun teeEspresso(): Boolean {
        if (!onkoPaalla) return false
        if (vedenMaara >= 30 && kahvinMaara >= 16) {
            vedenMaara -= 30
            kahvinMaara -= 16
            return true
        }
        return false
    }

    fun teeTavallinenKahvi(): Boolean {
        if (!onkoPaalla) return false
        if (vedenMaara >= 200 && kahvinMaara >= 15) {
            vedenMaara -= 200
            kahvinMaara -= 15
            return true
        }
        return false
    }

    override fun toString(): String {
        val tila = if (onkoPaalla) "päällä" else "kiinni"
        return "Keitin: $vedenMaara/$vesiKapasiteetti vettä, $kahvinMaara/$kahviKapasiteetti kahvia on $tila"
    }
}

fun main() {
    val k = Kahvinkeitin()
    println(k)
    k.taytaVesiSailio()
    k.taytaKahviSailio()
    println(k)
    println("espressoja: ${k.espressoja}")
    println("tavallisia: ${k.tavallisia}")
    println("tee espresso: ${k.teeEspresso()}")
    println(k)
    k.painaOnOffNappia()
    println("tee tavallinen kahvi: ${k.teeTavallinenKahvi()}")
    println(k)
    for (i in 0..4) {
        println("tee espresso: ${k.teeEspresso()}")
        println(k)
    }
    println("espressoja: ${k.espressoja}")
    println("tavallisia: ${k.tavallisia}")
    k.taytaVesiSailio()
    println(k)
    for (i in 0..4) {
        println("tee espresso: ${k.teeEspresso()}")
        println(k)
    }
}

/*
Tulostaa:
Keitin: 0/300 vettä, 0/100 kahvia on kiinni
Keitin: 300/300 vettä, 100/100 kahvia on kiinni
espressoja: 6
tavallisia: 1
tee espresso: false
Keitin: 300/300 vettä, 100/100 kahvia on kiinni
tee tavallinen kahvi: true
Keitin: 100/300 vettä, 85/100 kahvia on päällä
tee espresso: true
Keitin: 70/300 vettä, 69/100 kahvia on päällä
tee espresso: true
Keitin: 40/300 vettä, 53/100 kahvia on päällä
tee espresso: true
Keitin: 10/300 vettä, 37/100 kahvia on päällä
tee espresso: false
Keitin: 10/300 vettä, 37/100 kahvia on päällä
tee espresso: false
Keitin: 10/300 vettä, 37/100 kahvia on päällä
espressoja: 0
tavallisia: 0
Keitin: 300/300 vettä, 37/100 kahvia on päällä
tee espresso: true
Keitin: 270/300 vettä, 21/100 kahvia on päällä
tee espresso: true
Keitin: 240/300 vettä, 5/100 kahvia on päällä
tee espresso: false
Keitin: 240/300 vettä, 5/100 kahvia on päällä
tee espresso: false
Keitin: 240/300 vettä, 5/100 kahvia on päällä
tee espresso: false
Keitin: 240/300 vettä, 5/100 kahvia on päällä
*/