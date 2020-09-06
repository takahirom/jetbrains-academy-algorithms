import kotlin.math.pow

class StringHash {
    fun Char.toCharInt() = this.toInt() - 64

    fun linearHashSample() {
        println("ABAC".linearHash())
        println("CBAA".linearHash())
        println("collision!")
    }

    private fun String.linearHash() = fold(0) { acc, c -> c.toCharInt() + acc }

    fun polynomialSample() {
        println("BBAB".polynomialHash())
        println("ABCC".polynomialHash())
        println("collision!")
    }

    /**
     * @param a a is a constant, usually a prime number approximately equal to the total number of different symbols in the alphabet
     * @param m m is a constant as well, usually a big prime number
     */
    private fun String.polynomialHash(
        a: Int = 3,
        m: Int = 11
    ): Int {
        return polynomialValue(a).rem(m)
    }

    /**
     * hP(s)=(s0 ⋅a^0 +s1 ⋅a^1 +...+s n−1 ⋅a^(n−1)) mod m
     */
    private fun String.polynomialValue(a: Int = 3): Int {
        return foldIndexed(0) { index: Int, acc: Int, c: Char ->
            acc + c.toCharInt() * a.toDouble().pow(index.toDouble()).toInt()
        }
    }

    fun rollingSample() {
        println("ABBC:" + "ABBC".linearHash())
        println("BBCC = ABBC - A + C :" + ("ABBC".linearHash() - "A".linearHash() + "C".linearHash()))

        println("check: BBCC:" + "BBCC".linearHash())

        println("BCCB:" + "BCCB".polynomialHash())
        println(
            "BBCC = BCCB(B * 3^0 + C * 3^1 + C * 3^2 + B * 3^3) * 3 - B * 3^3 * 3 + B * 3^0 = " +
                    ("BCCB".polynomialValue() * 3 - 2/*B*/ * 3 * 3 * 3/* 3^3 */ * 3 + 2) % 11
        )
        println("check: BBCC:" + "BBCC".polynomialHash())

    }

    fun main6424() {
        println("BBACD".polynomialHash(m = 13))
    }

    fun main6425() {
        class Generator() {
            var a = 0
            private fun getOneString(int: Int): String {
                when (int) {
                    0 -> {
                        return "A"
                    }
                    1 -> {
                        return "B"
                    }
                    2 -> {
                        return "C"
                    }
                }
                return "C"
            }

            fun getString(): String {
                return getOneString((a / (3 * 3 * 3)) % 3) +
                        getOneString((a / (3 * 3)) % 3) +
                        getOneString((a / (3)) % 3) +
                        getOneString((a) % 3)
            }

            fun increment() {
                a++
            }
        }

        val generator = Generator()
        while (true) {
            val string = generator.getString()
            println(string)
            val hash = string.polynomialHash(m = 13)
            println(hash)
            generator.increment()
        }
    }

    fun main6426() {
        val s = "AACCDB"
        val rollingLength = 4
        // CCDB
        // ACCD IN A OUT B
        val first = s.substring(s.length - rollingLength)
        println(first)
        var currentValue = first.polynomialValue()
        println(currentValue % 13)
        ((s.length - rollingLength - 1) downTo 0).forEach { index ->
            val addIndex = index
            val removeIndex = index + rollingLength
            println("add:" + s[addIndex])
            println("""remove:${s[removeIndex]} (${s[removeIndex].toCharInt()})""")
            val remove = s[removeIndex].toCharInt().toDouble() * 3.0.pow(3).toInt() * 3
            val add = s[addIndex].toCharInt()
            println("remove value :$remove(${remove % 13})")
            println("add value:$add")
            val value = currentValue * 3 - remove + add
            currentValue = value.toInt()
            println("value:$currentValue(${currentValue % 13})")
            println(value % 13)
        }
        println("ACCD".polynomialHash(m = 13))
        println("ACCD".polynomialValue())
        println("AACC".polynomialHash(m = 13))
        println("AACC".polynomialValue())
    }
}

fun main() {
    StringHash().main6426()
}