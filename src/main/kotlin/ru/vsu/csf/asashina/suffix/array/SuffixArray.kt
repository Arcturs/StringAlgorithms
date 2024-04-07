package ru.vsu.csf.asashina.suffix.array

class SuffixArray {

    fun createSuffixArray(s: String): IntArray {
        val p = IntArray(MAX_LENGTH)
        var cnt = IntArray(MAX_LENGTH)
        val c = IntArray(MAX_LENGTH)

        for (i in s.indices) {
            ++cnt[s[i].code]
        }
        for (i in 1 until ALPHABET_SIZE) {
            cnt[i] += cnt[i - 1]
        }
        for (i in s.indices) {
            p[--cnt[s[i].code]] = i
        }

        var classes = 1
        for (i in 1 until s.length) {
            if (s[p[i]] != s[p[i - 1]]) ++classes
            c[p[i]] = classes - 1
        }

        val pn = IntArray(MAX_LENGTH)
        val cn = IntArray(MAX_LENGTH)
        var h = 0
        while (1 shl h < s.length) {
            for (i in s.indices) {
                pn[i] = p[i] - (1 shl h)
                if (pn[i] < 0) pn[i] += s.length
            }
            cnt = IntArray(MAX_LENGTH)
            h++

            for (i in s.indices) {
                ++cnt[c[pn[i]]]
            }
            for (i in 1 until classes) {
                cnt[i] += cnt[i - 1]
            }
            for (i in s.indices) {
                p[--cnt[c[pn[i]]]] = pn[i]
            }

            classes = 1
            for (i in 1 until s.length) {
                val mid1 = (p[i] + (1 shl h)) % s.length
                val mid2 = (p[i - 1] + (1 shl h))
                if (c[p[i]] != c[p[i - 1]] || c[mid1] != c[mid2]) ++classes
                cn[p[i]] = classes - 1
            }
            for (i in s.indices) {
                c[i] = cn[i]
            }
        }
        return c
    }

    private companion object {
        const val ALPHABET_SIZE = 256
        const val MAX_LENGTH = ALPHABET_SIZE * 5
    }

}
