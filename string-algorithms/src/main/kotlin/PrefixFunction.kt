class PrefixFunction {
    // https://hyperskill.org/learn/step/6417
    fun main6417() {
        val string = "ABRACADABRA"
        val prefixArray = Array<Int?>(string.length) { null }
        prefixArray[0] = 0
        for (i in string.indices) {
            if (i == 0) continue
            val prev = prefixArray[i - 1] ?: 0
            prefixArray[i] = if (string[prev] == string[i]) {
                prev + 1
            } else {
                0
            }
        }
        println(prefixArray.joinToString(" "))
    }
}

fun main() {
    PrefixFunction().main6417()
}