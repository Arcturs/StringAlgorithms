package ru.vsu.csf.asashina.shift.and

class ShiftAndAlgorithm {

    fun shiftAnd(p: String, t: String): Int {
        val startCharacter = '0'
        val endCharacter = 'z'
        val byteMapArray = IntArray(endCharacter - startCharacter + 1)
        for (j in p.indices)
            byteMapArray[p[j] - startCharacter] = byteMapArray[p[j] - startCharacter] or (1 shl (p.length - 1 - j))

        val uHigh = 1 shl (p.length - 1)
        var m = 0
        for (i in t.indices) {
            m = (m shr 1 or uHigh) and byteMapArray[t[i] - startCharacter]
            if (m and 1 > 0) return i - m - p.length + 1
        }
        return -1
    }

}
