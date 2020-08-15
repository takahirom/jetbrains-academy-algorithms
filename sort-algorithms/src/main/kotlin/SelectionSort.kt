class SelectionSort {
    fun main3149() {
        val numbers = mutableListOf(12, 20, 11, 10, 14, 16, 15, 10)
        numbers.sort()
        println(numbers)
    }

    fun main3152() {
        val numbers = mutableListOf(1, 2, 3, 4, 6, 5, 4)
        numbers.sort()
        println(numbers)
    }

    private fun MutableList<Int>.sort() {
        fun MutableList<Int>.swap(index1: Int, index2: Int) {
            println("swap:$index1 <-> $index2")
            val temp = get(index1)
            set(index1, get(index2))
            set(index2, temp)
        }
        indices.forEach { updateIndex ->
            val maxIndex = (updateIndex until size).maxBy { this[it] }!!
            if (maxIndex != updateIndex) {
                swap(updateIndex, maxIndex)
            }
            println(joinToString(" "))
        }
    }
}

fun main() {
    SelectionSort().main3152()
}