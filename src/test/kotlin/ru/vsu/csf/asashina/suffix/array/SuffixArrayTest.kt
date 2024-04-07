package ru.vsu.csf.asashina.suffix.array

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SuffixArrayTest {

    private val suffixArray: SuffixArray = SuffixArray()

    @Test
    fun createSuffixArrayTest() {
        val suffixArray = suffixArray.createSuffixArray("abaab")
        println("done")
    }

}
