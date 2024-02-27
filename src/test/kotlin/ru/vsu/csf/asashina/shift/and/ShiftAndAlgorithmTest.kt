package ru.vsu.csf.asashina.shift.and

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ShiftAndAlgorithmTest {

    private val shiftAndAlgorithm = ShiftAndAlgorithm()

    @ParameterizedTest
    @CsvSource(
        "string, somestringsome, 3",
        "apple, somestring, -1"
    )
    fun shiftAndAlgorithmTest(text: String, sub: String, expectedResult: Int) =
        assertEquals(expectedResult, shiftAndAlgorithm.shiftAnd(text, sub))

}
