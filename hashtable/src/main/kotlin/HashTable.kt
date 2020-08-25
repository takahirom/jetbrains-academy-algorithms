class HashTable {
    fun chainingSample() {
        class Table {
            private var bucketsSize = 4
            private var elementsSize = 0
            private var table = Array<MutableList<Int>>(bucketsSize) {
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

    fun linearProbingSample() {
        class Table {
            val REMOVE_SPECIAL_VALUE = -1
            val NOT_SET_SPECIAL_VALUE = -1
            private val bucketSize = 4
            private val table = Array(bucketSize) {
                NOT_SET_SPECIAL_VALUE
            }

            fun add(value: Int) {
                var colledeIndex = 0
                while (table[(value + colledeIndex) % bucketSize] > 0) {
                    colledeIndex++
                }
                table[(value + colledeIndex) % bucketSize] = value
            }

            fun delete(value: Int) {
                table[indexOf(value)] = REMOVE_SPECIAL_VALUE
            }

            fun indexOf(value: Int): Int {
                var additionalIndex = 0
                while (table[(value + additionalIndex) % bucketSize] != value && table[(value + additionalIndex) % bucketSize] != NOT_SET_SPECIAL_VALUE) {
                    // search not set field or value
                    additionalIndex++
                }
                val selectedValue = table[(value + additionalIndex) % bucketSize]
                if (selectedValue == NOT_SET_SPECIAL_VALUE) {
                    return -1
                }
                return (value + additionalIndex) % bucketSize
            }

            fun printTable() {
                println("table:")
                for (i in table) {
                    println(i)
                }
            }
        }

        val insertNumbers = listOf(1, 2, 5)
        val table = Table()
        insertNumbers.forEach {
            table.add(it)
        }
        table.printTable()
        println("indexOf(2):" + table.indexOf(2))
        table.delete(2)
        println("indexOf(5):" + table.indexOf(5))
        println("indexOf(2):" + table.indexOf(2))
        table.printTable()
    }

    fun quadraticProbingSample() {
        class Table {
            val REMOVE_SPECIAL_VALUE = -1
            val NOT_SET_SPECIAL_VALUE = 0
            private val bucketSize = 8
            private val table = Array(bucketSize) {
                NOT_SET_SPECIAL_VALUE
            }

            fun add(value: Int) {
                var colledeIndex = 0
                while (table[hash(value, colledeIndex) % bucketSize] > 0) {
                    colledeIndex++
                }
                table[hash(value, colledeIndex) % bucketSize] = value
            }

            fun delete(value: Int) {
                table[indexOf(value)] = REMOVE_SPECIAL_VALUE
            }

            fun indexOf(value: Int): Int {
                var additionalIndex = 0
                while (table[(hash(value, additionalIndex)) % bucketSize] != value && table[hash(
                        value,
                        additionalIndex
                    ) % bucketSize] != NOT_SET_SPECIAL_VALUE
                ) {
                    // search not set field or value
                    additionalIndex++
                }
                val selectedValue = table[hash(value, additionalIndex) % bucketSize]
                if (selectedValue == NOT_SET_SPECIAL_VALUE) {
                    return -1
                }
                return hash(value, additionalIndex) % bucketSize
            }

            private fun hash(value: Int, additionalIndex: Int) = value + additionalIndex * additionalIndex

            fun printTable() {
                println("table:")
                for (i in table) {
                    println(i)
                }
            }
        }

        val insertNumbers = listOf(1, 2, 3, 4, 9)
        val table = Table()
        insertNumbers.forEach {
            table.add(it)
        }
        table.printTable()
        println("indexOf(2):" + table.indexOf(2))
        table.delete(2)
        println("indexOf(5):" + table.indexOf(5))
        println("indexOf(2):" + table.indexOf(2))
        table.printTable()
    }

    fun main9569() {
        class Table {
            val REMOVE_SPECIAL_VALUE = -1
            val NOT_SET_SPECIAL_VALUE = 0
            private val bucketSize = 8
            private val table = arrayOf(16, 0, 2, 3, 0, 5, -1, -1)

            fun add(value: Int) {
                var colledeIndex = 0
                while (table[hash(value, colledeIndex) % bucketSize] > 0) {
                    colledeIndex++
                }
                table[hash(value, colledeIndex) % bucketSize] = value
            }

            fun delete(value: Int) {
                table[indexOf(value)] = REMOVE_SPECIAL_VALUE
            }

            fun indexOf(value: Int): Int {
                var additionalIndex = 0
                while (table[(hash(value, additionalIndex)) % bucketSize] != value && table[hash(
                        value,
                        additionalIndex
                    ) % bucketSize] != NOT_SET_SPECIAL_VALUE
                ) {
                    println("searched" + hash(value, additionalIndex) % bucketSize)
                    // search not set field or value
                    additionalIndex++
                }
                val selectedValue = table[hash(value, additionalIndex) % bucketSize]
                if (selectedValue == NOT_SET_SPECIAL_VALUE) {
                    return -1
                }
                return hash(value, additionalIndex) % bucketSize
            }

            private fun hash(value: Int, additionalIndex: Int) =
                value + additionalIndex * additionalIndex + additionalIndex

            fun printTable() {
                println("table:")
                for (i in table) {
                    println(i)
                }
            }
        }

        val table = Table()
        table.printTable()
        println(table.indexOf(8))
    }
}

fun main() {
    HashTable().main9569()
}