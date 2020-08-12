class FloydWarshallAlgorithm {
    // https://hyperskill.org/learn/step/5796
    fun main() {
        val inf = Int.MAX_VALUE
        val matrix = arrayOf(
            arrayOf(0, 2, 3, 6, 9),
            arrayOf(2, 0, inf, 3, inf),
            arrayOf(3, inf, 0, inf, 4),
            arrayOf(6, 3, inf, 0, 1),
            arrayOf(9, inf, 4, 1, 0)
        )

        matrix.indices.forEach { k ->
            matrix.indices.forEach { u ->
                matrix.indices.forEach iterate@{ v ->
                    val toCenterPointDistance = matrix[u][k]
                    val fromCenterPointDistance = matrix[k][v]
                    if (toCenterPointDistance == inf) return@iterate
                    if (fromCenterPointDistance == inf) return@iterate
                    val centerPointDistance = toCenterPointDistance + fromCenterPointDistance
                    if (centerPointDistance < matrix[u][v]) {
                        matrix[u][v] = centerPointDistance
                    }
                }
            }
        }
        println(matrix.joinToString("\n") {
            it.joinToString(" ") {
                if (it == Int.MAX_VALUE) "inf" else it.toString()
            }
        })
    }
}