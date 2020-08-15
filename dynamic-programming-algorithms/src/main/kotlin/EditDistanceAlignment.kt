import kotlin.concurrent.timer

@OptIn(ExperimentalStdlibApi::class)
class EditDistanceAlignment {
    // https://hyperskill.org/learn/step/5684
    fun mainSample() {
        val s = "bone"
        val t = "brown"
        editDistanceAlignment(s, t)
    }

    // https://hyperskill.org/learn/step/5689
    fun main5689(){
        val s = "hello"
        val t = "yellow"
        editDistanceAlignment(s, t)
    }

    // https://hyperskill.org/learn/step/5688
    fun main5688(){
        val s = "rat"
        val t = "robot"
        editDistanceAlignment(s, t)
    }

    // https://hyperskill.org/learn/step/5690
    fun main5690(){
        val s = "editing"
        val t = "distance"
        editDistanceAlignment(s, t)
    }

    private fun editDistanceAlignment(s: String, t: String) {
        val dpArray = Array(s.length + 1) {
            Array<Int?>(t.length + 1) {
                null
            }
        }
        dpArray.calcDpArray(s, t)

        var current = dpArray.size - 1 to dpArray[0].size - 1
        val list = mutableListOf<Pair<Int, Int>>(current)
        while (current != 0 to 0) {
            val (sIndex, tIndex) = current
            val nextCandidates = listOf(
                sIndex - 1 to tIndex - 1,
                sIndex - 1 to tIndex,
                sIndex to tIndex - 1
            )
            val min = nextCandidates.minBy {
                dpArray.getOrNull(it.first)?.getOrNull(it.second) ?: Int.MAX_VALUE
            }!!
            list.add(min)
            current = min
        }
        println(list.reversed())
        val sStringBuilder = StringBuilder()
        val tStringBuilder = StringBuilder()
        list.reversed().windowed(2, 1) {
            val (before, after) = it
            val (beforeSIndex, beforeTIndex) = before
            val (afterSIndex, afterTIndex) = after
            if (beforeSIndex < afterSIndex && beforeTIndex < afterTIndex) {
                sStringBuilder.append(s[afterSIndex - 1])
                tStringBuilder.append(t[afterTIndex - 1])
            } else if (beforeSIndex < afterSIndex) {
                sStringBuilder.append(s[afterSIndex - 1])
                tStringBuilder.append("-")
            } else {
                sStringBuilder.append("-")
                tStringBuilder.append(t[afterTIndex - 1])
            }
        }
        println(sStringBuilder)
        println(tStringBuilder)
    }

    private fun Array<Array<Int?>>.calcDpArray(s: String, t: String) {
        indices.forEach { index ->
            this[index][0] = index
        }
        this[0].indices.forEach { index ->
            this[0][index] = index
        }

        indices.drop(1).forEach { sIndexWithFirst ->
            this[sIndexWithFirst].indices.drop(1).forEach { tIndexWithFirst ->
                val sIndex = sIndexWithFirst - 1
                val tIndex = tIndexWithFirst - 1
                if (s[sIndex] == t[tIndex]) {
                    this[sIndexWithFirst][tIndexWithFirst] = this[sIndexWithFirst - 1][tIndexWithFirst - 1]
                } else {
                    this[sIndexWithFirst][tIndexWithFirst] = minOf(
                        this[sIndexWithFirst][tIndexWithFirst - 1]!!,
                        this[sIndexWithFirst - 1][tIndexWithFirst]!!,
                        this[sIndexWithFirst - 1][tIndexWithFirst - 1]!!
                    ) + 1
                }
            }
        }

        println("@, " + t.toCharArray().joinToString(" "))
        println(joinToString("\n") { it.joinToString(" ") })
    }
}

fun main() {
    EditDistanceAlignment().main5690()
}