class KosarajuAlgorithm {
    // https://hyperskill.org/learn/step/8038
    fun main8038() {
        val alice = "Alice"
        val bob = "Bob"
        val mike = "Mike"
        val sam = "Sam"
        val kate = "Kate"
        val john = "John"
        val andrew = "Andrew"
        val ann = "Ann"
        val tom = "Tom"

        val people = listOf(
            alice,
            bob,
            mike,
            sam,
            kate,
            john,
            andrew,
            ann,
            tom
        )
        val followEdges = listOf(
            alice to bob,
            sam to kate,
            mike to andrew,
            mike to sam,
            alice to andrew,
            alice to sam,
            ann to andrew,
            ann to sam,
            andrew to tom,
            tom to ann,
            tom to kate,
            bob to mike,
            kate to john,
            mike to alice,
            john to sam
        )
        val adjacencyList = followEdges.groupBy { it.first }
            .mapValues { it -> it.value.map { it.second } }
        val reversedAdjacencyList = followEdges.map { it.second to it.first }.groupBy { it.first }
            .mapValues { it -> it.value.map { it.second } }
        val foundOrderList = dfsFoundOrderList(adjacencyList, people)
        println(foundOrderList)

        val accessed = mutableListOf<String>()
        fun dfsGraph(node: String):List<String>{
            if(accessed.contains(node)) return emptyList()
            accessed.add(node)
            val list = reversedAdjacencyList[node]?.flatMap { childNode ->
//                println("$node to $childNode")
                dfsGraph(childNode)
            }.orEmpty()
            return list + node
        }

        println(foundOrderList.reversed().map {node ->
            dfsGraph(node)
        }.filter { it.isNotEmpty() }.joinToString("\n"){it.joinToString(" ")})


    }

    private fun dfsFoundOrderList(
        adjacencyList: Map<String, List<String>>,
        people: List<String>
    ): MutableList<String> {
        val accessed = mutableListOf<String>()
        val foundOrderList = mutableListOf<String>()
        fun dfs(node: String) {
            if (accessed.contains(node)) return
            accessed.add(node)
            adjacencyList[node]?.forEach { nextNode ->
                dfs(nextNode)
            }
            foundOrderList.add(node)
        }
        people.forEach { node ->
            dfs(node)
        }
        return foundOrderList
    }
}
