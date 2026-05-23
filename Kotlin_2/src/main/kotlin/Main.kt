fun main() {
    val kahvinkeitin = Kahvinkeitin()
    println(kahvinkeitin)
    kahvinkeitin.taytaVesiSailio()
    kahvinkeitin.taytaKahviSailio()
    println(kahvinkeitin)
    println("espressoja: ${kahvinkeitin.espressoja}")
    println("tavallisia: ${kahvinkeitin.tavallisia}")
    println("tee espresso: ${kahvinkeitin.teeEspresso()}")
    println(kahvinkeitin)
    kahvinkeitin.painaOnOffNappia()
    println("tee tavallinen kahvi: ${kahvinkeitin.teeTavallinenKahvi()}")
    println(kahvinkeitin)
    for (i in 0..4) {
        println("tee espresso: ${kahvinkeitin.teeEspresso()}")
        println(kahvinkeitin)
    }
    println("espressoja: ${kahvinkeitin.espressoja}")
    println("tavallisia: ${kahvinkeitin.tavallisia}")
    kahvinkeitin.taytaVesiSailio()
    println(kahvinkeitin)
    for (i in 0..4) {
        println("tee espresso: ${kahvinkeitin.teeEspresso()}")
        println(kahvinkeitin)
    }
}