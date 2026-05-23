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