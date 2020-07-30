import java.util.*

class BreadthFirstSearch {

    fun main5282() {
        val node1 = "1"
        val node2 = "2"
        val node3 = "3"
        val node4 = "4"
        val nodes = setOf(node1, node2, node3, node4)

        val adjacencyList = listOf(
            node1 to node2,
            node1 to node3,
            node3 to node1,
            node3 to node4,
            node2 to node3,
            node2 to node4,
            node4 to node2
        ).groupBy { it.first }
            .mapValues { it.value.map { it.second } }
        val firstNode = node3

        showNodeDistanceByBreadthFirstSearch(nodes, firstNode, adjacencyList)
    }

    fun main7073() {
        val adjacencyList = mapOf(
            "A" to listOf("B", "C"),
            "B" to listOf("A", "C", "E"),
            "C" to listOf("A", "B", "D", "E"),
            "D" to listOf("C", "F", "G"),
            "E" to listOf("B", "C", "F"),
            "F" to listOf("D", "G"),
            "G" to listOf("D", "F")
        )
        showNodeDistanceByBreadthFirstSearch(adjacencyList.keys, "A", adjacencyList)
    }

    // WIP
    // https://hyperskill.org/learn/step/7072
//    fun main7072() {
//        val a = "A"
//        val b = "B"
//        val c = "C"
//        val d = "D"
//        val nodes = setOf(a,)
//
//        val adjacencyList = listOf(
//            a to b
//        ).groupBy { it.first }
//            .mapValues { it.value.map { it.second } }
//        showNodeDistanceByBreadthFirstSearch(adjacencyList.keys, "A", adjacencyList)
//    }

    private fun showNodeDistanceByBreadthFirstSearch(
        nodes: Set<String>,
        firstNode: String,
        adjacencyList: Map<String, List<String>>
    ) {
        val nodeDistance = Array<Int>(nodes.size) { Int.MAX_VALUE }
        val queue = ArrayDeque<String>()
        queue.add(firstNode)
        nodeDistance[nodes.indexOf(firstNode)] = 0
        while (queue.isNotEmpty()) {
            val previousNode = queue.pop()
            val visitedNodes = nodeDistance
                .withIndex()
                .filter { (_, distance) ->
                    distance != Int.MAX_VALUE
                }
                .map { nodes.elementAt(it.index) }
            val notVisitedAdjacencyNodes = adjacencyList[previousNode].orEmpty() - visitedNodes
            val previousNodeDistance = nodeDistance[nodes.indexOf(previousNode)]
            notVisitedAdjacencyNodes.forEach { node ->
                nodeDistance[nodes.indexOf(node)] = previousNodeDistance + 1
            }
            queue.addAll(notVisitedAdjacencyNodes)
        }
        println(nodeDistance.joinToString( " "))
    }

}

//fun main() {
//    BreadthFirstSearch().main7072()
//}