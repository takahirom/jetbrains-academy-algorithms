class BinaryHeap {

    class Tree(
        private val nodes: MutableList<Int> = mutableListOf<Int>()
    ) {
        // log(n)
        fun add(newNode: Int) {
            println("startInsert!$newNode")
            nodes.add(newNode)
            var checkedNodeIndex = nodes.size - 1
            while (true) {
                val checkedNode = nodes[checkedNodeIndex]
                /*
                 *       0
                 *   1      2
                 * 3   4  5   6
                 * access from child to parent = nodeIndex / 2 - 1 + nodeIndex % 2
                 */
                val parentIndex = checkedNodeIndex / 2 - 1 + checkedNodeIndex % 2
                val parentNode = nodes[parentIndex]
                if (checkedNode < parentNode) {
                    println("rotate!")
                    nodes[parentIndex] = checkedNode
                    nodes[checkedNodeIndex] = parentNode
                } else {
                    break
                }
                checkedNodeIndex = parentIndex
            }
        }

        // log(n)
        @OptIn(ExperimentalStdlibApi::class)
        fun extractMin(): Int {
            val min = nodes[0]
            nodes[0] = nodes[nodes.size - 1]
            nodes.removeLast()
            if (nodes.isEmpty()) return min
            var currentNodeIndex = 0
            while (true) {
                val currentNode = nodes[currentNodeIndex]
                /*
                 *       0
                 *   1      2
                 * 3   4  5   6
                 * access from parent to child = nodeIndex * 2 + 1 [+ 1]
                 */
                val childNodeIndex1 = currentNodeIndex * 2 + 1
                val childNode1 = nodes.getOrElse(childNodeIndex1) { Int.MAX_VALUE }
                val childNodeIndex2 = currentNodeIndex * 2 + 2
                val childNode2 = nodes.getOrElse(childNodeIndex2) { Int.MAX_VALUE }
                if (minOf(childNode1, childNode2) < currentNode) {
                    if (childNode1 < childNode2) {
                        nodes[childNodeIndex1] = currentNode
                        nodes[currentNodeIndex] = childNode1
                        currentNodeIndex = childNodeIndex1
                    } else {
                        nodes[childNodeIndex2] = currentNode
                        nodes[currentNodeIndex] = childNode2
                        currentNodeIndex = childNodeIndex2
                    }
                } else {
                    break
                }
            }
            return min
        }

        fun printTree() {
            var currentIndex = 0
            var currentNodesOfDepth = 1
            while (true) {
                (0 until currentNodesOfDepth).forEach { _ ->
                    if (nodes.getOrNull(currentIndex) == null) return println()
                    print(" ".repeat(4 * nodes.size / currentNodesOfDepth) + nodes[currentIndex] + " ")
                    currentIndex++
                }
                println()
                currentNodesOfDepth *= 2
            }
        }

        fun heapSort(): MutableList<Int> {
            val list = mutableListOf<Int>()
            while (nodes.isNotEmpty()) {
                list.add(extractMin())
            }
            return list
        }

        companion object {
            fun of(vararg nodes: Int): Tree {
                return Tree(nodes.toMutableList())
            }
        }
    }

    fun sample() {
        val tree = Tree.of(1, 5, 8, 18, 14, 12, 17, 19, 22, 28)
        tree.printTree()
        tree.add(3)
        tree.printTree()
        tree.extractMin()
        tree.printTree()
        println(tree.heapSort())
    }

    // https://hyperskill.org/learn/step/5182
    fun main5182() {
        val tree = Tree.of(6, 10, 15, 14, 16, 22, 21, 34, 40, 27, 31, 36, 28, 33, 35)
        tree.add(13)
        tree.printTree()
        tree.add(12)
        tree.printTree()
        tree.add(11)
        tree.printTree()
    }
}

fun main() {
    BinaryHeap().main5182()
}