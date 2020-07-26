class DepthFirstTopologicalSort {
    // https://hyperskill.org/learn/step/7558
    fun main7558() {
        val a = 0
        val b = 1
        val c = 2
        val d = 3
        val e = 4
        val f = 5
        val g = 6
        val h = 7
        val i = 8
        val nodes = listOf(a, b, c, d, e, f, g, h, i)
        val adjacencyList = listOf(
            a to d,
            b to d,
            c to e,
            d to c,
            d to f,
            d to g,
            e to g,
            e to i,
            g to f,
            g to h,
            h to i
        ).groupBy { it.first }
            .mapValues { it -> it.value.map { it.second } }
        val result = mutableListOf<Int>()
        fun Int.toAlphabet(): String {
            return when (this) {
                0 -> "A"
                1 -> "B"
                2 -> "C"
                3 -> "D"
                4 -> "E"
                5 -> "F"
                6 -> "G"
                7 -> "H"
                8 -> "I"
                else -> "unknown"
            }
        }

        fun dfs(node: Int) {
            println("visit: " + node.toAlphabet())
            if (result.contains(node)) {
                return
            }
            val list = adjacencyList[node]
            val listOrEmpty = list.orEmpty()
            listOrEmpty.forEach { nextNode ->
                dfs(nextNode)
            }
            result.add(0, node)
        }
        nodes.forEach { node ->
            dfs(node)
        }
        println(result.map {
            it.toAlphabet()
        }.joinToString(" "))
    }


    // https://hyperskill.org/learn/step/7559
    fun main7559() {
        val adjacencyList = """0: 6
1: 0
2: 4, 5
3: 5
4: 1, 6
5: 4, 7, 8
6: 7
7: 8
8:""".lines().map {
            val (key, values) = it.split(":")
            key.toInt() to values.trim().split(", ").filter { !it.isBlank() }.map { it.toInt() }
        }.toMap()
        println(adjacencyList)
        val result = mutableListOf<Int>()
        fun dfs(node: Int) {
            println("visit: $node")
            if (result.contains(node)) {
                return
            }
            val list = adjacencyList[node]
            val listOrEmpty = list.orEmpty()
            listOrEmpty.forEach { nextNode ->
                dfs(nextNode)
            }
            result.add(0, node)
        }
        (0 until adjacencyList.size).forEach { node ->
            dfs(node)
        }
        println(result.map {
            it
        }.joinToString(" "))
    }

    fun main7561(){
        val adjacencyList = """0: 1, 2, 3, 5
1: 2, 4, 5
2: 3, 6
3: 6
4:
5:
6:""".lines().map {
            val (key, values) = it.split(":")
            key.toInt() to values.trim().split(", ").filter { !it.isBlank() }.map { it.toInt() }
        }.toMap()
        println(adjacencyList)
        // show it https://dreampuf.github.io/GraphvizOnline
        adjacencyList.forEach { (key, values) ->
            values.forEach {
                println("node$key -> node$it")
            }
        }
    }
}
