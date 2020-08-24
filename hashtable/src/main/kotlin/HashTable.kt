class HashTable {
    fun chainingSample() {
        class Table {
            var bucketsSize = 4
            var elementsSize = 0
            var table = Array<MutableList<Int>>(bucketsSize) {
                mutableListOf()
            }

            fun add(value: Int) {
                table[value % bucketsSize].add(value)
                elementsSize++
                if (elementsSize > bucketsSize * 0.75) {
                    // grow table
                    bucketsSize *= 2
                    val allElements = table.flatMap { it }
                    table = Array(bucketsSize) {
                        mutableListOf<Int>()
                    }
                    elementsSize = 0
                    for (element in allElements) {
                        add(element)
                    }
                }
            }

            fun printTable() {
                println(table.joinToString("\n") {
                    it.joinToString(", ")
                })
            }
        }


        val insertNumbers = listOf(1, 4, 6, 7, 11, 16)
        val table = Table()
        insertNumbers.forEach { number ->
            table.add(number)
        }
        table.printTable()
    }
}

fun main() {
    HashTable().chainingSample()
}