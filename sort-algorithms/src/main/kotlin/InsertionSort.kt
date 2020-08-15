class InsertionSort {
    // https://hyperskill.org/learn/step/4963
    fun mainSample() {
        val list = mutableListOf(21, 23, 19, 30, 11, 28)
        list.sort()
        println(list)
    }

    // https://hyperskill.org/learn/step/3171
    fun main3171() {
        val list = mutableListOf(20, 18, 16, 15)
        list.sort()
        list += listOf(14, 12, 10)
        println(list.joinToString(" "))
    }

    // https://hyperskill.org/learn/step/3170
    fun main3170() {
        val list = mutableListOf(12, 20, 11, 10, 14)
        list.sort()
        list += listOf(16, 15, 10)
        println(list.joinToString(" "))
    }

    // https://hyperskill.org/learn/step/3172
    fun main3172() {
        val list = mutableListOf('z', 'e', 'l', 'k')
        list.indices.forEach { updateIndex ->
            println(list.joinToString(" "))
            val minIndex = (updateIndex until list.size).maxBy { searchIndex ->
                list[searchIndex]
            }!!
            list.add(updateIndex, list.removeAt(minIndex))
        }
        list += listOf('a', 'x')
        println(list.joinToString(" "))
    }

    private fun MutableList<Int>.sort() {
        indices.forEach { updateIndex ->
            println(joinToString(" "))
            val minIndex = (updateIndex until size).minBy { searchIndex ->
                this[searchIndex]
            }!!
            add(updateIndex, removeAt(minIndex))
        }
    }
}

fun main() {
    InsertionSort().main3172()
}