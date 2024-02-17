package ru.vsu.csf.asashina.z.blocks

class ZFunction {

    fun getZFunction(s: String): IntArray {
        val zValues = IntArray(s.length)
        var left = 0
        var right = 0

        for (i in 1 until s.length) {
            if (i >= right) {
                zValues[i] = getEqSubstringMaxLength(s, 0, i)
                left = i
                right = left + zValues[i]
            } else {
                val j = i - left
                if (zValues[j] < right - i) zValues[i] = zValues[j]
                else {
                    zValues[i] = right - i + getEqSubstringMaxLength(s, right - i, right)
                    left = i
                    right = left + zValues[i]
                }
            }
        }
        return zValues
    }

    private fun getEqSubstringMaxLength(s: String, index1: Int, index2: Int): Int {
        var eqLength = 0
        var i1 = index1
        var i2 = index2
        while (i1 < s.length && i2 < s.length && s[i1++] == s[i2++]) eqLength++
        return eqLength
    }

}

fun isTextContainsSubstring(text: String, sub: String): Boolean {
    val modifiedText = "$sub#$text"
    val zValues = ZFunction().getZFunction(modifiedText)
    for (i in text.indices) {
        if (zValues[i + sub.length + 1] == sub.length) {
            return true
        }
    }
    return false
}
