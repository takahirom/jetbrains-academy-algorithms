class BinarySearchTree {
    class Tree(val root: Node) {
        data class Node(val value: Int, var right: Node?, var left: Node?)

        fun find(i: Int): Boolean {
            fun find(i: Int, currentNode: Node): Boolean {
                println("find:" + i + " current:" + currentNode.value)
                if (currentNode.value == i) return true
                if (i < currentNode.value) {
                    val left = currentNode.left ?: return false
                    return find(i, left)
                } else {
                    val right = currentNode.right ?: return false
                    return find(i, right)
                }
            }
            return find(i, root)
        }

        fun insert(i: Int) {
            fun insert(i: Int, currentNode: Node) {
                if (i <= currentNode.value) {
                    val left = currentNode.left
                    if (left == null) {
                        currentNode.left = Node(i, null, null)
                        return
                    }
                    insert(i, left)
                } else {
                    val right = currentNode.right
                    if (right == null) {
                        currentNode.right = Node(i, null, null)
                        return
                    }
                    insert(i, right)
                }
            }
            insert(i, root)
        }

        companion object {
            fun of(edges: List<Pair<Int, Int>>): Tree {
                fun buildTree(start: Int, edges: List<Pair<Int, Int>>): Node {
                    val children = edges.filter { it.first == start }
                    val leftEdge = children.firstOrNull { it.second <= start }
                    val rightEdge = children.firstOrNull { it.second > start }
                    return Node(
                        value = start,
                        right = rightEdge?.let { buildTree(rightEdge.second, edges) },
                        left = leftEdge?.let { buildTree(leftEdge.second, edges) }
                    )
                }
                return Tree(buildTree(5, edges))
            }
        }
    }

    fun sampleMain() {
        val edges = listOf(
            5 to 3,
            3 to 1,
            3 to 4,
            5 to 10,
            10 to 7,
            7 to 8,
            10 to 12
        )
        val tree = Tree.of(edges)
        println(tree.find(8))
        println(tree.find(30))
        tree.insert(30)
        println(tree.find(30))
    }

}

fun main() {
    BinarySearchTree().sampleMain()
}