class KruskalAlgorithm {
    data class Edge(
        val node1: String,
        val node2: String,
        val weight: Int
    )

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
}

fun main() {
    KruskalAlgorithm().main5571()
}