import kotlin.math.abs

class Murtoluku(osoittaja: Int, nimittäjä: Int, private val merkki: Int = 1) : Comparable<Murtoluku> {
    private val os: Int // osoittaja
    private val nim: Int // nimittäjä

    init {
        if (nimittäjä == 0) {
            throw IllegalArgumentException("Nimittäjä ei voi olla nolla")
        }

        var n = abs(osoittaja)
        var d = abs(nimittäjä)
        val yhteinen = syt(n, d) // suurin yhteinen tekijä
        n /= yhteinen
        d /= yhteinen

        val s = merkki * (if (osoittaja < 0) -1 else 1) * (if (nimittäjä < 0) -1 else 1)
        
        this.os = n * s
        this.nim = d
    }

    private fun syt(a: Int, b: Int): Int { // suurin yhteinen tekijä
        return if (b == 0) a else syt(b, a % b)
    }

    override fun toString(): String {
        return "$os/$nim"
    }

    fun summaa(toinen: Murtoluku): Murtoluku {
        val uusiOs = this.os * toinen.nim + toinen.os * this.nim
        val uusiNim = this.nim * toinen.nim
        return Murtoluku(uusiOs, uusiNim)
    }

    operator fun plus(toinen: Murtoluku) = summaa(toinen)

    fun vähennä(toinen: Murtoluku): Murtoluku {
        val uusiOs = this.os * toinen.nim - toinen.os * this.nim
        val uusiNim = this.nim * toinen.nim
        return Murtoluku(uusiOs, uusiNim)
    }

    operator fun minus(toinen: Murtoluku) = vähennä(toinen)

    fun kerro(toinen: Murtoluku): Murtoluku {
        return Murtoluku(this.os * toinen.os, this.nim * toinen.nim)
    }

    operator fun times(toinen: Murtoluku) = kerro(toinen)

    operator fun div(toinen: Murtoluku): Murtoluku {
        return Murtoluku(this.os * toinen.nim, this.nim * toinen.os)
    }

    fun neganoi(): Murtoluku {
        return Murtoluku(-os, nim)
    }

    operator fun unaryMinus() = neganoi()

    override fun compareTo(toinen: Murtoluku): Int {
        val vasen = this.os.toLong() * toinen.nim.toLong()
        val oikea = toinen.os.toLong() * this.nim.toLong()
        return vasen.compareTo(oikea)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Murtoluku) return false
        return this.os == other.os && this.nim == other.nim
    }

    override fun hashCode(): Int {
        var tulos = os
        tulos = 31 * tulos + nim
        return tulos
    }
}

fun main() {
    val murtolukuA = Murtoluku(1, 2, -1)
    println(murtolukuA)
    println(murtolukuA.summaa(Murtoluku(1, 3)))
    println(murtolukuA.kerro(Murtoluku(5, 2, -1)))
    println(murtolukuA.div(Murtoluku(2, 1)))
    println(-Murtoluku(1, 6) + Murtoluku(1, 2))
    println(Murtoluku(2, 3) * Murtoluku(3, 2))
    println(Murtoluku(1, 2) > Murtoluku(2, 3)) // Comparable-rajapinnan funktio compareTo()
}
