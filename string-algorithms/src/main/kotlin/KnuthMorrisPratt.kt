class KnuthMorrisPratt {
    // https://hyperskill.org/learn/step/6454
    fun samplePage() {
        val s = "BACBAD"
        val prefixArray = s.calcPrefix()

        val t = "BACBACBAD"
        var matched = ""
        var tIndex = 0
        var sIndex = 0

        while (true) {
            if (t[tIndex] != s[sIndex]) break
            matched += t[tIndex]
            tIndex++
            sIndex++
        }
        // BACBA is substring of the pattern that matches the beginning of the text
        println(matched)

        // length of this substring is 5
        val matchedLength = matched.length
        println(matchedLength)

        // the length of its longest border is p[4] = 2
        // we may shift the pattern by 5-2 = 3 symbols
        val longestBorder = prefixArray.take(matchedLength).max()!! // (this is wrong in algorithm)
        val shift = matchedLength - longestBorder
        tIndex += shift
        // continue the comparison with the 5th symbol of the text and
        // 2nd symbol of the pattern knowing that the previous symbols already match
        while (true) {
            if (s.length == sIndex) break
            if (t[tIndex] != s[sIndex]) break
            matched += t[tIndex]
            tIndex++
            sIndex++
        }

        println(matched)
    }

    private fun String.calcPrefix(): List<Int> {
        val prefixArray = VerboseMutableList(length) { 0 }
        prefixArray[0] = 0
        forEachIndexed { index, c ->
            if (index == 0) return@forEachIndexed
            val indexFromFirst = prefixArray[index - 1]
            if (this[indexFromFirst] == c) {
                prefixArray[index] = indexFromFirst + 1
            } else {
                prefixArray[index] = 0
            }
        }
        println(prefixArray.joinToString(" "))
        return prefixArray
    }

    fun sampleKMP() {
        val s = "BACBAD"
        val prefixArray = s.calcPrefix()

        val t = "BACBACBAD"
        val foundIndex = substringSearch(s, t, prefixArray)
        println(foundIndex)
    }

    fun sampleKMP2() {
        val s = "ABCABD"
        val prefixArray = s.calcPrefix()

        val t = "ABCABCAABCABD"
        val foundIndex = substringSearch(s, t, prefixArray)
        println(foundIndex)
    }

    private fun substringSearch(searchText: String, targetText: String, prefixArray: List<Int>): Int {
        var matched = ""
        var tIndex = 0
        var sIndex = 0

        var compairCountFor6459 = 0
        while (true) {
            if (searchText.length == matched.length) {
                println("count:$compairCountFor6459")
                return tIndex
            }
            if (tIndex > targetText.length) {
                return -1
            }
            println(targetText.toCharArray().joinToString(" "))
            println("  ".repeat(tIndex) + "↑")
            println(searchText.toCharArray().joinToString(" "))
            println("  ".repeat(sIndex) + "↑")
            compairCountFor6459++
            if (targetText[tIndex] != searchText[sIndex]) {
                // shift
                // L−p[L−1]
                // L is the length of the matched substring of the pattern

                //A C A C C A C C A C D A C C A C D A
                //    ↑
                //A C C A C D A
                //    ↑
                // ↓↓↓
                // A C A C C A C C A C D A C C A C D A
                //     ↑
                // A C C A C D A
                // ↑


                //A C A C C A C C A C D A C C A C D A
                //              ↑
                //A C C A C D A
                //          ↑
                // ↓↓↓
                // A C A C C A C C A C D A C C A C D A
                //               ↑
                // A C C A C D A
                //     ↑

                println(matched)
                val prefix = prefixArray.getOrElse(matched.length - 1) { 0 }
                Thread.sleep(1000)
                sIndex = prefix
                matched = matched.take(prefix)
                continue
            }
            matched += targetText[tIndex]
            tIndex++
            sIndex++
        }
    }

    fun main6458() {
        val s = "ABCDABCEE"
        val prefixArray = s.calcPrefix()

        val t = "BABCDABCDABCEE"
        val foundIndex = substringSearch(s, t, prefixArray)
        println(foundIndex)
    }

    fun main6459() {
        val s = "ACCACDA"
        val prefixArray = s.calcPrefix()

        val t = "ACACCACCACDACCACDA"
        val foundIndex = substringSearch(s, t, prefixArray)
        println(foundIndex)
    }

    fun main6460() {
        val s = "ABABAB"
        val prefixArray = s.calcPrefix()

        val t = "ABABAABAABABABABA"
        val foundIndex = substringSearch(s, t, prefixArray)
        println(foundIndex)
    }
}

fun main() {
    KnuthMorrisPratt().main6460()
}

// debug class
class VerboseMutableList<T>(private val delegate: MutableList<T>) : MutableList<T> by delegate {
    constructor(size: Int, init: (Int) -> T) : this(MutableList(size, init))

    override operator fun get(index: Int): T {
        println("get($index)")
        showUpArrow(index)
        return delegate.get(index = index)
    }

    override operator fun set(index: Int, element: T): T {
        println("set($index, $element)")
        showUpArrow(index)
        return delegate.set(index = index, element = element)
    }

    private fun showUpArrow(index: Int) {
        var sizeOfEmpty = 0
        println(buildString {
            delegate.forEachIndexed { logIndex, value ->
                if (logIndex == index) {
                    sizeOfEmpty = length
                }
                append("$value ")
            }
        })
        println(" ".repeat(sizeOfEmpty) + "↑")
    }
}