@file:Suppress("UnstableApiUsage")

import com.google.common.graph.EndpointPair
import com.google.common.graph.ValueGraph
import com.google.common.graph.ValueGraphBuilder
import kotlin.collections.ArrayDeque

class DijkstraAlgorithm {
    // https://hyperskill.org/learn/step/5557
    fun main5557() {
        val a = "A"
        val b = "B"
        val c = "C"
        val d = "D"
        val e = "E"
        val f = "F"

        val graph = ValueGraphBuilder
            .undirected()
            .allowsSelfLoops(false)
            .immutable<String, Int>()
            .putEdgeValue(a, b, 3)
            .putEdgeValue(a, c, 2)
            .putEdgeValue(b, c, 9)
            .putEdgeValue(b, d, 10)
            .putEdgeValue(c, d, 8)
            .putEdgeValue(c, e, 5)
            .putEdgeValue(d, e, 3)
            .putEdgeValue(d, f, 1)
            .putEdgeValue(e, f, 3)
            .build()
        pathByDijkstra(graph, a, f)
    }

    // https://hyperskill.org/learn/step/5777
    fun main5777() {
        val a = "A"
        val b = "B"
        val c = "C"
        val d = "D"
        val e = "E"
        val f = "F"
        val g = "G"
        val h = "H"
        val i = "I"

        val graph = ValueGraphBuilder
            .directed()
            .allowsSelfLoops(false)
            .immutable<String, Int>()
            .addNode(a)
            .addNode(b)
            .addNode(c)
            .addNode(d)
            .addNode(e)
            .addNode(f)
            .addNode(g)
            .addNode(h)
            .addNode(i)
            .putEdgeValue(a, b, 16)
            .putEdgeValue(a, c, 8)
            .putEdgeValue(b, e, 7)
            .putEdgeValue(c, d, 4)
            .putEdgeValue(d, a, 2)
            .putEdgeValue(d, b, 2)
            .putEdgeValue(d, f, 25)
            .putEdgeValue(d, g, 17)
            .putEdgeValue(e, g, 5)
            .putEdgeValue(f, h, 3)
            .putEdgeValue(f, i, 10)
            .putEdgeValue(g, i, 15)
            .putEdgeValue(g, f, 6)
            .putEdgeValue(h, i, 1)
            .build()
        pathByDijkstra(graph, a)
    }

    @OptIn(ExperimentalStdlibApi::class)
    inline fun <reified N : Any> pathByDijkstra(graph: ValueGraph<N, Int>, nodeU: N, nodeV: N? = null) {
        val distances = Array(graph.nodes().size) { Int.MAX_VALUE }
        distances[graph.nodes().indexOf(nodeU)] = 0

        val parentNodes = Array<N?>(graph.nodes().size) { null }


        val queue = ArrayDeque<N>()
        queue.add(nodeU)

        while (queue.isNotEmpty()) {
            val currentNode = queue.removeFirst()
            val currentNodeWeight = distances[graph.nodes().indexOf(currentNode)]
            val incidentEdges: Set<EndpointPair<N>> = graph.incidentEdges(currentNode)
                .filter {
                    if (it.isOrdered) {
                        it.nodeU() == currentNode
                    } else {
                        true
                    }
                }
                .toSet()
            println("$currentNode : $incidentEdges")
            incidentEdges.forEach { edge ->
                val adjacentNode = edge.adjacentNode(currentNode)
                val nextNodeIndex = graph.nodes().indexOf(adjacentNode)
                val currentDistance = distances[nextNodeIndex]
                val calculatedDistance = currentNodeWeight + graph.edgeValue(edge).orElse(Int.MAX_VALUE)
                if (currentDistance > calculatedDistance) {
                    distances[nextNodeIndex] = calculatedDistance
                    queue.add(adjacentNode)
                    parentNodes[nextNodeIndex] = currentNode
                }
            }
        }
        println("distancels:" + distances.joinToString(","))
        println("parentNodes:" + parentNodes.joinToString(","))


        println("shortest path tree")
        val nodeList = graph.nodes().toList()
        val shortestPathTree = parentNodes.mapIndexed { index, parentNode ->
            parentNode to nodeList[index]
        }
            .filter { (parent, _) -> parent != null }
            .sortedBy { (parent, child) -> "" + parent + child }
        println(shortestPathTree
            .joinToString("\n") { (parent, child) ->
                "$parent $child"
            })
        println("distances: " + distances.joinToString(" "))

        nodeV?.let {
            val path = mutableListOf<N?>()
            var currentNode: N? = nodeV
            path += nodeV
            while (currentNode != null) {
                currentNode = parentNodes[graph.nodes().indexOf(currentNode)]
                path += currentNode
            }
            println("path: " + path.reversed().filterNotNull().joinToString(" -> "))
        }
    }

}

fun main() {
    DijkstraAlgorithm().main5777()
}