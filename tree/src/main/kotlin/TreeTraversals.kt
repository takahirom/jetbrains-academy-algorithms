class TreeTraversals {
    data class Tree(val root: Node) {
        data class Node(val value: Int, var children: List<Node> = emptyList(), var parent: Node?) {
            override fun toString(): String {
                return "{node value:" + value + " parent: ${parent?.value} children: ${children.joinToString(",") { "" + it.value }}}"
            }
        }

        fun preOrderTraversal(): List<Node> {
            fun visit(node: Node): List<Node> {
                val nodes = mutableListOf<Node>()
                nodes.add(node)
                node.children.forEach{
                    nodes += visit(it)
                }
                return nodes
            }
            return visit(root)
        }


        companion object {
            fun of(start: Int, edges: List<Pair<Int, Int>>): Tree {
                fun buildTree(start: Int, edges: List<Pair<Int, Int>>, parent: Node?): Node {
                    val childrenEdge = edges.filter { it.first == start }
                    val node = Node(
                        value = start,
                        parent = parent
                    )
                    node.children = childrenEdge.map {
                        buildTree(it.second, edges, node)
                    }
                    return node
                }
                return Tree(buildTree(start, edges, null))
            }
        }
    }

    fun sampleMain() {
        val tree = Tree.of(
            1, listOf(
                1 to 2,
                2 to 4,
                2 to 5,
                1 to 3
            )
        )
        println(tree.preOrderTraversal())
    }

}

fun main() {
    TreeTraversals().sampleMain()
}