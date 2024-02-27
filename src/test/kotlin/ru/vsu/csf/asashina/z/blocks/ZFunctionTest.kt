package ru.vsu.csf.asashina.z.blocks

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ZFunctionTest {

    @ParameterizedTest
    @CsvSource(
        "somestring, string, true",
        "somestring, apple, false"
    )
    fun isTextContainsSubstringTest(text: String, sub: String, expectedResult: Boolean) =
        assertEquals(expectedResult, isTextContainsSubstring(text, sub))

}
