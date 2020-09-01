import java.lang.IllegalArgumentException

class BinarySearchTree {
    class Tree(val root: Node) {
        data class Node(val value: Int, var right: Node? = null, var left: Node? = null, var parent: Node?) {
            override fun toString(): String {
                return "[node value:" + value + " parent: ${parent?.value} left:${left?.value} right:${right?.value}]"
            }

            fun hasChild(): Boolean {
                return right != null || left != null
            }

            fun hasOnlyOneChild(): Boolean {
                return (right != null && left == null) || (right == null && left != null)
            }

            fun replaceChild(targetNode: Node, replacement: Node?) {
                if (this.right == targetNode) {
                    replacement?.parent = this
                    this.right = replacement
                } else if (this.left == targetNode) {
                    replacement?.parent = this
                    this.left = replacement
                } else {
                    throw IllegalArgumentException()
                }
            }

            fun removeFromParent() {
                parent?.replaceChild(this, null)
            }

            // probably O(log N)
            fun find(i: Int): Node? {
                println("find:" + i + " current:" + value)
                if (value == i) return this
                if (i < value) {
                    val left = left ?: return null
                    return left.find(i)
                } else {
                    val right = right ?: return null
                    return right.find(i)
                }
            }

            // probably O(log N)
            fun insert(i: Int) {
                if (i <= value) {
                    val left = left
                    if (left == null) {
                        this.left = Node(i, null, null, this)
                        return
                    }
                    left.insert(i)
                } else {
                    val right = right
                    if (right == null) {
                        this.right = Node(i, null, null, this)
                        return
                    }
                    right.insert(i)
                }
            }

            // probably O(log N)
            fun findRightMostNode(): Node {
                val rightNode = right
                if (rightNode != null) return rightNode.findRightMostNode()
                return this
            }
        }

        fun find(i: Int): Node? {
            return root.find(i)
        }

        fun insert(i: Int) {
            root.insert(i)
        }

        fun remove(i: Int) {
            val targetNode = find(i) ?: throw IllegalArgumentException("not exits")

            // if the node has no children, we simply delete it;
            if (!targetNode.hasChild()) {
                targetNode.removeFromParent()
                return
            }

            // if the node has only one child (either right or left), we delete the node and put its child in its place;
            if (targetNode.hasOnlyOneChild()) {
                if (targetNode.right != null) {
                    targetNode.replaceChild(targetNode, targetNode.right)
                }
                if (targetNode.left != null) {
                    targetNode.replaceChild(targetNode, targetNode.left)
                }
                return
            }

            // if the node has both left and right children, keep calm and follow the steps:
            // first, identify the node that you want to delete,
            // then find the rightmost child in its left subtree.
            // Put its value into the initial node and remove the duplicate in the bottom by using the algorithm for either the first or the second case.
            // Note that you may use the leftmost child from the right subtree instead; whichever option you choose, moving those two nodes will not break tree structure.
            //        50
            //    25     100
            // 10   30
            //     27
            val rightMostNode = checkNotNull(targetNode.left).findRightMostNode()
            // if left not exists, insert null
            checkNotNull(rightMostNode.parent).replaceChild(rightMostNode, rightMostNode.left)
            rightMostNode.left = null

            rightMostNode.right = targetNode.right
            rightMostNode.left = targetNode.left

            val parent = targetNode.parent
            parent?.replaceChild(targetNode, rightMostNode)
        }

        companion object {
            fun of(edges: List<Pair<Int, Int>>): Tree {
                fun buildTree(start: Int, edges: List<Pair<Int, Int>>, parent: Node?): Node {
                    val children = edges.filter { it.first == start }
                    val leftEdge = children.firstOrNull { it.second <= start }
                    val rightEdge = children.firstOrNull { it.second > start }
                    val node = Node(
                        value = start,
                        parent = parent
                    )
                    node.right = rightEdge?.let { buildTree(rightEdge.second, edges, node) }
                    node.left = leftEdge?.let { buildTree(leftEdge.second, edges, node) }
                    return node
                }
                return Tree(buildTree(5, edges, null))
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
        tree.remove(30)
        tree.remove(10)
        println(tree.find(8))
        println(tree.find(10))
    }

}

fun main() {
    BinarySearchTree().sampleMain()
}