package com.example.sampleapp.extension

import com.example.sampleapp.extensions.*
import org.assertj.core.api.Assertions.*
import org.junit.Test

class Numbers {
    @Test
    fun testStandardDeviation() {
        val sd = FloatArray(10) { it + 1f }.standardDeviation
        assertThat(sd).isEqualTo(2.87, within(0.01))
    }

    private fun testTempConversion(c: Number, f: Number) =
        assertThat(((c).celsiusToFahrenheit())).isEqualTo(f.toFloat(), within(0.01f))

    @Test
    fun testCelsiusToFahrenheitConversion() {
        testTempConversion(-50, -58)
        testTempConversion(0, 32)
        testTempConversion(10, 50)
        testTempConversion(21.11f, 70)
        testTempConversion(37.78f, 100)
        testTempConversion(100, 212)
        testTempConversion(1000, 1832)
    }
}