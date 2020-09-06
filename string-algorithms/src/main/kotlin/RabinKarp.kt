import kotlin.math.pow

class RabinKarp {
    fun Char.toCharInt() = this.toInt() - 64
    fun main5288() {
        val s = "ACACBAD"
        (s.length - 3 downTo 0).forEach {
            println(s.substring(it, it + 3).foldIndexed(0) { index: Int, acc: Int, c: Char ->
                acc + c.toCharInt() * 3.0.pow(index.toDouble()).toInt()
            } % 11)
        }

    }

    fun main5290() {
        val s = "AACCDB"
        val p = "ACC"
        s.search(p)
    }

    fun main5289() {
        val s = "DDABCD"
        val p = "DDA"
        s.search(p)
    }
}

private fun String.search(p: String, a: Int = 3, m: Int = 11) {
    fun Char.toCharInt() = this.toInt() - 64
    // first create right string hash
    val startIndex = length - p.length
    val rightString = substring(startIndex)
    fun String.polynomialValue(a: Int): Int {
        return foldIndexed(0) { index: Int, acc: Int, c: Char ->
            println(
                "acc:$acc c.toCharInt() * a.pow(index):${c.toCharInt() * a.toDouble().pow(index.toDouble()).toInt()}"
            )
            acc + c.toCharInt() * a.toDouble().pow(index.toDouble()).toInt()
        }
    }

    val patternHash = p.polynomialValue(a) % m
    println("patternHash:$patternHash")
    var currentValue = rightString.polynomialValue(a)

    print("" + (currentValue % m))
    if (patternHash == (currentValue % m)) {
        compairSubstring(p, startIndex)
    } else {
        println(" 0 0")
    }
    (startIndex - 1 downTo 0).forEach {
        val addIndex = it
        val removeIndex = it + p.length
//        println("add:" + get(addIndex))
//        println("remove:" + get(removeIndex))
        val add = get(addIndex).toCharInt()
//        println(add)
        val remove = get(removeIndex).toCharInt() * a * a.toDouble()
            .pow(p.length.toDouble() - 1).toInt()
//        println(remove)
        val value =
            currentValue * a + add - remove
        currentValue = value
        print((currentValue % m))
        if (patternHash == (currentValue % m)) {
            compairSubstring(p, addIndex)
        } else {
            println(" 0 0")
        }
    }
}

private fun String.compairSubstring(p: String, first: Int) {
    var compareCount = 0
    val match = p.withIndex().all { (index, c) ->
        compareCount++
        get(first + index) == c
    }
    print(" $compareCount ")
    if (match) {
        println(1)
    } else {
        println(0)
    }
}


fun main() {
    RabinKarp().main5289()
}