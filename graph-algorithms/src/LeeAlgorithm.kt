class LeeAlgorithm {
    fun main6412() {
        val maze = arrayOf(
            arrayOf(0, 0, 0, 1, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0, 1, 0),
            arrayOf(0, 1, 0, 0, 0, 1, 0),
            arrayOf(0, 1, 0, 1, 0, 1, 0),
            arrayOf(0, 1, 0, 1, 0, 1, 0),
            arrayOf(0, 0, 0, 0, 0, 0, 0),
            arrayOf(0, 0, 0, 1, 0, 0, 0)
        )

        val startRowToColumn = 2 to 0
        val targetRowToColumn = 3 to 6

        solve(maze, startRowToColumn, targetRowToColumn)
    }

    fun main6410() {
        val maze = arrayOf(
            arrayOf(0, 0, 0, 0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0, 1, 0),
            arrayOf(0, 0, 0, 0, 1, 0, 0),
            arrayOf(0, 0, 0, 0, 0, 1, 0),
            arrayOf(0, 0, 1, 0, 1, 0, 0),
            arrayOf(0, 1, 0, 1, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0, 0, 0)
        )

        val startRowToColumn = 2 to 0
        solve(maze, startRowToColumn)
    }

    fun main6411() {
        val maze = arrayOf(
            arrayOf(0, 0, 0, 0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 1, 1, 0),
            arrayOf(0, 0, 0, 0, 1, 0, 0),
            arrayOf(0, 0, 0, 0, 1, 0, 0),
            arrayOf(0, 0, 0, 0, 1, 0, 0),
            arrayOf(0, 0, 0, 0, 1, 1, 0),
            arrayOf(0, 0, 0, 0, 0, 0, 0)
        )

        val startRowToColumn = 5 to 1
        val targetRowToColumn = 3 to 5


        val rowSize = maze.size
        val columnSize = maze[0].size

        val distanceMap = Array(rowSize) {
            Array(columnSize) {
                Int.MAX_VALUE
            }
        }

        operator fun Array<Array<Int>>.get(rowToColumn: Pair<Int, Int>): Int {
            return get(rowToColumn.first)
                .get(rowToColumn.second)
        }


        operator fun Array<Array<Int>>.set(rowToColumn: Pair<Int, Int>, value: Int) {
            return get(rowToColumn.first)
                .set(rowToColumn.second, value)
        }


        fun findNeighbors(item: Pair<Int, Int>): List<Pair<Int, Int>> {
            val (row, column) = item
            return ((row - 1)..(row + 1)).flatMap { rowIndex ->
                ((column - 1)..(column + 1))
                    .map { rowIndex to it }
            }
                .filter { (rowIndex, columnIndex) -> !(row == rowIndex && column == columnIndex) }
                .filter { (rowIndex, columnIndex) -> !(row != rowIndex && column != columnIndex) }
                .filter { (rowIndex, columnIndex) -> maze.getOrNull(rowIndex)?.getOrNull(columnIndex) == 0 }
        }

        val nextQueue = ArrayDeque<Pair<Int, Int>>()
        nextQueue.add(startRowToColumn)
        val nextQueueFromTarget = ArrayDeque<Pair<Int, Int>>()
        nextQueueFromTarget.add(targetRowToColumn)
        distanceMap[startRowToColumn.first][startRowToColumn.second] = 0
        distanceMap[targetRowToColumn.first][targetRowToColumn.second] = 0

        while (nextQueue.isNotEmpty() && nextQueueFromTarget.isNotEmpty()) {
            val checkNextDistance = distanceMap[nextQueue.first()]
            val allNeighborsFromStart = mutableListOf<Pair<Int, Int>>()
            while (checkNextDistance == distanceMap[nextQueue.first()]) {
                val currentFromStart = nextQueue.removeFirst()
                val nextDistanceFromStart = distanceMap.get(currentFromStart) + 1
                val neighborsFromStart = findNeighbors(currentFromStart)
                allNeighborsFromStart.addAll(neighborsFromStart)
                neighborsFromStart
                    .forEach { next ->
                        if (distanceMap[next] > nextDistanceFromStart) {
                            distanceMap[next] = nextDistanceFromStart
                            nextQueue.add(next)
                        }
                    }
            }

            val checkNextDistanceFromTarget = distanceMap[nextQueueFromTarget.first()]
            val allNeighborsFromTarget = mutableListOf<Pair<Int, Int>>()
            while (checkNextDistanceFromTarget == distanceMap[nextQueueFromTarget.first()]) {
                val currentFromTarget = nextQueueFromTarget.removeFirst()
                val nextDistanceFromTarget = distanceMap.get(currentFromTarget) + 1
                val neighborsFromTarget = findNeighbors(currentFromTarget)
                allNeighborsFromTarget.addAll(neighborsFromTarget)
                neighborsFromTarget
                    .forEach { next ->
                        if (distanceMap[next] > nextDistanceFromTarget) {
                            distanceMap[next] = nextDistanceFromTarget
                            nextQueueFromTarget.add(next)
                        }
                    }
            }


            val center = allNeighborsFromStart.find { allNeighborsFromTarget.contains(it) }

            if (center != null) {
                println(center)
                break
            }
        }
        println("distance map")
        println(distanceMap.joinToString("\n") {
            it.joinToString(" ") {
                if (it == Int.MAX_VALUE) {
                    "o"
                } else if (it == 0) {
                    "s"
                } else {
                    it.toString()
                }
            }
        })
    }


    private fun solve(
        maze: Array<Array<Int>>,
        startRowToColumn: Pair<Int, Int>,
        targetRowToColumn: Pair<Int, Int>? = null
    ) {

        val rowSize = maze.size
        val columnSize = maze[0].size

        val distanceMap = Array(rowSize) {
            Array(columnSize) {
                Int.MAX_VALUE
            }
        }

        operator fun Array<Array<Int>>.get(rowToColumn: Pair<Int, Int>): Int {
            return get(rowToColumn.first)
                .get(rowToColumn.second)
        }


        operator fun Array<Array<Int>>.set(rowToColumn: Pair<Int, Int>, value: Int) {
            return get(rowToColumn.first)
                .set(rowToColumn.second, value)
        }


        fun findNeighbors(item: Pair<Int, Int>): List<Pair<Int, Int>> {
            val (row, column) = item
            return ((row - 1)..(row + 1)).flatMap { rowIndex ->
                ((column - 1)..(column + 1))
                    .map { rowIndex to it }
            }
                .filter { (rowIndex, columnIndex) -> !(row == rowIndex && column == columnIndex) }
                .filter { (rowIndex, columnIndex) -> !(row != rowIndex && column != columnIndex) }
                .filter { (rowIndex, columnIndex) -> maze.getOrNull(rowIndex)?.getOrNull(columnIndex) == 0 }
        }

        val nextQueue = ArrayDeque<Pair<Int, Int>>()
        nextQueue.add(startRowToColumn)
        distanceMap[startRowToColumn.first][startRowToColumn.second] = 0
        var current = startRowToColumn

        while (nextQueue.isNotEmpty()) {
            current = nextQueue.removeFirst()
            val nextDistance = distanceMap.get(current) + 1
            findNeighbors(current)
                .forEach { next ->
                    if (distanceMap[next] > nextDistance) {
                        distanceMap[next] = nextDistance
                        nextQueue.add(next)
                    }
                }
        }
        println("distance map")
        println(distanceMap.joinToString("\n") {
            it.joinToString(" ") {
                if (it == Int.MAX_VALUE) {
                    "o"
                } else if (it == 0) {
                    "s"
                } else {
                    it.toString()
                }
            }
        })

        targetRowToColumn?.let {
            println("target distance")
            println(distanceMap[targetRowToColumn])
            fun countPath(item: Pair<Int, Int>): Int {
                val distance = distanceMap[item]
                if (distance == 0) return 1
                val sortestNeighbors = findNeighbors(item)
                    .filter { distanceMap[it] == distance - 1 }
                if (sortestNeighbors.size > 1) {
                    println(sortestNeighbors)
                }
                return sortestNeighbors
                    .sumBy {
                        countPath(it)
                    }
            }

            println("path count")
            println(countPath(targetRowToColumn))
        }
    }
}

fun main() {
    LeeAlgorithm().main6411()
}