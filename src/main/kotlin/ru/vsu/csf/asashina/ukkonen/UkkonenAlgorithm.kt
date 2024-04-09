package ru.vsu.csf.asashina.ukkonen

class UkkonenAlgorithm(
    var root: Node? = null,
    var lastNewNode: Node? = null,
    var activeNode: Node? = null,
    var count: Int = 0,
    var activeEdge: Int = -1,
    var activeLength: Int = 0,
    var remainingSuffixCount: Int = 0,
    var leafEnd: Int = -1,
    var rootEnd: IntArray? = null,
    var splitEnd: IntArray? = null,
    var size: Int = -1,
) {

    fun buildSuffixTree(s: String): Node? {
        size = s.length
        rootEnd = IntArray(1)
        rootEnd!![0] = -1
        root = newNode(-1, rootEnd!!)
        activeNode = root
        for (i in 0 until size) extendSuffixTree(i, s)
        val labelHeight = 0
        setSuffixIndexByDFS(s, root, labelHeight)
        freeSuffixTreeByPostOrder(root)
        return root
    }

    private fun newNode(start: Int, end: IntArray): Node {
        count++
        val node = Node()
        for (i in 0..255) {
            node.children[i] = null
        }
        node.suffixLink = root
        node.start = start
        node.end = end
        node.suffixIndex = -1
        return node
    }

    private fun extendSuffixTree(pos: Int, s: String) {
        leafEnd = pos
        remainingSuffixCount++
        lastNewNode = null

        while (remainingSuffixCount > 0) {

            if (activeLength == 0) {
                activeEdge = s[pos] - ' '
            }

            if (activeNode!!.children[activeEdge] == null) {
                activeNode!!.children[activeEdge] = newNode(pos, IntArray(1){ leafEnd })

                if (lastNewNode != null) {
                    lastNewNode!!.suffixLink = activeNode
                    lastNewNode = null
                }
            } else {
                val next = activeNode!!.children[activeEdge]
                if (walkDown(s, next!!)) continue

                if (s[next.start + activeLength] == s[pos]) {
                    if (lastNewNode != null && activeNode != root) {
                        lastNewNode!!.suffixLink = activeNode
                        lastNewNode = null
                    }

                    activeLength++
                    break
                }

                splitEnd = IntArray(1) { next.start + activeLength - 1 };
                val split = newNode(next.start, splitEnd!!)
                activeNode!!.children[activeEdge] = split

                split.children[s[pos] - ' '] = newNode(pos, IntArray(1) { leafEnd })
                next.start += activeLength
                split.children[activeEdge] = next

                if (lastNewNode != null) lastNewNode!!.suffixLink = split

                lastNewNode = split
            }
            remainingSuffixCount--
            if (activeNode == root && activeLength > 0) {
                activeLength--
                activeEdge = s[pos - remainingSuffixCount + 1] - ' '
            }
            else if (activeNode != root) activeNode = activeNode!!.suffixLink
        }
    }

    private fun edgeLength(n: Node): Int {
        return n.end!![0] - n.start + 1
    }

    private fun walkDown(s: String, currNode: Node): Boolean {
        if (activeLength >= edgeLength(currNode)) {
            activeEdge = (s[size - remainingSuffixCount + 1] - ' ')
            activeLength -= edgeLength(currNode)
            activeNode = currNode
            return true
        }
        return false
    }

    private fun print(s:String, i: Int, j: Int) {
        for (k in i..j) println(s[k])
    }

    private fun setSuffixIndexByDFS(s: String, n: Node?, labelHeight: Int) {
        if (n == null) return
        if (n.start != -1) print(s, n.start, n.end!![0])

        var leaf = 1
        for (i in 0..255) {
            if (n.children[i] != null) {
                if (leaf == 1 && n.start != -1) {
                    println((" [" + n.suffixIndex) + "]")
                }
                leaf = 0
                setSuffixIndexByDFS(s, n.children[i], (labelHeight + edgeLength(n.children[i]!!)))
            }
        }
        if (leaf == 1) {
            n.suffixIndex = size - labelHeight
            println((" [" + n.suffixIndex) + "]")
        }
    }

    private fun freeSuffixTreeByPostOrder(n: Node?) {
        if (n == null) return
        for (i in 0..255) {
            if (n.children[i] != null) freeSuffixTreeByPostOrder(n.children[i])
        }
        if (n.suffixIndex == -1) n.end = null
    }

}

data class Node(
    var children: Array<Node?> = arrayOfNulls(256),
    var suffixLink: Node? = null,
    var start: Int = 0,
    var end: IntArray? = IntArray(1),
    var suffixIndex: Int = -1,
)
