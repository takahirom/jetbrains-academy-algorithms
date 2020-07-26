class PrimAlgorithm {
    // https://hyperskill.org/learn/step/8038
    data class Edge(
        val node1: String,
        val node2: String,
        val weight: Int
    )

    fun main5578() {
        val a = "a"
        val b = "b"
        val c = "c"
        val d = "d"
        val e = "e"
        val f = "f"
        val g = "g"
        val h = "h"

        val nodes = listOf(
            a, b, c, d, e, f, g, h
        )


        val edges = listOf(
            Edge(a, b, 25),
            Edge(a, d, 6),
            Edge(a, e, 19),
            Edge(b, a, 25),
            Edge(b, d, 11),
            Edge(b, c, 8),
            Edge(b, g, 2),
            Edge(c, b, 8),
            Edge(c, d, 17),
            Edge(d, a, 6),
            Edge(d, b, 11),
            Edge(d, c, 17),
            Edge(e, a, 19),
            Edge(e, f, 9),
            Edge(f, e, 9),
            Edge(f, g, 14),
            Edge(g, b, 2),
            Edge(g, f, 14),
            Edge(g, h, 21),
            Edge(h, g, 21)
        )
        val adjacencyMatrix = makeAdjacencyMatrix(nodes, edges)
        println(adjacencyMatrix.joinToString("\n"))
        `simply O(n^3)`(nodes, adjacencyMatrix)
    }

    fun main5579() {
        val a = "a"
        val b = "b"
        val c = "c"
        val d = "d"
        val e = "e"
        val f = "f"
        val g = "g"
        val h = "h"
        val i = "i"

        val nodes = listOf(
            a, b, c, d, e, f, g, h, i
        )


        val edges = listOf(
            Edge(a, b, 4),
            Edge(a, h, 8),
            Edge(b, c, 8),
            Edge(b, h, 11),
            Edge(c, b, 8),
            Edge(c, d, 7),
            Edge(c, f, 4),
            Edge(c, i, 2),
            Edge(d, c, 7),
            Edge(d, e, 9),
            Edge(d, f, 14),
            Edge(e, d, 9),
            Edge(e, f, 10),
            Edge(f, c, 4),
            Edge(f, d, 14),
            Edge(f, e, 10),
            Edge(f, g, 2),
            Edge(g, f, 2),
            Edge(g, h, 1),
            Edge(g, i, 6),
            Edge(h, a, 8),
            Edge(h, b, 11),
            Edge(h, g, 1),
            Edge(h, i, 7),
            Edge(i, c, 2),
            Edge(i, g, 6),
            Edge(i, h, 7)
        )
        val adjacencyMatrix = makeAdjacencyMatrix(nodes, edges)
        println(adjacencyMatrix.joinToString("\n"))
        `simply O(n^3)`(nodes, adjacencyMatrix)
    }

    fun main5783() {
        val node0 = "0"
        val node1 = "1"
        val node2 = "2"
        val node3 = "3"
        val node4 = "4"
        val node5 = "5"
        val node6 = "6"
        val node7 = "7"

        val nodes = listOf(
            node0, node1, node2, node3, node4, node5, node6, node7
        )


        val edges = listOf(
            Edge(node0, node1, 2),
            Edge(node0, node2, 3),
            Edge(node0, node3, 1),
            Edge(node1, node0, 2),
            Edge(node1, node3, 2),
            Edge(node2, node0, 3),
            Edge(node2, node3, 2),
            Edge(node2, node4, 4),
            Edge(node3, node0, 1),
            Edge(node3, node1, 2),
            Edge(node3, node2, 2),
            Edge(node3, node4, 1),
            Edge(node3, node5, 2),
            Edge(node4, node2, 4),
            Edge(node4, node3, 1),
            Edge(node4, node5, 2),
            Edge(node4, node7, 1),
            Edge(node4, node6, 5),
            Edge(node5, node3, 2),
            Edge(node5, node4, 2),
            Edge(node5, node7, 3),
            Edge(node6, node4, 5),
            Edge(node6, node7, 2),
            Edge(node7, node4, 1),
            Edge(node7, node6, 2),
            Edge(node7, node5, 3)
        )
        val adjacencyMatrix = makeAdjacencyMatrix(nodes, edges)
        println(adjacencyMatrix.joinToString("\n"))
        `simply O(n^3)`(nodes, adjacencyMatrix)
    }

    private fun makeAdjacencyMatrix(
        nodes: List<String>,
        edges: List<Edge>
    ): List<List<Int>> {
        val adjacencyMatrix = nodes.map { fromNode ->
            nodes.map { node2 ->
                edges.find { it.node1 == fromNode && it.node2 == node2 }?.weight ?: Int.MAX_VALUE
            }
        }
        return adjacencyMatrix
    }

    fun `simply O(n^3)`(
        nodes: List<String>,
        adjacencyMatrix: List<List<Int>>
    ) {
        val currentGraph: MutableList<Edge> = mutableListOf()
        fun greedy(lastNode: String?, node: String) {
            currentGraph.forEach {
                if (it.node1 == node || it.node2 == node) return
            }
            if (lastNode != null) {
                currentGraph.add(Edge(lastNode, node, adjacencyMatrix[nodes.indexOf(lastNode)][nodes.indexOf(node)]))
            }
            val candidateNodes = (currentGraph.flatMap { listOf(it.node1, it.node2) } + node)
                .flatMap { fromNode ->
                    adjacencyMatrix[nodes.indexOf(fromNode)].mapIndexed { distIndex, weight ->
                        Edge(fromNode, nodes[distIndex], weight)
                    }.filter { it.weight != Int.MAX_VALUE }
                }
                .sortedBy { it.weight }


            println("---candidateNodes---")
            println(
                candidateNodes
                    .distinct()
                    .filter { candiateNode -> currentGraph.none { it.node1 == candiateNode.node2 || it.node2 == candiateNode.node2 } }
//                    .flatMap { listOf(it, Edge(it.node2, it.node1, it.weight)) }
                    .joinToString("\n") { "${it.node1} ${it.node2}" }
            )

            candidateNodes
                .forEach {
                    greedy(it.node1, it.node2)
                }
            return
        }
        nodes.forEach { node ->
            greedy(null, node)
        }
        println(currentGraph)
        println(currentGraph.sumBy { it.weight })
    }
}

//fun main() {
//    PrimAlgorithm().main5783()
//}
