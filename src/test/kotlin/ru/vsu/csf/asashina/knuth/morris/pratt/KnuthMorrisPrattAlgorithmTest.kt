package ru.vsu.csf.asashina.knuth.morris.pratt

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class KnuthMorrisPrattAlgorithmTest {

    private val kmpAlgorithm = KnuthMorrisPrattAlgorithm()

    @ParameterizedTest
    @CsvSource(
        "string, somestringsome, 4",
        "apple, somestring, -1"
    )
    fun kmpAlgorithmTest(text: String, sub: String, expectedResult: Int) =
        Assertions.assertEquals(expectedResult, kmpAlgorithm.kmpAlgorithm(text, sub))

}
