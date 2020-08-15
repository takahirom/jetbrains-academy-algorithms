class MergeSort {
    // https://hyperskill.org/learn/step/4963
    fun mainSample() {
        val list = mutableListOf(30, 21, 23, 19, 28, 11, 23)

        println(mergeSortTopDown(list))
        println(mergeSortBottomUp(list.map { listOf(it) }))
    }

    fun main3189(){
        mergeSortTopDown(listOf(9, 11, 5, 10, 4, 12, 14, 19, 15, 3, 13, 11))
    }

    var mergeCount = 0
    private fun merge(before: List<Int>, after: List<Int>): List<Int> {
        println("merging: $before $after ${mergeCount++}")
        var beforeIndex = 0
        var afterIndex = 0
        val merged = mutableListOf<Int>()
        while (beforeIndex < before.size && afterIndex < after.size) {
            if (before[beforeIndex] < after[afterIndex]) {
                merged += before[beforeIndex]
                beforeIndex++
            } else {
                merged += after[afterIndex]
                afterIndex++
            }
        }
        merged += (beforeIndex until before.size).map { before[it] }
        merged += (afterIndex until after.size).map { after[it] }
        return merged
    }

    private fun mergeSortTopDown(list: List<Int>): List<Int> {
        println("dividing: $list")
        if (list.size == 1) return list
        val before = mergeSortTopDown(list.subList(0, list.size / 2))
        val after = mergeSortTopDown(list.subList(list.size / 2, list.size))
        return merge(before, after)
    }

    private fun mergeSortBottomUp(list: List<List<Int>>): List<Int> {
        if (list.size == 1) return list[0]
        val windowed = list.windowed(2, 2, true) {
            val second = it.getOrNull(1) ?: return@windowed it[0]
            merge(it[0], second)
        }
        return mergeSortBottomUp(windowed)
    }
}

fun main() {
    MergeSort().main3189()
}