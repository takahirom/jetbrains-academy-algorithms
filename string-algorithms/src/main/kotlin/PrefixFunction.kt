class PrefixFunction {
    fun sample() {
        // Properties of borders
        // ■ property 1
        val s = "ABAEFABA"
        val a = "A"
        val b = "ABA"

        // a is prefix of s
        // a is prefix of b

        // a is suffix of s
        // a is suffix of b

        // if b is longest border
        // next longest border of s can be found in b

        // ■ property 2
        // b is border of a string s
        // b can be extended by the symbol x
        val bx = b + "E"
        val sx = s + "E"
        // bx is border of sx ??
    }

    // https://hyperskill.org/learn/step/6417
    fun main6417() {
        val string = "ABRACADABRA"
        val prefixArray = Array<Int?>(string.length) { null }
        prefixArray[0] = 0
        for (i in string.indices) {
            if (i == 0) continue
            val prev = prefixArray[i - 1] ?: 0
            println("prefixArray:\n" + prefixArray.joinToString(" ") { "" + (it ?: "X") })
            println("  ".repeat(i - 1) + "↑")
            println(string.toCharArray().joinToString(" "))
            println("  ".repeat(prev) + "↑")
            println("  ".repeat(i) + "↑")

            println("string[prev]:" + string[prev])
            println("string[i = $i]:" + string[i])
            prefixArray[i] = if (string[prev] == string[i]) {
                println("border:" + string.substring(0, prev + 1))
                prev + 1
            } else {
                0
            }
        }
        println(string.toCharArray().joinToString(" "))
        println(prefixArray.joinToString(" "))
    }
}

fun main() {
    PrefixFunction().main6417()
}