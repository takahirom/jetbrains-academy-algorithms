class KruskalAlgorithm {
    data class Edge(
        val node1: String,
        val node2: String,
        val weight: Int
    )

    // https://hyperskill.org/learn/step/5571
    fun main5571() {
        val a = "a"
        val b = "b"
        val c = "c"
        val d = "d"
        val e = "e"

        val nodes = listOf(
            a, b, c, d, e
        )


        val edges = listOf(
            Edge(a, b, 2),
            Edge(a, c, 4),
            Edge(a, e, 2),
            Edge(b, a, 2),
            Edge(b, d, 4),
            Edge(b, e, 7),
            Edge(c, a, 4),
            Edge(c, d, 4),
            Edge(c, e, 3),
            Edge(d, c, 4),
            Edge(d, b, 4),
            Edge(d, e, 5),
            Edge(e, a, 2),
            Edge(e, b, 7),
            Edge(e, c, 3),
            Edge(e, d, 5)
        )
        val sortedEdges = edges.sortedBy { it.weight }
        val components = nodes.map { mutableListOf(it) }.toMutableList()
        val minimumSpanningTree = mutableListOf<Edge>()
        for (edge in sortedEdges) {
            val node1Component = components.first { it.contains(edge.node1) }
            val node2Component = components.first { it.contains(edge.node2) }
            if (node1Component == node2Component) {
                continue
            }
            minimumSpanningTree.add(edge)
            components.remove(node1Component)
            components.remove(node2Component)
            components.add(node1Component.union(node2Component).toMutableList())
        }
        println(minimumSpanningTree.joinToString("\n"))
    }

    // https://hyperskill.org/learn/step/5669
    fun main5668() {
        val node0 = "0"
        val node1 = "1"
        val node2 = "2"
        val node3 = "3"
        val node4 = "4"
        val node5 = "5"
        val node6 = "6"
        val node7 = "7"
        val node8 = "8"
        val node9 = "9"
        val node10 = "10"
        val node11 = "11"
        val node12 = "12"
        val node13 = "13"

        val nodes = listOf(
            node0,
            node1,
            node2,
            node3,
            node4,
            node5,
            node6,
            node7,
            node8,
            node9,
            node10,
            node11,
            node12,
            node13
        )


        val edges = listOf(
            Edge(node0, node1, 1),
            Edge(node0, node2, 1),
            Edge(node1, node3, 1),
            Edge(node2, node3, 1),
            Edge(node2, node5, 3),
            Edge(node3, node4, 2),
            Edge(node3, node6, 3),
            Edge(node4, node7, 3),
            Edge(node5, node6, 4),
            Edge(node5, node9, 5),
            Edge(node6, node7, 2),
            Edge(node6, node10, 1),
            Edge(node8, node9, 4),
            Edge(node8, node11, 5),
            Edge(node9, node10, 4),
            Edge(node9, node12, 5),
            Edge(node10, node13, 6),
            Edge(node11, node12, 4),
            Edge(node12, node13, 6)
        )
            .flatMap { listOf(it, it.copy(it.node2, it.node1)) }
        val sortedEdges = edges.sortedBy { it.weight }
        val components = nodes.map { mutableListOf(it) }.toMutableList()
        val minimumSpanningTree = mutableListOf<Edge>()
        for (edge in sortedEdges) {
            val node1Component = components.first { it.contains(edge.node1) }
            val node2Component = components.first { it.contains(edge.node2) }
            if (node1Component == node2Component) {
                continue
            }
            minimumSpanningTree.add(edge)
            components.remove(node1Component)
            components.remove(node2Component)
            components.add(node1Component.union(node2Component).toMutableList())
        }
        println(minimumSpanningTree.sumBy { it.weight })
        println(minimumSpanningTree
            .joinToString("\n") {
                it.node1 + " " + it.node2
            })
    }
}

//fun main() {
//    KruskalAlgorithm().main5668()
//}