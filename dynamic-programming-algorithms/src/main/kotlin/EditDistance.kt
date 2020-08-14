class EditDistance {
    // https://hyperskill.org/learn/step/5507
    fun mainSample() {
        val t = "brown"
        val s = "bone"
        editDistance(s, t)
    }

    private fun editDistance(s: String, t: String) {
        val dpArray = Array(s.length + 1) {
            Array<Int?>(t.length + 1) {
                null
            }
        }
        dpArray.indices.forEach { index ->
            dpArray[index][0] = index
        }
        dpArray[0].indices.forEach { index ->
            dpArray[0][index] = index
        }

        dpArray.indices.drop(1).forEach { sIndexWithFirst ->
            dpArray[sIndexWithFirst].indices.drop(1).forEach { tIndexWithFirst ->
                val sIndex = sIndexWithFirst - 1
                val tIndex = tIndexWithFirst - 1
                if (s[sIndex] == t[tIndex]) {
                    dpArray[sIndexWithFirst][tIndexWithFirst] = dpArray[sIndexWithFirst - 1][tIndexWithFirst - 1]
                } else {
                    dpArray[sIndexWithFirst][tIndexWithFirst] = minOf(
                        dpArray[sIndexWithFirst][tIndexWithFirst - 1]!!,
                        dpArray[sIndexWithFirst - 1][tIndexWithFirst]!!,
                        dpArray[sIndexWithFirst - 1][tIndexWithFirst - 1]!!
                    ) + 1
                }
            }
        }

        println("@, " + t.toCharArray().joinToString(" "))
        println(dpArray.joinToString("\n") { it.joinToString(" ") })
    }

    // https://hyperskill.org/learn/step/5511
    fun main5511() {
        editDistance("editing", "distance")
    }

    // https://hyperskill.org/learn/step/5511
    fun main5512() {
        editDistance("moment", "meaning")
    }

    fun main5513() {
        editDistance("mean", "median")
    }
}

fun main() {
    EditDistance().main5513()
}