class CountingSort {
    fun mainSample() {
        val numbers = listOf(2, 0, 0, 3, 5, 0, 3, 3, 1)

        unstable(numbers)
        stable(numbers)
    }

    fun main3178() {
        stable(listOf(11, 1, 1, 8, 12, 10, 5, 5, 7))
    }

    fun main3179(){
        stable(listOf(4, 5, 4, 7, 3, 2, 1, 7))
    }

    private fun unstable(numbers: List<Int>) {
        val sortedMap = sortedMapOf<Int, Int>()
        numbers.forEach { number ->
            sortedMap.compute(number) { k, v ->
                v ?: return@compute 1
                v + 1
            }
        }
        println(
            sortedMap
                .entries
                .flatMap { (key, value) -> (0 until value).map { key } }
        )
    }

    private fun stable(numbers: List<Int>) {
        val maxNumberArraySize = numbers.max()!! + 1
        val counts = Array(maxNumberArraySize) { 0 }
        numbers.forEach { number ->
            counts[number] = counts[number] + 1
        }
        println("counts:" + counts.joinToString(" "))
        val cumulativeCounts = Array(maxNumberArraySize) { 0 }
        counts.forEachIndexed { index, value ->
            cumulativeCounts[index] = value + cumulativeCounts.getOrElse(index - 1) { 0 }
        }
        println("cumulativeCounts:" + cumulativeCounts.joinToString(" "))
        val sortedArray = Array(numbers.size) { 0 }
        numbers.reversed().forEach {
            cumulativeCounts[it] -= 1
            val insertIndex = cumulativeCounts[it]
            sortedArray[insertIndex] = it
        }
        println(sortedArray.joinToString(" "))
    }
}

fun main() {
    CountingSort().main3179()
}