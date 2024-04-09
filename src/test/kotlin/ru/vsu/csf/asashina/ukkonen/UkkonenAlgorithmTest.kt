package ru.vsu.csf.asashina.ukkonen

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class UkkonenAlgorithmTest {

    private val ukkonenAlgorithm = UkkonenAlgorithm()

    @Test
    fun buildSuffixTreeTest() {
        assertDoesNotThrow {
            val tree = ukkonenAlgorithm.buildSuffixTree("abac")
            println("Done")
        }
    }

}