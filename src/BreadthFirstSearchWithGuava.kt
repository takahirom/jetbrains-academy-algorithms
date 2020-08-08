import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableSet
import com.google.common.graph.GraphBuilder
import com.google.common.graph.NetworkBuilder
import com.google.common.graph.SuccessorsFunction
import java.util.*


@Suppress("UnstableApiUsage")
class BreadthFirstSearchWithGuava {
    // https://hyperskill.org/learn/step/7072
    fun main7072() {
        val a = "A"
        val b = "B"
        val c = "C"
        val d = "D"
        val e = "E"
        val f = "F"
        val g = "G"
        val h = "H"
        val i = "I"
        val j = "J"
        val k = "K"
        val l = "L"
        val m = "M"
//        val nodes = setOf(a,)

        val graph = GraphBuilder
            .undirected()
            .allowsSelfLoops(false)
            .immutable<String>()
            .putEdge(a, b)
            .putEdge(a, d)
            .putEdge(a, e)
            .putEdge(c, d)
            .putEdge(d, g)
            .putEdge(e, f)
            .putEdge(e, f)
            .putEdge(e, h)
            .putEdge(e, i)
            .putEdge(i, g)
            .putEdge(g, j)
            .putEdge(j, l)
            .putEdge(l, m)
            .putEdge(l, k)
            .build()
        showNodeDistanceByBreadthFirstSearch(graph, i)
    }

    // FYR
    // https://raw.githubusercontent.com/google/dagger/beed87cc75439d873a2f53dbd70306266023d766/java/dagger/internal/codegen/extension/DaggerGraphs.java
    fun <N : Any> showNodeDistanceByBreadthFirstSearch(graph: SuccessorsFunction<N>, nodeU: N): ImmutableList<N>? {
        val successors: Set<N> = ImmutableSet.copyOf(graph.successors(nodeU))

        val visitedNodeToPathPredecessor: MutableMap<N, N?> =
            HashMap() // encodes shortest path tree

        val visitedNodeToDistance: MutableMap<N, Int?> =
            HashMap()

        visitedNodeToPathPredecessor[nodeU] = null
        visitedNodeToDistance[nodeU] = 0
        for (node in successors) {
            visitedNodeToPathPredecessor[node] = nodeU
            visitedNodeToDistance[node] = 1
        }
        var currentNodes: Queue<N> = ArrayDeque(successors)
        var nextNodes: Queue<N> = ArrayDeque()

        // Perform a breadth-first traversal starting with the successors of nodeU.
        while (!currentNodes.isEmpty()) {
            while (!currentNodes.isEmpty()) {
                val currentNode = currentNodes.remove()
                for (nextNode in graph.successors(currentNode)) {
                    if (visitedNodeToPathPredecessor.containsKey(nextNode)) {
                        continue  // we already have a shortest path to nextNode
                    }
                    visitedNodeToPathPredecessor[nextNode] = currentNode
                    visitedNodeToDistance[nextNode] = (visitedNodeToDistance[currentNode] ?: 0) + 1
                    nextNodes.add(nextNode)
                }
            }
            val emptyQueue = currentNodes
            currentNodes = nextNodes
            nextNodes = emptyQueue // reusing empty queue faster than allocating new one
        }
        println(visitedNodeToPathPredecessor.map { "" + it.value + " " + it.key }.joinToString("\n"))
        println(visitedNodeToDistance.map { it.value }.joinToString(" "))

        return ImmutableList.of<N>()

    }

}

//fun main() {
//    BreadthFirstSearchWithGuava().main7072()
//}