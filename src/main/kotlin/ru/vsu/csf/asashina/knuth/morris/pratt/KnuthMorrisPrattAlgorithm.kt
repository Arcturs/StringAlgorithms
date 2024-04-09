package ru.vsu.csf.asashina.knuth.morris.pratt

class KnuthMorrisPrattAlgorithm {

    fun kmpAlgorithm(p: String, t: String): Int {
        val bp = createPrefixBorderArray(p)
        val bpm = modifyPrefixBorderArray(bp)
        var k = 0
        for (i in t.indices) {
            while (k != 0 && p[k] != t[i]) k = bpm[k - 1]
            if (p[k] == t[i]) ++k
            if (k == p.length) {
                return i - k + 1
            }
        }
        return -1
    }

    private fun createPrefixBorderArray(s: String): IntArray {
        val prefixBorderArray = IntArray(s.length)
        for (i in 1 until s.length) {
            var bpRight = prefixBorderArray[i - 1]
            while (bpRight != 0 && s[i] != s[bpRight]) bpRight = prefixBorderArray[bpRight - 1]
            if (s[i] == s[bpRight]) prefixBorderArray[i] = bpRight + 1
            else prefixBorderArray[i] = 0
        }
        return prefixBorderArray
    }

    private fun modifyPrefixBorderArray(prefixBorderArray: IntArray): IntArray {
        val n = prefixBorderArray.size
        val modifiedPrefixBorderArray = IntArray(n)
        modifiedPrefixBorderArray[n - 1] = prefixBorderArray[n - 1]
        for (i in 1 until n - 1) {
            if (prefixBorderArray[i] != 0 && prefixBorderArray[i] + 1 == prefixBorderArray[i + 1])
                modifiedPrefixBorderArray[i] = modifiedPrefixBorderArray[prefixBorderArray[i] - 1]
            else modifiedPrefixBorderArray[i] = prefixBorderArray[i]
        }
        return modifiedPrefixBorderArray
    }

}
