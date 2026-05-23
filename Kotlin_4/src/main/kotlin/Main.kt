// --- Dataclass mittauksille ---
data class Säätiedot(val lämpötila: Float, val kosteus: Float, val paine: Float)

// --- Tarkkailija-rajapinta ---
interface Tarkkailija {
    fun päivitä(tiedot: Säätiedot)
}

// --- Aihe-rajapinta ---
interface Aihe {
    fun rekisteröiTarkkailija(t: Tarkkailija)
    fun poistaTarkkailija(t: Tarkkailija)
    fun ilmoitaTarkkailijoille()
}

// --- Aihe (tai Observable) ---
class Sääasema : Aihe {
    private var nykyisetTiedot: Säätiedot? = null
    private val tarkkailijat = mutableListOf<Tarkkailija>()

    // Tätä metodia kutsutaan aina, kun uusia säätietoja on saatavilla.
    fun mittauksetMuuttuivat(uudetTiedot: Säätiedot) {
        this.nykyisetTiedot = uudetTiedot
        println("Sääasema: Sai uudet tiedot -> $nykyisetTiedot")
        ilmoitaTarkkailijoille()
    }

    override fun rekisteröiTarkkailija(t: Tarkkailija) {
        tarkkailijat.add(t)
    }

    override fun poistaTarkkailija(t: Tarkkailija) {
        tarkkailijat.remove(t)
    }

    override fun ilmoitaTarkkailijoille() {
        nykyisetTiedot?.let { tiedot ->
            for (tarkkailija in tarkkailijat) {
                tarkkailija.päivitä(tiedot)
            }
        }
    }
}

// --- Tarkkailijat ---
class NykyisetOlosuhteetNäyttö : Tarkkailija {
    private var lämpötila: Float = 0.0f
    private var kosteus: Float = 0.0f

    override fun päivitä(tiedot: Säätiedot) {
        this.lämpötila = tiedot.lämpötila
        this.kosteus = tiedot.kosteus
        näytä()
    }

    fun näytä() {
        println("NykyisetOlosuhteetNäyttö: Nykyiset olosuhteet: ${lämpötila}C astetta ja ${kosteus}% kosteutta")
    }
}

class Tilastonäyttö : Tarkkailija {
    private val lämpötilat = mutableListOf<Float>()

    override fun päivitä(tiedot: Säätiedot) {
        lämpötilat.add(tiedot.lämpötila)
        näytä()
    }

    fun näytä() {
        val keskiarvo = if (lämpötilat.isNotEmpty()) lämpötilat.average() else 0.0
        println("Tilastonäyttö: Keskilämpötila: ${keskiarvo}C")
    }
}


// --- Pääfunktio simulaation ajamiseen ---
fun main() {
    // 1. Luodaan Sääasema (aihe).
    val sääasema = Sääasema()

    // 2. Luodaan näyttölaitteet (tarkkailijat).
    val nykyinenNäyttö = NykyisetOlosuhteetNäyttö()
    val tilastonäyttö = Tilastonäyttö()

    // 3. Rekisteröidään tarkkailijat sääasemalle.
    sääasema.rekisteröiTarkkailija(nykyinenNäyttö)
    sääasema.rekisteröiTarkkailija(tilastonäyttö)


    // Simuloidaan uusia säämittauksia.
    println("--- Simuloidaan uusi mittaus ---")
    sääasema.mittauksetMuuttuivat(Säätiedot(25.0f, 65f, 1012f))

    println("\n--- Simuloidaan toinen mittaus ---")
    sääasema.mittauksetMuuttuivat(Säätiedot(27.5f, 70f, 1011f))

    // 4. Poistetaan yksi tarkkailijoista.
    println("\n--- Poistetaan Tilastonäyttö rekisteristä ---")
    sääasema.poistaTarkkailija(tilastonäyttö)


    println("--- Simuloidaan viimeinen mittaus ---")
    sääasema.mittauksetMuuttuivat(Säätiedot(26.0f, 90f, 1013f))
}
